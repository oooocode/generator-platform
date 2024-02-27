package com.wth.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.wth.model.MainTemplateConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

/**
 * @Author: wth
 * @Create: 2024/2/27 - 21:54
 */
@Command(name = "config", description = "查看参数信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {
    @Override
    public void run() {
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        System.out.println("******************************************");
        for (Field field : fields) {
            System.out.println("字段名: " + field.getName() + "\t字段类型: " + field.getType());
            System.out.println("******************************************");
        }
    }
}
