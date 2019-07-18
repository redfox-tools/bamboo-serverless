package tools.redfox.bamboo.serverless.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.specs.api.validators.common.ValidationProblem;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import tools.redfox.bamboo.serverless.builders.ServerlessDeployTask;
import tools.redfox.bamboo.serverless.model.ServerlessDeployTaskProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerlessDeployTaskExporter extends AbstractServerlessTaskExporter<ServerlessDeployTaskProperties, ServerlessDeployTask> {
    @Autowired
    public ServerlessDeployTaskExporter(UIConfigSupport uiConfigSupport) {
        super(ServerlessDeployTaskProperties.class, uiConfigSupport);
    }

    @NotNull
    protected ValidationContext getValidationContext() {
        return ServerlessDeployTaskProperties.VALIDATION_CONTEXT;
    }

    @NotNull
    protected Map<String, String> toTaskConfiguration(@NotNull ServerlessDeployTaskProperties taskProperties) {
        return new HashMap<>();
    }

    @NotNull
    public ServerlessDeployTask toSpecsEntity(@NotNull Map<String, String> taskConfiguration) {
        return new ServerlessDeployTask();
    }

    @NotNull
    protected List<ValidationProblem> validate(@NotNull ServerlessDeployTaskProperties taskProperties) {
        return new ArrayList<>();
    }
}
