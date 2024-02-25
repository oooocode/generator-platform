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

    public static void main(String[] args) throws TemplateException, IOException {
        // 1.静态代码生成
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator + "generator-platform-demo-projects" + File.separator + "acm-template";
        String outputPath = projectPath;
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
        // 2.动态代码生成
        String dynamicInputPath = projectPath + File.separator + "generator-platform-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputPath = outputPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("wth");
        mainTemplateConfig.setOutputText("123");
        mainTemplateConfig.setLoop(true);
        DynamicGenerator.doGenerator(dynamicInputPath, dynamicOutputPath, mainTemplateConfig);
    }
}
