package requestmanager;

import com.jayway.restassured.response.Response;

import java.util.Collections;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;


/**
 * Created by Administrator on 10/29/2017.
 */
public class Request extends BaseRequest {


    public Response getMethod(PathType type){
        return getMethod(type, Collections.<String, String>emptyMap());
    }

    public Response getMethod(PathType type, Map<String,String> params){
        return given().contentType("application/json").spec(getRequestSpec())
                .log().all().when()
                .params(params)
                .get(type.getPath());
    }

    public Response postMethod(PathType type, Map<String,String> params){
        return given().spec(getRequestSpec())
                .log().all().when()
                .params(params)
                .post((type.getPath()));
    }

    public Response postMethod(PathType type, Class e){
        return given().spec(getRequestSpec())
                .log().all().body(e).when()
                .post((type.getPath()));
    }


}
