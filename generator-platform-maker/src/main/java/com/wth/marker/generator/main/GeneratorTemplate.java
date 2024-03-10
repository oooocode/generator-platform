package com.wth.marker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.wth.marker.generator.JarGenerator;
import com.wth.marker.generator.ScriptGenerator;
import com.wth.marker.generator.file.DynamicFileGenerator;
import com.wth.marker.meta.Meta;
import com.wth.marker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 代码生成模板
 * @Author: wth
 * @Create: 2024/3/6 - 23:45
 */
public abstract class GeneratorTemplate {

    /**
     * 生成代码
     * @throws TemplateException
     * @throws IOException
     * @throws InterruptedException
     */
    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();

        // 输出的根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 1. 复制源代码文件
        String sourceOutputPath = copySource(meta, outputPath);

        // 2. 复制静态文件
        generateCode(meta, outputPath);

        // 3. 构建 Jar 包
        String jarPath = buildJar(outputPath, meta);

        // 4. 封装脚本
        String shellOutputPath = buildScript(outputPath, jarPath);

        // 5. 生成精简版代码生成器
        buildDist(outputPath, sourceOutputPath, shellOutputPath, jarPath);

    }

    protected String buildScript(String outputPath, String jarPath) {
        String scriptOutputPath = outputPath + File.separator + "generator";
        ScriptGenerator.doGenerate(scriptOutputPath, jarPath);
        return scriptOutputPath;
    }

    protected String buildJar(String outputPath, Meta meta) throws IOException, InterruptedException {
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        return "target/" + jarName;
    }

    protected void generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        // com.wth
        String outputBasePackage = meta.getBasePackage();
        // com/wth
        String outputBasePackagePath = StrUtil.join(File.separator, StrUtil.split(outputBasePackage, "."));
        // generator/src/main/java/com/wth/
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        // DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + "/pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // README.md 文件
        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
        outputFilePath = outputPath + "/README.md";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
    }

    protected String copySource(Meta meta, String outputPath) {
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceOutputPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, sourceOutputPath, false);
        return sourceOutputPath;
    }

    protected void buildDist(String outputPath, String sourceOutputPath, String scriptOutputPath, String jarPath) {
        String distOutputPath = outputPath +"-dist";
        // - jar包
        String outputTargetPath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(outputTargetPath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, outputTargetPath, true);
        // - 脚本文件
        FileUtil.copy(scriptOutputPath, distOutputPath,true);
        FileUtil.copy(scriptOutputPath + ".bat", distOutputPath,true);
        // - .source文件
        FileUtil.copy(sourceOutputPath, distOutputPath, true);
    }

}
