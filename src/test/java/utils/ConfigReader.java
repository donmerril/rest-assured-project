package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties properties;
	
	
	 public ConfigReader() {
	        properties = new Properties();
	        try {
	            FileInputStream fis = new FileInputStream("src/test/resources/testdata.properties");
	            // above line reads the testdata.properties file in maven path and store it in fis object
	            properties.load(fis); // loads the key value pairs to the properties object
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public String getProperty(String key) {
	        return properties.getProperty(key);
	    }
	
	
}
