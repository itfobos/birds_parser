package com.vector.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "MY_TEST_TABLE")
public class Test {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "TEST_NAME")
    private String testName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
