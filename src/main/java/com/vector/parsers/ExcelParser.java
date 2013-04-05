package com.vector.parsers;

import com.vector.entity.Sample;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelParser {

    public void parse(final String fileName) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName));
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);

            for (Row row : sheet) {

            }
        }

    }

    private Sample createSample(Row row) {
        Sample sample = new Sample();

        sample.setNumber(Integer.valueOf(row.getCell(NUMBER).getStringCellValue()));
        sample.setBarCode(row.getCell(BAR_CODE).getStringCellValue());
        sample.setLocationOfSampling(row.getCell(LOCATION_OF_SAMPLING).getStringCellValue());

        sample.setGps(row.getCell(GPS).getStringCellValue());
        sample.setDateOfSampling(row.getCell(DATE_OF_SAMPLING).getDateCellValue());
        sample.setNameOfCollector(row.getCell(NAME_OF_COLLECTOR).getStringCellValue());

        sample.setSpeciesLatinName(row.getCell(SPECIES_LATIN_NAME).getStringCellValue().trim());
        sample.setTypeOfSample(row.getCell(TYPE_OF_SAMPLE).getStringCellValue());
        sample.setSampleCondition(row.getCell(SAMPLE_CONDITION).getStringCellValue());

        sample.setSampleCondition(row.getCell(SAMPLE_CONDITION).getStringCellValue());
        sample.setNumberOfAliquotes(Integer.valueOf(row.getCell(NUMBER_OF_ALIQUOTES).getStringCellValue()));
        sample.setVectorNumber(row.getCell(VECTOR_NUMBER).getStringCellValue());

        sample.setVirusTiterFirstPass(row.getCell(VIRUS_TITER_FIRST_PASS).getStringCellValue());
        sample.setVirusTiterSecondPass(row.getCell(VIRUS_TITER_SECOND_PASS).getStringCellValue());
        sample.setVirusTiterThirdPass(row.getCell(VIRUS_TITER_THIRD_PASS).getStringCellValue());

        sample.setInfluenzaPositive(row.getCell(INFLUENZA_POSITIVE).getStringCellValue());
        sample.setHaSubtypeH1Test(row.getCell(HA_SUBTYPE_H1_TEST).getStringCellValue());
        sample.setHaSubtypeSubtypingPCR(row.getCell(HA_SUBTYPE_SUBTYPING_PCR).getStringCellValue());

        sample.setHaSubtypeSequencing(row.getCell(HA_SUBTYPE_SEQUENCING).getStringCellValue());
        sample.setNaTypeSequencing(row.getCell(NA_TYPE_SEQUENCING).getStringCellValue());
        sample.setNdv(row.getCell(NA_TYPE_SEQUENCING).getStringCellValue());

        return sample;
    }

    private static final int NUMBER = 0;
    private static final int BAR_CODE = 1;
    private static final int LOCATION_OF_SAMPLING = 2;
    private static final int GPS = 3;
    private static final int DATE_OF_SAMPLING = 4;
    private static final int NAME_OF_COLLECTOR = 5;
    private static final int SPECIES_LATIN_NAME = 6;
    private static final int TYPE_OF_SAMPLE = 7;
    private static final int SAMPLE_CONDITION = 8;
    private static final int NUMBER_OF_ALIQUOTES = 9;
    private static final int VECTOR_NUMBER = 10;
    private static final int VIRUS_TITER_FIRST_PASS = 11;
    private static final int VIRUS_TITER_SECOND_PASS = 12;
    private static final int VIRUS_TITER_THIRD_PASS = 13;
    private static final int INFLUENZA_POSITIVE = 14;
    private static final int HA_SUBTYPE_H1_TEST = 15;
    private static final int HA_SUBTYPE_SUBTYPING_PCR = 16;
    private static final int HA_SUBTYPE_SEQUENCING = 17;
    private static final int NA_TYPE_SEQUENCING = 18;
    private static final int NDV = 19;

    public static void main(String[] args) throws Exception {

    }
}
