package com.wth.marker.generator;

import cn.hutool.core.util.StrUtil;
import java.io.*;

/**
 * @Author: wth
 * @Create: 2024/3/1 - 23:08
 */
public class JarGenerator {

    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;

        ProcessBuilder processBuilder = new ProcessBuilder(StrUtil.split(mavenCommand, " "));
        processBuilder.directory(new File(projectDir));

        Process process = processBuilder.start();

        // 读取命令行输出
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("命令执行结束, 结束码: " + exitCode);
    }

}
