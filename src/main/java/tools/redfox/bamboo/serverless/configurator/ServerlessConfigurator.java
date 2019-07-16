package tools.redfox.bamboo.serverless.configurator;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.AbstractTaskConfigurator;
import com.atlassian.bamboo.task.TaskConfiguratorHelper;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.task.TaskRequirementSupport;
import com.atlassian.bamboo.v2.build.agent.capability.Requirement;
import com.atlassian.bamboo.v2.build.agent.capability.RequirementImpl;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.google.common.collect.ImmutableSet;
import com.opensymphony.xwork2.TextProvider;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServerlessConfigurator extends AbstractTaskConfigurator implements TaskRequirementSupport {
    protected static Set<String> FIELDS_TO_COPY = ImmutableSet.of("environmentVariables", "workingSubDirectory", "options", "runtime");
    protected static final Map<String, Object> DEFAULT_FIELD_VALUES = Collections.emptyMap();

    protected UIConfigSupport uiConfigSupport;
    private TextProvider textProvider;

    public ServerlessConfigurator(@ComponentImport UIConfigSupport uiConfigSupport, @ComponentImport TextProvider textProvider, @ComponentImport TaskConfiguratorHelper taskConfiguratorHelper) {
        this.uiConfigSupport = uiConfigSupport;
        this.textProvider = textProvider;
        this.taskConfiguratorHelper = taskConfiguratorHelper;
    }

    @NotNull
    public Set<String> getFieldsToCopy() {
        return FIELDS_TO_COPY;
    }

    @NotNull
    public Set<Requirement> calculateRequirements(@NotNull TaskDefinition taskDefinition) {
        String serverlessRuntime = taskDefinition.getConfiguration().get("runtime");
        Set<Requirement> requirements = Collections.emptySet();
        if (StringUtils.isNotBlank(serverlessRuntime)) {
            return Collections.singleton(new RequirementImpl("system.builder.serverless." + serverlessRuntime, true, ".*"));
        }
        return requirements;
    }

    @NotNull
    public Map<String, String> generateTaskConfigMap(@NotNull ActionParametersMap params, @Nullable TaskDefinition previousTaskDefinition) {
        Map<String, String> map = super.generateTaskConfigMap(params, previousTaskDefinition);
        this.taskConfiguratorHelper.populateTaskConfigMapWithActionParameters(map, params, this.getFieldsToCopy());
        return map;
    }

    public void populateContextForCreate(@NotNull Map<String, Object> context) {
        super.populateContextForCreate(context);
        context.put("serverlessLabels", getServerlessLabels());
        context.putAll(this.getDefaultFieldValues());
    }

    public void populateContextForEdit(@NotNull Map<String, Object> context, @NotNull TaskDefinition taskDefinition) {
        super.populateContextForEdit(context, taskDefinition);
        context.put("serverlessLabels", getServerlessLabels());
        this.taskConfiguratorHelper.populateContextWithConfiguration(context, taskDefinition, this.getFieldsToCopy());
    }

    @NotNull
    public Map<String, Object> getDefaultFieldValues() {
        return DEFAULT_FIELD_VALUES;
    }

    protected List<String> getServerlessLabels() {
        List<String> labels = uiConfigSupport.getExecutableLabels("serverless");
        labels.add(0, "");
        return labels;
    }
}
