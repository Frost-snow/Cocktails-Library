package com.CocktailsLibrary.DataAccess.Helpers;

/**
 * Created with IntelliJ IDEA.
 * User: Volron
 * Date: 29.03.13
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */
public class DBColumn {
    private String name;
    private String type;

    public DBColumn(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }
}
