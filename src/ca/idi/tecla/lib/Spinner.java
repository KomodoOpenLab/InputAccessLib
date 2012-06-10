package ca.idi.tecla.lib;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ListAdapter;

public class Spinner extends android.widget.Spinner{

	private Dialog dialog = null;
	
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
		builder.setAdapter((ListAdapter) getAdapter(), this);

		dialog = builder.create();
		dialog.show();
		
		InputAccess.makeAccessible(dialog);
		return true;
	}

	 @Override
     public void onClick(DialogInterface dialog, int which) 
     {
         setSelection(which);     
         dialog.dismiss();
     }
	
}
