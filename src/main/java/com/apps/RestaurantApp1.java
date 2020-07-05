package com.apps;

import com.modules.FileHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RestaurantApp1 {
    public static void main(String[] args) {

        Properties props = new Properties();
        File aFile = new File("src\\main\\resources\\RestaurantTemplateFile.template");
        File bFile = new File("src\\main\\resources\\restaurantmenu.cfg");
        File cFile = new File("src\\main\\resources\\restaurantmenu.txt");
        List<String> lines = new ArrayList<String>();

        try {
            List<String> readLines = FileHelper.readFromFile(aFile);
            Reader areader = new FileReader(bFile);
            props.load(areader);

            for (String aLine : readLines) {
                aLine = aLine.replace("{{i}}", props.getProperty("Idly"));
                aLine = aLine.replace("{{v}}", props.getProperty("Vada"));
                aLine = aLine.replace("{{poo}}", props.getProperty("Poori"));
                aLine = aLine.replace("{{cb}}", props.getProperty("CholeBature"));
                aLine = aLine.replace("{{s}}", props.getProperty("Samosa"));
                aLine = aLine.replace("{{pp}}", props.getProperty("PaniPoori"));
                aLine = aLine.replace("{{md}}", props.getProperty("MasalaDosa"));
                aLine = aLine.replace("{{pd}}", props.getProperty("PlainDosa"));
                aLine = aLine.replace("{{kd}}", props.getProperty("KaramDosa"));
                aLine = aLine.replace("{{mb}}", props.getProperty("MirchiBajji"));
                aLine = aLine.replace("{{p}}", props.getProperty("Pakora"));

                lines.add(aLine);

                FileHelper.writeToFile(lines, cFile);
            }

        } catch (FileNotFoundException aExp) {
            System.out.println("File is not there");
        } catch (IOException aExp) {
            System.out.println("IOException");
        }
    }
}
