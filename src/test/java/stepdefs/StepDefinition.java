package stepdefs;

import com.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modules.CSVParser;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class StepDefinition {

    File responseFile;
    String userDataFile;
    List<Person> actualPersons;
    List<Person> expectedPersons;
    String baseUri;
    String listUsersPath;
    String listUserParamName;
    int listUserParamValue;
    Response response;
    RequestSpecification request;
    ResponseBody responseBody;
    ObjectMapper theObjectMapper = new ObjectMapper();

    //    ---------------------------
    File singleResponseFile;
    String userData1File;
    Person actualPerson;
    String singleUserPath;
    int userId;
//    --------------------------
    String createUserPath;
    Response createResponse;
    File createRequestFile;
    File createResponseFile;
    CreateResponseObject createResponseObject;
    CreateRequestObject createRequestObject;


    @Given("json file with filename {string}")
    public void getJsonFile(String jsonFileName) {

        responseFile = new File(jsonFileName);
    }

    @And("csv file with filename {string}")
    public void csvFileWithFilename(String csvFileName) {

        userData1File = csvFileName;
    }

    @When("parse json file and get actual user list")
    public void parseJsonFileAndGetActualUserList() {

        try {
            ResponseObject response = theObjectMapper.readValue(responseFile, ResponseObject.class);
            actualPersons = response.getData();
        } catch (FileNotFoundException aExp) {
            System.out.println("File is not there");
        } catch (IOException aExp) {
            System.out.println("IO exception");
        }
    }

    @And("parse csv file and get expected user list")
    public void parseCsvFileAndGetExpectedUserList() {

        CSVParser csvParser = new CSVParser();
        expectedPersons = csvParser.parsePersonFile(userData1File);

    }

    @Then("the user lists should be equal")
    public void theUserListsShouldBeEqual() {
        for (Person actualPerson : actualPersons) {
            boolean found = false;
            boolean personEqual = false;
            for (Person expectedPerson : expectedPersons) {
                if (actualPerson.getId().equals(expectedPerson.getId())) {
                    found = true;
                    if (!actualPerson.getEmail().equals(expectedPerson.getEmail())) {
                        personEqual = false;
                        break;
                    }
                    if (!actualPerson.getFirst_name().equals(expectedPerson.getFirst_name())) {
                        personEqual = false;
                        break;
                    }
                    if (!actualPerson.getLast_name().equals(expectedPerson.getLast_name())) {
                        personEqual = false;
                        break;
                    }
                    if (!actualPerson.getAvatar().equals(expectedPerson.getAvatar())) {
                        personEqual = false;
                        break;
                    }
                    personEqual = true;
                    break;
                }
            }
            Assert.assertTrue(found && personEqual);
        }
    }

    @Given("uri is {string}")
    public void uriIs(String uri) {
        baseUri = uri;
    }

    @And("path is {string}")
    public void pathIs(String path) {
        listUsersPath = path;
    }

    @When("get method is called")
    public void getMethodIsCalled() {
        request = RestAssured.given();
        request.baseUri(baseUri);
        request.basePath(listUsersPath);
        request.param(listUserParamName, listUserParamValue);
        response = request.get();
    }

    @Then("status is {int}")
    public void statusIs(int statusCode) {

//        ValidatableResponse validatableResponse = response.then();
//        validatableResponse.statusCode(statusCode);
        Assert.assertEquals(response.statusCode(), statusCode);
    }

    @And("users are parsed from the response to get actual user list")
    public void usersAreParsedFromTheResponseToGetActualUserList() {
        responseBody = response.getBody();
        responseBody.prettyPrint();

        try {
            ResponseObject response = theObjectMapper.readValue(responseBody.asString(), ResponseObject.class);
            actualPersons = response.getData();
        } catch (FileNotFoundException aExp) {
            System.out.println("File is not there");
        } catch (IOException aExp) {
            System.out.println("IO exception");
        }
        for (Person person : actualPersons) {
            System.out.println(person.getEmail());
        }
    }

    @And("param {string} is {int}")
    public void paramIs(String paramName, int paramValue) {
        listUserParamName = paramName;
        listUserParamValue = paramValue;
    }

    // S I N G L E   U S E R
    @Given("json file with single user filename {string}")
    public void jsonFileWithSingleUserFilename(String singleFileName) {
        singleResponseFile = new File(singleFileName);
    }

    @And("csv file with list of all twelve users filename {string}")
    public void csvFileWithListOfUsersFilename(String csvFile) {
        userData1File = csvFile;
    }

    @When("parse json file and get actual user")
    public void parseJsonFileAndGetActualUser() {
        ObjectMapper theObjectMapper = new ObjectMapper();
        try {
            SingleResponseObject singleResponse = theObjectMapper.readValue(singleResponseFile, SingleResponseObject.class);
            actualPerson = singleResponse.getData();
        } catch (FileNotFoundException aExp) {
            System.out.println("File is not there");
        } catch (IOException aExp) {
            System.out.println("IO exception");
        }
    }

    @Then("the expected user should be equal to actual user")
    public void theExpectedUserShouldBeEqualToActualUser() {

        boolean found = false;
        boolean personEqual = false;
        Person expectedPerson = null;
        for (Person personFromFile : expectedPersons) {
            if (Integer.parseInt(personFromFile.getId()) == userId) {
                found = true;
                expectedPerson = personFromFile;
                break;
            }
        }
        if (found) {
            if (!actualPerson.getEmail().equals(expectedPerson.getEmail())) {
                personEqual = false;
            }
            if (!actualPerson.getFirst_name().equals(expectedPerson.getFirst_name())) {
                personEqual = false;
            }
            if (!actualPerson.getLast_name().equals(expectedPerson.getLast_name())) {
                personEqual = false;
            }
            if (!actualPerson.getAvatar().equals(expectedPerson.getAvatar())) {
                personEqual = false;
            }
            if (!personEqual) {
                System.out.println("Person with id: " + actualPerson.getId() + " and firstname " + actualPerson.getFirst_name() + " are not equal");
            } else {
                System.out.println("Person with id: " + actualPerson.getId() + " and firstname " + actualPerson.getFirst_name() + " are equal");
            }
        } else {
            System.out.println("The test data does not have this user.");
        }

    }

    @And("path for single user is {string}")
    public void pathForSingleUserIs(String path) {
        singleUserPath = path;
//        System.out.println(path.split("/")[3]);
        userId = Integer.parseInt(path.split("/")[3]);

    }

    @When("get method is called for single user")
    public void getMethodIsCalledForSingleUser() {

        request = RestAssured.given();
        request.baseUri(baseUri);
        request.basePath(singleUserPath);
//        request.
//        request.param(listUserParamName,listUserParamValue);
        response = request.get();
    }

    @And("users are parsed from the response to get actual user")
    public void usersAreParsedFromTheResponseToGetActualUser() {
        responseBody = response.getBody();
        responseBody.prettyPrint();
        ObjectMapper theObjectMapper = new ObjectMapper();
        try {
            SingleResponseObject response = theObjectMapper.readValue(responseBody.asString(), SingleResponseObject.class);
            actualPerson = response.getData();
        } catch (FileNotFoundException aExp) {
            System.out.println("File is not there");
        } catch (IOException aExp) {
            System.out.println("IO exception");
        }
    }
//   C R E A T E   U S E R
    @And("path for create user is {string}")
    public void pathForCreateUserIs(String path) {
        createUserPath = path;
    }

    @When("post method is called for create user")
    public void postMethodIsCalledForCreateUser() {

        request = RestAssured.given();
        request.baseUri(baseUri);
        request.basePath(createUserPath);
        System.out.println(request.toString());
        request.contentType(ContentType.JSON);
        request.body(createRequestFile);
        createResponse = request.post();
        System.out.println(createResponse.prettyPrint());
    }

      @And("request file for createuser filename is {string}")
    public void requestFileWithTheUserToBeCreatedFilenameIs(String createReqFileName) {

        createRequestFile = new File(createReqFileName);
          ObjectMapper theObjectMapper = new ObjectMapper();
          try {
              createRequestObject = theObjectMapper.readValue(createRequestFile, CreateRequestObject.class);

          } catch (FileNotFoundException aExp) {
              System.out.println("File is not there");
          } catch (IOException aExp) {
              System.out.println("IO exception");
          }
      }

    @And("users are parsed from the response to get a created user")
    public void usersAreParsedFromTheResponseToGetACreatedUser() {
       responseBody = createResponse.getBody();
        ObjectMapper theObjectMapper = new ObjectMapper();
        try {
            createResponseObject = theObjectMapper.readValue(responseBody.asString(), CreateResponseObject.class);

        } catch (FileNotFoundException aExp) {
            System.out.println("File is not there");
        } catch (IOException aExp) {
            System.out.println("IO exception");
        }
    }

    @Then("statuscode for createUser is {int}")
    public void statuscodeForCreateUserIs(int statusCode) {
        Assert.assertEquals(createResponse.statusCode(),statusCode);
        
    }
    @And("response file for createuser filename is {string}")
    public void responseFileForCreateuserFilenameIs(String createResFileName) {
        createResponseFile = new File(createResFileName);
    }


    @And("the user in the response should matches the user in the request")
    public void theUserInTheResponseShouldMatchesTheUserInTheRequest() {
        Assert.assertEquals(createResponseObject.getName(),createRequestObject.getName());
        Assert.assertEquals(createResponseObject.getJob(),createRequestObject.getJob());
    }

}
