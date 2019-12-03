package tools.redfox.bamboo.serverless.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.specs.api.validators.common.ValidationProblem;
import com.atlassian.bamboo.specs.model.serverless.ServerlessDestroyTaskProperties;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import com.atlassian.bamboo.specs.model.serverless.ServerlessPackageTaskProperties;
import tools.redfox.bamboo.base.exporter.BaseExporter;
import tools.redfox.bamboo.serverless.builders.ServerlessDestroyTask;
import tools.redfox.bamboo.serverless.builders.ServerlessPackageTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerlessPackageTaskExporter extends BaseExporter<ServerlessPackageTaskProperties, ServerlessPackageTask> {
    public ServerlessPackageTaskExporter() {
        super(ServerlessPackageTaskProperties.class);
    }

    @NotNull
    @Override
    protected ValidationContext getValidationContext() {
        return ServerlessPackageTaskProperties.VALIDATION_CONTEXT;
    }

    @NotNull
    @Override
    protected ServerlessPackageTask toSpecsEntity(@NotNull Map<String, String> configuration) {
        return new ServerlessPackageTask();
    }
}
