package tools.redfox.bamboo.serverless.configurator;

import com.atlassian.bamboo.task.TaskConfiguratorHelper;
import com.atlassian.bamboo.task.TaskRequirementSupport;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.configuration.BaseTaskConfiguration;
import tools.redfox.bamboo.serverless.type.ServerlessDeployTaskType;
import tools.redfox.bamboo.serverless.type.ServerlessPackageTaskType;

public class ServerlessPackageConfigurator extends BaseTaskConfiguration implements TaskRequirementSupport {
    public ServerlessPackageConfigurator(@ComponentImport UIConfigSupport uiConfigSupport, @ComponentImport TaskConfiguratorHelper taskConfiguratorHelper) {
        super(uiConfigSupport, taskConfiguratorHelper);
    }

    @NotNull
    @Override
    protected String getExecutableName() {
        return ServerlessPackageTaskType.NAME;
    }
}
