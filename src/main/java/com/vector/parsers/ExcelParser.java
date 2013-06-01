package com.vector.parsers;

import com.vector.entity.Sample;
import com.vector.persistence.service.SampleCachingService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class ExcelParser {

    private static final Logger logger = LoggerFactory.getLogger(ExcelParser.class);

    @Resource
    private SampleCachingService sampleService;

    public void parse(final String fileName) throws IOException {
        logger.debug("Begin");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("parse");

        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName));
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            logger.info("Processing sheet '{}'", sheet.getSheetName());

            for (int r = sheet.getFirstRowNum() + 1/*skip header*/; r </*=  - skip last line too - summ*/ sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                Sample sample = createSample(row);
                if (sample != null) {
                    sampleService.persist(sample);
                    logger.debug("Processed {}", sample);
                }
            }
        }

        stopWatch.stop();
        logger.info("Finished excel parsing. {}", stopWatch);
    }

    private static Sample createSample(Row row) {
        Sample sample = new Sample();

        sample.setNumber(getIntCellValue(row, NUMBER));
        sample.setBarCode(getStringCellValue(row, BAR_CODE));
//        sample.setLocationOfSampling(getStringCellValue(row, LOCATION_OF_SAMPLING));
        sample.setLocationOfSampling(getStringCellValue(row, LOCATION_OF_SAMPLING_RUS));

        sample.setGps(getStringCellValue(row, GPS));

        Cell dateCell = row.getCell(DATE_OF_SAMPLING);
        if (dateCell != null) {
            sample.setDateOfSampling(dateCell.getDateCellValue());
        } else {
            logger.debug("Skipping unfinished {}, row num - {}", sample, row.getRowNum());
            return null;
        }

        sample.setNameOfCollector(getStringCellValue(row, NAME_OF_COLLECTOR));

        sample.setSpeciesLatinName(getStringCellValue(row, SPECIES_LATIN_NAME).trim());
        sample.setTypeOfSample(getStringCellValue(row, TYPE_OF_SAMPLE));
        sample.setSampleCondition(getStringCellValue(row, SAMPLE_CONDITION));

        sample.setSampleCondition(getStringCellValue(row, SAMPLE_CONDITION));
        sample.setNumberOfAliquotes(getIntCellValue(row, NUMBER_OF_ALIQUOTES));
        sample.setVectorNumber(getStringCellValue(row, VECTOR_NUMBER));

        sample.setVirusTiterFirstPass(getStringCellValue(row, VIRUS_TITER_FIRST_PASS));
        sample.setVirusTiterSecondPass(getStringCellValue(row, VIRUS_TITER_SECOND_PASS));
        sample.setVirusTiterThirdPass(getStringCellValue(row, VIRUS_TITER_THIRD_PASS));

        sample.setInfluenzaPositive(getStringCellValue(row, INFLUENZA_POSITIVE));
        sample.setHaSubtypeH1Test(getStringCellValue(row, HA_SUBTYPE_H1_TEST));
        sample.setHaSubtypeSubtypingPCR(getStringCellValue(row, HA_SUBTYPE_SUBTYPING_PCR));

        sample.setHaSubtypeSequencing(getStringCellValue(row, HA_SUBTYPE_SEQUENCING));
        sample.setNaTypeSequencing(getStringCellValue(row, NA_TYPE_SEQUENCING));
        sample.setNdv(getStringCellValue(row, NA_TYPE_SEQUENCING));

        return sample;
    }

    private static Integer getIntCellValue(Row row, final int cellNumber) {
        Cell cell = row.getCell(cellNumber);
        if (cell == null) {
            return null;
        } else {
            return Double.valueOf(row.getCell(cellNumber).getNumericCellValue()).intValue();
        }
    }

    private static String getStringCellValue(Row row, final int cellNumber) {
        Cell cell = row.getCell(cellNumber);
        if (cell == null) {
            return null;
        } else {
            return StringUtils.trimToNull(row.getCell(cellNumber).getStringCellValue());
        }
    }

    private static final int NUMBER = 0;
    private static final int BAR_CODE = 1;
    private static final int LOCATION_OF_SAMPLING = 2;
    private static final int LOCATION_OF_SAMPLING_RUS = 3;
    private static final int GPS = 4;
    private static final int DATE_OF_SAMPLING = 5;
    private static final int NAME_OF_COLLECTOR = 6;
    private static final int SPECIES_LATIN_NAME = 7;

    private static final int SPECIES_EN_NAME = 8;
    private static final int ORDER = 9;
    private static final int FAMILY = 10;

    private static final int TYPE_OF_SAMPLE = 11;
    private static final int SAMPLE_CONDITION = 12;
    private static final int NUMBER_OF_ALIQUOTES = 13;
    private static final int VECTOR_NUMBER = 14;
    private static final int VIRUS_TITER_FIRST_PASS = 15;
    private static final int VIRUS_TITER_SECOND_PASS = 16;
    private static final int VIRUS_TITER_THIRD_PASS = 17;
    private static final int INFLUENZA_POSITIVE = 18;
    private static final int HA_SUBTYPE_H1_TEST = 19;
    private static final int HA_SUBTYPE_SUBTYPING_PCR = 20;
    private static final int HA_SUBTYPE_SEQUENCING = 21;
    private static final int NA_TYPE_SEQUENCING = 22;
    private static final int NDV = 23;

    public static void main(String[] args) throws Exception {

    }
}
