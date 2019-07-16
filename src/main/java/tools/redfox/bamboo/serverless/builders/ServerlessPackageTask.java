package tools.redfox.bamboo.serverless.builders;

import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.serverless.model.ServerlessPackageTaskProperties;

public class ServerlessPackageTask extends BaseServerlessTask<ServerlessPackageTask, ServerlessPackageTaskProperties> {
    @NotNull
    @Override
    protected ServerlessPackageTaskProperties build() {
        return new ServerlessPackageTaskProperties(
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
