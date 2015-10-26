package com.quenty.quentyfund.util;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Diego on 25/10/15.
 */
public class JsonParser {
    private static final Gson json=new Gson();
    public static <T> List<T> toList(String sjson, Class<T> typeClass)
    {
        return json.fromJson(sjson, new JsonList<T>(typeClass));
    }
    public static String toJson(Object o){
        return json.toJson(o);
    }
}
