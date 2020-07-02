package com.apps;

import com.modules.FileHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RestaurantApp {

    public static void main(String[] args) {
        Properties props = new Properties();
        File aFile = new File("src\\main\\resources\\restaurantmenu.cfg");
        File RestMenuFile = new File("src\\main\\resources\\restaurant.txt");
        List<String> restLines = new ArrayList<String >();
        try {
            Reader aReader = new FileReader(aFile);
            props.load(aReader);

            restLines.add(":::::::::::::::::::::::::::::::::::");
            restLines.add(":::::::::::::M E N U:::::::::::::::");
            restLines.add("\tIdly\t\t\t" + props.getProperty("Idly"));
            restLines.add("\tVada\t\t\t" +props.getProperty("Vada"));
            restLines.add("\tPoori\t\t\t" +props.getProperty("Poori"));
            restLines.add("\tCholeBature\t\t" +props.getProperty("CholeBature"));
            restLines.add("\tSamosa\t\t\t" +props.getProperty("Samosa"));
            restLines.add("\tPaniPoori\t\t" +props.getProperty("PaniPoori"));
            restLines.add("\tMasalaDosa\t\t" +props.getProperty("MasalaDosa"));
            restLines.add("\tPlainDosa\t\t" +props.getProperty("PlainDosa"));
            restLines.add("\tKaramDosa\t\t" + props.getProperty("KaramDosa"));
            restLines.add("\tMirchiBajji\t\t" +props.getProperty("MirchiBajji"));
            restLines.add("\tPakora\t\t\t" + props.getProperty("Pakora"));
            restLines.add(":::::::::::::::::::::::::::::::::::");
            restLines.add(":::::::::::::::::::::::::::::::::::");

            FileHelper.writeLines(restLines,RestMenuFile);
            String masalaDosa =props.getProperty("MasalaDosa");
            System.out.println("The Price for MasalaDosa:: " + masalaDosa);

            String choleBature = props.getProperty("CholeBature");
            System.out.println("The Price for CholeBature:: " + choleBature);

            String mirchiBajji = props.getProperty("MirchiBajji");
            System.out.println("The Price for MirchiBajji:: " + mirchiBajji);

        }catch (FileNotFoundException aExp){
            System.out.println("File is not there");
        }catch (IOException aExp){
            System.out.println("IO exception");
        }


    }
}
