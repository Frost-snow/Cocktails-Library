package com.CocktailsLibrary.UI;

import android.os.Bundle;

import com.CocktailsLibrary.R;
import com.CocktailsLibrary.UI.Activities.Base.ActivityBase;
import com.CocktailsLibrary.UI.Models.Base.StartViewModel;

public class GalleryView extends ActivityBase<StartViewModel> {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	@Override
	public void onLoadLayoutsParts() {
		setContentView(R.layout.gallery_view);
		
	}

	@Override
	public void onDestroyLayoutsParts() {
		// TODO Auto-generated method stub
		
	}

}
