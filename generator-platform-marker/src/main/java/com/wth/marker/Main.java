package com.wth.marker;


import com.wth.marker.generator.main.CodeGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @Author: wth
 * @Create: 2024/2/27 - 22:23
 */
public class Main {


    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.doGenerate();
    }
}
