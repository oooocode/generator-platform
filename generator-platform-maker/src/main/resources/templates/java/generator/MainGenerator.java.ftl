package ${basePackage}.generator;

import freemarker.template.TemplateException;
import com.wth.model.DataModel;

import java.io.File;
import java.io.IOException;

<#macro generateFile indent fileInfo>
${indent}inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
${indent}outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
<#if fileInfo.generateType == 'dynamic'>
${indent}DynamicGenerator.doGenerator(inputPath, outputPath, model);
<#else>
${indent}StaticGenerator.copyFileByHutool(inputPath, outputPath);
</#if>
</#macro>


/**
 * @Author: ${author}
 * @Create: ${createTime}
 */
public class MainGenerator {

    public static void execute(DataModel model) throws TemplateException, IOException {

        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String inputPath;
        String outputPath;
    <#list modelConfig.models as model>
        <#if model.groupKey??>
        <#list model.models as subModel>
        ${subModel.type} ${subModel.fieldName} = model.${model.groupKey}.${subModel.fieldName};
        </#list>
        <#else>
        ${model.type} ${model.fieldName} = model.${model.fieldName};
        </#if>
    </#list>

<#list fileConfig.files as fileInfo>
    <#if fileInfo.type="group">
    <#if fileInfo.condition??>
        // ${fileInfo.groupKey}
        if(${fileInfo.condition}) {
            <#list fileInfo.files as fileInfo>
            <@generateFile indent="            " fileInfo=fileInfo/>
            </#list>
        }
        <#else>
        <#list fileInfo.files as fileInfo>
        <@generateFile indent="        " fileInfo=fileInfo/>
        </#list>
    </#if>
    <#else>
    <#if fileInfo.condition??>
        if(${fileInfo.condition}) {
            <@generateFile indent="            " fileInfo=fileInfo/>
        }
    <#else>
        <@generateFile indent="        " fileInfo=fileInfo/>
    </#if>
    </#if>
</#list>
    }
}
