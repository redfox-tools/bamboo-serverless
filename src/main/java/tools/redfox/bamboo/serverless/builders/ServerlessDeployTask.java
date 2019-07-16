package tools.redfox.bamboo.serverless.builders;

import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.serverless.model.ServerlessDeployTaskProperties;

public class ServerlessDeployTask extends BaseServerlessTask<ServerlessDeployTask, ServerlessDeployTaskProperties> {
    @NotNull
    @Override
    protected ServerlessDeployTaskProperties build() {
        return new ServerlessDeployTaskProperties(
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
