package ca.idi.tecla.lib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
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

		//The layout of each item in the list
		//defined here dynamically so that the third party applications 
		//can avoid copying an extra layout in their resource folder

		RelativeLayout container = new RelativeLayout(context);

		LinearLayout layout = new LinearLayout(context);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		layout.setPadding(5, 5, 5, 5);
		layout.setOrientation(LinearLayout.HORIZONTAL);

		ImageView imageView = new ImageView(context);
		LayoutParams image_params = new LayoutParams(50, 50);
		image_params.setMargins(5, 5, 20, 0);
		image_params.gravity = Gravity.CENTER_VERTICAL;
		imageView.setLayoutParams(image_params);
		layout.addView(imageView);

		TextView textView = new TextView(context);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER;
		textView.setLayoutParams(layoutParams);
		textView.setTextAppearance(getContext(), android.R.attr.textAppearanceMedium);
		layout.addView(textView);
		
		textView.setText(values[position]);
		imageView.setImageDrawable(imageResource[position]);

		//in case no item in the list has an icon associated with it
		if(noDrawableSet){
			imageView.setVisibility(View.GONE);
		}

		container.addView(layout);
		return container;
	}
}