package com.CocktailsLibrary.UI.Activities.Base;

import android.app.Activity;
import android.os.Bundle;
import com.CocktailsLibrary.UI.Models.Base.ViewModelBase;

/*
    Represents a base class for all activities.
 */
public abstract class ActivityBase<TViewModel extends ViewModelBase>  extends Activity{
    private TViewModel model;

    public TViewModel getModel(){
        return model;
    }
    private void setModel(TViewModel model){
        this.model = model;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // TODO: (dk)
        //model =
        //model.load();

        onLoadLayoutsParts();
    }

    public abstract void onLoadLayoutsParts();
    public abstract void onDestroyLayoutsParts();
}
