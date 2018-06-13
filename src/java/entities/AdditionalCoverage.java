package entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author keshpan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalCoverage", propOrder = {
    "strahSumma",
    "strahVznos"
})
public class AdditionalCoverage {
    private String strahSumma;
    private String strahVznos;

    public String getStrahSumma() {
        return strahSumma;
    }

    public void setStrahSumma(String strahSumma) {
        this.strahSumma = strahSumma;
    }

    
    
    public String getStrahVznos() {
        return strahVznos;
    }

    public void setStrahVznos(String strahVznos) {
        this.strahVznos = strahVznos;
    }
    
}
