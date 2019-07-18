package tools.redfox.bamboo.serverless.type;

import com.atlassian.bamboo.build.logger.BuildLogger;
import com.atlassian.bamboo.configuration.ConfigurationMap;
import com.atlassian.bamboo.process.CommandlineStringUtils;
import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ExternalProcessBuilder;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.*;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.google.common.collect.ImmutableList;
import com.opensymphony.xwork2.TextProvider;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

abstract class ServerlessTaskType implements CommonTaskType {
    private final ProcessService processService;
    private final EnvironmentVariableAccessor environmentVariableAccessor;
    private final CapabilityContext capabilityContext;
    private TextProvider textProvider;

    public ServerlessTaskType(
            ProcessService processService,
            EnvironmentVariableAccessor environmentVariableAccessor,
            CapabilityContext capabilityContext,
            TextProvider textProvider) {
        this.processService = processService;
        this.environmentVariableAccessor = environmentVariableAccessor;
        this.capabilityContext = capabilityContext;
        this.textProvider = textProvider;
    }

    public TaskResult execute(@NotNull CommonTaskContext taskContext, String command) throws TaskException {
        try {
            BuildLogger buildLogger = taskContext.getBuildLogger();
            ConfigurationMap configurationMap = taskContext.getConfigurationMap();
            String serverlessRuntime = configurationMap.get("runtime");
            String serverlessPath = this.capabilityContext.getCapabilityValue("system.builder.serverless." + serverlessRuntime);
            if (StringUtils.isBlank(serverlessPath)) {
                serverlessPath = FilenameUtils.concat(
                        taskContext.getWorkingDirectory().toString(),
                        "node_modules/.bin/"
                );
            }
            String cmd = command + " " + configurationMap.get("options");

            Map<String, String> extraEnvironmentVariables = this.environmentVariableAccessor.splitEnvironmentAssignments(configurationMap.get("environmentVariables"), false);
            List<String> arguments = CommandlineStringUtils.tokeniseCommandline(cmd);
            ImmutableList.Builder<String> commandListBuilder = ImmutableList.builder();
            commandListBuilder.add(serverlessPath).addAll(arguments);

            buildLogger.addBuildLogEntry("Exec: " + String.join(" ", commandListBuilder.build()));

            TaskResultBuilder taskResultBuilder = TaskResultBuilder.newBuilder(taskContext);
            taskResultBuilder.checkReturnCode(
                    this.processService.executeExternalProcess(
                            taskContext,
                            (new ExternalProcessBuilder())
                                    .command(commandListBuilder.build())
                                    .env(extraEnvironmentVariables)
                                    .path(FilenameUtils.getFullPath(serverlessPath))
                                    .workingDirectory(taskContext.getWorkingDirectory())
                    )
            );
            return taskResultBuilder.build();
        } catch (Exception e) {
            taskContext.getBuildLogger().addErrorLogEntry("Failed to execute task: " + e.getMessage());
            throw new TaskException("Failed to execute task", e);
        }
    }
}
