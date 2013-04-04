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

    private static final String PACKAGE_SEPARATOR = ".";
    /**
     * Get the short name of the specified class by striping off the package name.
     *
     * @param classname
     *          Class name.
     * @return Short class name.
     */
    public static String stripPackageName(final String classname) {
        int idx = classname.lastIndexOf(PACKAGE_SEPARATOR);

        if (idx != -1)
            return classname.substring(idx + 1, classname.length());
        return classname;
    }
}
