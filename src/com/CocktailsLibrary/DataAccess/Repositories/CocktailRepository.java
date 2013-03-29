package com.CocktailsLibrary.DataAccess.Repositories;

import android.content.Context;
import com.CocktailsLibrary.DataAccess.Repositories.Base.SqlRepositoryBase;
import com.CocktailsLibrary.DataAccess.Entities.Cocktail;

/**
 * Created with IntelliJ IDEA.
 * User: Volron
 * Date: 29.03.13
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
public class CocktailRepository extends SqlRepositoryBase<Cocktail> {
    public CocktailRepository(Context context){
        super(context);
    }
}
