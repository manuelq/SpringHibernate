package com.simplehibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="spireCommonAccessionNumber")
public class SpireCommonAccessionNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="caccn")
    private String caccn;
    
    @ManyToOne()
    @JoinColumn(name="uaccn_id")
    private SpireAccessionNumberMap spireAccessionNumberMap;


    //@Override
    public Integer getId() {
        return id;
    }

    public SpireAccessionNumberMap getSpireAccessionNumberMap() {
		return spireAccessionNumberMap;
	}

	public void setSpireAccessionNumberMap(
			SpireAccessionNumberMap spireAccessionNumberMap) {
		this.spireAccessionNumberMap = spireAccessionNumberMap;
	}

	public void setId(Integer id) {
        this.id = id;
    }   

    public String getCommonAccessionNumber() {
        return caccn;
    }

    public void setCommonAccessionNumber(String caccn) {
        this.caccn = caccn;
    }


}