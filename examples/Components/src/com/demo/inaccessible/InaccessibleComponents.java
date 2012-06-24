package com.demo.inaccessible;

import com.demo.accessible.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InaccessibleComponents extends ListActivity {
    /** Called when the activity is first created. */
	@SuppressWarnings("rawtypes")
	private static final Class[] classes = {
		AlertDialogActivity.class,
		ListPreferenceActivity.class,
		OptionsMenuActivity.class,
		SpinnerActivity.class};
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.inaccessible_component_list));
        setListAdapter(adapter);
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
    	Intent intent = new Intent();
    	intent.setClass(getApplicationContext(), classes[position]);
    	startActivity(intent);
    }
    
}