package ca.idi.tecla.lib;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

public class InputAccess {
	
	/**
	 * Arrange the z-order of a Dialog or AlertDialog so that it becomes accessible to any input method (e.g.,
	 * soft-keyboard, remote control, hands-free kit or external device driver) installed on the user's device.
	 * Call this function only after the Dialog or AlertDialog has been shown on the screen (e.g., after a call to
	 * the show() method).
	 * @param dialog is the Dialog or AlertDialog whose z-order need to be fixed.
	 */
	public static void makeAccessible(Dialog dialog) {
		if (dialog.isShowing()) {
			// Window gets key input focus
			dialog.getWindow().setFlags(0, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
			// Do not invert FLAG_NOT_FOCUSABLE
			dialog.getWindow().setFlags(0, WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		}
	}
	
	/**
	 * Use the Menu object provided to create an equivalent AlertDialog that can be shown instead of the inaccessible
	 * menu options layer. 
	 * @param menu is the inaccessible options menu layer that will be recreated as an AlertDialog
	 * @param context is the context where the options menu is shown (typically an Activity)
	 * @return the AlertDialog that should be shown instead of the inaccessible Options Menu
	 */
	public static AlertDialog menu2Dialog(Menu menu, Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		ArrayList<CharSequence> item_titles = new ArrayList<CharSequence>();

		Boolean endReached = false;
		int i = 0;
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		while (!endReached) {
			try {
				MenuItem item = menu.getItem(i);
				items.add(item);
				item_titles.add(item.getTitle());
				i++;
			} catch (IndexOutOfBoundsException e) {
				endReached = true;
				Log.e("Tecla", "Reached end. Size is: " + i);
			}
		}

		final CharSequence[] item_titles_array = new CharSequence[i];
		for (int j=0;j<i;j++) {
			item_titles_array[j] = item_titles.get(j);
		}
		
		builder.setItems(item_titles_array, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
				Log.d("Tecla", "Pressed " + item_titles_array[item]);
		    }
		});
		return builder.create();
	}
	
}
