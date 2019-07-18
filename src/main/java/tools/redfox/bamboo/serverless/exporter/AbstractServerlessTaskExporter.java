package tools.redfox.bamboo.serverless.exporter;

import com.atlassian.bamboo.specs.api.model.task.TaskProperties;
import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.specs.api.validators.common.ValidationProblem;
import com.atlassian.bamboo.task.TaskContainer;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.task.export.TaskDefinitionExporter;
import com.atlassian.bamboo.task.export.TaskValidationContext;
import com.atlassian.bamboo.util.Narrow;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.serverless.model.BaseServerlessTaskProperties;
import tools.redfox.bamboo.serverless.builders.BaseServerlessTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractServerlessTaskExporter<P extends BaseServerlessTaskProperties, B extends BaseServerlessTask<B, P>> implements TaskDefinitionExporter {
    private final Class<P> propertiesClass;
    private UIConfigSupport uiConfigSupport;

    protected AbstractServerlessTaskExporter(Class<P> propertiesClass, UIConfigSupport uiConfigSupport) {
        this.propertiesClass = propertiesClass;
        this.uiConfigSupport = uiConfigSupport;
    }

    @NotNull
    protected abstract ValidationContext getValidationContext();

    @NotNull
    protected abstract Map<String, String> toTaskConfiguration(@NotNull P var1);

    @NotNull
    public final Map<String, String> toTaskConfiguration(@NotNull TaskContainer taskContainer, @NotNull TaskProperties taskProperties) {
        P typedTaskProperties = Narrow.downTo(taskProperties, this.propertiesClass);
        Preconditions.checkState(typedTaskProperties != null, "Don't know how to import task properties of type: " + taskProperties.getClass().getName());
        Map<String, String> config = new HashMap<>();
        config.put("runtime", typedTaskProperties.getServerlessExecutable());
        config.put("options", typedTaskProperties.getOptions());
        config.put("environmentVariables", typedTaskProperties.getEnvironmentVariables());
        config.put("workingSubDirectory", typedTaskProperties.getWorkingSubdirectory());
        config.putAll(this.toTaskConfiguration(typedTaskProperties));
        return config;
    }

    @NotNull
    protected abstract B toSpecsEntity(@NotNull Map<String, String> configuration);

    @NotNull
    public final B toSpecsEntity(@NotNull TaskDefinition taskDefinition) {
        Map<String, String> config = taskDefinition.getConfiguration();
        return this.toSpecsEntity(config)
                .serverlessExecutable(config.get("runtime"))
                .options(config.get("options"))
                .environmentVariables(config.get("environmentVariables"))
                .workingSubdirectory(config.get("workingSubDirectory"));
    }

    @NotNull
    protected abstract List<ValidationProblem> validate(@NotNull P taskProperties);

    @NotNull
    public final List<ValidationProblem> validate(@NotNull TaskValidationContext taskValidationContext, @NotNull TaskProperties taskProperties) {
        List<ValidationProblem> validationProblems = new ArrayList<>();

        return validationProblems;
    }
}
