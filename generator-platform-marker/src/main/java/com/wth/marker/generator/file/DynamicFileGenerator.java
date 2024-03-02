package com.wth.marker.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 动态文件生成器
 * @Author: wth
 * @Create: 2024/2/25 - 23:24
 */
public class DynamicFileGenerator {

    /**
     *
     * @param dataModel 数据模型
     * @throws IOException
     * @throws TemplateException
     */
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
        // 文件不存在则创建目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }
        // 设置输出路径
        FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
        Writer out = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        // 合并数据模型和模版
        template.process(dataModel, out);
        // 关闭流
        out.close();
    }
}
