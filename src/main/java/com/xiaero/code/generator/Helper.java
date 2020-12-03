package com.xiaero.code.generator;


import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.ext.tableconfig.model.TableConfig;
import cn.org.rapid_framework.generator.ext.tableconfig.model.TableConfigSet;

import java.io.File;
import java.util.*;

public class Helper {
    public static List<String> getTableConfigFiles(File basedir) {
        String[] tableConfigFilesArray = basedir.list();
        List<String> result = new ArrayList<String>();
        String[] var3 = tableConfigFilesArray;
        assert tableConfigFilesArray != null;
        int var4 = Objects.requireNonNull(tableConfigFilesArray).length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String str = var3[var5];
            if (str.endsWith(".xml")) {
                result.add(str);
            }
        }

        return result;
    }

    public static Collection<TableConfig> getTableConfigs(TableConfigSet tableConfigSet, String tableSqlName) {
        if ("*".equals(tableSqlName)) {
            return tableConfigSet.getTableConfigs();
        } else {
            TableConfig tableConfig = tableConfigSet.getBySqlName(tableSqlName);
            if (tableConfig == null) {
                throw new RuntimeException("根据tableName:${tableSqlName}没有找到相应的配置文件");
            } else {
                return Collections.singletonList(tableConfig);
            }
        }
    }

    public static GeneratorFacade createGeneratorFacade(String outRootDir, String... templateRootDirs) {
        if (templateRootDirs == null) {
            throw new IllegalArgumentException("templateRootDirs must be not null");
        } else if (outRootDir == null) {
            throw new IllegalArgumentException("outRootDir must be not null");
        } else {
            GeneratorFacade gf = new GeneratorFacade();
            gf.getGenerator().setTemplateRootDirs(templateRootDirs);
            gf.getGenerator().setOutRootDir(outRootDir);
            return gf;
        }
    }
}

