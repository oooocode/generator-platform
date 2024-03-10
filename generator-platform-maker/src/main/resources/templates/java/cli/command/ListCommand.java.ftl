package ${basePackage}.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
 * 列出生成的文件列表
 * @Author: ${author}
 * @Create: ${createTime}
 */
@Command(name = "list", description = "查看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {
    @Override
    public void run() {
        String inputPath = "${fileConfig.inputRootPath}";
        List<File> pathList = FileUtil.loopFiles(inputPath);
        pathList.forEach(System.out::println);
    }
}
