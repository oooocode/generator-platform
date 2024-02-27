package com.wth.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
 * 列出生成的文件列表
 * @Author: wth
 * @Create: 2024/2/27 - 21:53
 */
@Command(name = "list", description = "查看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{
    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "generator-platform-demo-projects/acm-template").getAbsolutePath();
        List<File> pathList = FileUtil.loopFiles(inputPath);
        pathList.forEach(System.out::println);
    }
}
