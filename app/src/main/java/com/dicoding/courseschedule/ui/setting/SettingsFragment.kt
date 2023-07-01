package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder
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
            true
        }
        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference ok
        val switchPreference = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        switchPreference?.setOnPreferenceChangeListener { _, newValue ->
            when (newValue) {
                true -> DailyReminder().setDailyReminder(requireContext())
                else -> DailyReminder().cancelAlarm(requireContext())
            }
            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        Log.d("Test", nightMode.toString())
        requireActivity().recreate()
        return true
    }
}