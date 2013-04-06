package com.vector;

import com.vector.parsers.FileParser;
import com.vector.utils.AppInitHelper;

public class FileParserApp {

    public static void main(String[] args) throws Exception {
        AppInitHelper.initApp();

        FileParser fileParser = AppInitHelper.getBean(FileParser.class);
//        fileParser.parse("D:\\Docs\\Sonya_parser\\Sources\\birds_parser\\src\\main\\resources\\data\\birds_short.txt");
        fileParser.parse("D:\\Docs\\Sonya_parser\\Sources\\birds_parser\\src\\main\\resources\\data\\birds.txt");
    }
}
