package com.vuebin.common.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * 代码生成器
 *
 * @author fengjiabin
 * @date 2019/6/11 14:32
 */
public class BinGenerator {

    public static void doGeneration(GenerateParam generateParam) {

        AutoGenerator mpg = new AutoGenerator();

//        全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generateParam.getOutputDirectory());
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setEnableCache(true);
        gc.setOpen(true);
        gc.setAuthor(generateParam.getAuthor());

//        自定义文件命名，注意 %s 会自动填充表实体属性！
        if (generateParam.getGeneratorInterface()) {
            gc.setServiceName("%sService");
            gc.setServiceImplName("%sServiceImpl");
        } else {
            gc.setServiceName("%sService");
            gc.setServiceImplName("%sService");
        }
        mpg.setGlobalConfig(gc);

//        数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(generateParam.getJdbcDriver());
        dsc.setUrl(generateParam.getJdbcUrl());
        dsc.setUsername(generateParam.getJdbcUserName());
        dsc.setPassword(generateParam.getJdbcPassword());
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true);

        // 此处可以移除表前缀表前缀
        strategy.setTablePrefix(generateParam.getRemoveTablePrefix());

        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        // 需要生成的表
        strategy.setInclude(generateParam.getIncludeTables());

        // 公共字段填充
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill("CREATE_TIME", FieldFill.INSERT));
        tableFills.add(new TableFill("UPDATE_TIME", FieldFill.UPDATE));
        tableFills.add(new TableFill("CREATE_USER", FieldFill.INSERT));
        tableFills.add(new TableFill("UPDATE_USER", FieldFill.UPDATE));
        strategy.setTableFillList(tableFills);

        mpg.setStrategy(strategy);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);

        if (!generateParam.getGeneratorInterface()) {
            tc.setService(null);
            tc.setServiceImpl("/templates/NoneInterfaceServiceImpl.java");
        }

        //如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(generateParam.getParentPackage());
        pc.setModuleName("");
        pc.setXml("mapper.mapping");

        if (generateParam.getGeneratorInterface()) {
            pc.setServiceImpl("service.impl");
            pc.setService("service");
        } else {
            pc.setServiceImpl("service");
            pc.setService("service");
        }

        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }

    public static void main(String[] args) {
        BinGenerator.doGeneration(new GenerateParam());
    }
}
