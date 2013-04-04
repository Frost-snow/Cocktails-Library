package com.CocktailsLibrary.UI.Models.Base;

/*
    Represents a base class for UI view models.
 */
public abstract class ViewModelBase {

    // Is model data currently loading.
    private boolean isLoading;

    protected ViewModelBase(){

    }

    // Loads a view model initial data.
    public abstract void load();
    
}
