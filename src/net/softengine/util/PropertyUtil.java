package net.softengine.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Copyright &copy; 2017-2018 Soft Engine Inc.
 * <p>
 * Original author: Khomeni
 * Date: 18/12/2017 2:57 PM
 * Last modification by: Khomeni: Khomeni
 * Last modification on 18/12/2017: 18/12/2017 2:57 PM
 * Current revision: 1.0.0: 1.1 $
 * <p>
 * Revision History:
 * ------------------
 */

public class PropertyUtil {
    // public final static String DOC_DRIVE;
    // public final static String PATH_AVATAR;
    private static String fileName = "config";

    public PropertyUtil(String fileName) {
        this.fileName = fileName;
    }

    public static PropertyUtil getInstance(String fileName) {
        return new PropertyUtil(fileName);
    }


   /* static {
        Properties p = new Properties();
        InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            p.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DOC_DRIVE = p.getProperty("legal.doc.drive");
        PATH_AVATAR = p.getProperty("legal.doc.path");
    }*/


    public String getPropertyValue(String propName) {
        Properties prop = new Properties();
        InputStream input = null;
        String propValue = "";
        try {

            // String filename = "config.properties";
            input = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName+".properties");
            if(input==null){
                return "Sorry, unable to find ";

            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            propValue = prop.getProperty(propName);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return propValue;

    }

    public static void main(String[] args) {
        String s =  PropertyUtil.getInstance("config").getPropertyValue("LEGAL_DOC_PATH");
        System.out.println("s = " + s);

    }
}
