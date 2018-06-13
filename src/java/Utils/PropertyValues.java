/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author keshpan
 */
public class PropertyValues {
     public static String getPropertyValue (String key) {
        String value = "";
        Properties prop = new Properties();
	InputStream input = null;
	try {

		input = new FileInputStream("config.properties");

		// load a properties file
		prop.load(input);
                value = prop.getProperty(key);
                		

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 
        return value;
    }
}
