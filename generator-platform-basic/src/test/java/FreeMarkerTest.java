import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import javax.xml.soap.Detail;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: wth
 * @Create: 2024/2/25 - 22:14
 */
public class FreeMarkerTest {

    @Test
    public void testFreeMarker() throws IOException, TemplateException {
        // 配置对象，参数为版本号
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        // 设置模版文件所在路径
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        // 设置字符集
        cfg.setDefaultEncoding("UTF-8");
        cfg.setNumberFormat("0.######");
        // 数据模型
        HashMap<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 2024);

        // 菜单1
        ArrayList<HashMap<String, Object>> menuItems = new ArrayList<>();
        HashMap<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "1234");
        menuItem1.put("label", "标签1");
        // 菜单2
        HashMap<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url", "12345");
        menuItem2.put("label", "标签2");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        dataModel.put("menuItems", menuItems);
        // 获取模版
        Template template = cfg.getTemplate("myweb.html.ftl");
        // 设置输出路径
        String outputRoot = "src/main/resources/output/";
        Writer out = new FileWriter(outputRoot + "myweb.html");
        // 合并数据模型和模版
        template.process(dataModel, out);
        // 关闭流
        out.close();
    }
}
