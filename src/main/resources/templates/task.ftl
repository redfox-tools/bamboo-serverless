[#-- @ftlvariable name="uiConfigBean" type="com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport" --]

[#assign addExecutableLink][@ui.displayAddExecutableInline executableKey='serverless' /][/#assign]
[@s.select
    cssClass="builderSelectWidget"
    labelKey="tools.redfox.bamboo.serverless.runtime.label"
    descriptionKey="tools.redfox.bamboo.serverless.runtime.description"
    name="runtime"
    list=serverlessLabels
    extraUtility=addExecutableLink /]

[@s.textfield
    labelKey="tools.redfox.bamboo.serverless.options.label"
    descriptionKey="tools.redfox.bamboo.serverless.options.description"
    name="command"
    cssClass="long-field" /]

[@ui.bambooSection titleKey='repository.advanced.option' collapsible=true isCollapsed=!(environmentVariables?has_content || workingSubDirectory?has_content)]
    [@s.textfield labelKey='builder.common.env' name='environmentVariables' cssClass="long-field" /]
    [@s.textfield labelKey='builder.common.sub' name='workingSubDirectory' cssClass="long-field" /]
[/@ui.bambooSection]
