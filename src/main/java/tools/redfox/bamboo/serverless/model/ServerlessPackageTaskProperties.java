package tools.redfox.bamboo.serverless.model;

import com.atlassian.bamboo.specs.api.codegen.annotations.Builder;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.AtlassianModuleProperties;
import com.atlassian.bamboo.specs.api.model.plan.condition.ConditionProperties;
import com.atlassian.bamboo.specs.api.model.plan.requirement.RequirementProperties;
import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.redfox.bamboo.serverless.builders.ServerlessPackageTask;

import java.util.List;

@Builder(ServerlessPackageTask.class)
public final class ServerlessPackageTaskProperties extends BaseServerlessTaskProperties {
    private static final AtlassianModuleProperties ATLASSIAN_PLUGIN =
            new AtlassianModuleProperties("tools.redfox.bamboo.serverless:tools.redfox.serverless.package");

    protected ServerlessPackageTaskProperties() {
        super();
    }

    public ServerlessPackageTaskProperties(@Nullable String description, boolean enabled, @Nullable String serverlessExecutable, @Nullable String environmentVariables, @Nullable String workingSubdirectory, @Nullable String options, @NotNull List<RequirementProperties> requirements, @NotNull List<? extends ConditionProperties> conditions) throws PropertiesValidationException {
        super(description, enabled, serverlessExecutable, environmentVariables, workingSubdirectory, options, requirements, conditions);
    }

    public static final ValidationContext VALIDATION_CONTEXT = ValidationContext.of("serverless task");

    @Override
    public void validate() {
    }

    @NotNull
    @Override
    public AtlassianModuleProperties getAtlassianPlugin() {
        return ATLASSIAN_PLUGIN;
    }
}
