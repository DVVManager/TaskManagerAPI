package requestmanager;

import com.jayway.restassured.response.Response;
import models.Item;
import models.Model;
import models.User;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
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
        ArrayList<String> attributeValues=new ArrayList<String>();
        try {
            jsonArray = new JSONArray(response.body().asString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                attributeValues.add(jsonArray.getJSONObject(i).getString(attributName));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return attributeValues;
    }

    public static List<Model> getRespnseObjectsList(Response response, Class<? extends Model> clazz){
        List<Model> arrayList=null;
        if(clazz.getName().contains("Item")){
            arrayList=Arrays.asList(response.getBody().as(Item[].class));
        }else{
            arrayList=Arrays.asList(response.getBody().as(User[].class));

        }
        return arrayList;
    }

    public static Class getModel(Class e){
        return e;
    }


}
