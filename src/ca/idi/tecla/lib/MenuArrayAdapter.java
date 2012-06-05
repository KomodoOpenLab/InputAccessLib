package ca.idi.tecla.lib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
 
public class MenuArrayAdapter extends ArrayAdapter<String> {

	private Context context;
	private String[] values;
	private Drawable[] imageResource;
	
	public MenuArrayAdapter(Context context, String[] objects, Drawable[] imagelist) {
		super(context, 0, objects);
		this.context = context;
		//the strings to be displayed as list items
		this.values = objects;
		//the corresponding drawables
		this.imageResource = imagelist;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		

		//The layout of each item in the list
		//defined here dynamically so that the third party applications 
		//can avoid copying an extra layout in their resource folder
		LinearLayout layout = new LinearLayout(context);
		layout.setPadding(5, 5, 5, 5);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		ImageView imageView = new ImageView(context);
		LayoutParams image_params = new LayoutParams(50, 50, Gravity.CENTER);
		image_params.setMargins(0, 5, 0, 0);
		image_params.gravity = Gravity.CENTER;
		imageView.setLayoutParams(image_params);
		layout.addView(imageView);
		
		TextView textView = new TextView(context);
		textView.setLines(1);
		textView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.FILL_PARENT));
		textView.setGravity(Gravity.CENTER);
		textView.setTextAppearance(getContext(), android.R.attr.textAppearanceMedium);
		layout.addView(textView);
		
		//in case there is no image associated with an item
		if(imageResource[position] == null){
			imageView.setVisibility(View.GONE);
			textView.setHeight(textView.getHeight() + 55);
		}
		textView.setText(values[position]);
		imageView.setImageDrawable(imageResource[position]);
		return layout;
	}
}