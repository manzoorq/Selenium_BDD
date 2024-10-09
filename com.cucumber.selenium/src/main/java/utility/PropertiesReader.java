package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	static Properties properties;
	public static void loadData(){
		try {
			properties = new Properties();
			File f = new File("./resources/config.properties");
			FileReader obj = new FileReader (f);
			properties.load(obj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getProp(String Data) {
		String data = properties.getProperty(Data);
		return data;
	}
}
