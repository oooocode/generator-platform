package ${basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.generator.MainGenerator;
import ${basePackage}.model.DataModel;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
<#--生成命令参数选项-->
<#macro generateModel indent modelInfo>
${indent}/**
${indent} * ${modelInfo.description}
${indent} */
${indent}@Option(names = {<#if modelInfo.abbr??>"-${modelInfo.abbr}", </#if>"--${modelInfo.fieldName}"}, arity = "0..1", description = "${modelInfo.description}", interactive = true, echo = true)
${indent}private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#macro>

<#--生成执行命令选项-->
<#macro generateCommand indent modelInfo>
${indent}System.out.println("请输入${modelInfo.description}配置:");
${indent}CommandLine commandLine = new CommandLine(${modelInfo.type}Command.class);
${indent}commandLine.execute(${modelInfo.allArgsStr});
</#macro>

/**
 * @Author: ${author}
 * @Create: ${createTime}
 */
@Command(name = "generate", description = "生成代码文件", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Runnable {

<#list modelConfig.models as modelInfo>
    <#--有分组-->
    <#if modelInfo.groupKey??>
    static DataModel.${modelInfo.type} ${modelInfo.groupKey} = new DataModel.${modelInfo.type}();

    @Command(name = "${modelInfo.groupKey}", description = "${modelInfo.description}")
    @Data
    static class ${modelInfo.type}Command implements Runnable {
        <#list modelInfo.models as subModelInfo>
        <@generateModel indent="        " modelInfo=subModelInfo/>
        </#list>
        @Override
        public void run() {
            <#list modelInfo.models as subModelInfo>
            ${modelInfo.groupKey}.${subModelInfo.fieldName} = ${subModelInfo.fieldName};
            </#list>
        }
    }
    <#else>
    <@generateModel indent="    " modelInfo=modelInfo/>
    </#if>
</#list>

    @Override
    public void run() {
<#list modelConfig.models as modelInfo>
    <#if modelInfo.groupKey??>
        <#if modelInfo.condition??>
        if (${modelInfo.condition}) {
            <@generateCommand indent="            " modelInfo=modelInfo/>
        }
        <#else>
            <@generateCommand indent="        " modelInfo=modelInfo/>
        </#if>
    </#if>
</#list>
        <#--填充模板对象-->
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        <#--赋值对象属性-->
<#list modelConfig.models as modelInfo>
    <#if modelInfo.groupKey??>
        dataModel.${modelInfo.groupKey} = ${modelInfo.groupKey};
    </#if>
</#list>
        try {
            MainGenerator.execute(dataModel);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
