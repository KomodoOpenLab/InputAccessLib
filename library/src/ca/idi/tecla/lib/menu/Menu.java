package ca.idi.tecla.lib.menu;

import java.util.HashMap;
import android.content.ComponentName;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;

public class Menu implements android.view.Menu{

	private android.view.Menu menu;
	private HashMap<android.view.MenuItem, ca.idi.tecla.lib.menu.MenuItem> menuItemMap;

	public Menu(android.view.Menu menu){
		this.menu = menu;
		menuItemMap = new HashMap<android.view.MenuItem, ca.idi.tecla.lib.menu.MenuItem>();
	}

	public Menu() {
		menuItemMap = new HashMap<android.view.MenuItem, ca.idi.tecla.lib.menu.MenuItem>();
	}

	/**
	 * Set the standard menu to support this custom Menu class
	 * @param menu the options menu to be displayed
	 */
	public void setMenu(android.view.Menu menu){
		this.menu = menu;
		if(menuItemMap != null)
			menuItemMap.clear();
		else{
			menuItemMap = new HashMap<android.view.MenuItem, ca.idi.tecla.lib.menu.MenuItem>();
		}
	}

	public ca.idi.tecla.lib.menu.MenuItem add(CharSequence title) {
		//store the created MenuItem in a HashMap with its custom MenuItem counterpart
		//and return the custom MenuItem
		android.view.MenuItem item = menu.add(title);
		ca.idi.tecla.lib.menu.MenuItem menuItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, menuItem);
		return menuItem;
	}

	public ca.idi.tecla.lib.menu.MenuItem add(int titleRes) {
		android.view.MenuItem item = menu.add(titleRes);
		ca.idi.tecla.lib.menu.MenuItem menuItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, menuItem);
		return menuItem;
	}

	public ca.idi.tecla.lib.menu.MenuItem add(int groupId, int itemId, int order, CharSequence title) {
		android.view.MenuItem item = menu.add(groupId,itemId,order,title);
		ca.idi.tecla.lib.menu.MenuItem menuItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, menuItem);
		return menuItem;
	}

	public ca.idi.tecla.lib.menu.MenuItem add(int groupId, int itemId, int order, int titleRes) {
		android.view.MenuItem item = menu.add(groupId,itemId,order,titleRes);
		ca.idi.tecla.lib.menu.MenuItem menuItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, menuItem);
		return menuItem;
	}

	public int addIntentOptions(int groupId, int itemId, int order,
			ComponentName caller, Intent[] specifics, Intent intent, int flags,
			MenuItem[] outSpecificItems) {
		return menu.addIntentOptions(groupId, itemId, order, caller, specifics, intent, flags, outSpecificItems);
	}

	public ca.idi.tecla.lib.menu.SubMenu addSubMenu(CharSequence title) {
		//store the menu item created in a Hash map with its custom counterpart
		SubMenu subMenu = menu.addSubMenu(title);
		//creating custom subMenu
		ca.idi.tecla.lib.menu.SubMenu mSubMenu = new ca.idi.tecla.lib.menu.SubMenu(subMenu);

		MenuItem item = subMenu.getItem();
		ca.idi.tecla.lib.menu.MenuItem mItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, mItem);

		mItem.setSubMenu(mSubMenu);
		mSubMenu.setMenuItem(mItem);
		return mSubMenu;
	}

	public ca.idi.tecla.lib.menu.SubMenu addSubMenu(int titleRes) {
		SubMenu subMenu = menu.addSubMenu(titleRes);
		ca.idi.tecla.lib.menu.SubMenu mSubMenu = new ca.idi.tecla.lib.menu.SubMenu(subMenu);

		MenuItem item = subMenu.getItem();
		ca.idi.tecla.lib.menu.MenuItem mItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, mItem);

		mItem.setSubMenu(mSubMenu);
		mSubMenu.setMenuItem(mItem);
		return mSubMenu;
	}

	public ca.idi.tecla.lib.menu.SubMenu addSubMenu(int groupId, int itemId, int order,
			CharSequence title) {
		SubMenu subMenu = menu.addSubMenu(groupId, itemId, order, title);
		ca.idi.tecla.lib.menu.SubMenu mSubMenu = new ca.idi.tecla.lib.menu.SubMenu(subMenu);

		MenuItem item = subMenu.getItem();
		ca.idi.tecla.lib.menu.MenuItem mItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, mItem);

		mItem.setSubMenu(mSubMenu);
		mSubMenu.setMenuItem(mItem);
		return mSubMenu;
	}

	public ca.idi.tecla.lib.menu.SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes) {
		SubMenu subMenu = menu.addSubMenu(groupId, itemId, order, titleRes);
		ca.idi.tecla.lib.menu.SubMenu mSubMenu = new ca.idi.tecla.lib.menu.SubMenu(subMenu);

		MenuItem item = subMenu.getItem();
		ca.idi.tecla.lib.menu.MenuItem mItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, mItem);

		mItem.setSubMenu(mSubMenu);
		mSubMenu.setMenuItem(mItem);
		return mSubMenu;
	}

	public void clear() {
		//clear the map since all menu items have been removed
		menuItemMap.clear();
		menu.clear();
	}

	public void close() {
		menu.close();
	}

	public ca.idi.tecla.lib.menu.MenuItem findItem(int id) {
		MenuItem item = menu.findItem(id);
		//return the custom menu item from the hash map
		return menuItemMap.get(item);
	}

	public ca.idi.tecla.lib.menu.MenuItem getItem(int index) {
		MenuItem item = menu.getItem(index);
		return menuItemMap.get(item);
	}

	public boolean hasVisibleItems() {
		return menu.hasVisibleItems();
	}

	public boolean isShortcutKey(int keyCode, KeyEvent event) {
		return menu.isShortcutKey(keyCode, event);
	}

	public boolean performIdentifierAction(int id, int flags) {
		return menu.performIdentifierAction(id, flags);
	}

	public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
		return menu.performShortcut(keyCode, event, flags);
	}

	public void removeGroup(int groupId) {
		menu.removeGroup(groupId);
	}

	public void removeItem(int id) {
		menu.removeItem(id);
	}

	public void setGroupCheckable(int group, boolean checkable,
			boolean exclusive) {
		menu.setGroupCheckable(group, checkable, exclusive);
	}

	public void setGroupEnabled(int group, boolean enabled) {
		menu.setGroupEnabled(group, enabled);
	}

	public void setGroupVisible(int group, boolean visible) {
		menu.setGroupVisible(group, visible);
	}

	public void setQwertyMode(boolean isQwerty) {
		menu.setQwertyMode(isQwerty);
	}

	public int size() {
		return menu.size();
	}

}
