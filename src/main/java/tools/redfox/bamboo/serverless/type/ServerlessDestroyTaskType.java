package tools.redfox.bamboo.serverless.type;

import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.specs.model.task.NpmTaskProperties;
import com.atlassian.bamboo.task.CommonTaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.opensymphony.xwork2.TextProvider;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class ServerlessDestroyTaskType extends ServerlessTaskType {
    public ServerlessDestroyTaskType(
            @ComponentImport ProcessService processService,
            @ComponentImport EnvironmentVariableAccessor environmentVariableAccessor,
            @ComponentImport CapabilityContext capabilityContext,
            @ComponentImport TextProvider textProvider) {
        super(processService, environmentVariableAccessor, capabilityContext, textProvider);
    }

    @Override
    public TaskResult execute(CommonTaskContext commonTaskContext) throws TaskException {
        return execute(commonTaskContext, "remove --verbose");
    }
}
