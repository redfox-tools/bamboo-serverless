package tools.redfox.bamboo.serverless.type;

import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.CommonTaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskState;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.opensymphony.xwork2.TextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerlessDeployTaskType extends ServerlessTaskType {
    private static final Logger logger = LoggerFactory.getLogger(ServerlessDeployTaskType.class);

    public ServerlessDeployTaskType(
            @ComponentImport ProcessService processService,
            @ComponentImport EnvironmentVariableAccessor environmentVariableAccessor,
            @ComponentImport CapabilityContext capabilityContext,
            @ComponentImport TextProvider textProvider) {
        super(processService, environmentVariableAccessor, capabilityContext, textProvider);
    }

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
    }
}
