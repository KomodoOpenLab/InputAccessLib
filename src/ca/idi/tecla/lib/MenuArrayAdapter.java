package ca.idi.tecla.lib;

import com.microcontrollerbg.irdroid.R;
import com.microcontrollerbg.irdroid.R.id;
import com.microcontrollerbg.irdroid.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MenuArrayAdapter extends ArrayAdapter<String> {

	private Context context;
	private String[] values;
	private int[] imageResourceId;
 
	public MenuArrayAdapter(Context context, String[] objects, int[] imagelist) {
		super(context, R.layout.image_text_list, objects);
		this.context = context;
		this.values = objects;
		this.imageResourceId = imagelist;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.image_text_list, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position]);
		imageView.setImageResource(imageResourceId[position]);
		return rowView;
	}
}