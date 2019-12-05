package tools.redfox.bamboo.serverless.type;

import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskType;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.redfox.bamboo.base.tools.output.handler.OutputProcessor;
import tools.redfox.bamboo.base.tools.output.handler.ProcessorOutput;
import tools.redfox.bamboo.base.type.BaseTaskType;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerlessDeployTaskType extends BaseTaskType implements TaskType {
    private static final Logger logger = LoggerFactory.getLogger(ServerlessDeployTaskType.class);

    public static final String NAME = "serverless";
    public static final String TASK_ID = "tools.redfox.bamboo.serverless:tools.redfox.serverless.deploy.task";

    class ServerlessUrlExtractor implements OutputProcessor {
        private TaskContext taskContext;

        public ServerlessUrlExtractor(TaskContext taskContext) {
            this.taskContext = taskContext;
        }

        @Override
        public ProcessorOutput handle(String output) {
            Pattern url = Pattern.compile("ServiceEndpoint:\\s*(https://.*?amazonaws\\.com/\\w+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = url.matcher(output);

            if (matcher.find()) {
                taskContext.getCommonContext().getVariableContext().addResultVariable("serverless.api.url", matcher.group(0));
            }
            return null;
        }
    }

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

    @Override
    public TaskResult execute(@NotNull TaskContext taskContext) throws TaskException {
        processors.add(new ServerlessUrlExtractor(taskContext));
        return super.execute(taskContext);
    }
}
