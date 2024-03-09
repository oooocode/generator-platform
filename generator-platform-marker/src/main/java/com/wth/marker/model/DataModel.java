package com.wth.marker.model;

import lombok.Data;

/**
 * 动态模版数据模型
 * @Author: wth
 * @Create: 2024/2/25 - 23:23
 */
@Data
public class DataModel {
    /**
     * 是否生成 .gitignore 文件
     */
    public boolean needGit = false;
    /**
     * 是否生成循环
     */
    public boolean loop = false;

    public MainTemplate mainTemplate = new MainTemplate();
    /**
     * 用于生成核心模板文件
     */
    @Data
    public static class MainTemplate {

        /**
         * 作者注释
         */
        public String author = "yupi";

        /**
         * 输出信息
         */
        public String outputText = "sum = ";
    }
}
