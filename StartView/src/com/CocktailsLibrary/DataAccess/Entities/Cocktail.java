package com.CocktailsLibrary.DataAccess.Entities;

import com.CocktailsLibrary.Common.Interfaces.IEntity;
import com.CocktailsLibrary.DataAccess.Helpers.DBKey;
import com.CocktailsLibrary.DataAccess.Helpers.DBMapping;
import com.CocktailsLibrary.DataAccess.Helpers.DBNullable;
import com.CocktailsLibrary.DataAccess.Helpers.DBType;

/**
 * Created with IntelliJ IDEA.
 * User: Volron
 * Date: 27.03.13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */
public class Cocktail implements IEntity<Integer> {
    @DBMapping(dataType = DBType.INTEGER, dataKey = DBKey.PRIMARY_KEY, dataNullable = DBNullable.NOT_NULL)
    public int id;

    @DBMapping(dataType = DBType.TEXT, dataKey = DBKey.NONE, dataNullable = DBNullable.NOT_NULL)
    public String name;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
