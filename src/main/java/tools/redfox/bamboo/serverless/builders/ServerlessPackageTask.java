package tools.redfox.bamboo.serverless.builders;

import org.jetbrains.annotations.NotNull;
import com.atlassian.bamboo.specs.model.serverless.ServerlessPackageTaskProperties;
import tools.redfox.bamboo.base.builder.BaseTask;

public class ServerlessPackageTask extends BaseTask<ServerlessPackageTask, ServerlessPackageTaskProperties> {
    @NotNull
    @Override
    protected ServerlessPackageTaskProperties build() {
        return new ServerlessPackageTaskProperties(
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
