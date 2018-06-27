package com.xyauto.qa.core.conver;

import com.xyauto.qa.cons.ConvertCons;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * Created by shiqm on 2017-05-03.
 */
public class MyConver implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (null != arguments && 2 == arguments.size()) {
            String key = String.valueOf(arguments.get(0));
            try {
                switch (key) {
                    case "source":
                        return ConvertCons.ResourceCover.get(Integer.parseInt(String.valueOf(arguments.get(1))));
                    case "userType":
                        return ConvertCons.UserTypeCover.get(Integer.parseInt(String.valueOf(arguments.get(1))));
                    case "logModule":
                        return ConvertCons.LogModuleCover.get(Integer.parseInt(String.valueOf(arguments.get(1))));
                    case "logAction":
                        return ConvertCons.LogActionCover.get(Integer.parseInt(String.valueOf(arguments.get(1))));
                    default:
                        return null;
                }
            }catch (Exception e){
                return null;
            }
        } else {
            return null;
        }
    }
}
