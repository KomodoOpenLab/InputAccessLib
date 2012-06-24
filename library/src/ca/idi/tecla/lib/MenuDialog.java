package ca.idi.tecla.lib;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MenuDialog extends Dialog {

	private Context mContext;
	//the options menu to convert
	private Menu mOptionsMenu = null;
	//the selected item in the list
	private MenuItem selectedMenuItem;
	private String[] item_title;
	private Drawable[] item_icon;
	
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

		item_title = new String[menuItems.size()];
		item_icon = new Drawable[menuItems.size()];

		for(int i=0;i<item_title.length;i++){
			MenuItem obj = menuItems.get(i);
			if(obj.isEnabled() && obj.isVisible()){
				item_title[i] = (String) obj.getTitle();
				item_icon[i] = menuItems.get(i).getIcon();
			}
		}

		lview.setAdapter(new MenuArrayAdapter(mContext));
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
		mContext = context;
		mOptionsMenu = menu;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	private void setSelectedMenuItem(MenuItem menu_item){
		selectedMenuItem = menu_item;
	}
	
	public MenuItem getSelectedMenuItem(){
		return selectedMenuItem;
	}
	
	private class MenuArrayAdapter extends ArrayAdapter<String> {

		//set to true of no menu item has an icon associated with it
		private boolean noDrawableSet;

		public MenuArrayAdapter(Context context) {
			super(context, 0, item_title);
			noDrawableSet = true;
			if(item_icon != null){
				for(int i=0;i<item_icon.length;i++){
					if(item_icon[i] != null){
						noDrawableSet = false;
						break;
					}
				}
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = ((Activity)mContext).getLayoutInflater().inflate(R.layout.accessible_menu_item, parent, false);

			ImageView imageView = (ImageView)row.findViewById(R.id.accessible_menu_item_icon);
			TextView textView = (TextView)row.findViewById(R.id.accessible_menu_item_label);
			imageView.setImageDrawable(item_icon[position]);
			textView.setText(item_title[position]);

			//in case no item in the list has an icon associated with it
			if(noDrawableSet){
				imageView.setVisibility(View.GONE);
			}
			return row;
		}
	}

}
