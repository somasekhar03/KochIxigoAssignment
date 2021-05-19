package helperMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertFileHandler {

	
	
	public Properties getPropertyValues() {
		String filePath = System.getProperty("user.dir")+"/config.properties";
		Properties prop = new Properties();
		try {
			FileInputStream fin = new FileInputStream(filePath);
			prop.load(fin);
			
		}catch(IOException e) {
			
		}
		return prop;
		
	}
	
	
}
