package com.wth.marker.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * 静态文件生成器
 * @Author: wth
 * @Create: 2024/2/25 - 0:25
 */
public class StaticFileGenerator {

    /**
     * 复制文件或目录
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFileByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, true);
    }

}
