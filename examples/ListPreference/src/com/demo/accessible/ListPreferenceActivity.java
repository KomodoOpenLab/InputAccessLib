package com.demo.accessible;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ListPreferenceActivity extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //changed the ListPreference in main.xml to ca.idi.tecla.lib.ListPreference
        addPreferencesFromResource(R.layout.main);
        //use ca.idi.tecla.lib.ListPreference here instead of android.preference.ListPreference
        ca.idi.tecla.lib.ListPreference list_pref = (ca.idi.tecla.lib.ListPreference)findPreference("list_preference");
        list_pref.setEntries(R.array.list_items);
        list_pref.setEntryValues(R.array.list_items_values);
        list_pref.setDefaultValue(2);
    }
}