package ca.idi.tecla.lib;

import java.util.ArrayList;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public class InputAccess {

	private Activity activity;
	private boolean isActivityRunning = false;
	private MenuDialog menuDialog = null;
	private boolean accessible = true;

	private BroadcastReceiver mReceiver = new BroadcastReceiver() {

	@Override
		public void onReceive(Context context, Intent intent) {
			if(isActivityRunning && intent.getAction().equals("ca.idi.tekla.ime.action.CLICK_IME_MENU_BUTTON")){
				activity.openOptionsMenu();
			}
		}
	};

	public boolean setAccessible(boolean accessible){
		this.accessible = accessible;
		if(isActivityRunning && !accessible){
			this.activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.HIDE_IME_MENU_BUTTON"));
			if(menuDialog != null && menuDialog.isShowing()){
				menuDialog.dismiss();
			}
			this.activity.closeOptionsMenu();
			return true;
		}
		else if(isActivityRunning && accessible){
			this.activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.SHOW_IME_MENU_BUTTON"));
			if(menuDialog != null && menuDialog.isShowing()){
				menuDialog.dismiss();
			}
			this.activity.closeOptionsMenu();
			return true;
		}
		return false;
	}

	public InputAccess(Activity activity){
		this.activity = activity;
		this.activity.registerReceiver(mReceiver, new IntentFilter("ca.idi.tekla.ime.action.CLICK_IME_MENU_BUTTON"));
	}

	public void onResume(){
		isActivityRunning = true;
		if(this.accessible)
			this.activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.SHOW_IME_MENU_BUTTON"));
	}

	public void onPause(){
		isActivityRunning = false;
		this.activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.HIDE_IME_MENU_BUTTON"));
	}

	public void onDestroy(){
		this.activity.unregisterReceiver(mReceiver);
	}

	public boolean onPrepareOptionsMenu(Menu menu){
		if((menuDialog == null || !menuDialog.isShowing()) && accessible){
			activity.closeOptionsMenu();
			menuDialog = new MenuDialog(this.activity, menu);
			menuDialog.setOnCancelListener(new OnCancelListener() {

				public void onCancel(DialogInterface dialog) {
					if(menuDialog.getSelectedMenuItem() != null)
						activity.onOptionsItemSelected(menuDialog.getSelectedMenuItem());
				}
			});
			menuDialog.show();
			InputAccess.makeAccessible(menuDialog);
			return false;
		}
		else if(menuDialog != null && menuDialog.isShowing()){
			activity.closeOptionsMenu();
			menuDialog.dismiss();
			return false;
		}
		return true;
	}

	public boolean isMenuDialogShowing(){
		return menuDialog != null && menuDialog.isShowing();
	}

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
