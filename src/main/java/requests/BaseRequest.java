package requests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Administrator on 10/29/2017.
 */
public class BaseRequest {

    private static String session_id;
    private static final String API_KEY="7af146e0431e4fb1ba3d879a864b7276";
    private static final String PASSWORD="P110892DVV";
    private static final String LOGIN="dvvmanager";
    private static final String BASE_HOST="https://hitask.com/api";
    private static RequestSpecification requestSpec;

    static {
        RestAssured.baseURI = BASE_HOST;
        authenticate();
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addParam("session_id", session_id);
        requestSpec = builder.build();

    }

    public static void authenticate(){
        session_id= given().log().all().when()
                .queryParam("api_key",API_KEY)
                .queryParam("login",LOGIN)
                .queryParam("password",PASSWORD)
                .get(RequestType.AUTH.getPath()).getBody().jsonPath().getString("session_id");
    }

    public String getSessionId(){
        if(session_id==null){
            authenticate();
        }
        return session_id;
    }

    public static RequestSpecification getRequestSpec() {
        return requestSpec;
    }

    public enum RequestType {
        AUTH("/user/authenticate"),USER("/user"), TASK("/item");
        private String path;

        private RequestType(String path) {
            this.path = path;
        }
        public String getPath(){
            return path;
        }
    }




}
