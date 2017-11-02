package requestmanager;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.path.json.JsonPath;
import models.Item;
import models.Model;
import models.User;

import com.jayway.restassured.response.Response;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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


    public static <T> List<T> getResponseObjectsListDynamic(Response response, Class<T[]> clazz) {
        T[] resultArray=new Gson().fromJson(response.getBody().asString(), clazz);
        return asList(resultArray);
    }

    public static List<Model> getResponseObjectsListMain(Response response, Class<? extends Model> clazz){
        List<Model> arrayList=null;
        if(clazz.getClass().equals(Item.class)){
            arrayList= asList(response.getBody().as(Item[].class));
        }else{
            arrayList= asList(response.getBody().as(User[].class));

        }
        return arrayList;
    }

    public static <T> T getResponseAsModel(Response response,Class <T[]> clazz,String attribute,int value){
        List<String> resutlList=new ArrayList<>();
        JsonParser parser=new JsonParser();
        JsonElement jsonTree=parser.parse(response.body().asString());
        JsonArray array=jsonTree.getAsJsonArray();
        for (int i = 0; i <array.size() ; i++) {
            JsonObject object= (JsonObject) array.get(i);
            if(object.get(attribute).getAsInt()==value)
            resutlList.add(object.toString());
        }




        return null;
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
