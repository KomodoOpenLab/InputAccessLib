package ca.idi.tecla.lib.menu;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

public class SubMenu implements android.view.SubMenu{

	private android.view.SubMenu subMenu;
	private String header_title;
	private int header_icon;
	private View header_view;
	
	public SubMenu(android.view.SubMenu subMenu){
		this.subMenu = subMenu;
	}
	
	public MenuItem add(CharSequence title) {
		return subMenu.add(title);
	}

	public MenuItem add(int titleRes) {
		return subMenu.add(titleRes);
	}

	public MenuItem add(int groupId, int itemId, int order, CharSequence title) {
		return subMenu.add(groupId, itemId, order, title);
	}

	public MenuItem add(int groupId, int itemId, int order, int titleRes) {
		return subMenu.add(groupId, itemId, order, titleRes);
	}

	public int addIntentOptions(int groupId, int itemId, int order,
			ComponentName caller, Intent[] specifics, Intent intent, int flags,
			MenuItem[] outSpecificItems) {
		return subMenu.addIntentOptions(groupId, itemId, order, caller, specifics, intent, flags, outSpecificItems);
	}

	public android.view.SubMenu addSubMenu(CharSequence title) {
		subMenu.addSubMenu(title);
		return this;
	}

	public android.view.SubMenu addSubMenu(int titleRes) {
		subMenu.addSubMenu(titleRes);
		return this;
	}

	public android.view.SubMenu addSubMenu(int groupId, int itemId, int order,
			CharSequence title) {
		subMenu.addSubMenu(groupId, itemId, order, title);
		return this;
	}

	public android.view.SubMenu addSubMenu(int groupId, int itemId, int order,
			int titleRes) {
		subMenu.addSubMenu(groupId, itemId, order, titleRes);
		return this;
	}

	public void clear() {
		subMenu.clear();
	}

	public void close() {
		subMenu.close();
	}

	public MenuItem findItem(int id) {
		return subMenu.findItem(id);
	}

	public MenuItem getItem(int index) {
		return subMenu.getItem(index);
	}

	public boolean hasVisibleItems() {
		return subMenu.hasVisibleItems();
	}

	public boolean isShortcutKey(int keyCode, KeyEvent event) {
		return subMenu.isShortcutKey(keyCode, event);
	}

	public boolean performIdentifierAction(int id, int flags) {
		return subMenu.performIdentifierAction(id, flags);
	}

	public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
		return subMenu.performShortcut(keyCode, event, flags);
	}

	public void removeGroup(int groupId) {
		subMenu.removeGroup(groupId);
	}

	public void removeItem(int id) {
		subMenu.removeItem(id);
	}

	public void setGroupCheckable(int group, boolean checkable,
			boolean exclusive) {
		 subMenu.setGroupCheckable(group, checkable, exclusive);
	}

	public void setGroupEnabled(int group, boolean enabled) {
		subMenu.setGroupEnabled(group, enabled);
	}

	public void setGroupVisible(int group, boolean visible) {
		subMenu.setGroupVisible(group, visible);
	}

	public void setQwertyMode(boolean isQwerty) {
		subMenu.setQwertyMode(isQwerty);
	}

	public int size() {
		return subMenu.size();
	}

	public void clearHeader() {
		subMenu.clear();
	}

	public MenuItem getItem() {
		return subMenu.getItem();
	}

	public android.view.SubMenu setHeaderIcon(int iconRes) {
		Log.d("SubMenu", "here in setHeaderIcon");
		subMenu.setHeaderIcon(iconRes);
		return this;
	}

	public android.view.SubMenu setHeaderIcon(Drawable icon) {
		subMenu.setHeaderIcon(icon);
		return this;
	}

	public android.view.SubMenu setHeaderTitle(int titleRes) {
		subMenu.setHeaderTitle(titleRes);
		return this;
	}

	public android.view.SubMenu setHeaderTitle(CharSequence title) {
		subMenu.setHeaderTitle(title);
		return this;
	}

	public android.view.SubMenu setHeaderView(View view) {
		subMenu.setHeaderView(view);
		return this;
	}

	public android.view.SubMenu setIcon(int iconRes) {
		subMenu.setIcon(iconRes);
		return this;
	}

	public android.view.SubMenu setIcon(Drawable icon) {
		subMenu.setIcon(icon);
		return this;
	}

}
