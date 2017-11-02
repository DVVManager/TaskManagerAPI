package models;

import com.google.gson.*;

import java.lang.reflect.Type;

public class UserDeserializer implements JsonDeserializer<User> {
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
