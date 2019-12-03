package com.atlassian.bamboo.specs.model.serverless;

import com.atlassian.bamboo.specs.api.codegen.annotations.Builder;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.AtlassianModuleProperties;
import com.atlassian.bamboo.specs.api.model.plan.condition.ConditionProperties;
import com.atlassian.bamboo.specs.api.model.plan.requirement.RequirementProperties;
import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.model.BaseTaskProperties;
import tools.redfox.bamboo.serverless.builders.ServerlessPackageTask;
import tools.redfox.bamboo.serverless.type.ServerlessPackageTaskType;

import java.util.List;

@Builder(ServerlessPackageTask.class)
public final class ServerlessPackageTaskProperties extends BaseTaskProperties {
    public static final ValidationContext VALIDATION_CONTEXT = ValidationContext.of(ServerlessPackageTaskType.NAME);
    private static final AtlassianModuleProperties ATLASSIAN_PLUGIN =
            new AtlassianModuleProperties(ServerlessPackageTaskType.TASK_ID);

    public ServerlessPackageTaskProperties() {
        super();
    }

    public ServerlessPackageTaskProperties(
            String description,
            boolean enabled,
            String runtime,
            String options,
            String output,
            String workingSubDirectory,
            String environmentVariables,
            @NotNull List<RequirementProperties> requirements,
            @NotNull List<? extends ConditionProperties> conditions) throws PropertiesValidationException {
        super(description, enabled, runtime, options, output, workingSubDirectory, environmentVariables, requirements, conditions);
    }

    @Override
    protected String getModulePropertiesName() {
        return ServerlessPackageTaskType.TASK_ID;
    }
}
