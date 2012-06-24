package com.demo.inaccessible;

import com.demo.accessible.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SpinnerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inaccessible_spinner_activity);

        android.widget.Spinner spinner = (android.widget.Spinner)findViewById(R.id.Spinner01);
        ArrayAdapter<String>itemList = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		itemList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		String[] list_items = getResources().getStringArray(R.array.spinner_list_items);
		for(int i=0;i<list_items.length;i++){
			itemList.add(list_items[i]);
		}
		spinner.setPromptId(R.string.spinner_title);
		spinner.setAdapter(itemList);
    }
}