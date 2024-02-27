package com.wth.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.wth.generator.MainGenerator;
import com.wth.model.MainTemplateConfig;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;

/**
 * @Author: wth
 * @Create: 2024/2/27 - 21:54
 */
@Command(name = "generate", description = "生成代码文件", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Runnable {


    /**
     * 是否循环输出
     */
    @Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否循环", interactive = true, echo = true)
    private Boolean loop;

    /**
     * 作者
     */
    @Option(names = {"-a", "--author"}, arity = "0..1", description = "作者", interactive = true, echo = true)
    private String author;

    /**
     * 输出内容
     */
    @Option(names = {"-o", "--output"}, arity = "0..1", description = "输出信息", interactive = true, echo = true)
    private String outputText;


    @Override
    public void run() {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        try {
            MainGenerator.execute(mainTemplateConfig);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
