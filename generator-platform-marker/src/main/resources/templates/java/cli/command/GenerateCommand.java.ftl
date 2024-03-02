package ${basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.generator.MainGenerator;
import ${basePackage}.model.DataModel;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;

/**
 * @Author: ${author}
 * @Create: ${createTime}
 */
@Command(name = "generate", description = "生成代码文件", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Runnable {

<#list modelConfig.models as model>
    /**
     * ${model.description}
     */
    @Option(names = {<#if model.abbr??>"-${model.abbr}", </#if>"--${model.fieldName}"}, arity = "0..1", description = "${model.description}", interactive = true, echo = true)
    private ${model.type} ${model.fieldName}<#if model.defaultValue??> = ${model.defaultValue?c}</#if>;
</#list>


    @Override
    public void run() {
        DataModel mainTemplateConfig = new DataModel();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        try {
            MainGenerator.execute(mainTemplateConfig);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
