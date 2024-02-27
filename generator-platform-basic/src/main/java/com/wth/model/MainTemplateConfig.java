package com.wth.model;

import lombok.Data;

/**
 * 动态模版数据模型
 * @Author: wth
 * @Create: 2024/2/25 - 23:23
 */
@Data
public class MainTemplateConfig {

    /**
     * 作者
     */
    private String author = "ikun";

    /**
     * 输出内容
     */
    private String outputText = "输出内容";

    /**
     * 是否循环输出
     */
    private Boolean loop;
}
