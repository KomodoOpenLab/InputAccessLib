package com.demo.inaccessible;

import com.demo.accessible.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ListPreferenceActivity extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.inaccessible_list_preference_activity);
        android.preference.ListPreference list_pref = (android.preference.ListPreference)findPreference("list_preference");
        list_pref.setEntries(R.array.list_preference_list_items);
        list_pref.setEntryValues(R.array.list_preference_list_items_values);
        list_pref.setDefaultValue(2);
    }
}