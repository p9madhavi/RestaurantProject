package com.apps;

import com.domain.Person;
import com.domain.ResponseObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modules.CSVParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserApp {

    public static void main(String[] args) {

        File responseFile = new File("src\\main\\resources\\response.json");
        String userDataFile = "src\\main\\resources\\userData.csv";
        List<String> lines = new ArrayList<>();
        CSVParser csvParser = new CSVParser();
        ObjectMapper theObjectMapper = new ObjectMapper();
        try{
             ResponseObject response = theObjectMapper.readValue(responseFile, ResponseObject.class);
             List<Person> actualPersons = response.getData();
             List<Person> expectedPersons = csvParser.parsePersonFile(userDataFile);
             for (Person actualPerson : actualPersons){
                 boolean found = false;
                 boolean personEqual = false;
                 for (Person expectedPerson : expectedPersons){
                     if(actualPerson.getId().equals(expectedPerson.getId())){
                         found = true;
                         if(!actualPerson.getEmail().equals(expectedPerson.getEmail())){
                             personEqual =false;
                             break;
                         }
                         if(!actualPerson.getFirst_name().equals(expectedPerson.getFirst_name())){
                             personEqual =false;
                             break;
                         }
                         if(!actualPerson.getLast_name().equals(expectedPerson.getLast_name())){
                             personEqual =false;
                             break;
                         }
                         if(!actualPerson.getAvatar().equals(expectedPerson.getAvatar())){
                             personEqual =false;
                             break;
                         }
                         personEqual =true;
                         break;
                     }
                 }
                 if(found && personEqual){
                     System.out.println("Person with id: "+ actualPerson.getId()+ " and firstname "+actualPerson.getFirst_name()+" are equal");
                 }else {
                     System.out.println("Person with id: "+ actualPerson.getId()+ " and firstname "+actualPerson.getFirst_name()+" are not equal");
                 }
             }

        }catch(FileNotFoundException aExp){
        System.out.println("File is not there");
        }catch(IOException aExp){
        System.out.println("IO exception");
        }



    }
}
