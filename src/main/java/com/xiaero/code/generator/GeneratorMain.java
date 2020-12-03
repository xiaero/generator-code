package com.xiaero.code.generator;

import cn.org.rapid_framework.generator.GeneratorProperties;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GeneratorMain {
    public static String basePackage = "com.jns.datacenter";

    public static void main(String[] args) {
        try {
            GeneratorMain.MeGeneratorProperties.load();
            Map<String, String> map = new HashMap<String, String>();
            map.put("gen_table_name", "graphic_live_content");
            Targets targets = new Targets(map);
            targets.generator();
            System.out.println("---------------------Generator executed SUCCESS---------------------");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private static class MeGeneratorProperties {
        private MeGeneratorProperties() {
        }

        public static void load() {
            try {
                GeneratorProperties.getProperties().put("generator_tools_class", "cn.org.rapid_framework.generator.util.StringHelper,org.apache.commons.lang.StringUtils");
                GeneratorProperties.getProperties().put("generator_sourceEncoding", "UTF-8");
                GeneratorProperties.getProperties().put("generator_outputEncoding", "UTF-8");
                GeneratorProperties.getProperties().put("gg_isOverride", "true");
                GeneratorProperties.load("classpath:gen_config.xml");
                GeneratorProperties.getProperties().put("basedir", new File("."));
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }
    }
}
