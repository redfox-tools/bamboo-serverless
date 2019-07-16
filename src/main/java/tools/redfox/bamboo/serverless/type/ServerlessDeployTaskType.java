package tools.redfox.bamboo.serverless.type;

import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.CommonTaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.opensymphony.xwork2.TextProvider;

public class ServerlessDeployTaskType extends ServerlessTaskType {
    public ServerlessDeployTaskType(
            @ComponentImport ProcessService processService,
            @ComponentImport EnvironmentVariableAccessor environmentVariableAccessor,
            @ComponentImport CapabilityContext capabilityContext,
            @ComponentImport TextProvider textProvider) {
        super(processService, environmentVariableAccessor, capabilityContext, textProvider);
    }

    @Override
    public TaskResult execute(CommonTaskContext commonTaskContext) throws TaskException {
        return execute(commonTaskContext, "deploy --verbose");
    }
}
