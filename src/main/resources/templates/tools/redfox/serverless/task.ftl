[#assign addExecutableLink][@ui.displayAddExecutableInline executableKey='${executable}' /][/#assign]

[@s.select
    cssClass="builderSelectWidget"
    labelKey="tools.redfox.bamboo.${executable}.runtime.label"
    descriptionKey="tools.redfox.bamboo.${executable}.runtime.description"
    name="runtime"
    list=executableLabels
    extraUtility=addExecutableLink /]

[@s.textfield
    labelKey="tools.redfox.bamboo.${executable}.options.label"
    descriptionKey="tools.redfox.bamboo.${executable}.options.description"
    name="options"
    cssClass="long-field" /]

[@ui.bambooSection titleKey='repository.advanced.option' collapsible=true isCollapsed=!(environmentVariables?has_content || workingSubDirectory?has_content)]
    [@s.textfield labelKey='builder.common.env' name='environmentVariables' cssClass="long-field" /]
    [@s.textfield labelKey='builder.common.sub' name='workingSubDirectory' cssClass="long-field" /]
[/@ui.bambooSection]
