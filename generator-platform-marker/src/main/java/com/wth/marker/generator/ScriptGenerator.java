package com.wth.marker.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * @Author: wth
 * @Create: 2024/3/1 - 23:55
 */
public class ScriptGenerator {

    public static void doGenerate(String outputPath, String jarPath) {
        StringBuilder sb = new StringBuilder();

        // Linux 脚本
        // #!/bin/bash
        // java -jar target/generator-platform-basic-1.0-SNAPSHOT-jar-with-dependencies.jar "$@"
        sb.append("#!/bin/bash").append("\n");
        sb.append(String.format("java -jar %s \"$@\"", jarPath)).append("\n");
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath);
        // 添加可执行权限
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
        try {
            Files.setPosixFilePermissions(Paths.get(outputPath), permissions);
        } catch (Exception e) {
        }

        // Windows 脚本
        // @echo off
        // java -jar target/generator-platform-basic-1.0-SNAPSHOT-jar-with-dependencies.jar %*
        sb = new StringBuilder();
        sb.append("@echo off").append("\n");
        sb.append(String.format("java -jar %s  %%*", jarPath)).append("\n");
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath + ".bat");
    }

    public static void main(String[] args) {
        String outputPath = System.getProperty("user.dir") + File.separator + "generator";
        doGenerate(outputPath, "");
    }
}
