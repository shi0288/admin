package com.xyauto.qa.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * Created by shiqm on 2017-10-30.
 */
public class UploadCons {


    public static JSONObject jsonObject;

    static {
        load("upload.json");
    }

    private static void load(String name) {
        InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(name);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String line = null;
        StringBuffer strObj = new StringBuffer();
        try {
            while ((line = br.readLine()) != null) {
                strObj.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        jsonObject = JSON.parseObject(strObj.toString());
    }



}
