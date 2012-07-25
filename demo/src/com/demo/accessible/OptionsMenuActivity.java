package com.demo.accessible;

import ca.idi.tecla.lib.InputAccess;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;

public class OptionsMenuActivity extends Activity {
    /** Called when the activity is first created. */
	private InputAccess intputAccess;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_menu_activity);
        
        //pass the activity whose options menu has to made accessible as a parameter to the constructor
        intputAccess = new InputAccess(this);
        //make this call to make your options menu accessible
        intputAccess.onCreate();
    }
    
    public boolean onCreateOptionsMenu(Menu menu){
    	String[] menu_items = getResources().getStringArray(R.array.options_menu_items);
    	menu.add(1, 0, 0, menu_items[0]).setIcon(android.R.drawable.ic_menu_upload);
		menu.add(1, 1, 0, menu_items[1]);
		menu.add(1, 2, 0, menu_items[2]).setIcon(android.R.drawable.ic_input_add);
		menu.add(1, 3, 0, menu_items[3]).setIcon(android.R.drawable.arrow_up_float);
		menu.add(1, 4, 0, menu_items[4]).setIcon(android.R.drawable.ic_menu_help).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			public boolean onMenuItemClick(MenuItem item) {
				Toast.makeText(getApplicationContext(), R.string.listener_invoked, Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		Menu sub = menu.addSubMenu(1,5,0,R.string.sub_menu_title).setIcon(android.R.drawable.ic_dialog_alert).setHeaderTitle(R.string.header_title).setHeaderIcon(android.R.drawable.ic_dialog_info);
		sub.add(2,6,0,menu_items[0]);
		sub.add(2,7,0,menu_items[1]);
		sub.add(2,8,0,menu_items[2]);
		sub.add(2,9,0,menu_items[3]);
		sub.add(2,10,0,menu_items[4]);
		return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem menu_item){
    	int item_id = menu_item.getItemId();
		Toast.makeText(getApplicationContext(), menu_item.getTitle() + " was clicked", Toast.LENGTH_SHORT).show();
    	switch(item_id){
    		//do something with the item_id
    	}
    	return false;//or return true as per your requirement
    }
}