package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	public static String getProperty(String key) {
		
		Properties properties = new Properties();
		File fis = new File(System.getProperty("user.dir")+"/config/config.properties");
		
		try {
			FileInputStream io = new FileInputStream(fis);
			properties.load(io);		
		} catch (FileNotFoundException e) {
			System.out.println("File not found at the above location" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Unable to read the file" + e.getMessage());
		}
		
		String prop = properties.getProperty(key);
		
		return prop;
	}

}
