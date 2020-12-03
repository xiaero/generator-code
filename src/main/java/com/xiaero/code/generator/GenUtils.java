package com.xiaero.code.generator;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.ext.tableconfig.model.TableConfig;
import cn.org.rapid_framework.generator.ext.tableconfig.model.TableConfigSet;
import cn.org.rapid_framework.generator.provider.db.sql.model.Sql;
import cn.org.rapid_framework.generator.util.BeanHelper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GenUtils {
    public static void genByTableConfigSet(GeneratorFacade generatorFacade, TableConfigSet tableConfigSet) throws Exception {
        Map map = new HashMap();
        map.putAll(BeanHelper.describe(tableConfigSet));
        map.put("tableConfigSet", tableConfigSet);
        generatorFacade.generateByMap(map);
    }

    public static void genByTableConfig(GeneratorFacade generatorFacade, TableConfigSet tableConfigSet, String tableSqlName) throws Exception {
        Collection<TableConfig> tableConfigs = Helper.getTableConfigs(tableConfigSet, tableSqlName);

        for (TableConfig tableConfig : tableConfigs) {
            Map map = new HashMap();
            String[] ignoreProperties = new String[]{"sqls"};
            map.putAll(BeanHelper.describe(tableConfig, ignoreProperties));
            map.put("tableConfig", tableConfig);
            generatorFacade.generateByMap(map);
        }

    }

    public static void genByOperation(GeneratorFacade generatorFacade, TableConfigSet tableConfigSet, String tableSqlName) throws Exception {
        Collection<TableConfig> tableConfigs = Helper.getTableConfigs(tableConfigSet, tableSqlName);

        for (TableConfig tableConfig : tableConfigs) {

            for (Sql sql : tableConfig.getSqls()) {
                Map operationMap = new HashMap();
                operationMap.putAll(BeanHelper.describe(sql));
                operationMap.put("sql", sql);
                operationMap.put("tableConfig", tableConfig);
                operationMap.put("basepackage", GeneratorMain.basePackage);
                generatorFacade.generateByMap(operationMap);
            }
        }

    }

    public static void genByTable(GeneratorFacade generatorFacade, String... tableSqlNames) throws Exception {
        generatorFacade.generateByTable(tableSqlNames);
    }
}
