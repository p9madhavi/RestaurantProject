package com.tests;

import com.domain.Person;
import com.domain.ResponseObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modules.CSVParser;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestAPIProject {

    CSVParser csvParser=null;

    @BeforeTest
    public void setUp(){

        csvParser = new CSVParser();
    }
    @Test
    public void testCSVParser(){
        List<Person> personList = csvParser.parsePersonFile("src\\main\\resources\\userData.csv");

        Assert.assertEquals(personList.size(), 6);
        for (Person person : personList){
            System.out.println(person.toString());
        }
    }

    @Test
    public void testJsonParser(){
        File responseFile = new File("src\\main\\resources\\response.json");
        List<String> lines = new ArrayList<>();
        ObjectMapper theObjectMapper = new ObjectMapper();
        try {
            ResponseObject response = theObjectMapper.readValue(responseFile, ResponseObject.class);
            Assert.assertEquals(response.getData().size(), 6);
            for (Person person : response.getData()){
                System.out.println(person.toString());
            }
        }catch (IOException exp){
            System.out.println("IO exception");
        }
    }
}
