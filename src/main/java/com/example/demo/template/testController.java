package com.example.demo.template;

import com.example.demo.shiro.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试Freemarker生成html
 */
@Controller
public class testController {

    // 1、从spring容器中获得FreeMarkerConfigurer对象。
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @GetMapping("/testFreeMarker")
    @ResponseBody
    public Map testFreeMarker( ModelMap modelMap) throws Exception {
        // 2、从FreeMarkerConfigurer对象中获得Configuration对象。
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        // 3、使用Configuration对象获得Template对象。
        Template template = configuration.getTemplate("receivables.html");
        // 4、创建数据集
        Map dataModel = new HashMap<>();
        User user = new User();
        user.setPassword("123456");
        user.setUsername("admin");
        dataModel.put("user", user);
        // 5、创建输出文件的Writer对象。
        Writer out = new FileWriter(new File("G:/spring-freemarker.html"));
        // 6、调用模板对象的process方法，生成文件。
        template.process(dataModel, out);
        // 7、关闭流。
        out.close();
        return dataModel;
    }

    @GetMapping("/onlineTest")
    @ResponseBody
    public Map onlineTest( ModelMap modelMap) throws Exception {
        //构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");//模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html");//模板文件后缀
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        //构造上下文(Model)
        Context context = new Context();
        context.setVariable("name", "蔬菜列表");
        context.setVariable("array", new String[]{"土豆", "番茄", "白菜", "芹菜"});

        //渲染模板
        FileWriter write = new FileWriter("result.html");
        templateEngine.process("example", context, write);

        return modelMap;
    }

}
