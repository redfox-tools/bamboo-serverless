package tools.redfox.bamboo.serverless.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.specs.api.validators.common.ValidationProblem;
import com.atlassian.bamboo.specs.model.serverless.ServerlessDeployTaskProperties;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import tools.redfox.bamboo.base.exporter.BaseExporter;
import tools.redfox.bamboo.serverless.builders.ServerlessDeployTask;
import tools.redfox.bamboo.serverless.builders.ServerlessDestroyTask;
import com.atlassian.bamboo.specs.model.serverless.ServerlessDestroyTaskProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerlessDestroyTaskExporter extends BaseExporter<ServerlessDestroyTaskProperties, ServerlessDestroyTask> {
    public ServerlessDestroyTaskExporter() {
        super(ServerlessDestroyTaskProperties.class);
    }

    @NotNull
    @Override
    protected ValidationContext getValidationContext() {
        return ServerlessDestroyTaskProperties.VALIDATION_CONTEXT;
    }

    @NotNull
    @Override
    protected ServerlessDestroyTask toSpecsEntity(@NotNull Map<String, String> configuration) {
        return new ServerlessDestroyTask();
    }
}
