package requests;

import com.jayway.restassured.response.Response;

import java.util.Collections;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;


/**
 * Created by Administrator on 10/29/2017.
 */
public class Request extends BaseRequest {


    public Response getMethod(RequestType type){
        return getMethod(type, Collections.<String, String>emptyMap());
    }
    public Response getMethod(RequestType type, Map<String,String> params){
        return given().spec(getRequestSpec())
                .log().all().when()
                .params(params)
                .get(type.getPath());
    }

    public Response postMethod(RequestType type,Map<String,String> params){
        return given().spec(getRequestSpec())
                .log().all().when()
                .params(params)
                .post((type.getPath()));
    }

    public Response postMethod(RequestType type,Class e){
        return given().spec(getRequestSpec())
                .log().all().body(e).when()
                .post((type.getPath()));
    }


}
