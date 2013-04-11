package com.CocktailsLibrary.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.CocktailsLibrary.R;
import com.CocktailsLibrary.DataAccess.Repositories.CocktailRepository;
import com.CocktailsLibrary.UI.Activities.Base.ActivityBase;
import com.CocktailsLibrary.UI.Models.Base.StartViewModel;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;

public class StartView extends ActivityBase<StartViewModel> implements OnNavigationListener {

	String[] classification; //String[] with cocktails classifications on StartView
	CocktailRepository repository = new CocktailRepository(this);
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*making adapter with 0 element sort 
        SimpleExpandableListAdapter adapter = getSimpleExpandableListAdapter(classification[0]);*/
        
    }
    
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		//SimpleExpandableListAdapter seAdapter = getSortedseAdapter(classification[itemPosition]);
		
		return false;
	}
	

    @Override
    public void onLoadLayoutsParts() {
        setContentView(R.layout.main);
        
    }

    @Override
    public void onDestroyLayoutsParts() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Navigation list
        classification = getResources().getStringArray(R.array.navigation_menu_locations);
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(
                this, R.array.navigation_menu_locations, R.layout.sherlock_spinner_item);
        list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
        
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(list, this);
        onLoadLayoutsParts();
        
        menu.add("Add").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | 
                MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        
		//Search field
		SearchView searchView = new SearchView(getSupportActionBar().getThemedContext());
        searchView.setQueryHint("Search");
        menu.add(0,0,0,"Add")
        	.setIcon( R.drawable.abs__ic_search_api_holo_light)
        	.setActionView(searchView)
        	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case(0):
			Intent addCocktail = new Intent(this,AddCocktailView.class);
			startActivity(addCocktail);

		}
		return true;
	}

}
