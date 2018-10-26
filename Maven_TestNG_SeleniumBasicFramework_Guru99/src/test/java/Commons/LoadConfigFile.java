package Commons;

import java.io.*;
import java.util.Properties;

public class LoadConfigFile {
     static Properties prop;
     BufferedReader reader;

    /*public  Properties loadPropertiesFile(String propertyFilePath) throws FileNotFoundException {

        reader = new BufferedReader(new FileReader(propertyFilePath));
        prop = new Properties();
        try {
            prop.load(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }*/

    public static Properties loadPropertiesFile(String propertyFilePath) {

        prop = new Properties();

        InputStream in = null;
        try {
            in = new FileInputStream(new File(propertyFilePath));
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }


}
