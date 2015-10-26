package com.quenty.quentyfund.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Diego on 25/10/15.
 */
public class JsonList<T> implements ParameterizedType
{
    private Class<?> wrapped;

    public JsonList(Class<T> wrapper)
    {
        this.wrapped = wrapper;
    }

    @Override
    public Type[] getActualTypeArguments()
    {
        return new Type[] { wrapped };
    }

    @Override
    public Type getRawType()
    {
        return List.class;
    }

    @Override
    public Type getOwnerType()
    {
        return null;
    }
}