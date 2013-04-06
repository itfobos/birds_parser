package com.vector;

import com.vector.parsers.ExcelParser;
import com.vector.utils.AppInitHelper;

public class ExcelParseApp {

    public static void main(String[] args) throws Exception {
        AppInitHelper.initApp();

        ExcelParser excelParser = AppInitHelper.getBean(ExcelParser.class);
        excelParser.parse("D:\\Docs\\Sonya_parser\\Стандартный протокол_1.xls");
    }
}
