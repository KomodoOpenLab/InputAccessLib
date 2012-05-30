package ca.idi.tecla.lib;

import java.util.LinkedList;
import java.util.List;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MenuDialog extends Dialog {

	//the options menu to convert
	private Menu mOptionsMenu = null;
	//the selected item in the list
	private MenuItem selectedMenuItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//whenever the accessible menu is generated no items is selected
		selectedMenuItem = null;

		//creating a list view and attaching a custom adapter to it
		ListView lview = new ListView(getContext());
		List<MenuItem> menuItems = new LinkedList<MenuItem>();
		int index=0;
		while(mOptionsMenu != null && true){
			try{
				menuItems.add(mOptionsMenu.getItem(index));
			}catch(Exception exp){
				break;
			}
			index++;
		}
		setTitle("Menu");
		String[] item_title = new String[menuItems.size()];
		Drawable[] item_icon = new Drawable[menuItems.size()];
		for(int i=0;i<item_title.length;i++){
			MenuItem obj = menuItems.get(i);
			if(obj.isEnabled() && obj.isVisible()){
				item_title[i] = (String) obj.getTitle();
				item_icon[i] = menuItems.get(i).getIcon();
			}
		}
	    lview.setAdapter(new MenuArrayAdapter(getContext(), item_title==null?new String[]{}:item_title, item_title==null?new Drawable[]{}:item_icon));
	    lview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				//set the selected item
				setSelectedMenuItem(mOptionsMenu.getItem(position));
				//close the menu
				//will in turn invoke OnCancelListener where we will use this set selected menu item
				cancel();
			}
	    	
		});
	    setContentView(lview);
	}
	
	//to close the menu dialog when the menu key is pressed again
	public boolean onPrepareOptionsMenu(Menu menu){
		dismiss();
		return false;
	}

	public MenuDialog(Context context, Menu menu) {
		super(context);
		mOptionsMenu = menu;
	}

	private void setSelectedMenuItem(MenuItem menu_item){
		selectedMenuItem = menu_item;
	}
	
	public MenuItem getSelectedMenuItem(){
		return selectedMenuItem;
	}
	
}
