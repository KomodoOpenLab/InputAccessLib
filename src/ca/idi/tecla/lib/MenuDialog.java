package ca.idi.tecla.lib;

import java.util.LinkedList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuDialog extends Dialog {

	private Menu mOptionsMenu = null;
	private MenuItem selectedMenuItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
			item_title[i] = (String) menuItems.get(i).getTitle();
			item_icon[i] = menuItems.get(i).getIcon();
//			if(item_icon[i] == null){
//				item_icon[i] = R.drawable.icon;
//			}
		}
	    //lview.setAdapter(new MenuArrayAdapter(getContext(), items==null?new String[]{}:items, items==null?new int[]{}:imageresource));
	    lview.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, item_title));
	    lview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				setSelectedMenuItem(mOptionsMenu.getItem(position));
				cancel();
			}
	    	
		});
	    setContentView(lview);
	}
	
	public MenuDialog(Context context, Menu menu) {
		super(context);
		mOptionsMenu = menu;
		selectedMenuItem = null;
	}

	private void setSelectedMenuItem(MenuItem menu_item){
		selectedMenuItem = menu_item;
	}
	
	public MenuItem getSelectedMenuItem(){
		return selectedMenuItem;
	}
	
}
