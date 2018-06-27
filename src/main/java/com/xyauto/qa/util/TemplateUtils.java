package com.xyauto.qa.util;

import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.ViewKeyCons;
import com.xyauto.qa.exceptions.HtmlTransException;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shiqm on 2017/3/17.
 */
@Component
public class TemplateUtils {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public String parse(ViewKeyCons viewKeyCons, Object value) {
        if (value == null) {
            throw new HtmlTransException("获取数据为空!");
        }
        Map map = new HashMap<>();
        map.put(viewKeyCons.getKey(), value);
        try {
            Template template = freeMarkerConfigurer.getConfiguration()
                    .getTemplate(CommonCons.Freemarker_Flag.FILE_PREFIX +
                            viewKeyCons.getViewName() +
                            CommonCons.Freemarker_Flag.FILE_SUFFIX);
            Writer out = new StringWriter();
            template.process(map, out);
            out.flush();
            return out.toString();
        } catch (Exception e) {
            throw new HtmlTransException("数据转换失败!");
        }
    }
    
    public String parse(ViewKeyCons viewKeyCons, Map map) {
        if (map == null) {
            throw new HtmlTransException("获取数据为空!");
        }
        try {
            Template template = freeMarkerConfigurer.getConfiguration()
                    .getTemplate(CommonCons.Freemarker_Flag.FILE_PREFIX +
                            viewKeyCons.getViewName() +
                            CommonCons.Freemarker_Flag.FILE_SUFFIX);
            Writer out = new StringWriter();
            template.process(map, out);
            out.flush();
            return out.toString();
        } catch (Exception e) {
            throw new HtmlTransException("数据转换失败!");
        }
    }


}
