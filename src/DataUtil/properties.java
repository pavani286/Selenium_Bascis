package DataUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class properties {
	public static Properties prop;
	public static Properties init_prop() {
		try {
			prop = new Properties();
			
				FileInputStream ip = new FileInputStream("/Users/pavanivemula/Documents/Workspace/Selenium_Bascis/src/Config/Config.properties");
				prop.load(ip);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return prop;
    }
}
