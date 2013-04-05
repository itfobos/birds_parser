package com.vector.entity.dictionary;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "SPECIES")
public class Species implements Dictionary, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME_ENG", nullable = false, unique = true)
    private String name;

    @Column(name = "NAME_LAT", nullable = false, unique = true)
    private String nameLatin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Family family;

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String nameEng) {
        this.name = nameEng;
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
                ", nameEng='" + name + '\'' +
                ", nameLatin='" + nameLatin + '\'' +
                ", family=" + family +
                '}';
    }
}
