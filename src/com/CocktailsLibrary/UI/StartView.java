package com.CocktailsLibrary.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.CocktailsLibrary.R;
import com.CocktailsLibrary.UI.Activities.Base.ActivityBase;
import com.CocktailsLibrary.UI.Models.Base.StartViewModel;

public class StartView extends ActivityBase<StartViewModel> implements OnClickListener {
    private Button btnLibrary;
    private Button btnShaker;
    private Button btnFavorites;
    private Button btnGallery;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onLoadLayoutsParts() {
        setContentView(R.layout.main);
        //Initializing buttons
        btnLibrary = (Button)findViewById(R.id.btnLibrary);
        btnShaker = (Button)findViewById(R.id.btnShaker);
        btnFavorites = (Button)findViewById(R.id.btnFavorites);
        btnGallery = (Button)findViewById(R.id.btnGallery);
        
        btnLibrary.setOnClickListener(this);
        btnShaker.setOnClickListener(this);
        btnFavorites.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
    }

    @Override
    public void onDestroyLayoutsParts() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

	@Override
	public void onClick(View v) {
		//Making intent depending on view ID 
		Intent fromStartActivity = null;
		switch(v.getId()){
		case(R.id.btnLibrary):
			fromStartActivity = new Intent(this, LibraryView.class);
			break;
		case(R.id.btnShaker):
			fromStartActivity = new Intent(this, ShakerView.class);
			break;
		case(R.id.btnFavorites):
			fromStartActivity = new Intent(this, FavoritesView.class);
			break;
		case(R.id.btnGallery):
			fromStartActivity = new Intent(this, GalleryView.class);
			break;
		}
		//if one of the buttons was pressed
		if (!fromStartActivity.equals(null)){
			startActivity(fromStartActivity);
		}
		
	}
		

}
