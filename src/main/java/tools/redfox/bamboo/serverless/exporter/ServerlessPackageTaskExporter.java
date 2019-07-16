package tools.redfox.bamboo.serverless.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.specs.api.validators.common.ValidationProblem;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import tools.redfox.bamboo.serverless.model.ServerlessPackageTaskProperties;
import tools.redfox.bamboo.serverless.builders.ServerlessPackageTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerlessPackageTaskExporter extends AbstractServerlessTaskExporter<ServerlessPackageTaskProperties, ServerlessPackageTask> {
    @Autowired
    public ServerlessPackageTaskExporter(UIConfigSupport uiConfigSupport) {
        super(ServerlessPackageTaskProperties.class, uiConfigSupport);
    }

    @NotNull
    protected ValidationContext getValidationContext() {
        return ServerlessPackageTaskProperties.VALIDATION_CONTEXT;
    }

    @NotNull
    protected Map<String, String> toTaskConfiguration(@NotNull ServerlessPackageTaskProperties taskProperties) {
        return new HashMap<>();
    }

    @NotNull
    public ServerlessPackageTask toSpecsEntity(@NotNull Map<String, String> taskConfiguration) {
        return new ServerlessPackageTask();
    }

    @NotNull
    protected List<ValidationProblem> validate(@NotNull ServerlessPackageTaskProperties taskProperties) {
        return new ArrayList<>();
    }
}
