package tools.redfox.bamboo.serverless.type;

import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.redfox.bamboo.base.type.BaseTaskType;

public class ServerlessPackageTaskType extends BaseTaskType {
    private static final Logger logger = LoggerFactory.getLogger(ServerlessDeployTaskType.class);

    public static final String NAME = "serverless";
    public static final String TASK_ID = "tools.redfox.bamboo.serverless:tools.redfox.serverless.package.task";

    public ServerlessPackageTaskType(
            @ComponentImport ProcessService processService,
            @ComponentImport EnvironmentVariableAccessor environmentVariableAccessor,
            @ComponentImport CapabilityContext capabilityContext) {
        super(processService, environmentVariableAccessor, capabilityContext);
    }

    @Override
    protected String getName() {
        return NAME;
    }

    @Override
    protected String getBaseCommand() {
        return "package";
    }
}
