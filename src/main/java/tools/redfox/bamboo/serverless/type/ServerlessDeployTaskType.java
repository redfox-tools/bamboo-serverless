package tools.redfox.bamboo.serverless.type;

import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.redfox.bamboo.base.type.BaseTaskType;

public class ServerlessDeployTaskType extends BaseTaskType {
    private static final Logger logger = LoggerFactory.getLogger(ServerlessDeployTaskType.class);

    public static final String NAME = "serverless";
    public static final String TASK_ID = "tools.redfox.bamboo.serverless:tools.redfox.serverless.deploy.task";

    public ServerlessDeployTaskType(
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
        return "deploy --verbose";
    }
/*
    @Override
    public TaskResult execute(CommonTaskContext commonTaskContext) throws TaskException {
        TaskResult result = execute(commonTaskContext, "deploy --verbose");

        if (result.getTaskState() != TaskState.SUCCESS) {
            return result;
        }

        Pattern url = Pattern.compile("https://.*?amazonaws.com/\\w+", Pattern.CASE_INSENSITIVE);
        String endpoint = commonTaskContext
                .getBuildLogger()
                .getLastNLogEntries(100)
                .stream()
                .filter(l -> url.matcher(l.getLog()).find())
                .map(l -> {
                    Matcher matcher = url.matcher(l.getLog());
                    matcher.find();
                    return matcher.group(0);
                })
                .findFirst().orElse(null);

        if (endpoint != null) {
            commonTaskContext.getCommonContext().getVariableContext().addResultVariable("serverless.api.url", endpoint);
        }

        return result;
    }*/
}
