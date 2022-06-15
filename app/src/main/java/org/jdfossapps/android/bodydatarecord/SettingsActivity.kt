package org.jdfossapps.android.bodydatarecord

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceFragmentCompat
import org.jdfossapps.android.bodydatarecord.database.BodyDataViewModel

class SettingsActivity : AppCompatActivity() {

    private lateinit var bodyDataViewModel: BodyDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bodyDataViewModel = ViewModelProvider(this).get(BodyDataViewModel::class.java)

        val btnDeleteAllBDRecords: Button = findViewById(R.id.btnDeleteAllBDRecords)
        //val sharedPref = getSharedPreferences("autophagy", 0)
        //val sharedPrefEditor = sharedPref.edit()
        btnDeleteAllBDRecords.setOnClickListener {
            //sharedPrefEditor.remove("lastMeal")
            //sharedPrefEditor.apply()
            //MainActivity.refreshLastMealInfo()
            val builder = AlertDialog.Builder(this)
            builder.setTitle(resources.getString(R.string.activity_preferences_delete_all_confirm_delete_action_title))
            builder.setMessage(resources.getString(R.string.activity_preferences_delete_all_confirm_delete_action_msg))
            builder.setPositiveButton(resources.getString(R.string.activity_preferences_delete_all_confirm_delete_action_yes)) { _, _ ->
                bodyDataViewModel.removeAllBodyData()
            }
            builder.setNegativeButton(resources.getString(R.string.activity_preferences_delete_all_confirm_delete_action_no)) { _, _ ->
            }
            builder.show()

        }

    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}