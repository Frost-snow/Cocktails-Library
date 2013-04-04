package com.CocktailsLibrary.UI;

import android.os.Bundle;

import com.CocktailsLibrary.R;
import com.CocktailsLibrary.UI.Activities.Base.ActivityBase;
import com.CocktailsLibrary.UI.Models.Base.StartViewModel;

public class FavoritesView extends ActivityBase<StartViewModel>{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	@Override
	public void onLoadLayoutsParts() {
		setContentView(R.layout.favorites_view);		
	}

	@Override
	public void onDestroyLayoutsParts() {
		// TODO Auto-generated method stub
		
	}

}
