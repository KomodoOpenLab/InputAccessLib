package com.demo.accessible;

import ca.idi.tecla.lib.InputAccess;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlertDialogActivity extends Activity {
    /** Called when the activity is first created. */
	AlertDialog.Builder builder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.message).
		setPositiveButton(R.string.positive_button_label, new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		Button showDialog = (Button)findViewById(R.id.Button01);
        
		showDialog.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				AlertDialog dialog = builder.create();
				dialog.show();
				//make this static call after '.show()' has been called on a dialog to make ot accessible
				InputAccess.makeAccessible(dialog);
			}
		});
    }
}