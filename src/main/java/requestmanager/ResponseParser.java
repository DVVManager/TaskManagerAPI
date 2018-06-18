package requestmanager;



import models.*;

import com.jayway.restassured.response.Response;
import com.google.gson.*;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

/**
 * Created by Administrator on 10/29/2017.
 */
public class ResponseParser {

    public static String getResponseBodyAttribute(String attributeName, Response response){
        return response.getBody().jsonPath().getString(attributeName);
    }

    public static List<String> getResponseBodyAttributesList(String attributeName,Response response) {
        List<String> resultList=new ArrayList<>();
        JsonParser parser=new JsonParser();
        JsonElement jsonTree=parser.parse(response.body().asString());
        JsonArray array=jsonTree.getAsJsonArray();
        for (int i = 0; i <array.size() ; i++) {
            JsonObject object= (JsonObject) array.get(i);
            resultList.add(object.get(attributeName).getAsString());
        }

            return resultList;
        }

    public static <T> List<T> getResponseAsObjectsListDynamic(Response response, Class<T[]> clazz) {
        T[] resultArray=new Gson().fromJson(response.getBody().asString(), clazz);
        return asList(resultArray);
    }


    public static Model getResponseAsModel(Response response,String attribute,String value,Class <? extends Model> clazz){
        List<Model> resultList=new ArrayList<>();
        JsonParser parser=new JsonParser();
        Gson gsonParser=new GsonBuilder()
                //.registerTypeAdapterFactory(new NullStringAdapterFactory())
                .registerTypeAdapter(Item.class,new ItemDeserializer())
                .registerTypeAdapter(User.class,new UserDeserializer())
                .create();
        JsonElement jsonTree=parser.parse(response.body().asString());
        JsonArray array=jsonTree.getAsJsonArray();
        for (int i = 0; i <array.size() ; i++) {
            JsonObject object= array.get(i).getAsJsonObject();
            if(object.has(attribute) && object.getAsJsonPrimitive(attribute).getAsString().equals(value)){
                resultList.add(gsonParser.fromJson(object,clazz));
            }

        }
        return resultList.get(0);
    }

    public static class ItemDeserializer implements JsonDeserializer<Item> {
        @Override
        public Item deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Item itemObject=new Item();
            itemObject.setId(jsonObject.get("id").getAsInt());
            itemObject.setCategory(jsonObject.get("category").getAsInt());
            itemObject.setUser_id(jsonObject.get("user_id").getAsInt());
            itemObject.setTitle(jsonObject.get("title").getAsString());
            String shortName= (String)ObjectUtils.defaultIfNull(jsonObject.get("short_name"),"Nullable");
            itemObject.setShort_name(shortName);


            return itemObject;
        }
    }

    public static class UserDeserializer implements JsonDeserializer<User> {
        @Override
        public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            User userObject=new User();
            userObject.setId(jsonObject.get("id").getAsInt());
            userObject.setAccountType(jsonObject.get("accountType").getAsString());
            userObject.setEmailConfirmed(jsonObject.get("emailConfirmed").getAsString());
            userObject.setFirstName(jsonObject.get("firstName").getAsString());
            userObject.setLastName(jsonObject.get("lastName").getAsString());
            userObject.setLogin(jsonObject.get("login").getAsString());
            return userObject;
        }
    }

}
