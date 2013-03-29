package com.CocktailsLibrary.DataAccess.Helpers;

import java.lang.reflect.*;

/**
 * Created with IntelliJ IDEA.
 * User: Volron
 * Date: 29.03.13
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class ReflectionUtils {
    public static Class getGenericParameterClass(Class actualClass, int parameterIndex){
        return (Class)((ParameterizedType)actualClass.getGenericSuperclass()).getActualTypeArguments()[parameterIndex];
    }
}
