package ${basePackage}.generator;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Author: ${author}
 * @Create: ${createTime}
 */
public class MainGenerator {

    public static void execute(Object model) throws TemplateException, IOException {

        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String inputPath;
        String outputPath;
<#list fileConfig.files as fileInfo>
        inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == 'dynamic'>
        DynamicGenerator.doGenerator(inputPath, outputPath, model);
    <#else>
        StaticGenerator.copyFileByHutool(inputPath, outputPath);
    </#if>
</#list>
    }
}
