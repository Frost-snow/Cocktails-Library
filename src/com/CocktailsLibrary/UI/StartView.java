package com.CocktailsLibrary.UI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import com.CocktailsLibrary.R;
import com.CocktailsLibrary.UI.Activities.Base.ActivityBase;
import com.CocktailsLibrary.UI.Models.Base.StartViewModel;

public class StartView extends ActivityBase<StartViewModel> {
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

        btnLibrary = (Button)findViewById(R.id.btnLibrary);
        btnShaker = (Button)findViewById(R.id.btnShaker);
        btnFavorites = (Button)findViewById(R.id.btnFavorites);
        btnGallery = (Button)findViewById(R.id.btnGallery);
    }

    @Override
    public void onDestroyLayoutsParts() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
