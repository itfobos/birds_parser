package com.vector.entity.dictionary;

import javax.persistence.*;

public class Species {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME_ENG", nullable = false, unique = true)
    private String nameEng;

    @Column(name = "NAME_LAT", nullable = false, unique = true)
    private String nameLatin;

    @ManyToOne(fetch = FetchType.EAGER)
    private Family family;

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameLatin() {
        return nameLatin;
    }

    public void setNameLatin(String nameLatin) {
        this.nameLatin = nameLatin;
    }

    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", nameEng='" + nameEng + '\'' +
                ", nameLatin='" + nameLatin + '\'' +
                ", family=" + family +
                '}';
    }
}
