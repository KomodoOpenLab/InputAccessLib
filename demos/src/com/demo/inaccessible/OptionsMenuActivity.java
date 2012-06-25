package com.demo.inaccessible;

import com.demo.accessible.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class OptionsMenuActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inaccessible_options_menu_activity);
    }
    
    public boolean onCreateOptionsMenu(Menu menu){
    	String[] menu_items = getResources().getStringArray(R.array.options_menu_items);
    	menu.addSubMenu(1, 0, 0, menu_items[0]).setIcon(android.R.drawable.ic_menu_upload);
	menu.addSubMenu(1, 1, 0, menu_items[1]);
	menu.addSubMenu(1, 2, 0, menu_items[2]).setIcon(android.R.drawable.ic_input_add);
	menu.addSubMenu(1, 3, 0, menu_items[3]).setIcon(android.R.drawable.arrow_up_float);
	menu.addSubMenu(1, 4, 0, menu_items[4]).setIcon(android.R.drawable.ic_menu_help);
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem menu_item){
    	int item_id = menu_item.getItemId();
    	switch(item_id){
    		//do something with the item_id
    	}
    	return false;//or return true as per your requirement
    }
}