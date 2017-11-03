package models;

import com.google.gson.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

public class ItemDeserializer implements JsonDeserializer<Item> {
    @Override
    public Item deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Item itemObject=new Item();
        itemObject.setId(jsonObject.get("id").getAsInt());
        itemObject.setCategory(jsonObject.get("category").getAsInt());
        itemObject.setUser_id(jsonObject.get("user_id").getAsInt());
        itemObject.setTitle(jsonObject.get("title").getAsString());
        String shortName=(String )ObjectUtils.defaultIfNull(jsonObject.get("short_name"),"Default");
        itemObject.setShort_name(shortName);


        return itemObject;
    }
}
