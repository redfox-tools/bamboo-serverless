package tools.redfox.bamboo.serverless.model;

import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.plan.condition.ConditionProperties;
import com.atlassian.bamboo.specs.api.model.plan.requirement.RequirementProperties;
import com.atlassian.bamboo.specs.api.model.task.TaskProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public abstract class BaseServerlessTaskProperties extends TaskProperties {
    @Nullable
    protected String serverlessExecutable;

    @Nullable
    protected String options;

    @Nullable
    protected String environmentVariables;

    @Nullable
    protected String workingSubdirectory;

    protected BaseServerlessTaskProperties() {
        super();
    }

    public BaseServerlessTaskProperties(@Nullable String description,
                                        boolean enabled,
                                        @Nullable String serverlessExecutable,
                                        @Nullable String environmentVariables,
                                        @Nullable String workingSubdirectory,
                                        @Nullable String options,
                                        @NotNull List<RequirementProperties> requirements,
                                        @NotNull List<? extends ConditionProperties> conditions
    ) throws PropertiesValidationException {
        super(description, enabled, requirements, conditions);
        this.serverlessExecutable = serverlessExecutable;
        this.options = options;
        this.environmentVariables = environmentVariables;
        this.workingSubdirectory = workingSubdirectory;
        validate();
    }

    @Override
    public abstract void validate();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServerlessPackageTaskProperties)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final BaseServerlessTaskProperties that = (BaseServerlessTaskProperties) o;
        return Objects.equals(getOptions(), that.getOptions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOptions());
    }

    @Nullable
    public String getOptions() {
        return options;
    }

    @Nullable
    public String getServerlessExecutable() {
        return serverlessExecutable;
    }

    @Nullable
    public String getEnvironmentVariables() {
        return environmentVariables;
    }

    @Nullable
    public String getWorkingSubdirectory() {
        return workingSubdirectory;
    }
}
