/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Utils.PropertyValues;
import entities.AdditionalCoverage;
import entities.Response;
import entities.ResponseAB;
import entities.ResponseAK;
import entities.ResponseAut;
import entities.ResponseMst;
import entities.ResponsePass;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlElement;
import oracle.jdbc.driver.OracleDriver;


/**
 *
 * @author keshpan
 */
public class MethodWS {
    //<------------------------------->
    //Операция веб-службы ОСРНС
    
    @WebMethod(operationName = "osrns")
    public Response osrns( 
            //We take webParams from site
            @XmlElement(required = true) @WebParam(name = "oked") int oked,
            @XmlElement(required = true) @WebParam(name = "fond") double yearFot,
            @XmlElement(required = true) @WebParam(name = "col_sotr") int col_sotr,
            
            //@XmlElement(required = true) @WebParam(name = "jurName") String jurName,
            //@XmlElement(required = true) @WebParam(name = "contactNumber") String contactNumber,
            //@XmlElement(required = true) @WebParam(name = "email") String email,
            //@XmlElement(required = true) @WebParam(name = "kpr") int kpr,
            //@XmlElement(required = true) @WebParam(name = "employeeNum") int employeeNum,
            
            @WebParam(name = "countDate") Date countDate) {
        // created new response
        Response response = new Response();
        try {
            
            DriverManager.registerDriver(new OracleDriver()); //oracle driver
            String url = "jdbc:oracle:thin:@10.0.0.10:1526:bsolife"; //connection to DB
            Connection conn = DriverManager.getConnection(url, "mlm", "mlm");
            //Connection conn = DriverManager.getConnection(PropertyValues.getPropertyValue("url_db"), PropertyValues.getPropertyValue("mlm"/*db_username*/), PropertyValues.getPropertyValue("mlm"/*db_password*/));
            String sql = "{ ? = call mlm.WEBSERVICE.calc_osrns(?,?,?,?,?) }"; // connected to webserevice and call method from LIC 
            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setInt(2, oked);
            callableStatement.setDouble(3, yearFot); 
            callableStatement.setInt(4, col_sotr); 
          
            
            callableStatement.registerOutParameter(1, java.sql.Types.DOUBLE);
            callableStatement.registerOutParameter(5, java.sql.Types.DOUBLE);
            callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);

            callableStatement.execute();
            //this is the main line to the return response
            response.setPremKz(callableStatement.getString(1));
            response.setSumStrahKz(String.valueOf(callableStatement.getDouble(5)));
            response.setErr(callableStatement.getString(6));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;

    }
    
    //<------------------------------->
    //Операция веб-службы МСТ
     
    @WebMethod(operationName = "mst")
    public ResponseMst mst(@XmlElement(required = true) @WebParam(name = "country1") int country1,
            @XmlElement(required = true) @WebParam(name = "country2") int country2,
            @XmlElement(required = true) @WebParam(name = "country3") int country3,
            @XmlElement(required = true) @WebParam(name = "insuranceProgramm") int insuranceProgramm,
            @XmlElement(required = true) @WebParam(name = "beginDate") Date beginDate,
            @XmlElement(required = true) @WebParam(name = "endDate") Date endDate,
            @XmlElement(required = true) @WebParam(name = "numberOfDays") int numberOfDays,
            @XmlElement(required = true) @WebParam(name = "birthDate") Date birthDate,
            @XmlElement(required = true) @WebParam(name = "contactNumber") String contactNumber,
            @XmlElement(required = true) @WebParam(name = "email") String email) {
        ResponseMst responseMst = new ResponseMst();            
        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection conn = DriverManager.getConnection(PropertyValues.getPropertyValue("url_db"), PropertyValues.getPropertyValue("db_username"), PropertyValues.getPropertyValue("db_password"));
            String sql = "{ ? = call WEBSERVICE.calc_mst(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setInt(2, insuranceProgramm);
            callableStatement.setDate(3, new java.sql.Date(beginDate.getTime()));
            callableStatement.setDate(4, new java.sql.Date(endDate.getTime()));
            callableStatement.setDate(5, new java.sql.Date(birthDate.getTime()));
            callableStatement.setInt(6, 0);//sum_strah 
            callableStatement.setInt(7, country1);
            callableStatement.setInt(8, country2);
            callableStatement.setInt(9, country3);
            callableStatement.setInt(10, numberOfDays);
            callableStatement.setInt(11, 30);//rprog_max_days
            callableStatement.setString(12, contactNumber);
            callableStatement.setString(13, email);
            
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(14, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(15, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(16, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(17, java.sql.Types.VARCHAR);

            callableStatement.execute();
            //this is the main line
            responseMst.setResult(callableStatement.getString(1));
            responseMst.setKurs(callableStatement.getString(14));
            responseMst.setSumStrahKz(callableStatement.getString(15));
            responseMst.setPremEur(callableStatement.getString(16));            
            responseMst.setErr(callableStatement.getString(17));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return responseMst;
    }
    
    //<------------------------------->
    //Операция веб-службы АВТОРИЗАЦИИ
    
    @WebMethod(operationName = "authorization")
    public ResponseAut authorization(
            
            @WebParam(name = "inn") String inn, 
            @WebParam(name = "password") String password) {
        //implementation code here:
        ResponseAut responseAut = new ResponseAut();
        try {
            
            DriverManager.registerDriver(new OracleDriver());
            String url = "jdbc:oracle:thin:@10.0.0.10:1526:bsolife";
            Connection conn = DriverManager.getConnection(url, "mlm", "mlm");
            String sql = "{ ? = call mlm.WEBSERVICE.kab_kln_authoriz(?,?,?,?,?) }";
            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setString(2, inn);
            callableStatement.setString(3, password);          
            
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);

          

            callableStatement.execute();
            //this is the main line
            responseAut.setResult(callableStatement.getString(1));
            responseAut.setFio(callableStatement.getString(4));
            responseAut.setPhone(callableStatement.getString(5));
            responseAut.setEmail(callableStatement.getString(6));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return responseAut;
    } 
    
    //<------------------------------->
    //Операция веб-службы СМЕНА ПАРОЛЯ
    
    @WebMethod(operationName = "changePass")
    public ResponsePass changePass( 
            
            @XmlElement(required = true) @WebParam(name = "inn") String inn,
            @XmlElement(required = true) @WebParam(name = "pass") String pass,
            @XmlElement(required = true) @WebParam(name = "oldPass") String oldPass) {

        ResponsePass responsePass = new ResponsePass();
        try {         
            DriverManager.registerDriver(new OracleDriver());
            String url = "jdbc:oracle:thin:@10.0.0.10:1526:bsolife";
            Connection conn = DriverManager.getConnection(url, "mlm", "mlm");
            String sql = "{ ? = call mlm.WEBSERVICE.kab_kln_pass(?,?,?) }";
            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setString(2, inn);
            callableStatement.setString(3, pass);
            callableStatement.setString(4, oldPass);
                    
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            
            callableStatement.execute();
            //this is the main line
            responsePass.setResult(callableStatement.getString(1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return responsePass;
    } 
    
    //<------------------------------->
    //Операция веб-службы АЗИЯ КАЗЫНА
   
    @WebMethod(operationName = "asiaKazyna")
    public ResponseAK asiaKazyna(@XmlElement(required = true) @WebParam(name = "birthDate") Date birthDate,
            @XmlElement(required = true) @WebParam(name = "sex") String sex,
            @WebParam(name = "fullName") String fullName,
            @WebParam(name = "phone") String phone,
            @WebParam(name = "email") String email,
            @WebParam(name = "countDate") Date countDate,
            @XmlElement(required = true) @WebParam(name = "insuranceTerm") int insuranceTerm,
            @XmlElement(required = true) @WebParam(name = "periodicity") String periodicity,
            @XmlElement(required = true) @WebParam(name = "strahSumma") String strahSumma,
            @XmlElement(required = true) @WebParam(name = "strahVznos") String strahVznos,
            @WebParam(name = "nsDeath") AdditionalCoverage nsDeath,
            @WebParam(name = "trauma") AdditionalCoverage trauma,
            @WebParam(name = "tempDisability") AdditionalCoverage tempDisability,
            @WebParam(name = "nsHospital") AdditionalCoverage nsHospital,
            @WebParam(name = "nsDisability") AdditionalCoverage nsDisability,
            @WebParam(name = "criticalDisease") AdditionalCoverage criticalDisease,
            @WebParam(name = "nsPaymentExemption") AdditionalCoverage nsPaymentExemption) {
        ResponseAK responseAK = new ResponseAK();
        return responseAK;
    }
    
    //<------------------------------->
    //Операция веб-службы АЗИЯ БОЛАШАК
    
    @WebMethod(operationName = "asiaBolashak")
    public ResponseAB asiaBolashak(@XmlElement(required = true) @WebParam(name = "birthDate") Date birthDate,
            @XmlElement(required = true) @WebParam(name = "sex") String sex,
            @WebParam(name = "fullName") String fullName,
            @WebParam(name = "phone") String phone,
            @WebParam(name = "email") String email,
            @WebParam(name = "countDate") Date countDate,
            @XmlElement(required = true) @WebParam(name = "insuranceTerm") int insuranceTerm,
            @XmlElement(required = true) @WebParam(name = "periodicity") String periodicity,
            @XmlElement(required = true) @WebParam(name = "strahSumma") String strahSumma,
            @XmlElement(required = true) @WebParam(name = "strahVznos") String strahVznos,
            @WebParam(name = "nsDeath") AdditionalCoverage nsDeath,
            @WebParam(name = "trauma") AdditionalCoverage trauma,
            @WebParam(name = "tempDisability") AdditionalCoverage tempDisability,
            @WebParam(name = "nsHospital") AdditionalCoverage nsHospital,
            @WebParam(name = "nsDisability") AdditionalCoverage nsDisability,
            @WebParam(name = "criticalDisease") AdditionalCoverage criticalDisease,
            @WebParam(name = "nsPaymentExemption") AdditionalCoverage nsPaymentExemption,
            @WebParam(name = "nsDeathDisease") AdditionalCoverage nsDeathDisease,
            @WebParam(name = "nsTrauma") AdditionalCoverage nsTrauma,
            @WebParam(name = "paymentExemptionDeath") AdditionalCoverage paymentExemptionDeath,
            @WebParam(name = "strahBirthDate") Date strahBirthDate,
            @WebParam(name = "strahSex") String strahSex
    ) {
        ResponseAB responseAB = new ResponseAB();
        return responseAB;

    }
}
