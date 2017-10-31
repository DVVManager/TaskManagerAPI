package requestmanager;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 10/29/2017.
 */
public class ResponseParser {

    public static String getResponseBodyAttribute(String attributeName, Response response){
        return response.getBody().jsonPath().getString(attributeName);
    }
    public static List<String> getResponseBodyAttributesList(String attributName,Response response) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(response.body().asString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                    System.out.println("RESPONSE OBJ: " + jsonArray.getJSONObject(i).getString(attributName));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static Class getModel(Class e){
        return e;
    }


}
