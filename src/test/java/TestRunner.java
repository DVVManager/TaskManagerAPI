
import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestmanager.BaseRequest;
import requestmanager.Request;

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
        Response response=testReq.getMethod(BaseRequest.PathType.TASK);
        JSONArray jsonArray=null;
        try {
            jsonArray=new JSONArray(response.body().asString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <jsonArray.length() ; i++) {
            try {
                if(jsonArray.getJSONObject(i).getString("category").equals("1"))
                System.out.println( "RESPONSE OBJ: " +      jsonArray.getJSONObject(i).getString("title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


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
