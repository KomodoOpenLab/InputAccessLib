package ca.idi.tecla.lib;

import java.lang.reflect.Method;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.provider.Settings;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.Window.Callback;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;

public class InputAccess {

	//The activity whose options menu has to made accessible
	private Activity activity;
	//The accessible version of the options menu associated with the activity
	private MenuDialog menuDialog = null;
	//whether accessilbe options menu should be used even when Tecla Access keyboard is not
	//the currently selected keyboard
	private boolean isDefaultMenu;
	private static final String TECLA_IME_ID = "ca.idi.tekla/.ime.TeclaIME";

	private static Method dispatchGenericMotionEvent;
	private static Method dispatchKeyShortcutEvent;
	private static Method onActionModeFinished;
	private static Method onActionModeStarted;
	private static Method onWindowStartingActionMode;

	static{
		 try {
             Class<?> partypes[] = new Class[1];
             partypes[0] = MotionEvent.class;
             dispatchGenericMotionEvent = Callback.class.getMethod("dispatchGenericMotionEvent", partypes);
		 } catch (NoSuchMethodException e) {
		 }

		 try {
             Class<?> partypes[] = new Class[1];
             partypes[0] = KeyEvent.class;
             dispatchKeyShortcutEvent = Callback.class.getMethod("dispatchKeyShortcutEvent", partypes);
		 } catch (NoSuchMethodException e) {
		 }

		 try {
             Class<?> partypes[] = new Class[1];
             partypes[0] = ActionMode.class;
             onActionModeFinished = Callback.class.getMethod("onActionModeFinished", partypes);
		 } catch (NoSuchMethodException e) {
		 }

		 try {
             Class<?> partypes[] = new Class[1];
             partypes[0] = ActionMode.class;
             onActionModeStarted = Callback.class.getMethod("onActionModeStarted", partypes);
		 } catch (NoSuchMethodException e) {
		 }

		 try {
             Class<?> partypes[] = new Class[1];
             partypes[0] = android.view.ActionMode.Callback.class;
             onWindowStartingActionMode = Callback.class.getMethod("onWindowStartingActionMode", partypes);
		 } catch (NoSuchMethodException e) {
		 }

}

	/**
	 * Create an object only if you wish to implement the accessible version of the options menu.
	 * @param activity is the activity whose accessible version of the options menu has to be implemented.
	 */
	public InputAccess(Activity activity){
		this.activity = activity;
		this.isDefaultMenu = true;
	}

	/**
	 * Create an object only if you wish to implement the accessible version of the options menu.
	 * @param activity is the activity whose accessible version of the options menu has to be implemented.
	 * @param isDefaultMenu is used to decide that in case TeclaIME is not selected as the current input method
	 * should the accessible options menu be displayed(if set to true) or the default inaccessible options menu should 
	 * be displayed(if set to false).
	 */
	public InputAccess(Activity activity, boolean isDefaultMenu){
		this.activity = activity;
		this.isDefaultMenu = isDefaultMenu;
	}
	
	/**
	 * It displays the options menu and returns a boolean value.
	 * @param menu the options menu to be displayed
	 * @param useAccessibleMenu is used to decide that in case TeclaIME is not selected as the current input method
	 * should the accessible options menu be displayed(if set to true) or the default inaccessible options menu should 
	 * be displayed(if set to false).
	 * @return false if the accessible version of the menu has been displayed after calling this method and true if
	 * the default inaccessible version of the options menu has been displayed after calling this method.
	 */
	private boolean onPrepareOptionsMenu(Menu menu, boolean useAccessibleMenu){
		if((menuDialog == null || !menuDialog.isShowing()) && (isTeclaIMESelected() || useAccessibleMenu)){
			activity.closeOptionsMenu();
			menuDialog = new MenuDialog(this.activity, menu);
			menuDialog.setOnCancelListener(new OnCancelListener() {

				public void onCancel(DialogInterface dialog) {
					if(menuDialog.getSelectedMenuItem() != null)
						activity.getWindow().getCallback().onMenuItemSelected(Window.FEATURE_OPTIONS_PANEL, menuDialog.getSelectedMenuItem());
				}
			});
			menuDialog.show();
			InputAccess.showBelowIME(menuDialog);
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
	 * Checks if Tecla IME is the selected as the current input method or not.
	 * @return true if Tecla IME is the current input method, false otherwise.
	 */
	private boolean isTeclaIMESelected(){
		String id = Settings.Secure.getString(this.activity.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
		return id != null && id.equals(TECLA_IME_ID);
	}

	/**
	 * Call this method inside the onCreate(Bundle) method of your activity
	 */
	public void onCreate() {

		final Callback cb = activity.getWindow().getCallback();

		activity.getWindow().setCallback(new Callback() {

			public void onWindowFocusChanged(boolean hasFocus) {
				if(hasFocus){
					activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.SHOW_IME_MENU_BUTTON"));
				}
				else{
					activity.sendBroadcast(new Intent("ca.idi.tekla.ime.action.HIDE_IME_MENU_BUTTON"));
				}
				cb.onWindowFocusChanged(hasFocus);
			}

			public void onWindowAttributesChanged(LayoutParams attrs) {
				cb.onWindowAttributesChanged(attrs);
			}

			public boolean onSearchRequested() {
				return cb.onSearchRequested();
			}

			public boolean onPreparePanel(int featureId, View view, Menu menu) {
				return cb.onPreparePanel(featureId, view, menu);
			}

			public void onPanelClosed(int featureId, Menu menu) {
				cb.onPanelClosed(featureId, menu);
			}

			public boolean onMenuOpened(int featureId, Menu menu) {
				return onPrepareOptionsMenu(menu, isDefaultMenu);
			}

			public boolean onMenuItemSelected(int featureId, MenuItem item) {
				return cb.onMenuItemSelected(featureId, item);
			}

			public void onDetachedFromWindow() {
				cb.onDetachedFromWindow();
			}

			public View onCreatePanelView(int featureId) {
				return cb.onCreatePanelView(featureId);
			}

			public boolean onCreatePanelMenu(int featureId, Menu menu) {
				return cb.onCreatePanelMenu(featureId, menu);
			}

			public void onContentChanged() {
				cb.onContentChanged();
			}

			public void onAttachedToWindow() {
				cb.onAttachedToWindow();
			}

			public boolean dispatchTrackballEvent(MotionEvent event) {
				return cb.dispatchTrackballEvent(event);
			}

			public boolean dispatchTouchEvent(MotionEvent event) {
				return cb.dispatchTouchEvent(event);
			}

			public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
				return dispatchPopulateAccessibilityEvent(event);
			}

			public boolean dispatchKeyEvent(KeyEvent event) {
				return cb.dispatchKeyEvent(event);
			}

			public boolean dispatchGenericMotionEvent(MotionEvent event) {
				if(dispatchGenericMotionEvent !=null){
					try {
						return (Boolean) dispatchGenericMotionEvent.invoke(cb, event);
					} catch (Exception e) {
					}
				}
				return false;
			}

			public boolean dispatchKeyShortcutEvent(KeyEvent event) {
				if(dispatchKeyShortcutEvent != null){
					try {
						return (Boolean) dispatchKeyShortcutEvent.invoke(cb, event);
					} catch (Exception e) {
					}
				}
				return false;
			}

			public void onActionModeFinished(ActionMode mode) {
				if(onActionModeFinished != null){
					try {
						onActionModeFinished.invoke(cb, mode);
					} catch (Exception e) {
					}
				}
			}

			public void onActionModeStarted(ActionMode mode) {
				if(onActionModeStarted != null){
					try {
						onActionModeStarted.invoke(cb, mode);
					} catch (Exception e) {
					}
				}
			}

			public ActionMode onWindowStartingActionMode(
					android.view.ActionMode.Callback callback) {
				if(onWindowStartingActionMode != null){
					try {
						return (ActionMode) onWindowStartingActionMode.invoke(cb, callback);
					} catch (Exception e) {
					}
				}
				return null;
			}
		});
	}

	/**
	 * Arrange the z-order of a Dialog or AlertDialog so that it becomes accessible to any input method (e.g.,
	 * soft-keyboard, remote control, hands-free kit or external device driver) installed on the user's device.
	 * Call this function only after the Dialog or AlertDialog has been shown on the screen (e.g., after a call to
	 * the show() method).
	 * @param dialog is the Dialog or AlertDialog whose z-order need to be fixed.
	 */
	public static void showBelowIME(Dialog dialog) {
		if (dialog.isShowing()) {
			// Window gets key input focus
			dialog.getWindow().setFlags(0, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
			// Do not invert FLAG_NOT_FOCUSABLE
			dialog.getWindow().setFlags(0, WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		}
	}
}