package com.CocktailsLibrary.DataAccess.Helpers;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Volron
 * Date: 29.03.13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DBMapping {
    DBType dataType();
    DBNullable dataNullable();
    DBKey dataKey();
}
