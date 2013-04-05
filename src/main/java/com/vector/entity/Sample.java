package com.vector.entity;

import com.vector.entity.dictionary.Species;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "SAMPLE")
public class Sample {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "BAR_CODE")
    private String barCode;

    @Column(name = "LOCATION_OF_SAMPLING")
    private String locationOfSampling;

    @Column(name = "GPS")
    private String gps;

    @Column(name = "DATE_OF_SAMPLING")
    private Date dateOfSampling;

    @Column(name = "NAME_OF_COLLECTOR")
    private String nameOfCollector;

    @ManyToOne(optional = false)
    private Species species;

    @Transient
    private String speciesLatinName;

    @Column(name = "TYPE_OF_SAMPLE")
    private String typeOfSample;

    @Column(name = "SAMPLE_CONDITION")
    private String sampleCondition;

    @Column(name = "NUMBER_OF_ALIQUOTES")
    private Integer numberOfAliquotes;

    @Column(name = "VECTOR_NUMBER")
    private String vectorNumber;

    @Column(name = "VIRUS_TITER_FIRST_PASS")
    private String virusTiterFirstPass;

    @Column(name = "VIRUS_TITER_SECOND_PASS")
    private String virusTiterSecondPass;

    @Column(name = "VIRUS_TITER_THIRD_PASS")
    private String virusTiterThirdPass;

    @Column(name = "INFLUENZA_POSITIVE")
    private String influenzaPositive;

    @Column(name = "HA_SUBTYPE_H1_TEST")
    private String haSubtypeH1Test;

    @Column(name = "HA_SUBTYPE_SUBTYPING_PCR")
    private String haSubtypeSubtypingPCR;

    @Column(name = "HA_SUBTYPE_SEQUENCING")
    private String haSubtypeSequencing;

    @Column(name = "NA_TYPE_SEQUENCING")
    private String naTypeSequencing;

    @Column(name = "NDV")
    private String ndv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getLocationOfSampling() {
        return locationOfSampling;
    }

    public void setLocationOfSampling(String locationOfSampling) {
        this.locationOfSampling = locationOfSampling;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Date getDateOfSampling() {
        return dateOfSampling;
    }

    public void setDateOfSampling(Date dateOfSampling) {
        this.dateOfSampling = dateOfSampling;
    }

    public String getNameOfCollector() {
        return nameOfCollector;
    }

    public void setNameOfCollector(String nameOfCollector) {
        this.nameOfCollector = nameOfCollector;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getTypeOfSample() {
        return typeOfSample;
    }

    public void setTypeOfSample(String typeOfSample) {
        this.typeOfSample = typeOfSample;
    }

    public String getSampleCondition() {
        return sampleCondition;
    }

    public void setSampleCondition(String sampleCondition) {
        this.sampleCondition = sampleCondition;
    }

    public Integer getNumberOfAliquotes() {
        return numberOfAliquotes;
    }

    public void setNumberOfAliquotes(Integer numberOfAliquotes) {
        this.numberOfAliquotes = numberOfAliquotes;
    }

    public String getVectorNumber() {
        return vectorNumber;
    }

    public void setVectorNumber(String vectorNumber) {
        this.vectorNumber = vectorNumber;
    }

    public String getVirusTiterFirstPass() {
        return virusTiterFirstPass;
    }

    public void setVirusTiterFirstPass(String virusTiterFirstPass) {
        this.virusTiterFirstPass = virusTiterFirstPass;
    }

    public String getVirusTiterSecondPass() {
        return virusTiterSecondPass;
    }

    public void setVirusTiterSecondPass(String virusTiterSecondPass) {
        this.virusTiterSecondPass = virusTiterSecondPass;
    }

    public String getVirusTiterThirdPass() {
        return virusTiterThirdPass;
    }

    public void setVirusTiterThirdPass(String virusTiterThirdPass) {
        this.virusTiterThirdPass = virusTiterThirdPass;
    }

    public String getInfluenzaPositive() {
        return influenzaPositive;
    }

    public void setInfluenzaPositive(String influenzaPositive) {
        this.influenzaPositive = influenzaPositive;
    }

    public String getHaSubtypeH1Test() {
        return haSubtypeH1Test;
    }

    public void setHaSubtypeH1Test(String haSubtypeH1Test) {
        this.haSubtypeH1Test = haSubtypeH1Test;
    }

    public String getHaSubtypeSubtypingPCR() {
        return haSubtypeSubtypingPCR;
    }

    public void setHaSubtypeSubtypingPCR(String haSubtypeSubtypingPCR) {
        this.haSubtypeSubtypingPCR = haSubtypeSubtypingPCR;
    }

    public String getHaSubtypeSequencing() {
        return haSubtypeSequencing;
    }

    public void setHaSubtypeSequencing(String haSubtypeSequencing) {
        this.haSubtypeSequencing = haSubtypeSequencing;
    }

    public String getNaTypeSequencing() {
        return naTypeSequencing;
    }

    public void setNaTypeSequencing(String naTypeSequencing) {
        this.naTypeSequencing = naTypeSequencing;
    }

    public String getNdv() {
        return ndv;
    }

    public void setNdv(String ndv) {
        this.ndv = ndv;
    }

    public String getSpeciesLatinName() {
        return speciesLatinName;
    }

    public void setSpeciesLatinName(String speciesLatinName) {
        this.speciesLatinName = speciesLatinName;
    }
}
