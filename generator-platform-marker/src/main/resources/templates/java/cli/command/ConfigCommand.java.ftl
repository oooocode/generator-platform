package ${basePackage}.cli.command;

import cn.hutool.core.util.ReflectUtil;
import ${basePackage}.model.DataModel;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

/**
 * @Author: ${author}
 * @Create: ${createTime}
 */
@Command(name = "config", description = "查看参数信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {
    @Override
    public void run() {
        Field[] fields = ReflectUtil.getFields(DataModel.class);
        System.out.println("******************************************");
        for (Field field : fields) {
            System.out.println("字段名: " + field.getName() + "\t字段类型: " + field.getType());
            System.out.println("******************************************");
        }
    }
}
