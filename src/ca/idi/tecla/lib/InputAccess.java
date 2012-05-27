package ca.idi.tecla.lib;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.WindowManager;

public class InputAccess {

	//The activity whose options menu has to made accessible
	private Activity activity;
	//To keep a check on the state of the activity
	private boolean isActivityRunning = false;
	//The accessible version of the options menu associated with the activity
	private MenuDialog menuDialog = null;
	//To know whether the accessible or the default inaccessible options menu has to be used
	private boolean accessible = true;

	//A broadcast receiver to receive menu button click events from TeclaIME's navigation keyboard
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {

	@Override
		public void onReceive(Context context, Intent intent) {
			if(isActivityRunning && intent.getAction().equals("ca.idi.tekla.ime.action.CLICK_IME_MENU_BUTTON")){
				activity.openOptionsMenu();
			}
		}
	};

	/**Set whether the accessible options menu(ideally a dialog) be used or the default inaccessible one.
	 * @param accessible is the boolean value that when set hides the menu associated with the activity, if visible.
	 * If set to true then the accessible version of the menu is used otherwise the default inaccessible menu is used, whenever
	 * a menu has to be displayed.
	 */
	public boolean setAccessible(boolean accessible){
		this.accessible = accessible;
		//hide the options menu only if this activity is running because only then its options menu will be visible.
		if(isActivityRunning && !accessible){
			this.activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.HIDE_IME_MENU_BUTTON"));
			//hide any kind of option menu if currently visible
			//since there is no way to know if the default options menu is currently visible or not
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

	/**
	 * Create an object only if you wish to implement the accessible version of the options menu.
	 * @param activity is the activity whose accessible version of the options menu has to be implemented.
	 */
	public InputAccess(Activity activity){
		this.activity = activity;
	}

	/**
	 * Call this method inside the public void onCreate(Bundle) method of your activity.
	 */
	public void onCreate(){
		this.activity.registerReceiver(mReceiver, new IntentFilter("ca.idi.tekla.ime.action.CLICK_IME_MENU_BUTTON"));
	}

	/**
	 * Call this method inside the public void onResume() method of your activity
	 */
	public void onResume(){
		isActivityRunning = true;
		//if accessible ask TeclaIME to show the menu key in its navigation keyboard
		if(this.accessible)
			this.activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.SHOW_IME_MENU_BUTTON"));
	}

	/**
	 * Call this method inside the public void onPause() method of your activity
	 */
	public void onPause(){
		isActivityRunning = false;
		//if pausing ask TeclaIME to hide the menu key in its navigation keyboard
		this.activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.HIDE_IME_MENU_BUTTON"));
	}

	/**
	 * Call this method inside the public void onDestroy() method of your activity
	 */
	public void onDestroy(){
		this.activity.unregisterReceiver(mReceiver);
	}

	/**
	 * Add this method as a return statement to the public boolean onPrepareOptionsMenu(Menu) method of your activity.
	 * It displays the accessible version of the options menu and returns true if accessibility is set to true(set to true by default)
	 * or displays the default inaccessible version of the options menu and returns false if accessibility is set to false.
	 * @param menu is the menu that has to be displayed
	 * @return false if the accessible version of the menu has been displayed after calling this method and true if
	 * the default inaccessible version of the options menu has been displayed after calling this method.
	 */
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

	/**
	 * Use this method to know if the accessible version of the options menu is currently being displayed.
	 * @return true if the accessible version of the options menu is currently being displayed and false if
	 * the accessible version of the options menu is currently not being displayed.
	 */
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

}
