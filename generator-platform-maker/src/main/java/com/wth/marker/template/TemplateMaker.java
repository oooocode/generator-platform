package com.wth.marker.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.wth.marker.meta.Meta;
import com.wth.marker.meta.enums.FileGenerateTypeEnum;
import com.wth.marker.meta.enums.FileTypeEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wth
 * @Create: 2024/3/10 - 15:27
 */
public class TemplateMaker {


    public static void main(String[] args) {
        // 指定原始项目路径
        String projectPath = System.getProperty("user.dir");
        String originSourceRootPath = new File(projectPath).getParentFile() + File.separator + "generator-platform-demo-projects/acm-template";

        // 复制目录
        long id = IdUtil.getSnowflakeNextId();
        String tempDirPath = projectPath + File.separator + ".temp";
        String templatePath = tempDirPath + File.separator + id;
        if (!FileUtil.exist(templatePath)) {
            FileUtil.mkdir(tempDirPath);
        }
        FileUtil.copy(originSourceRootPath, templatePath, true);

        // 一、 输入信息
        // 1. 项目基础信息
        String name = "acm-template-pro-generator";
        String description = "ACM 示例模板生成器";
        // 2. 模板文件路径
        // 要挖坑的项目根目录
        String sourceRootPath = templatePath + File.separator + FileUtil.getLastPathEle(Paths.get(originSourceRootPath)).toString();
        sourceRootPath = sourceRootPath.replaceAll( "\\\\", "/");
        // 要挖坑的文件
        String fileInputPath = "/src/com/wth/acm/MainTemplate.java";
        String fileOutputPath = fileInputPath + ".ftl";
        // 3. 输入模板文件信息
        Meta.ModelConfig.ModelInfo modelInfo = new Meta.ModelConfig.ModelInfo();
        modelInfo.setFieldName("outputText");
        modelInfo.setType("String");
        modelInfo.setDefaultValue("sum = ");


        // 二、使用字符串替换，生成模版文件
        String fileInputAbsPath = sourceRootPath + File.separator + fileInputPath;
        String fileContent = FileUtil.readUtf8String(fileInputAbsPath);

        String replacement = String.format("${%s}", modelInfo.getFieldName());
        String newFileContent = StrUtil.replace(fileContent, "Sum: ", replacement);
        String fileOutputAbsPath = sourceRootPath + File.separator + fileOutputPath;
        FileUtil.writeUtf8String(newFileContent, fileOutputAbsPath);

        // 三、生成配置文件
        // 模型生成路径
        String metaOutputPath = sourceRootPath + File.separator + "meta.json";

        Meta meta = new Meta();
        meta.setName(name);
        meta.setDescription(description);
        // 文件配置信息
        Meta.FileConfig fileConfig = new Meta.FileConfig();
        meta.setFileConfig(fileConfig);
        fileConfig.setSourceRootPath(sourceRootPath);
        List<Meta.FileConfig.FileInfo> fileInfoList = new ArrayList<>();
        fileConfig.setFiles(fileInfoList);

        Meta.FileConfig.FileInfo fileInfo = new Meta.FileConfig.FileInfo();
        fileInfo.setInputPath(fileInputPath);
        fileInfo.setOutputPath(fileOutputPath);
        fileInfo.setType(FileTypeEnum.FILE.getValue());
        fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
        fileInfoList.add(fileInfo);

        // 数据模型信息
        Meta.ModelConfig modelConfig = new Meta.ModelConfig();
        meta.setModelConfig(modelConfig);
        List<Meta.ModelConfig.ModelInfo> modelInfoList = new ArrayList<>();
        modelConfig.setModels(modelInfoList);
        modelInfoList.add(modelInfo);

        String metaJsonStr = JSONUtil.toJsonPrettyStr(meta);
        FileUtil.writeUtf8String(metaJsonStr, metaOutputPath);

    }
}
