package com.apps;

import com.domain.CreateRequestObject;
import com.domain.CreateResponseObject;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateUserApp {

    public static void main(String[] args) {

        File createResponseFile = new File("src\\main\\resources\\createResponse.json");
        File createRequestFile = new File("src\\main\\resources\\createRequest.json");
        ObjectMapper theObjectMapper = new ObjectMapper();
        try{
            CreateRequestObject request= theObjectMapper.readValue(createRequestFile,CreateRequestObject.class);
            CreateResponseObject createResponse = theObjectMapper.readValue(createResponseFile,CreateResponseObject.class);
            // file to object --- deserialization
            // object ---- file  --- serialization
            // POJO -- plain old java object
            // Object class is mother of all classes

            // File --- classs
            // createResponseFile -- is an object of File class

            // DOM --- html dom --- VB Script microsoft Document "Object"
            // POM -- maven -- project object module --- "object"
           if(request.getName().equals(createResponse.getName()) ||
                   request.getJob().equals(createResponse.getJob())){
               System.out.println("the user is created with the given name and job ");
           }else{
               System.out.println("invalid name and job");
           }

        }catch(FileNotFoundException aExp){
            System.out.println("File is not there");
        }catch(IOException aExp){
            System.out.println("IO exception");
        }

}
}
