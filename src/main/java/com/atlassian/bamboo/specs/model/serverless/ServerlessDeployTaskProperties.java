package com.atlassian.bamboo.specs.model.serverless;

import com.atlassian.bamboo.specs.api.codegen.annotations.Builder;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.AtlassianModuleProperties;
import com.atlassian.bamboo.specs.api.model.plan.condition.ConditionProperties;
import com.atlassian.bamboo.specs.api.model.plan.requirement.RequirementProperties;
import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.model.BaseTaskProperties;
import tools.redfox.bamboo.serverless.builders.ServerlessDeployTask;
import tools.redfox.bamboo.serverless.type.ServerlessDeployTaskType;

import java.util.List;

@Builder(ServerlessDeployTask.class)
public final class ServerlessDeployTaskProperties extends BaseTaskProperties {
    public static final ValidationContext VALIDATION_CONTEXT = ValidationContext.of(ServerlessDeployTaskType.NAME);
    private static final AtlassianModuleProperties ATLASSIAN_PLUGIN =
            new AtlassianModuleProperties(ServerlessDeployTaskType.TASK_ID);

    public ServerlessDeployTaskProperties() {
        super();
    }

    public ServerlessDeployTaskProperties(
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
        return ServerlessDeployTaskType.TASK_ID;
    }
}
