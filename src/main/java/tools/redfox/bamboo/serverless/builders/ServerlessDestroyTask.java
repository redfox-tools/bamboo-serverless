package tools.redfox.bamboo.serverless.builders;

import org.jetbrains.annotations.NotNull;
import com.atlassian.bamboo.specs.model.serverless.ServerlessDestroyTaskProperties;
import tools.redfox.bamboo.base.builder.BaseTask;

public class ServerlessDestroyTask extends BaseTask<ServerlessDestroyTask, ServerlessDestroyTaskProperties> {
    @NotNull
    @Override
    protected ServerlessDestroyTaskProperties build() {
        return new ServerlessDestroyTaskProperties(
                description,
                taskEnabled,
                runtime,
                options,
                output,
                workingSubDirectory,
                environmentVariables,
                requirements,
                conditions
        );
    }
}
