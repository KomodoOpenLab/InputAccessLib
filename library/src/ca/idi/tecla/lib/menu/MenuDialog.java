package ca.idi.tecla.lib.menu;

import java.util.LinkedList;
import java.util.List;

import ca.idi.tecla.lib.InputAccess;
import ca.idi.tecla.view.R;
import android.R.anim;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
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

public class MenuDialog {

	private Context mContext;
	//the options menu to convert
	private Menu mOptionsMenu = null;
	//the selected item in the list
	private MenuItem selectedMenuItem;
	private String[] item_title;
	private Drawable[] item_icon;
	private int menu_type;
	private static int MENU = 0;
	private static int SUB_MENU = 1;
	private AlertDialog.Builder builder;
	private AlertDialog alertDialog;
	
	public void show(){
		alertDialog.show();
		InputAccess.showBelowIME(alertDialog);
	}
	
	public AlertDialog getDialog(){
		return alertDialog;
	}
	
    private AlertDialog create(){

    	if(menu_type == SUB_MENU){
    		((ca.idi.tecla.lib.menu.SubMenu)mOptionsMenu).setHeader(builder);
    	}
    	
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

		builder.setAdapter(new MenuArrayAdapter(mContext), new OnClickListener() {

			public void onClick(DialogInterface dialog, int position) {
				//set the selected item
				setSelectedMenuItem(mOptionsMenu.getItem(position));
				//close the menu will in turn invoke 
				//OnCancelListener where we will use this set selected menu item
				alertDialog.cancel();
			}

		});

		AlertDialog dialog = builder.create();
		dialog.setOnShowListener(new OnShowListener() {
			
			public void onShow(DialogInterface dialog) {
				setSelectedMenuItem(null);
			}
		});
		return dialog;
	}
	
	//to close the menu dialog when the menu key is pressed again
	public boolean onPrepareOptionsMenu(Menu menu){
		alertDialog.dismiss();
		return false;
	}

	public MenuDialog(Context context, Menu menu) {
		mContext = context;
		mOptionsMenu = menu;
		if(menu instanceof ca.idi.tecla.lib.menu.SubMenu){
			menu_type = SUB_MENU;
		}
		else{
			menu_type = MENU;
		}
		builder = new AlertDialog.Builder(mContext);
		alertDialog = create();
	}
	
	public void refresh(){
		if(alertDialog != null && alertDialog.isShowing())
			alertDialog.dismiss();
		setSelectedMenuItem(null);
		builder = new AlertDialog.Builder(mContext);
		alertDialog = create();
	}

	private void setSelectedMenuItem(MenuItem menu_item){
		selectedMenuItem = menu_item;
	}
	
	public MenuItem getSelectedMenuItem(){
		return selectedMenuItem;
	}

	/**
	 * Get the menu associated with this dialog
	 * @return the menu shown by this dialog
	 */
	public Menu getMenu(){
		return mOptionsMenu;
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
			if(true){//menu_type == MENU){
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
			return null;
		}
	}

}
