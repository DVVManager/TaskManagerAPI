import com.google.gson.JsonArray;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requests.BaseRequest;
import requests.Request;

import java.util.HashMap;


import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Administrator on 10/28/2017.
 */
public class TestRunner {

    Request testReq;

    @BeforeClass
    public  void setup() {
        testReq=new Request();
        System.out.println(testReq.getSessionId());
    }

    @Test
    public void getTasksList(){
        Response response=testReq.getMethod(BaseRequest.RequestType.TASK);
        System.out.println( "RESPONSE: " +      response.asString());
    }

    @Test
    public void createNewTask(){
        HashMap <String,String> testData=new  HashMap();
        testData.put("title","NewTask4");
        testData.put("category","1");
        Response response=testReq.postMethod(BaseRequest.RequestType.TASK,testData);
        System.out.println( "RESPONSE: " +      response.asString());
        //response.jsonPath().getList()
    }
}
