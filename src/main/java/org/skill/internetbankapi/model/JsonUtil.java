package org.skill.internetbankapi.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    private JsonUtil(){}
    public static String  serialToJs (Object o){
        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        String js = gs.toJson(o);
        return js;
    }
}
