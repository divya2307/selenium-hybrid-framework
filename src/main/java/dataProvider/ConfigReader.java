package dataProvider;

import java.io.IOException;
import java.util.Properties;

import java.io.InputStream;
/***
 * It first checks system properties passed through Maven using -D, and if no override is present, it reads from config.properties.
 * This makes the framework more CI/CD friendly because environment, browser, and headless mode can be changed at runtime without modifying code or config files.”
 */


public class ConfigReader {
	
	public static String getProperty(String key) {
		
		String systemProperty = System.getProperty(key);

	    if (systemProperty != null) {
	        return systemProperty;
	    }
		
		Properties properties = new Properties();
		
		try {
			InputStream inputStream = ConfigReader.class
					.getClassLoader()
					.getResourceAsStream("config/config.properties");
			
			if(inputStream == null) {
				throw new RuntimeException("config.properties file not found in src/main/resources/config");
			}
			properties.load(inputStream);	
			
		} catch (IOException e) {
			System.out.println("Unable to read the file" + e.getMessage());
		}
		
		String prop = properties.getProperty(key);
		
		return prop;
	}

}
