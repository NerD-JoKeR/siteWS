/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author keshpan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseMst", propOrder = {
    "country1",
    "country2",
    "country3",
    "insuranceProgramm",
    "beginDate",
    "endDate",
    "numberOfDays",
    "birthDate",
    "contactNumber",    
    "email",
    "result",
    "premKz",
    "kurs",
    "sumStrahKz",
    "premEur",
    "err"
})
public class ResponseMst {
    private String contactNumber;
    private String email;
    private String result; 
    private String premKz;
    private String kurs;
    private String sumStrahKz;
    private String premEur;
    private String err;
    private int country1;
    private int country2;
    private int country3;
    private int insuranceProgramm;
    private Date beginDate;
    private Date endDate;
    private Date birthDate;
    private int numberOfDays;

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPremKz() {
        return premKz;
    }

    public void setPremKz(String premKz) {
        this.premKz = premKz;
    }

    public String getKurs() {
        return kurs;
    }

    public void setKurs(String kurs) {
        this.kurs = kurs;
    }

    public String getSumStrahKz() {
        return sumStrahKz;
    }

    public void setSumStrahKz(String sumStrahKz) {
        this.sumStrahKz = sumStrahKz;
    }

    public String getPremEur() {
        return premEur;
    }

    public void setPremEur(String premEur) {
        this.premEur = premEur;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
    
    
    
    public int getCountry1() {
        return country1;
    }

    public void setCountry1(int country1) {
        this.country1 = country1;
    }

    public int getCountry2() {
        return country2;
    }

    public void setCountry2(int country2) {
        this.country2 = country2;
    }

    public int getCountry3() {
        return country3;
    }

    public void setCountry3(int country3) {
        this.country3 = country3;
    }

    public int getInsuranceProgramm() {
        return insuranceProgramm;
    }

    public void setInsuranceProgramm(int insuranceProgramm) {
        this.insuranceProgramm = insuranceProgramm;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
    
    
}
