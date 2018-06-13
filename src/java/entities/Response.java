/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author keshpan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
    "premKz",
    "kurs",
    "sumStrahKz",
    "premEur",
    "err"
})
public class Response {
    private String premKz;
    private String kurs;
    private String sumStrahKz;
    private String premEur;
    private String err;

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
}
