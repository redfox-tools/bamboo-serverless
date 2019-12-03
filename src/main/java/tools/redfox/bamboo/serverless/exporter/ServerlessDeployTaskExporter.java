package tools.redfox.bamboo.serverless.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.specs.model.serverless.ServerlessDeployTaskProperties;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.exporter.BaseExporter;
import tools.redfox.bamboo.serverless.builders.ServerlessDeployTask;

import java.util.Map;

public class ServerlessDeployTaskExporter extends BaseExporter<ServerlessDeployTaskProperties, ServerlessDeployTask> {
    public ServerlessDeployTaskExporter() {
        super(ServerlessDeployTaskProperties.class);
    }

    @NotNull
    @Override
    protected ValidationContext getValidationContext() {
        return ServerlessDeployTaskProperties.VALIDATION_CONTEXT;
    }

    @NotNull
    @Override
    protected ServerlessDeployTask toSpecsEntity(@NotNull Map<String, String> configuration) {
        return new ServerlessDeployTask();
    }
}
