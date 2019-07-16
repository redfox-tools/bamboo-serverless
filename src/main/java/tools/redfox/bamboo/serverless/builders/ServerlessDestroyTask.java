package tools.redfox.bamboo.serverless.builders;

import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.serverless.model.ServerlessDestroyTaskProperties;

public class ServerlessDestroyTask extends BaseServerlessTask<ServerlessDestroyTask, ServerlessDestroyTaskProperties> {
    @NotNull
    @Override
    protected ServerlessDestroyTaskProperties build() {
        return new ServerlessDestroyTaskProperties(
                description,
                taskEnabled,
                serverlessExecutable,
                environmentVariables,
                workingSubdirectory,
                options,
                requirements,
                conditions
        );
    }
}
