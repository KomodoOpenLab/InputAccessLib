package ca.idi.tecla.lib;

import android.content.Context;
import android.view.WindowManager;

public class AlertDialog extends android.app.AlertDialog {

	protected AlertDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		super.show();
		// Window gets key input focus
		this.getWindow().setFlags(0, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
		// Do not invert FLAG_NOT_FOCUSABLE
		this.getWindow().setFlags(0, WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	}

}
