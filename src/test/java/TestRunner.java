
import com.jayway.restassured.response.Response;
import models.Item;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestmanager.BaseRequest;
import requestmanager.Request;
import requestmanager.ResponseParser;


import java.util.HashMap;
import java.util.List;



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
       /* Response response=testReq.getMethod(BaseRequest.PathType.TASK);
        List<Item> list=ResponseParser.getResponseObjectsListDynamic(response,Item[].class);
        list.stream().filter((s)->s.getCategory()==1).forEach(s-> System.out.println(s));*/

        /*Response response=testReq.getMethod(BaseRequest.PathType.TASK);
        List<String> list=ResponseParser.getResponseBodyAttributesList("title",response);
        System.out.println(list);*/

        Response response=testReq.getMethod(BaseRequest.PathType.TASK);

        System.out.println(ResponseParser.getResponseAsModel(response,Item[].class,"id",9763431).toString());
    }

    @Test
    public void createNewTask(){
        HashMap <String,String> testData=new  HashMap();
        testData.put("title","NewTask4");
        testData.put("category","1");
        Response response=testReq.postMethod(BaseRequest.PathType.TASK,testData);
        System.out.println( "RESPONSE: " +      response.asString());
        //response.jsonPath().getList()
    }
}
