package ca.idi.tecla.lib;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

public class Spinner extends android.widget.Spinner{

	public Spinner(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public Spinner(Context context){
		super(context);
	}
	
	public Spinner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public boolean performClick(){
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle(getPrompt());

		//Count the total number of items in the spinner adapter
		ArrayList<Integer> item_count = new ArrayList<Integer>(getAdapter().getCount());
		int i = 0;
		for(i=0;i<getAdapter().getCount();i++){
			item_count.add(i);
		}

		//Creating a new custom array adapter to take drop down view resource into account
		SpinnerArrayAdapter customAdapter = new SpinnerArrayAdapter(getContext(), 0, item_count, getAdapter());
		builder.setAdapter(customAdapter, this);

		AlertDialog dialog = builder.create();
		dialog.show();
		InputAccess.showBelowIME(dialog);

		//In case drop down view resource is simple_spinner_dropdown_item or simple_list_item_checked then
		//the selected item should be shown checked
		ListView listView = dialog.getListView();
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setItemChecked(getSelectedItemPosition(), true);
		return true;
	}
	
	private class SpinnerArrayAdapter extends ArrayAdapter<Integer>{

		private SpinnerAdapter spinnerAdapter;

		public SpinnerArrayAdapter(Context context, int textViewResourceId, ArrayList<Integer> item_count, SpinnerAdapter spinnerAdapter) {
			super(context, textViewResourceId, item_count);
			this.spinnerAdapter = spinnerAdapter;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View v = spinnerAdapter.getDropDownView(position, convertView, parent);
			return v;
		}
	}
}
