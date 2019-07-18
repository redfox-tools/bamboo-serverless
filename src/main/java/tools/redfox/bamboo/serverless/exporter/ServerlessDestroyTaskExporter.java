package tools.redfox.bamboo.serverless.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.specs.api.validators.common.ValidationProblem;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import tools.redfox.bamboo.serverless.builders.ServerlessDestroyTask;
import tools.redfox.bamboo.serverless.model.ServerlessDestroyTaskProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerlessDestroyTaskExporter extends AbstractServerlessTaskExporter<ServerlessDestroyTaskProperties, ServerlessDestroyTask> {
    @Autowired
    public ServerlessDestroyTaskExporter(UIConfigSupport uiConfigSupport) {
        super(ServerlessDestroyTaskProperties.class, uiConfigSupport);
    }

    @NotNull
    protected ValidationContext getValidationContext() {
        return ServerlessDestroyTaskProperties.VALIDATION_CONTEXT;
    }

    @NotNull
    protected Map<String, String> toTaskConfiguration(@NotNull ServerlessDestroyTaskProperties taskProperties) {
        return new HashMap<>();
    }

    @NotNull
    public ServerlessDestroyTask toSpecsEntity(@NotNull Map<String, String> taskConfiguration) {
        return new ServerlessDestroyTask();
    }

    @NotNull
    protected List<ValidationProblem> validate(@NotNull ServerlessDestroyTaskProperties taskProperties) {
        return new ArrayList<>();
    }
}
