package com.vector.site_parser;

import com.vector.entity.dictionary.Family;
import com.vector.entity.dictionary.Order;
import com.vector.entity.dictionary.Species;
import com.vector.persistence.service.PersistFamilyCachedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileParser {
    private static final Logger logger = LoggerFactory.getLogger(FileParser.class);

    private static final String ORDER_PREFIX = "${order}";
    private static final String FAMILY_PREFIX = "${family}";
    public static final int SPECIES_NAMES_NUMBER = 2;

    private Order currentOrder;
    private Family currentFamily;

    //TODO:
    private PersistFamilyCachedService familyCachedService;


    public void parse(final String fileName) {

        try (BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName),
                        "UTF-16LE"))
        ) {
            for (String currentLine = fileReader.readLine(); currentLine != null; currentLine = fileReader.readLine()) {
                if (currentLine.contains(ORDER_PREFIX)) {
                    parseOrder(currentLine);
                } else if (currentLine.contains(FAMILY_PREFIX)) {
                    parseFamily(currentLine);
                } else {
                    parseSpecies(currentLine);
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("File '{}' not found.", fileName);
        } catch (IOException e) {
            logger.error("IO problem.", e);
        }
    }

    private void parseSpecies(final String currentLine) {
        String[] names = currentLine.split("\t");

        if (names.length != SPECIES_NAMES_NUMBER) {
            logger.error("Wrong species line format: '{}'", currentLine);
            return;
        }

        Species species = new Species();
        species.setNameLatin(names[0]);
        species.setNameEng(names[1]);
        species.setFamily(currentFamily);

        logger.debug(species.toString());
        //TODO: add saving
    }

    private void parseOrder(final String currentLine) {
        Order order = new Order();
        order.setName(extractEntityName(currentLine, ORDER_PREFIX));

        currentOrder = order;

        logger.debug("Current order is:" + currentOrder);
        //TODO: add saving

    }

    private void parseFamily(final String currentLine) {
        Family family = new Family();
        family.setName(extractEntityName(currentLine, FAMILY_PREFIX));
        family.setOrder(currentOrder);

        currentFamily = family;

        logger.debug("Current family is:" + currentOrder);
        //TODO: add saving

    }

    private static String extractEntityName(final String currentLine, final String prefix) {
        return currentLine.substring(currentLine.indexOf(prefix) + prefix.length()).trim();
    }
}
