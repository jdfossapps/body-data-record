package org.jdfossapps.android.bodydatarecord

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jdfossapps.android.bodydatarecord.database.BodyDataListAdapter
import org.jdfossapps.android.bodydatarecord.database.BodyDataViewModel
import java.io.File
import java.io.FileOutputStream
import java.text.DateFormat
import java.text.NumberFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var outputElapsedTime: TextView
    private lateinit var outputLastMeal: TextView

    //private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
    private var currentDateTime = Calendar.getInstance()

    private lateinit var itemViewModel: BodyDataViewModel
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: BodyDataListAdapter

    val getCreatedBDEntry = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        // Handle the returned Uri
    }

    private val localDateTimeFormat: DateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault())
    private val localDateFormat: DateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
    private var mNumberFormat : NumberFormat = NumberFormat.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getSupportActionBar()!!//.setDisplayHomeAsUpEnabled(true)

        sharedPref = getSharedPreferences("body_data_preferences", 0)
        val sharedPrefEditor = sharedPref.edit()
        sharedPrefEditor.apply()

        recyclerView = findViewById<RecyclerView>(R.id.rvBD)
        //val adapter = BodyDataListAdapter(this)
        adapter = BodyDataListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        itemViewModel = ViewModelProvider(this).get(BodyDataViewModel::class.java)

        itemViewModel.allBodyDataItems.observe(this, Observer { items ->
            items?.let {
                adapter.setItems(it)
                //mNumberFormat.setMaximumFractionDigits(2)
                //var updatedItemsTotal = mNumberFormat.format(adapter.itemsTotal).toString()
                //shoppingListTotal.setText("${rootView.getContext().getResources().getString(R.string.view_pager_fragment_il_total)} ${totalCurrencySymbol}${updatedItemsTotal}")
            }
        })

/*        outputElapsedTime = findViewById(R.id.outputElapsedTime)
        outputLastMeal = findViewById(R.id.outputLastMeal)

        val btnPickDatetime: Button = findViewById(R.id.btnPickDateTime)
        val btnRefresh: Button = findViewById(R.id.btnRefresh)*/

        currentDateTime = Calendar.getInstance()

/*        if (sharedPref.getLong("lastMeal", 0) <= 0) {
            outputElapsedTime.text = getString(R.string.provide_information_of_last_meal)
            outputLastMeal.text = ""
        } else {
            refreshLastMealInfo()
        }*/


        /*btnPickDatetime.setOnClickListener {

            currentDateTime = Calendar.getInstance()
            if (sharedPref.getLong("lastMeal", 0) <= 0) {
                currentDateTime.add(Calendar.DATE, -1)
            } else {
                currentDateTime.timeInMillis = sharedPref.getLong("lastMeal",0)
            }

            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)

            DatePickerDialog(
                this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(year, month, day, hour, minute)
                        val calDate: Long = pickedDateTime.timeInMillis
                        sharedPrefEditor.putLong("lastMeal", calDate)
                        sharedPrefEditor.apply()

                        //refreshLastMealInfo()

                    }, startHour, startMinute, false).show()
                }, startYear, startMonth, startDay
            ).show()

        }*/

/*        btnRefresh.setOnClickListener {
            //refreshLastMealInfo()
        }*/

        val fab: View = findViewById(R.id.fabAddBD)
        fab.setOnClickListener {
            //val intent = Intent(this, CreateEditBodyDataActivity::class.java)
            //startActivity(intent)

            val intent = Intent(this, CreateEditBodyDataActivity::class.java)
            getCreatedBDEntry.launch(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            true
        }
        R.id.action_refresh -> {
            //recyclerView.adapter!!.notifyDataSetChanged()
            recreate()
            true
        }
        R.id.action_export_csv -> {
            exportBDToCSV()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun exportBDToCSV() {
        var fileHeader = "Id,User Id,Name,Description,Weight,Height,Waist Size,Chest Size,"
        fileHeader += "Blood Pressure Systole 1,Blood Pressure Diastole 1,"
        fileHeader += "Blood Pressure Systole 2,Blood Pressure Diastole 2,Blood Pressure Systole 3,"
        fileHeader += "Blood Pressure Diastole 3,Blood Pressure Systole 4,Blood Pressure Diastole 4,"
        fileHeader += "Blood Pressure Systole 5,Blood Pressure Diastole 5,"
        fileHeader += "Temperature 1,Temperature 2,Temperature 3,Temperature 4,Temperature 5,"
        fileHeader += "Blood Glucose 1,Blood Glucose 2,"
        fileHeader += "Blood Glucose 3,Blood Glucose 4,Blood Glucose 5,"
        fileHeader += "Resting Heart Rate 1,Resting Heart Rate 2,Resting Heart Rate 3,"
        fileHeader += "Resting Heart Rate 4,Resting Heart Rate 5,Blood Pressure 1 Date,"
        fileHeader += "Blood Pressure 2 Date,Blood Pressure 3 Date,Blood Pressure 4 Date,"
        fileHeader += "Blood Pressure 5 Date,Pill 1 At,Pill 2 At,Pill 3 At,Pill 4 At,Pill 5 At,"
        fileHeader += "Cholesterol,C Reactive Protein,Exercise Minutes,Exercise Description,"
        fileHeader += "Slept AT,Wakeup At,"
        fileHeader += "Bathroom 1 At,"//Bathroom 2 At,"
        fileHeader += "Last Meal At,Breakfast At,Body Data Date,Modified At,Created At"
        var fileRows = ""
        mNumberFormat.maximumFractionDigits = 2
        for(item in adapter.getItems()) {
            val temperature_1_at = if (item.temperature_1_at !== null) localDateTimeFormat.format(item.temperature_1_at!!) else ""
            val temperature_2_at = if (item.temperature_2_at !== null) localDateTimeFormat.format(item.temperature_2_at!!) else ""
            val temperature_3_at = if (item.temperature_3_at !== null) localDateTimeFormat.format(item.temperature_3_at!!) else ""
            val temperature_4_at = if (item.temperature_4_at !== null) localDateTimeFormat.format(item.temperature_4_at!!) else ""
            val temperature_5_at = if (item.temperature_5_at !== null) localDateTimeFormat.format(item.temperature_5_at!!) else ""

            val blood_pressure_1_at = if (item.blood_pressure_1_at !== null) localDateTimeFormat.format(item.blood_pressure_1_at!!) else ""
            val blood_pressure_2_at = if (item.blood_pressure_2_at !== null) localDateTimeFormat.format(item.blood_pressure_2_at!!) else ""
            val blood_pressure_3_at = if (item.blood_pressure_3_at !== null) localDateTimeFormat.format(item.blood_pressure_3_at!!) else ""
            val blood_pressure_4_at = if (item.blood_pressure_4_at !== null) localDateTimeFormat.format(item.blood_pressure_4_at!!) else ""
            val blood_pressure_5_at = if (item.blood_pressure_5_at !== null) localDateTimeFormat.format(item.blood_pressure_5_at!!) else ""

            val pill_1_at = if (item.pill_1_at !== null) localDateTimeFormat.format(item.pill_1_at!!) else ""
            val pill_2_at = if (item.pill_2_at !== null) localDateTimeFormat.format(item.pill_2_at!!) else ""
            val pill_3_at = if (item.pill_3_at !== null) localDateTimeFormat.format(item.pill_3_at!!) else ""
            val pill_4_at = if (item.pill_4_at !== null) localDateTimeFormat.format(item.pill_4_at!!) else ""
            val pill_5_at = if (item.pill_5_at !== null) localDateTimeFormat.format(item.pill_5_at!!) else ""

            val slept_at = if (item.slept_at !== null) localDateTimeFormat.format(item.slept_at!!) else ""
            val wakeup_at = if (item.wakeup_at !== null) localDateTimeFormat.format(item.wakeup_at!!) else ""

            val bathroom1_at = if (item.bathroom_1_at !== null) localDateTimeFormat.format(item.bathroom_1_at!!) else ""
            //val bathroom2_at = if (item.bathroom_2_at !== null) localDateTimeFormat.format(item.bathroom_2_at!!) else ""

            val last_meal_at = if (item.last_meal_at !== null) localDateTimeFormat.format(item.last_meal_at!!) else ""
            val breakfast_at = if (item.breakfast_at !== null) localDateTimeFormat.format(item.breakfast_at!!) else ""
            val body_data_date = if (item.body_data_date !== null) localDateFormat.format(item.body_data_date!!) else ""
            val modified_at = if (item.modified_at !== null) localDateTimeFormat.format(item.modified_at!!) else ""
            val created_at = if (item.created_at !== null) localDateTimeFormat.format(item.created_at!!) else ""

            fileRows += "\n${item.id},${item.user_id},\"${item.name}\",\"${item.description}\","
            fileRows += "${mNumberFormat.format(item.weight)},${mNumberFormat.format(item.height)},"
            fileRows += "${mNumberFormat.format(item.waist_size)},${mNumberFormat.format(item.chest_size)},"
            fileRows += "${mNumberFormat.format(item.blood_pressure_systole_1)},${mNumberFormat.format(item.blood_pressure_diastole_1)},"
            fileRows += "${mNumberFormat.format(item.blood_pressure_systole_2)},${mNumberFormat.format(item.blood_pressure_diastole_2)},"
            fileRows += "${mNumberFormat.format(item.blood_pressure_systole_3)},${mNumberFormat.format(item.blood_pressure_diastole_3)},"
            fileRows += "${mNumberFormat.format(item.blood_pressure_systole_4)},${mNumberFormat.format(item.blood_pressure_diastole_4)},"
            fileRows += "${mNumberFormat.format(item.blood_pressure_systole_5)},${mNumberFormat.format(item.blood_pressure_diastole_5)},"
            fileRows += "${mNumberFormat.format(item.temperature_1)},${mNumberFormat.format(item.temperature_2)},${mNumberFormat.format(item.temperature_3)},"
            fileRows += "${mNumberFormat.format(item.temperature_4)},${mNumberFormat.format(item.temperature_5)},"
            fileRows += "${temperature_1_at},${temperature_2_at},${temperature_3_at},${temperature_4_at},${temperature_5_at},"
            fileRows += "${mNumberFormat.format(item.blood_glucose_1)},${mNumberFormat.format(item.blood_glucose_2)},"
            fileRows += "${mNumberFormat.format(item.blood_glucose_3)},${mNumberFormat.format(item.blood_glucose_4)},"
            fileRows += "${mNumberFormat.format(item.blood_glucose_5)},${mNumberFormat.format(item.resting_heart_rate_1)},"
            fileRows += "${mNumberFormat.format(item.resting_heart_rate_2)},${mNumberFormat.format(item.resting_heart_rate_3)},"
            fileRows += "${mNumberFormat.format(item.resting_heart_rate_4)},${mNumberFormat.format(item.resting_heart_rate_5)},"
            fileRows += "${blood_pressure_1_at},${blood_pressure_2_at},${blood_pressure_3_at},${blood_pressure_4_at},${blood_pressure_5_at},"
            fileRows += "${pill_1_at},${pill_2_at},${pill_3_at},${pill_4_at},${pill_5_at},"
            fileRows += "${mNumberFormat.format(item.cholesterol)},${mNumberFormat.format(item.c_reactive_protein)},"
            fileRows += "${mNumberFormat.format(item.exercise_minutes)},\"${item.exercise_description}\","
            fileRows += "${slept_at},${wakeup_at},"
            fileRows += "${bathroom1_at},"//${bathroom2_at},"
            fileRows += "${last_meal_at},${breakfast_at},"
            fileRows += "${body_data_date},${modified_at},${created_at}"

        }

        try {
            val out: FileOutputStream = openFileOutput("body_data_record.csv", Context.MODE_PRIVATE)
            out.write((fileHeader + fileRows).toByteArray())
            out.close()

            val context: Context = applicationContext
            val filelocation = File(filesDir, "body_data_record.csv")
            val path: Uri = FileProvider.getUriForFile(
                context,
                "org.jdfossapps.android.bodydatarecord.fileprovider",
                filelocation
            )
            val fileIntent = Intent(Intent.ACTION_SEND)
            fileIntent.type = "text/csv"
            fileIntent.data = path
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "body_data_record.csv")
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            fileIntent.putExtra(Intent.EXTRA_STREAM, path)
            fileIntent.putExtra(Intent.EXTRA_TITLE, "body_data_record.csv")
            startActivity(Intent.createChooser(fileIntent, "Body Data Record"))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}