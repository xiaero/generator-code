package com.xiaero.code.generator;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;
import cn.org.rapid_framework.generator.util.StringHelper;
import cn.org.rapid_framework.generator.util.SystemHelper;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Targets extends HashMap {
    public Targets() {
        Map map = new HashMap();
        map.put("gen_table_name", "*");
        this.init(map);
    }

    public Targets(Map map) {
        this.init(map);
    }

    private void init(Map map) {
        if (map != null && !map.isEmpty()) {
            this.putAll(map);
        }

        Properties generatorProperties = GeneratorProperties.getProperties();
        Enumeration generatorEnumerations = generatorProperties.propertyNames();

        while(generatorEnumerations.hasMoreElements()) {
            Object propertyName = generatorEnumerations.nextElement();
            Object propertyValue = generatorProperties.get(propertyName);
            this.put(propertyName, propertyValue);
        }

    }

    public void table() {
        String gen_table_name = this.get("gen_table_name").toString();
        String dir_out_root = this.get("dir_out_root").toString();
        String dir_templates_root = this.get("dir_templates_root").toString();

        try {
            GenUtils.genByTable(Helper.createGeneratorFacade(dir_out_root, dir_templates_root + "/table/dalgen_config", dir_templates_root + "/share/dal"), StringHelper.tokenizeToStringArray(gen_table_name, ","));
            if (SystemHelper.isWindowsOS) {
                Runtime.getRuntime().exec("cmd.exe /c start " + dir_out_root);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void generator() {
        String gen_table_name = this.get("gen_table_name").toString();
        String pathorg = this.getClass().getClassLoader().getResource("").toString();
        System.out.println(pathorg);
        String dir_out_root = this.get("dir_out_root").toString();
        String dir_templates_root = this.get("dir_templates_root").toString();
        GeneratorProperties.setProperty("basepackage", GeneratorMain.basePackage);
        GeneratorFacade gf = new GeneratorFacade();
        gf.getGenerator().setOutRootDir(dir_out_root);
        gf.getGenerator().setTemplateRootDirs(new File(pathorg + "/share/basic"), new File(pathorg + "/table/mybatis_plus"), new File(pathorg + "/table/vue_springmvc_rest"));

        try {
            gf.deleteOutRootDir();
            GenUtils.genByTable(gf, StringHelper.tokenizeToStringArray(gen_table_name, ","));
            if (SystemHelper.isWindowsOS) {
                Runtime.getRuntime().exec("cmd.exe /c start " + dir_out_root);
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }
}
