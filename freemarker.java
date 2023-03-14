import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerConverter {

    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(FreemarkerConverter.class, "/");
        Template template = cfg.getTemplate("example.ftl");
        Map<String, Object> model = new HashMap<>();
        model.put("name", "John Doe");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        System.out.println(text);
    }
}
