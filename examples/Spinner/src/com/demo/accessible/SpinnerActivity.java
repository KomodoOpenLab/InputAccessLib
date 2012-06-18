package com.demo.accessible;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SpinnerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Notice that instead of android.widget.Spinner ca.idi.tecla.lib.Spinner has been used
        ca.idi.tecla.lib.Spinner spinner = (ca.idi.tecla.lib.Spinner)findViewById(R.id.Spinner01);
        ArrayAdapter<String>itemList = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		itemList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		String[] list_items = getResources().getStringArray(R.array.list_items);
		for(int i=0;i<list_items.length;i++){
			itemList.add(list_items[i]);
		}
		spinner.setPromptId(R.string.title);
		spinner.setAdapter(itemList);
    }
}