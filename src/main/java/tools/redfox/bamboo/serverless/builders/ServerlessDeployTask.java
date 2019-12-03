package tools.redfox.bamboo.serverless.builders;

import org.jetbrains.annotations.NotNull;
import com.atlassian.bamboo.specs.model.serverless.ServerlessDeployTaskProperties;
import tools.redfox.bamboo.base.builder.BaseTask;

public class ServerlessDeployTask extends BaseTask<ServerlessDeployTask, ServerlessDeployTaskProperties> {
    @NotNull
    @Override
    protected ServerlessDeployTaskProperties build() {
        return new ServerlessDeployTaskProperties(
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
