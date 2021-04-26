package com.modules;

import com.domain.Person;
import com.exceptions.NotAValidStringFormat;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public List<Person> parsePersonFile(String fileName) {

        String globalString =  null;
        File personFile = new File(fileName);
        List<Person> personList = new ArrayList<>();
        try {
            List<String> linesList = FileUtils.readLines(personFile, StandardCharsets.US_ASCII);

            for(int i=1;i<linesList.size();i++){
                globalString = linesList.get(i);
                try {
                    Person aPerson = convertData(globalString);
                    personList.add(aPerson);
                }catch (NotAValidStringFormat aExp){
                    System.out.println("entered an invalid string:: " + globalString);
                }
            }

        }catch(IOException e){
            System.out.println("bad file,give another file");
        }
        return personList;
    }

    private Person convertData(String personString) throws NotAValidStringFormat {

        Person aData = null;
        String[] dataSplits = personString.split(",");
        if (dataSplits.length == 5) {
            if (dataSplits[0] == null || dataSplits[0].isEmpty() ||
                    dataSplits[1] == null || dataSplits[1].isEmpty() ||
                    dataSplits[2] == null || dataSplits[2].isEmpty() ||
                    dataSplits[3] == null || dataSplits[3].isEmpty() ||
                    dataSplits[4] == null || dataSplits[4].isEmpty()) {
                throw new NotAValidStringFormat();
            }

            aData = new Person();
            aData.setId(dataSplits[0]);
            aData.setEmail(dataSplits[1]);
            aData.setFirst_name(dataSplits[2]);
            aData.setLast_name(dataSplits[3]);
            aData.setAvatar(dataSplits[4]);
        }else {
            throw new NotAValidStringFormat();
        }
        return aData;
    }
}

