package com.vector.site_parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileParser {
    private static final Logger logger = LoggerFactory.getLogger(FileParser.class);

    public void parse(final String fileName) {

        try (Reader fileReader = new BufferedReader(new FileReader(fileName))) {

        } catch (FileNotFoundException e) {
            logger.error("File '{}' not found.", fileName);
        } catch (IOException e) {
            logger.error("IO problem.", e);
        }
    }

    public static void main(String[] args) {

    }
}
