package ca.idi.tecla.lib;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.provider.Settings;
import android.view.Menu;
import android.view.WindowManager;

public class InputAccess {

	//The activity whose options menu has to made accessible
	private Activity activity;
	//The accessible version of the options menu associated with the activity
	private MenuDialog menuDialog = null;
	private static final String TECLA_IME_ID = "ca.idi.tekla/.ime.TeclaIME";

	/**
	 * Create an object only if you wish to implement the accessible version of the options menu.
	 * @param activity is the activity whose accessible version of the options menu has to be implemented.
	 */
	public InputAccess(Activity activity){
		this.activity = activity;
	}

	/**
	 * Call this method inside the public void onResume() method of your activity
	 */
	public void onResume(){
		//if resumed ask TeclaIME to show the menu key in its navigation keyboard
		this.activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.SHOW_IME_MENU_BUTTON"));
	}

	/**
	 * Call this method inside the public void onPause() method of your activity
	 */
	public void onPause(){
		//if pausing, ask TeclaIME to hide the menu key in its navigation keyboard
		this.activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.HIDE_IME_MENU_BUTTON"));
	}

	/**
	 * Call this method inside public boolean onPrepareOptionsMenu(Menu) method of your activity. It displays the 
	 * options menu and returns a boolean value.
	 * @param menu the options menu to be displayed
	 * @param useAccessibleMenu is used to decide that in case TeclaIME is not selected as the current input method
	 * should the accessible options menu be displayed(if set to true) or the default inaccessible options menu should 
	 * be displayed(if set to false).
	 * @return false if the accessible version of the menu has been displayed after calling this method and true if
	 * the default inaccessible version of the options menu has been displayed after calling this method.
	 */
	public boolean onPrepareOptionsMenu(Menu menu, boolean useAccessibleMenu){
		if((menuDialog == null || !menuDialog.isShowing()) && (isTeclaIMESelected() || useAccessibleMenu)){
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
	 * Call this method inside public boolean onPrepareOptionsMenu(Menu) method of your activity. It displays the 
	 * options menu whether or not TeclaIME is selected as the current input method and returns a boolean value.
	 * @param menu the options menu to be displayed
	 * @return false if the accessible version of the menu has been displayed after calling this method and true if
	 * the default inaccessible version of the options menu has been displayed after calling this method.
	 */
	public boolean onPrepareOptionsMenu(Menu menu){
		return this.onPrepareOptionsMenu(menu, true);
	}

	/**
	 * Checks if Tecla IME is the selected as the current input method or not.
	 * @return true if Tecla IME is the current input method, false otherwise.
	 */
	private boolean isTeclaIMESelected(){
		String id = Settings.Secure.getString(this.activity.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
		return id != null && id.equals(TECLA_IME_ID);
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