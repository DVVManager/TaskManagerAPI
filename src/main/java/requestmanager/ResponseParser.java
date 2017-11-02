package requestmanager;


import models.*;

import com.jayway.restassured.response.Response;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

/**
 * Created by Administrator on 10/29/2017.
 */
public class ResponseParser<T extends Model> {

    public static String getResponseBodyAttribute(String attributeName, Response response){
        return response.getBody().jsonPath().getString(attributeName);
    }

    public static List<String> getResponseBodyAttributesList(String attributName,Response response) {
        List<String> resutlList=new ArrayList<>();
        JsonParser parser=new JsonParser();
        JsonElement jsonTree=parser.parse(response.body().asString());
        JsonArray array=jsonTree.getAsJsonArray();
        for (int i = 0; i <array.size() ; i++) {
            JsonObject object= (JsonObject) array.get(i);
            resutlList.add(object.get(attributName).getAsString());
        }

            return resutlList;
        }


    public static <T> List<T> getResponseAsObjectsListDynamic(Response response, Class<T[]> clazz) {
        T[] resultArray=new Gson().fromJson(response.getBody().asString(), clazz);
        return asList(resultArray);
    }

    public static List<Model> getResponseAsObjectsListCustomed(Response response, Class<? extends Model> clazz){
        List<Model> arrayList=null;
        if(clazz.getClass().equals(Item.class)){
            arrayList= asList(response.getBody().as(Item[].class));
        }else{
            arrayList= asList(response.getBody().as(User[].class));

        }
        return arrayList;
    }

    public static <T> T getResponseAsModel(Response response,String attribute,String value,Class <T> clazz){
        List<T> resutlList=new ArrayList<>();
        JsonParser parser=new JsonParser();
        Gson gsonParser=new GsonBuilder()
                .registerTypeAdapter(Item.class,new ItemDeserializer())
                .registerTypeAdapter(User.class,new UserDeserializer())
                .create();
        JsonElement jsonTree=parser.parse(response.body().asString());
        JsonArray array=jsonTree.getAsJsonArray();
        for (int i = 0; i <array.size() ; i++) {
            JsonObject object= array.get(i).getAsJsonObject();
            if(object.has(attribute) && object.getAsJsonPrimitive(attribute).toString().equals(value)){
                resutlList.add(new Gson().fromJson(object,clazz));
            }

        }
        return resutlList.get(0);
    }

    /* public static List<String> getResponseBodyAttributesListOld(String attributName,Response response) {
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
    }*/


}
