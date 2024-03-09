package com.wth.marker.generator.file;

import com.wth.marker.meta.Meta;
import com.wth.marker.meta.MetaManager;
import com.wth.marker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Author: wth
 * @Create: 2024/2/25 - 23:47
 */
public class MainGenerator {

    public static void execute(DataModel model) throws TemplateException, IOException {

        String inputRootPath = "D:\\workspace\\generator-platform\\generator-platform-marker";
        String outputRootPath = "generated";


        String inputPath;
        String outputPath;
        boolean needGit = model.needGit;
        boolean loop = model.loop;
        String author = model.mainTemplate.author;
        String outputText = model.mainTemplate.outputText;
        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
        DynamicFileGenerator.doGenerate(inputPath, outputPath, model);

        if (needGit) {
            inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
            outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
            StaticFileGenerator.copyFileByHutool(inputPath, outputPath);

            inputPath = new File(inputRootPath, "src/com/wth/acm/MainTemplate.java.ftl").getAbsolutePath();
            outputPath = new File(outputRootPath, "src/com/wth/acm/MainTemplate.java").getAbsolutePath();
            StaticFileGenerator.copyFileByHutool(inputPath, outputPath);
        }

    }

}
