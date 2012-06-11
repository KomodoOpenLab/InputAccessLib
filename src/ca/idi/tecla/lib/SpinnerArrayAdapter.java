package ca.idi.tecla.lib;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

public class SpinnerArrayAdapter extends ArrayAdapter<Integer>{

	private SpinnerAdapter spinnerAdapter;
	
	public SpinnerArrayAdapter(Context context, int textViewResourceId, ArrayList<Integer> item_count, SpinnerAdapter spinnerAdapter) {
		super(context, textViewResourceId, item_count);
		this.spinnerAdapter = spinnerAdapter;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View v = spinnerAdapter.getDropDownView(position, null, parent);
		return v;
	}

}
