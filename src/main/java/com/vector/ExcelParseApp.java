package com.vector;

import com.vector.parsers.ExcelParser;
import com.vector.utils.AppInitHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelParseApp {
    public static void main(String[] args) throws Exception {
        AppInitHelper.initApp();
        final Logger logger = LoggerFactory.getLogger(ExcelParseApp.class);

        ExcelParser excelParser = AppInitHelper.getBean(ExcelParser.class);
        logger.info("File: 2007.xls");
        excelParser.parse("D:\\Docs\\Sonya_parser\\пробы_дополнено\\пробы\\2007.xls");

        logger.info("File: 2008.xls");
        excelParser.parse("D:\\Docs\\Sonya_parser\\пробы_дополнено\\пробы\\2008.xls");

        logger.info("File: 2009.xls");
        excelParser.parse("D:\\Docs\\Sonya_parser\\пробы_дополнено\\пробы\\2009.xls");

        logger.info("File: 2010.xls");
        excelParser.parse("D:\\Docs\\Sonya_parser\\пробы_дополнено\\пробы\\2010.xls");

        logger.info("File: 2011.xls");
        excelParser.parse("D:\\Docs\\Sonya_parser\\пробы_дополнено\\пробы\\2011.xls");

        logger.info("File: 2012.xls");
        excelParser.parse("D:\\Docs\\Sonya_parser\\пробы_дополнено\\пробы\\2012.xls");
    }
}
