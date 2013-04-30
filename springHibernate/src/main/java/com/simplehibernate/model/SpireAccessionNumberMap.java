package com.simplehibernate.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="spireAccessionNumberMap")
public class SpireAccessionNumberMap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    @Column(name="uaccn")
    private Integer uaccn;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,mappedBy="spireAccessionNumberMap")
    private List<SpireCommonAccessionNumber> commonAccessionNumbers = new ArrayList<SpireCommonAccessionNumber>();

    public SpireAccessionNumberMap() {
    }

    public SpireAccessionNumberMap(Integer uniqueAccn) {
        this.uaccn = uniqueAccn;
    }


    //@Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }   

    public Integer getUniqueAccessionNumber() {
        return uaccn;
    }

    public void setUniqueAccessionNumber(Integer uaccn) {
        this.uaccn = uaccn;
    }

    public List<SpireCommonAccessionNumber> getCommonAccessionNumbers() {
        return commonAccessionNumbers;
    }

    @PrePersist
    @PreUpdate 
    public void prePersist() {
        Iterator<SpireCommonAccessionNumber> i = this.commonAccessionNumbers.iterator();
        SpireCommonAccessionNumber commonAccessionNumber;
        while(i.hasNext()) {
            commonAccessionNumber = i.next();
            commonAccessionNumber.setSpireAccessionNumberMap(this);         
        }
        System.out.println("pre persist running");
        this.setUniqueAccessionNumber(123);
    }

}