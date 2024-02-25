package com.wth.generator;

import com.wth.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @Author: wth
 * @Create: 2024/2/25 - 23:24
 */
public class DynamicGenerator {


    public static void main(String[] args) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator + "generator-platform-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("wth");
        mainTemplateConfig.setOutputText("123");
        mainTemplateConfig.setLoop(true);
        doGenerator(inputPath, outputPath, mainTemplateConfig);
    }

    public static void doGenerator(String inputPath, String outputPath, Object dataModel) throws IOException, TemplateException {
        // 配置对象，参数为版本号
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        // 设置模版文件所在路径
        File file = new File(inputPath).getParentFile();
        cfg.setDirectoryForTemplateLoading(file);
        // 设置字符集
        cfg.setDefaultEncoding("UTF-8");
        cfg.setNumberFormat("0.######");
        // 获取模版路径，加载指令模版
        String templateName = new File(inputPath).getName();
        Template template = cfg.getTemplate(templateName);
        // 设置输出路径
        Writer out = new FileWriter(outputPath);
        // 合并数据模型和模版
        template.process(dataModel, out);
        // 关闭流
        out.close();
    }
}
