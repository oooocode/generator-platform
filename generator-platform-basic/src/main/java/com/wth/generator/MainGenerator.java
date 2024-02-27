package com.wth.generator;

import com.wth.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Author: wth
 * @Create: 2024/2/25 - 23:47
 */
public class MainGenerator {

    public static void execute(Object model) throws TemplateException, IOException {
        // 1.静态代码生成
        String projectPath = System.getProperty("user.dir");
        File parentFile  = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "generator-platform-demo-projects" + File.separator + "acm-template").getAbsolutePath();
        String outputPath = projectPath;
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
        // 2.动态代码生成
        String dynamicInputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputPath = outputPath + File.separator + "acm-template/src/com/wth/acm/MainTemplate.java";
        DynamicGenerator.doGenerator(dynamicInputPath, dynamicOutputPath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        execute(mainTemplateConfig);
    }
}
