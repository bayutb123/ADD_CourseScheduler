package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.NightMode

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference OK SIH
        val themePreference = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        themePreference?.setOnPreferenceChangeListener { _, newValue ->
            val nightMode = when (newValue) {
                "on" -> NightMode.OFF
                "off" -> NightMode.ON
                else -> NightMode.AUTO
            }
            updateTheme(nightMode.ordinal)
            Log.d("Test", nightMode.ordinal.toString())
            Log.d("Test", "$newValue -> ${getString(R.string.pref_dark_on)} / ${getString(R.string.pref_dark_off)}")
            true
        }
        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        Log.d("Test", nightMode.toString())
        requireActivity().recreate()
        return true
    }
}