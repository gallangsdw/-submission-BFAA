package com.sdwtech.githubuser.setting

import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.sdwtech.githubuser.R
import com.sdwtech.githubuser.alarm.AlarmReceiver

class MyPreferenceFragment: PreferenceFragmentCompat() {

    private lateinit var switchPreference: SwitchPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        switchPreference = findPreference(resources.getString(R.string.notification))!!

        val alarmReceiver = AlarmReceiver()

        switchPreference.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference: Preference, any: Any ->
            if (switchPreference.isChecked) {
                activity?.let {
                    alarmReceiver.cancelAlarm(it) }
                    Toast.makeText(activity, "Reminder is turn off", Toast.LENGTH_SHORT).show()
                    switchPreference.isChecked = false
                } else {
                    activity?.let {
                        alarmReceiver.setRepeatingAlarm(it)
                    }
                    Toast.makeText(activity, "Reminder is turn on", Toast.LENGTH_SHORT).show()
                    switchPreference.isChecked = true
                }
                false
            }
    }

}