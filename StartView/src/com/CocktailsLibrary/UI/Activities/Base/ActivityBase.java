package com.CocktailsLibrary.UI.Activities.Base;

import android.os.Bundle;
import android.widget.SimpleExpandableListAdapter;

import com.CocktailsLibrary.UI.Models.Base.ViewModelBase;
// TODO: (dk)
import com.actionbarsherlock.app.SherlockActivity;

/*
    Represents a base class for all activities.
 */
public abstract class ActivityBase<TViewModel extends ViewModelBase>  extends SherlockActivity {
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
    
    public SimpleExpandableListAdapter getSimpleExpandableListAdapter(String classification){
    	
    	SimpleExpandableListAdapter seAdapter = new SimpleExpandableListAdapter(null, null, 0, 0, null, null, null, 0, 0, null, null);
    	
    	return seAdapter;
    }
}
