package ca.idi.tecla.lib.menu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;

public class MenuItem implements android.view.MenuItem{

	private android.view.MenuItem menuItem;
	private ca.idi.tecla.lib.menu.SubMenu subMenu;
	private OnMenuItemClickListener mClickListener;

	public MenuItem(android.view.MenuItem menuItem){
		this.menuItem = menuItem;
		subMenu = null;
	}

	/**
	 * Set the sub menu(if any) associated with this menu item
	 * @param subMenu the sub menu associated with this menu item
	 */
	public void setSubMenu(ca.idi.tecla.lib.menu.SubMenu subMenu){
		this.subMenu = subMenu;
	}

	public boolean collapseActionView() {
		return menuItem.collapseActionView();
	}

	public boolean expandActionView() {
		return menuItem.expandActionView();
	}

	public ActionProvider getActionProvider() {
		return menuItem.getActionProvider();
	}

	public View getActionView() {
		return menuItem.getActionView();
	}

	public char getAlphabeticShortcut() {
		return menuItem.getAlphabeticShortcut();
	}

	public int getGroupId() {
		return menuItem.getGroupId();
	}

	public Drawable getIcon() {
		return menuItem.getIcon();
	}

	public Intent getIntent() {
		return menuItem.getIntent();
	}

	public int getItemId() {
		return menuItem.getItemId();
	}

	public ContextMenuInfo getMenuInfo() {
		return menuItem.getMenuInfo();
	}

	public char getNumericShortcut() {
		return menuItem.getNumericShortcut();
	}

	public int getOrder() {
		return menuItem.getOrder();
	}

	public ca.idi.tecla.lib.menu.SubMenu getSubMenu() {
		return subMenu;
	}

	public CharSequence getTitle() {
		return menuItem.getTitle();
	}

	public CharSequence getTitleCondensed() {
		return menuItem.getTitleCondensed();
	}

	public boolean hasSubMenu() {
		return menuItem.hasSubMenu();
	}

	public boolean isActionViewExpanded() {
		return menuItem.isActionViewExpanded();
	}

	public boolean isCheckable() {
		return menuItem.isCheckable();
	}

	public boolean isChecked() {
		return menuItem.isChecked();
	}

	public boolean isEnabled() {
		return menuItem.isEnabled();
	}

	public boolean isVisible() {
		return menuItem.isVisible();
	}

	public ca.idi.tecla.lib.menu.MenuItem setActionProvider(ActionProvider actionProvider) {
		menuItem.setActionProvider(actionProvider);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setActionView(View view) {
		menuItem.setActionView(view);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setActionView(int resId) {
		menuItem.setActionView(resId);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setAlphabeticShortcut(char alphaChar) {
		menuItem.setAlphabeticShortcut(alphaChar);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setCheckable(boolean checkable) {
		menuItem.setCheckable(checkable);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setChecked(boolean checked) {
		menuItem.setChecked(checked);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setEnabled(boolean enabled) {
		menuItem.setEnabled(enabled);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setIcon(Drawable icon) {
		menuItem.setIcon(icon);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setIcon(int iconRes) {
		menuItem.setIcon(iconRes);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setIntent(Intent intent) {
		menuItem.setIntent(intent);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setNumericShortcut(char numericChar) {
		menuItem.setNumericShortcut(numericChar);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setOnActionExpandListener(
			OnActionExpandListener listener) {
		menuItem.setOnActionExpandListener(listener);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setOnMenuItemClickListener(
			OnMenuItemClickListener menuItemClickListener) {
		Log.d("MenuItem","setOnMenuItemClickListener()");
		mClickListener = menuItemClickListener;
		menuItem.setOnMenuItemClickListener(menuItemClickListener);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setShortcut(char numericChar, char alphaChar) {
		menuItem.setShortcut(numericChar, alphaChar);
		return this;
	}

	public void setShowAsAction(int actionEnum) {
		menuItem.setShowAsAction(actionEnum);
	}

	public ca.idi.tecla.lib.menu.MenuItem setShowAsActionFlags(int actionEnum) {
		menuItem.setShowAsAction(actionEnum);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setTitle(CharSequence title) {
		menuItem.setTitle(title);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setTitle(int title) {
		menuItem.setTitle(title);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setTitleCondensed(CharSequence title) {
		menuItem.setTitleCondensed(title);
		return this;
	}

	public ca.idi.tecla.lib.menu.MenuItem setVisible(boolean visible) {
		menuItem.setVisible(visible);
		return this;
	}

	public OnMenuItemClickListener getOnMenuItemClickListener(){
		return mClickListener;
	}

	public boolean invokeOnMenuItemClickListener(){
		if(mClickListener == null)
			return false;
		else
			return mClickListener.onMenuItemClick(this);
	}

}
