package com.usn.smilefjes;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    public static final String PREFS_FILE_NAME = "usn.smilefjes.prefs";

    public


    @Override
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);


        }
    }



    public static class SettingsFragment extends PreferenceFragmentCompat {

        PreferenceManager preferenceManager ;
        EditTextPreference editText;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            preferenceManager = getPreferenceManager();
            editText = findPreference(getString(R.string.aar_valg));

        }

        @Override
        public void onPause() {
            super.onPause();

            preferenceManager.getSharedPreferences().edit().putString("aar", editText.getText()).apply();
        }
    }


}