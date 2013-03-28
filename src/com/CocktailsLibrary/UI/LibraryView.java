package com.CocktailsLibrary.UI;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.CocktailsLibrary.R;
import com.CocktailsLibrary.UI.Activities.Base.ActivityBase;
import com.CocktailsLibrary.UI.Models.Base.StartViewModel;

public class LibraryView extends ActivityBase<StartViewModel> {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	@Override
	public void onLoadLayoutsParts() {
		setContentView(R.layout.library_view);
		ListView lvLibrary = (ListView)findViewById(R.id.lvLibrary);
		//SimpleAdapter sAdapter = new SimpleAdapter(null, null, R.layout.lv_llibrary_element, null, null)
	}

		
	@Override
	public void onDestroyLayoutsParts() {
		// TODO Auto-generated method stub
		
	}

}
