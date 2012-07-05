package ca.idi.tecla.lib.menu;

import java.util.HashMap;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

public class SubMenu implements android.view.SubMenu{

	private android.view.SubMenu subMenu;
	private ca.idi.tecla.lib.menu.MenuItem menuItem;
	private HashMap<android.view.MenuItem, ca.idi.tecla.lib.menu.MenuItem> menuItemMap;
	private HashMap<Integer, Boolean> checkableItemMap;
	
	public static enum data_type{
		integer,string,drawable,none
	}
	data_type header_icon_type;
	data_type header_title_type;
	private int header_type;
	private static int HEADER_TITLE = 0;
	private static int HEADER_VIEW = 1;
	private static int HEADER_NONE = 2;
	private int header_icon_int;
	private Drawable header_icon;
	private String header_title;
	private int header_title_int;
	private View header_view;
	
	public SubMenu(android.view.SubMenu subMenu){
		this.subMenu = subMenu;
		menuItemMap = new HashMap<android.view.MenuItem, ca.idi.tecla.lib.menu.MenuItem>();
		checkableItemMap = new HashMap<Integer, Boolean>();
	}
	
	public void setMenuItem(ca.idi.tecla.lib.menu.MenuItem menuItem){
		this.menuItem = menuItem;
	}
	
	public ca.idi.tecla.lib.menu.MenuItem add(CharSequence title) {
		MenuItem item = subMenu.add(title);
		ca.idi.tecla.lib.menu.MenuItem mItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, mItem);
		return mItem;
	}

	public ca.idi.tecla.lib.menu.MenuItem add(int titleRes) {
		MenuItem item = subMenu.add(titleRes);
		ca.idi.tecla.lib.menu.MenuItem mItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, mItem);
		return mItem;
	}

	public MenuItem add(int groupId, int itemId, int order, CharSequence title) {
		MenuItem item = subMenu.add(groupId, itemId, order, title);
		ca.idi.tecla.lib.menu.MenuItem mItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, mItem);
		return mItem;
	}

	public MenuItem add(int groupId, int itemId, int order, int titleRes) {
		MenuItem item = subMenu.add(groupId, itemId, order, titleRes);
		ca.idi.tecla.lib.menu.MenuItem mItem = new ca.idi.tecla.lib.menu.MenuItem(item);
		menuItemMap.put(item, mItem);
		return mItem;
	}

	public int addIntentOptions(int groupId, int itemId, int order,
			ComponentName caller, Intent[] specifics, Intent intent, int flags,
			MenuItem[] outSpecificItems) {
		return subMenu.addIntentOptions(groupId, itemId, order, caller, specifics, intent, flags, outSpecificItems);
	}

	public ca.idi.tecla.lib.menu.SubMenu addSubMenu(CharSequence title) {
		subMenu.addSubMenu(title);
		return this;
	}

	public ca.idi.tecla.lib.menu.SubMenu addSubMenu(int titleRes) {
		subMenu.addSubMenu(titleRes);
		return this;
	}

	public ca.idi.tecla.lib.menu.SubMenu addSubMenu(int groupId, int itemId, int order,
			CharSequence title) {
		subMenu.addSubMenu(groupId, itemId, order, title);
		return this;
	}

	public ca.idi.tecla.lib.menu.SubMenu addSubMenu(int groupId, int itemId, int order,
			int titleRes) {
		subMenu.addSubMenu(groupId, itemId, order, titleRes);
		return this;
	}

	public void clear() {
		menuItemMap.clear();
		checkableItemMap.clear();
		subMenu.clear();
	}

	public void close() {
		subMenu.close();
	}

	public ca.idi.tecla.lib.menu.MenuItem findItem(int id) {
		return menuItemMap.get(subMenu.findItem(id));
	}

	public ca.idi.tecla.lib.menu.MenuItem getItem(int index) {
		return menuItemMap.get(subMenu.getItem(index));
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
		checkableItemMap.remove(groupId);
		subMenu.removeGroup(groupId);
	}

	public void removeItem(int id) {
		subMenu.removeItem(id);
	}

	public void setGroupCheckable(int group, boolean checkable,
			boolean exclusive) {
		checkableItemMap.put(group, exclusive);
		subMenu.setGroupCheckable(group, checkable, exclusive);
	}
	
	public boolean isExclusiveItem(MenuItem item){
		int groupId = item.getGroupId();
		Boolean exclusive = checkableItemMap.get(groupId);
		if(exclusive == null){
			Log.e("ca.idi.tecla.lib.SubMenu", "This should never happen. This is an error");
			return true;
		}
		else{
			return exclusive;
		}
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
		header_type = HEADER_NONE;
		header_icon_type = data_type.none;
		header_title_type = data_type.none;
		subMenu.clearHeader();
	}

	public ca.idi.tecla.lib.menu.MenuItem getItem() {
		return menuItem;//return subMenu.getItem();
	}

	public ca.idi.tecla.lib.menu.SubMenu setHeaderIcon(int iconRes) {
		Log.d("SubMenu", "here in setHeaderIcon");
		header_type = HEADER_TITLE;
		header_icon_int = iconRes;
		header_icon_type = data_type.integer;
		subMenu.setHeaderIcon(iconRes);
		return this;
	}

	public ca.idi.tecla.lib.menu.SubMenu setHeaderIcon(Drawable icon) {
		header_type = HEADER_TITLE;
		header_icon = icon;
		header_icon_type = data_type.drawable;
		subMenu.setHeaderIcon(icon);
		return this;
	}

	public ca.idi.tecla.lib.menu.SubMenu setHeaderTitle(int titleRes) {
		header_type = HEADER_TITLE;
		header_title_int = titleRes;
		header_title_type = data_type.integer;
		subMenu.setHeaderTitle(titleRes);
		return this;
	}

	public ca.idi.tecla.lib.menu.SubMenu setHeaderTitle(CharSequence title) {
		header_type = HEADER_TITLE;
		header_title = (String)title;
		header_title_type = data_type.string;
		subMenu.setHeaderTitle(title);
		return this;
	}

	public ca.idi.tecla.lib.menu.SubMenu setHeaderView(View view) {
		header_type = HEADER_VIEW;
		header_view = view;
		subMenu.setHeaderView(view);
		return this;
	}

	public ca.idi.tecla.lib.menu.SubMenu setIcon(int iconRes) {
		subMenu.setIcon(iconRes);
		return this;
	}

	public ca.idi.tecla.lib.menu.SubMenu setIcon(Drawable icon) {
		subMenu.setIcon(icon);
		return this;
	}
	
	public void setHeader(AlertDialog.Builder builder){
		if((header_type == HEADER_NONE) || (header_type == HEADER_TITLE && header_title_type == data_type.none)){
			//no header
		}
		else if(header_type == HEADER_VIEW){
			builder.setCustomTitle(header_view);
		}
		else{
			if(header_title_type == data_type.string)
				builder.setTitle(header_title);
			else if(header_title_type == data_type.integer)
				builder.setTitle(header_title_int);
			if(header_icon_type == data_type.drawable)
				builder.setIcon(header_icon);
			else if(header_icon_type == data_type.integer)
				builder.setIcon(header_icon_int);
		}
	}
	
}
