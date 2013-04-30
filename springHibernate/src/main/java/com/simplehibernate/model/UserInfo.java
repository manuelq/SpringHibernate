package com.simplehibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "userInfo")
public class UserInfo {

    
    private String domainName;
    
    private Boolean corpActive;
    
    private int tblCorporation_Id;
    @Id
    
    private int tblUser_Id;
    
    private Boolean active;
    
    private int admin;
    
    private String username;
    
    private Boolean ForcePasswordUpdate;
    
    private String nameFirst;
    
    private String nameLast;
    
    private int use_PT_CO_Ind;
    
    private int serviceLevel;

    @Column(name = "DomainName")
    public String getDomainName() {
        return domainName;
    }
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Column(name = "CorpActive")
    public Boolean getCorpActive() {
        return corpActive;
    }
    public void setCorpActive(Boolean corpActive) {
        this.corpActive = corpActive;
    }

    @Column(name = "tblCorporation_Id")
    public int getTblCorporation_Id() {
        return tblCorporation_Id;
    }
    public void setTblCorporation_Id(int tblCorporation_Id) {
        this.tblCorporation_Id = tblCorporation_Id;
    }

    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tblUser_Id")
    public int getUserId() {
        return tblUser_Id;
    }
    public void setUserId(int tblUser_Id) {
        this.tblUser_Id = tblUser_Id;
    }

    @Column(name = "Active")
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Column(name = "admin")
    public int getAdmin() {
        return admin;
    }
    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Column(name = "User_Id")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "ForcePasswordUpdate")
    public Boolean getForcePasswordUpdate() {
        return ForcePasswordUpdate;
    }
    public void setForcePasswordUpdate(Boolean forcePasswordUpdate) {
        ForcePasswordUpdate = forcePasswordUpdate;
    }

    @Column(name = "NameFirst")
    public String getFirstName() {
        return nameFirst;
    }
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    @Column(name = "NameLast")
    public String getLastName() {
        return nameLast;
    }
    public void setLastName(String nameLast) {
        this.nameLast = nameLast;
    }

    @Column(name = "Use_PT_CO_Ind")
    public int getUse_PT_CO_Ind() {
        return use_PT_CO_Ind;
    }
    public void setUse_PT_CO_Ind(int use_PT_CO_Ind) {
        this.use_PT_CO_Ind = use_PT_CO_Ind;
    }

    @Column(name = "getServiceLevel")
    public int getServiceLevel() {
        return serviceLevel;
    }
    public void setServiceLevel(int serviceLevel) {
        this.serviceLevel = serviceLevel;
    }
}