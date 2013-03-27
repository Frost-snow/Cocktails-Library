package com.CocktailsLibrary.DataAccess.Entities;

import com.CocktailsLibrary.Common.Interfaces.IEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Volron
 * Date: 27.03.13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */
public class Cocktail implements IEntity<Integer> {
    private int id;

    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
