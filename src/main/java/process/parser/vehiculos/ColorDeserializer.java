/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.parser.vehiculos;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.vehiculos.ColorVehiculo;

/**
 *
 * @author Aar�n
 */
public class ColorDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
         ColorVehiculo color = new ColorVehiculo();
         JsonObject jsonObject = (JsonObject)jsonElement;
         color.setId(jsonObject.get("id").getAsInt());
         color.setNombre(jsonObject.get("color").getAsString());
  
         return color;
    }
}