package api;
import com.google.gson.*;
import java.lang.reflect.Type;

// This class was built to avoid writing the methods serialize and deserialize for each class in the project

public class InterfaceAddapter implements JsonSerializer , JsonDeserializer {
    private static final  String className="className";
    private  static final String data= "data";

    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        String cn = obj.get(className).getAsString();
        return jsonDeserializationContext.deserialize(obj.get(data), getObjectClass(cn));
    }

    private Class getObjectClass(String cn)  {
        try{
            return Class.forName(cn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.addProperty(className,o.getClass().getName());
        obj.add(data,jsonSerializationContext.serialize(o));
        return obj;
    }
}
