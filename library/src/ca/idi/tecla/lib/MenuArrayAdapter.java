package ca.idi.tecla.lib;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MenuArrayAdapter extends ArrayAdapter<String> {

	private Context context;
	private String[] values;
	private Drawable[] imageResource;
	//set to true of no menu item has an icon associated with it
	private boolean noDrawableSet;
	
	public MenuArrayAdapter(Context context, String[] objects, Drawable[] imagelist) {
		super(context, 0, objects);
		this.context = context;
		//the strings to be displayed as list items
		this.values = objects;
		//the corresponding drawables
		this.imageResource = imagelist;
		noDrawableSet = true;
		if(imageResource != null){
			for(int i=0;i<imageResource.length;i++){
				if(imageResource[i] != null){
					noDrawableSet = false;
					break;
				}
			}
		}
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = ((Activity)context).getLayoutInflater().inflate(R.layout.accessible_menu_item, parent, false);

		ImageView imageView = (ImageView)row.findViewById(R.id.accessible_menu_item_icon);
		TextView textView = (TextView)row.findViewById(R.id.accessible_menu_item_label);
		imageView.setImageDrawable(imageResource[position]);
		textView.setText(values[position]);

		//in case no item in the list has an icon associated with it
		if(noDrawableSet){
			imageView.setVisibility(View.GONE);
		}
		return row;
	}
}