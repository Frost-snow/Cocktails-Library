package com.CocktailsLibrary.Common.Interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: Volron
 * Date: 23.03.13
 * Time: 23:43
 * To change this template use File | Settings | File Templates.
 */
public interface IEntity<TId> {
    public TId getId();
    public void setId(TId id);
}
