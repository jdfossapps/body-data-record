package org.jdfossapps.android.bodydatarecord

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.jdfossapps.android.bodydatarecord.database.BodyData
import org.jdfossapps.android.bodydatarecord.database.BodyDataViewModel
import java.io.File
import java.io.FileOutputStream
import java.text.DateFormat
import java.text.NumberFormat
import java.util.Calendar
import java.util.Locale
import java.util.Date

private var localDateTimeFormat: DateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault())
private var localDateFormat: DateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
private var localTimeFormat: DateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault())
private var mNumberFormat : NumberFormat = NumberFormat.getInstance()

class CreateEditBodyDataActivity : AppCompatActivity() {

    private lateinit var bdEntry: BodyData
    private lateinit var bodyDataViewModel: BodyDataViewModel

    lateinit var editBDWeight: TextInputEditText
    lateinit var editBDHeight: TextInputEditText
    lateinit var editBDWaist: TextInputEditText
    lateinit var editBDChest: TextInputEditText

    lateinit var editBDSystole1: TextInputEditText
    lateinit var editBDDiastole1: TextInputEditText
    lateinit var editBDSystole2: TextInputEditText
    lateinit var editBDDiastole2: TextInputEditText
    lateinit var editBDSystole3: TextInputEditText
    lateinit var editBDDiastole3: TextInputEditText
    lateinit var editBDSystole4: TextInputEditText
    lateinit var editBDDiastole4: TextInputEditText
    lateinit var editBDSystole5: TextInputEditText
    lateinit var editBDDiastole5: TextInputEditText

    lateinit var editBDTemperature1: TextInputEditText
    lateinit var editBDTemperature2: TextInputEditText
    lateinit var editBDTemperature3: TextInputEditText
    lateinit var editBDTemperature4: TextInputEditText
    lateinit var editBDTemperature5: TextInputEditText

    lateinit var editBDBloodGlucose1: TextInputEditText
    lateinit var editBDBloodGlucose2: TextInputEditText
    lateinit var editBDBloodGlucose3: TextInputEditText
    lateinit var editBDBloodGlucose4: TextInputEditText
    lateinit var editBDBloodGlucose5: TextInputEditText

    lateinit var editBDHeartRate1: TextInputEditText
    lateinit var editBDHeartRate2: TextInputEditText
    lateinit var editBDHeartRate3: TextInputEditText
    lateinit var editBDHeartRate4: TextInputEditText
    lateinit var editBDHeartRate5: TextInputEditText

    lateinit var editBDDTBloodPressure1: TextInputEditText
    lateinit var editBDDTBloodPressure2: TextInputEditText
    lateinit var editBDDTBloodPressure3: TextInputEditText
    lateinit var editBDDTBloodPressure4: TextInputEditText
    lateinit var editBDDTBloodPressure5: TextInputEditText

    lateinit var tfBDDTBloodPressure1: TextInputLayout
    lateinit var tfBDDTBloodPressure2: TextInputLayout
    lateinit var tfBDDTBloodPressure3: TextInputLayout
    lateinit var tfBDDTBloodPressure4: TextInputLayout
    lateinit var tfBDDTBloodPressure5: TextInputLayout

    lateinit var editBDDTTemperature1: TextInputEditText
    lateinit var editBDDTTemperature2: TextInputEditText
    lateinit var editBDDTTemperature3: TextInputEditText
    lateinit var editBDDTTemperature4: TextInputEditText
    lateinit var editBDDTTemperature5: TextInputEditText

    lateinit var tfBDDTTemperature1: TextInputLayout
    lateinit var tfBDDTTemperature2: TextInputLayout
    lateinit var tfBDDTTemperature3: TextInputLayout
    lateinit var tfBDDTTemperature4: TextInputLayout
    lateinit var tfBDDTTemperature5: TextInputLayout

    lateinit var editBDDTPill1: TextInputEditText
    lateinit var editBDDTPill2: TextInputEditText
    lateinit var editBDDTPill3: TextInputEditText
    lateinit var editBDDTPill4: TextInputEditText
    lateinit var editBDDTPill5: TextInputEditText

    lateinit var tfBDDTPill1: TextInputLayout
    lateinit var tfBDDTPill2: TextInputLayout
    lateinit var tfBDDTPill3: TextInputLayout
    lateinit var tfBDDTPill4: TextInputLayout
    lateinit var tfBDDTPill5: TextInputLayout

    lateinit var editBDCholesterol: TextInputEditText
    lateinit var editBDCReactiveProtein: TextInputEditText
    lateinit var editBDExerciseMinutes: TextInputEditText
    lateinit var editBDExerciseDescription: TextInputEditText

    lateinit var editBDDTSleptAt: TextInputEditText
    lateinit var editBDDTWakeupAt: TextInputEditText

    lateinit var editBDDTBathroom1At: TextInputEditText
    //lateinit var editBDDTBathroom2At: TextInputEditText

    lateinit var editBDDTLastMeal: TextInputEditText
    lateinit var editBDDTBreakfastAt: TextInputEditText
    lateinit var editBDDTRecordDate: TextInputEditText

    var bDDTBloodPressure1: Date? = null
    var bDDTBloodPressure2: Date? = null
    var bDDTBloodPressure3: Date? = null
    var bDDTBloodPressure4: Date? = null
    var bDDTBloodPressure5: Date? = null

    var bDDTTemperature1: Date? = null
    var bDDTTemperature2: Date? = null
    var bDDTTemperature3: Date? = null
    var bDDTTemperature4: Date? = null
    var bDDTTemperature5: Date? = null

    var bDDTPill1: Date? = null
    var bDDTPill2: Date? = null
    var bDDTPill3: Date? = null
    var bDDTPill4: Date? = null
    var bDDTPill5: Date? = null

    lateinit var tfBDDTSleptAt: TextInputLayout
    lateinit var tfBDDTWakeupAt: TextInputLayout

    lateinit var tfBDDTBathroom1At: TextInputLayout
    //lateinit var tfBDDTBathroom2At: TextInputLayout

    lateinit var tfBDDTLastMeal: TextInputLayout
    lateinit var tfBDDTBreakfast: TextInputLayout
    lateinit var tfBDDTRecordDate: TextInputLayout

    var bDDTSleptAt: Date? = null
    var bDDTWakeupAt: Date? = null

    var bDDTBathroom1At: Date? = null
    //var bDDTBathroom2At: Date? = null

    var bDDTLastMeal: Date? = null
    var bDDTBreakfast: Date? = null
    var bDDTRecordDate: Date? = null

    var pill1UserValue: String? = ""
    var pill2UserValue: String? = ""
    var pill3UserValue: String? = ""
    var pill4UserValue: String? = ""
    var pill5UserValue: String? = ""


    private var currentDateTime = Calendar.getInstance()
    private var localFormatDateTime: DateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT, Locale.getDefault())
    private var localFormatTime: DateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault())
    private var localFormatDate: DateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())

    private var isEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_edit_body_data)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)

        mNumberFormat.maximumFractionDigits = 2

        bodyDataViewModel = ViewModelProvider(this).get(BodyDataViewModel::class.java)

        editBDWeight = findViewById<TextInputEditText>(R.id.inputBDEditWeight)
        editBDHeight = findViewById<TextInputEditText>(R.id.inputBDEditHeight)
        editBDWaist = findViewById<TextInputEditText>(R.id.inputBDEditWaist)
        editBDChest = findViewById<TextInputEditText>(R.id.inputBDEditChest)

        editBDSystole1 = findViewById<TextInputEditText>(R.id.inputBDEditHeartSystole1)
        editBDDiastole1 = findViewById<TextInputEditText>(R.id.inputBDEditHeartDiastole1)
        editBDSystole2 = findViewById<TextInputEditText>(R.id.inputBDEditHeartSystole2)
        editBDDiastole2 = findViewById<TextInputEditText>(R.id.inputBDEditHeartDiastole2)
        editBDSystole3 = findViewById<TextInputEditText>(R.id.inputBDEditHeartSystole3)
        editBDDiastole3 = findViewById<TextInputEditText>(R.id.inputBDEditHeartDiastole3)
        editBDSystole4 = findViewById<TextInputEditText>(R.id.inputBDEditHeartSystole4)
        editBDDiastole4 = findViewById<TextInputEditText>(R.id.inputBDEditHeartDiastole4)
        editBDSystole5 = findViewById<TextInputEditText>(R.id.inputBDEditHeartSystole5)
        editBDDiastole5 = findViewById<TextInputEditText>(R.id.inputBDEditHeartDiastole5)

        editBDBloodGlucose1 = findViewById<TextInputEditText>(R.id.inputBDEditBloodGlucose1)
        editBDBloodGlucose2 = findViewById<TextInputEditText>(R.id.inputBDEditBloodGlucose2)
        editBDBloodGlucose3 = findViewById<TextInputEditText>(R.id.inputBDEditBloodGlucose3)
        editBDBloodGlucose4 = findViewById<TextInputEditText>(R.id.inputBDEditBloodGlucose4)
        editBDBloodGlucose5 = findViewById<TextInputEditText>(R.id.inputBDEditBloodGlucose5)

        editBDHeartRate1 = findViewById<TextInputEditText>(R.id.inputBDEditHeartRate1)
        editBDHeartRate2 = findViewById<TextInputEditText>(R.id.inputBDEditHeartRate2)
        editBDHeartRate3 = findViewById<TextInputEditText>(R.id.inputBDEditHeartRate3)
        editBDHeartRate4 = findViewById<TextInputEditText>(R.id.inputBDEditHeartRate4)
        editBDHeartRate5 = findViewById<TextInputEditText>(R.id.inputBDEditHeartRate5)

        editBDTemperature1 = findViewById<TextInputEditText>(R.id.inputBDEditTemperature1)
        editBDTemperature2 = findViewById<TextInputEditText>(R.id.inputBDEditTemperature2)
        editBDTemperature3 = findViewById<TextInputEditText>(R.id.inputBDEditTemperature3)
        editBDTemperature4 = findViewById<TextInputEditText>(R.id.inputBDEditTemperature4)
        editBDTemperature5 = findViewById<TextInputEditText>(R.id.inputBDEditTemperature5)

        editBDDTBloodPressure1 = findViewById<TextInputEditText>(R.id.inputBDEditDTBloodPressure1)
        editBDDTBloodPressure2 = findViewById<TextInputEditText>(R.id.inputBDEditDTBloodPressure2)
        editBDDTBloodPressure3 = findViewById<TextInputEditText>(R.id.inputBDEditDTBloodPressure3)
        editBDDTBloodPressure4 = findViewById<TextInputEditText>(R.id.inputBDEditDTBloodPressure4)
        editBDDTBloodPressure5 = findViewById<TextInputEditText>(R.id.inputBDEditDTBloodPressure5)

        tfBDDTBloodPressure1 = findViewById<TextInputLayout>(R.id.tfBDDTBloodPressure1)
        tfBDDTBloodPressure2 = findViewById<TextInputLayout>(R.id.tfBDDTBloodPressure2)
        tfBDDTBloodPressure3 = findViewById<TextInputLayout>(R.id.tfBDDTBloodPressure3)
        tfBDDTBloodPressure4 = findViewById<TextInputLayout>(R.id.tfBDDTBloodPressure4)
        tfBDDTBloodPressure5 = findViewById<TextInputLayout>(R.id.tfBDDTBloodPressure5)

        editBDDTTemperature1 = findViewById<TextInputEditText>(R.id.inputBDEditDTTemperature1)
        editBDDTTemperature2 = findViewById<TextInputEditText>(R.id.inputBDEditDTTemperature2)
        editBDDTTemperature3 = findViewById<TextInputEditText>(R.id.inputBDEditDTTemperature3)
        editBDDTTemperature4 = findViewById<TextInputEditText>(R.id.inputBDEditDTTemperature4)
        editBDDTTemperature5 = findViewById<TextInputEditText>(R.id.inputBDEditDTTemperature5)

        tfBDDTBloodPressure1 = findViewById<TextInputLayout>(R.id.tfBDDTBloodPressure1)
        tfBDDTBloodPressure2 = findViewById<TextInputLayout>(R.id.tfBDDTBloodPressure2)
        tfBDDTBloodPressure3 = findViewById<TextInputLayout>(R.id.tfBDDTBloodPressure3)
        tfBDDTBloodPressure4 = findViewById<TextInputLayout>(R.id.tfBDDTBloodPressure4)
        tfBDDTBloodPressure5 = findViewById<TextInputLayout>(R.id.tfBDDTBloodPressure5)

        editBDDTPill1 = findViewById<TextInputEditText>(R.id.inputBDEditDTPill1)
        editBDDTPill2 = findViewById<TextInputEditText>(R.id.inputBDEditDTPill2)
        editBDDTPill3 = findViewById<TextInputEditText>(R.id.inputBDEditDTPill3)
        editBDDTPill4 = findViewById<TextInputEditText>(R.id.inputBDEditDTPill4)
        editBDDTPill5 = findViewById<TextInputEditText>(R.id.inputBDEditDTPill5)

        tfBDDTPill1 = findViewById<TextInputLayout>(R.id.tfBDDTPill1)
        tfBDDTPill2 = findViewById<TextInputLayout>(R.id.tfBDDTPill2)
        tfBDDTPill3 = findViewById<TextInputLayout>(R.id.tfBDDTPill3)
        tfBDDTPill4 = findViewById<TextInputLayout>(R.id.tfBDDTPill4)
        tfBDDTPill5 = findViewById<TextInputLayout>(R.id.tfBDDTPill5)

        editBDCholesterol = findViewById<TextInputEditText>(R.id.inputBDEditCholesterol)
        editBDCReactiveProtein = findViewById<TextInputEditText>(R.id.inputBDEditCReactiveProtein)
        editBDExerciseMinutes = findViewById<TextInputEditText>(R.id.inputBDEditExerciseMinutes)
        editBDExerciseDescription = findViewById<TextInputEditText>(R.id.inputBDEditExerciseDescription)

        editBDDTSleptAt = findViewById<TextInputEditText>(R.id.inputBDEditDTSleptAt)
        editBDDTWakeupAt = findViewById<TextInputEditText>(R.id.inputBDEditDTWakeupAt)

        editBDDTBathroom1At = findViewById<TextInputEditText>(R.id.inputBDEditDTBathroom1At)
        //editBDDTBathroom2At = findViewById<TextInputEditText>(R.id.inputBDEditDTBathroom2At)

        editBDDTLastMeal = findViewById<TextInputEditText>(R.id.inputBDEditDTLastMeal)
        editBDDTBreakfastAt = findViewById<TextInputEditText>(R.id.inputBDEditDTBreakfast)
        editBDDTRecordDate = findViewById<TextInputEditText>(R.id.inputBDEditDTRecordDate)

        tfBDDTSleptAt = findViewById<TextInputLayout>(R.id.tfBDDTSleptAt)
        tfBDDTWakeupAt = findViewById<TextInputLayout>(R.id.tfBDDTWakeupAt)

        tfBDDTBathroom1At = findViewById<TextInputLayout>(R.id.tfBDDTBathroom1)
        //tfBDDTBathroom2At = findViewById<TextInputLayout>(R.id.tfBDDTBathroom2)

        tfBDDTLastMeal = findViewById<TextInputLayout>(R.id.tfBDDTLastMeal)
        tfBDDTBreakfast = findViewById<TextInputLayout>(R.id.tfBDDTBreakfast)
        tfBDDTRecordDate = findViewById<TextInputLayout>(R.id.tfBDDTRecordDate)

        pill1UserValue = sharedPref.getString("defaultPill1Name", getString(R.string.default_Pill1Name))
        pill2UserValue = sharedPref.getString("defaultPill2Name", getString(R.string.default_Pill2Name))
        pill3UserValue = sharedPref.getString("defaultPill3Name", getString(R.string.default_Pill3Name))
        pill4UserValue = sharedPref.getString("defaultPill4Name", getString(R.string.default_Pill4Name))
        pill5UserValue = sharedPref.getString("defaultPill5Name", getString(R.string.default_Pill5Name))

        tfBDDTPill1.hint = pill1UserValue
        tfBDDTPill2.hint = pill2UserValue
        tfBDDTPill3.hint = pill3UserValue
        tfBDDTPill4.hint = pill4UserValue
        tfBDDTPill5.hint = pill5UserValue

        /*tfBDDTPill1.hint = sharedPref.getString("defaultPill1Name", getString(R.string.default_Pill1Name))
        tfBDDTPill2.hint = sharedPref.getString("defaultPill2Name", getString(R.string.default_Pill2Name))
        tfBDDTPill3.hint = sharedPref.getString("defaultPill3Name", getString(R.string.default_Pill3Name))
        tfBDDTPill4.hint = sharedPref.getString("defaultPill4Name", getString(R.string.default_Pill4Name))
        tfBDDTPill5.hint = sharedPref.getString("defaultPill5Name", getString(R.string.default_Pill5Name))*/

        editBDDTTemperature1.inputType = InputType.TYPE_NULL
        editBDDTTemperature1.keyListener = null
        editBDDTTemperature2.inputType = InputType.TYPE_NULL
        editBDDTTemperature2.keyListener = null
        editBDDTTemperature3.inputType = InputType.TYPE_NULL
        editBDDTTemperature3.keyListener = null
        editBDDTTemperature4.inputType = InputType.TYPE_NULL
        editBDDTTemperature4.keyListener = null
        editBDDTTemperature5.inputType = InputType.TYPE_NULL
        editBDDTTemperature5.keyListener = null

        editBDDTBloodPressure1.inputType = InputType.TYPE_NULL
        editBDDTBloodPressure1.keyListener = null
        editBDDTBloodPressure2.inputType = InputType.TYPE_NULL
        editBDDTBloodPressure2.keyListener = null
        editBDDTBloodPressure3.inputType = InputType.TYPE_NULL
        editBDDTBloodPressure3.keyListener = null
        editBDDTBloodPressure4.inputType = InputType.TYPE_NULL
        editBDDTBloodPressure4.keyListener = null
        editBDDTBloodPressure5.inputType = InputType.TYPE_NULL
        editBDDTBloodPressure5.keyListener = null

        editBDDTPill1.inputType = InputType.TYPE_NULL
        editBDDTPill1.keyListener = null
        editBDDTPill2.inputType = InputType.TYPE_NULL
        editBDDTPill2.keyListener = null
        editBDDTPill3.inputType = InputType.TYPE_NULL
        editBDDTPill3.keyListener = null
        editBDDTPill4.inputType = InputType.TYPE_NULL
        editBDDTPill4.keyListener = null
        editBDDTPill5.inputType = InputType.TYPE_NULL
        editBDDTPill5.keyListener = null

        editBDDTSleptAt.inputType = InputType.TYPE_NULL
        editBDDTSleptAt.keyListener = null
        editBDDTWakeupAt.inputType = InputType.TYPE_NULL
        editBDDTWakeupAt.keyListener = null

        editBDDTBathroom1At.inputType = InputType.TYPE_NULL
        editBDDTBathroom1At.keyListener = null
        //editBDDTBathroom2At.inputType = InputType.TYPE_NULL
        //editBDDTBathroom2At.keyListener = null

        editBDDTLastMeal.inputType = InputType.TYPE_NULL
        editBDDTLastMeal.keyListener = null
        editBDDTBreakfastAt.inputType = InputType.TYPE_NULL
        editBDDTBreakfastAt.keyListener = null
        editBDDTRecordDate.inputType = InputType.TYPE_NULL
        editBDDTRecordDate.keyListener = null

        val editBodyDataActivity = intent.getParcelableExtra<BodyData>(CreateEditBodyDataActivity.EXTRA_EDIT_REPLY)

        val btnBDSaveEntry = findViewById<Button>(R.id.btnBDSave)
        btnBDSaveEntry.setOnClickListener {
            setBDObjectFromUserInput()
            insertUpdateDBRecord()
            finish()
        }

        val btnBDDeleteEntry = findViewById<Button>(R.id.btnBDDelete)
        btnBDDeleteEntry.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(resources.getString(R.string.activity_create_edit_confirm_delete_action_title))
            builder.setMessage(resources.getString(R.string.activity_create_edit_confirm_delete_action_msg))
            builder.setPositiveButton(resources.getString(R.string.activity_create_edit_confirm_delete_action_yes)) { _, _ ->
                bodyDataViewModel.remove(bdEntry)
                finish()
            }
            builder.setNegativeButton(resources.getString(R.string.activity_create_edit_confirm_delete_action_no)) { _, _ ->
            }
            builder.show()

        }

        val btnBDSetNowDTSleptAt = findViewById<ImageView>(R.id.btnSetNowDTSleptAt)
        btnBDSetNowDTSleptAt.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.slept_at = nowDateTime
            editBDDTSleptAt.setText(localFormatDateTime.format(nowDateTime))
        }

        val btnBDSetNowDTWakeupAt = findViewById<ImageView>(R.id.btnSetNowDTWakeupAt)
        btnBDSetNowDTWakeupAt.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.wakeup_at = nowDateTime
            editBDDTWakeupAt.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTBathroom1At = findViewById<ImageView>(R.id.btnSetNowDTBathroom1At)
        btnBDSetNowDTBathroom1At.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.bathroom_1_at = nowDateTime
            editBDDTBathroom1At.setText(localFormatTime.format(nowDateTime))
        }

        /*val btnBDSetNowDTBathroom2At = findViewById<ImageView>(R.id.btnSetNowDTBathroom2At)
        btnBDSetNowDTBathroom2At.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.bathroom_2_at = nowDateTime
            editBDDTBathroom2At.setText(localFormatDateTime.format(nowDateTime))
        }*/

        val btnBDSetNowDTLastMeal = findViewById<ImageView>(R.id.btnSetNowDTLastMeal)
        btnBDSetNowDTLastMeal.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.last_meal_at = nowDateTime
            editBDDTLastMeal.setText(localFormatDateTime.format(nowDateTime))
        }

        val btnBDSetNowDTBreakfastAt = findViewById<ImageView>(R.id.btnSetNowDTBreakfastAt)
        btnBDSetNowDTBreakfastAt.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.breakfast_at = nowDateTime
            editBDDTBreakfastAt.setText(localFormatDateTime.format(nowDateTime))
        }

        val btnBDSetNowDTRecordDate = findViewById<ImageView>(R.id.btnSetNowDTRecordDate)
        btnBDSetNowDTRecordDate.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.body_data_date = nowDateTime
            editBDDTRecordDate.setText(localFormatDate.format(nowDateTime))
        }

        val btnBDSetNowDTTemperature1 = findViewById<ImageView>(R.id.btnSetNowDTTemperature1)
        btnBDSetNowDTTemperature1.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.temperature_1_at = nowDateTime
            editBDDTTemperature1.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTTemperature2 = findViewById<ImageView>(R.id.btnSetNowDTTemperature2)
        btnBDSetNowDTTemperature2.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.temperature_2_at = nowDateTime
            editBDDTTemperature2.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTTemperature3 = findViewById<ImageView>(R.id.btnSetNowDTTemperature3)
        btnBDSetNowDTTemperature3.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.temperature_3_at = nowDateTime
            editBDDTTemperature3.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTTemperature4 = findViewById<ImageView>(R.id.btnSetNowDTTemperature4)
        btnBDSetNowDTTemperature4.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.temperature_4_at = nowDateTime
            editBDDTTemperature4.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTTemperature5 = findViewById<ImageView>(R.id.btnSetNowDTTemperature5)
        btnBDSetNowDTTemperature5.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.temperature_5_at = nowDateTime
            editBDDTTemperature5.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTBloodPressure1 = findViewById<ImageView>(R.id.btnSetNowDTBloodPressure1)
        btnBDSetNowDTBloodPressure1.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.blood_pressure_1_at = nowDateTime
            editBDDTBloodPressure1.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTBloodPressure2 = findViewById<ImageView>(R.id.btnSetNowDTBloodPressure2)
        btnBDSetNowDTBloodPressure2.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.blood_pressure_2_at = nowDateTime
            editBDDTBloodPressure2.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTBloodPressure3 = findViewById<ImageView>(R.id.btnSetNowDTBloodPressure3)
        btnBDSetNowDTBloodPressure3.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.blood_pressure_3_at = nowDateTime
            editBDDTBloodPressure3.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTBloodPressure4 = findViewById<ImageView>(R.id.btnSetNowDTBloodPressure4)
        btnBDSetNowDTBloodPressure4.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.blood_pressure_4_at = nowDateTime
            editBDDTBloodPressure4.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTBloodPressure5 = findViewById<ImageView>(R.id.btnSetNowDTBloodPressure5)
        btnBDSetNowDTBloodPressure5.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.blood_pressure_5_at = nowDateTime
            editBDDTBloodPressure5.setText(localFormatTime.format(nowDateTime))
        }

        val btnBDSetNowDTPill1 = findViewById<ImageView>(R.id.btnSetNowDTPill1)
        btnBDSetNowDTPill1.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.pill_1_at = nowDateTime
            editBDDTPill1.setText(localFormatTime.format(nowDateTime))
        }
        val btnBDSetNowDTPill2 = findViewById<ImageView>(R.id.btnSetNowDTPill2)
        btnBDSetNowDTPill2.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.pill_2_at = nowDateTime
            editBDDTPill2.setText(localFormatTime.format(nowDateTime))
        }
        val btnBDSetNowDTPill3 = findViewById<ImageView>(R.id.btnSetNowDTPill3)
        btnBDSetNowDTPill3.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.pill_3_at = nowDateTime
            editBDDTPill3.setText(localFormatTime.format(nowDateTime))
        }
        val btnBDSetNowDTPill4 = findViewById<ImageView>(R.id.btnSetNowDTPill4)
        btnBDSetNowDTPill4.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.pill_4_at = nowDateTime
            editBDDTPill4.setText(localFormatTime.format(nowDateTime))
        }
        val btnBDSetNowDTPill5 = findViewById<ImageView>(R.id.btnSetNowDTPill5)
        btnBDSetNowDTPill5.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.pill_5_at = nowDateTime
            editBDDTPill5.setText(localFormatTime.format(nowDateTime))
        }

        if (editBodyDataActivity !== null) {
            bdEntry = editBodyDataActivity
            bdEntry.modified_at = Date()
            isEdit = true
            setUIValuesFromBD()
        } else {
            bdEntry = emptyBDEntry()
        }

        editBDDTTemperature1.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTTemperature1 = pickedDateTime.time
                        bdEntry.temperature_1_at = bDDTTemperature1
                        editBDDTTemperature1.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTTemperature2.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTTemperature2 = pickedDateTime.time
                        bdEntry.temperature_2_at = bDDTTemperature2
                        editBDDTTemperature2.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTTemperature3.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTTemperature3 = pickedDateTime.time
                        bdEntry.temperature_3_at = bDDTTemperature3
                        editBDDTTemperature3.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTTemperature4.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTTemperature4 = pickedDateTime.time
                        bdEntry.temperature_4_at = bDDTTemperature4
                        editBDDTTemperature4.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTTemperature5.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTTemperature5 = pickedDateTime.time
                        bdEntry.temperature_5_at = bDDTTemperature5
                        editBDDTTemperature5.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTBloodPressure1.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTBloodPressure1 = pickedDateTime.time
                        bdEntry.blood_pressure_1_at = bDDTBloodPressure1
                        editBDDTBloodPressure1.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTBloodPressure2.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTBloodPressure2 = pickedDateTime.time
                        bdEntry.blood_pressure_2_at = bDDTBloodPressure2
                        editBDDTBloodPressure2.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTBloodPressure3.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTBloodPressure3 = pickedDateTime.time
                        bdEntry.blood_pressure_3_at = bDDTBloodPressure3
                        editBDDTBloodPressure3.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTBloodPressure4.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTBloodPressure4 = pickedDateTime.time
                        bdEntry.blood_pressure_4_at = bDDTBloodPressure4
                        editBDDTBloodPressure4.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTBloodPressure5.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTBloodPressure5 = pickedDateTime.time
                        bdEntry.blood_pressure_5_at = bDDTBloodPressure5
                        editBDDTBloodPressure5.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTPill1.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTPill1 = pickedDateTime.time
                        bdEntry.pill_1_at = bDDTPill1
                        editBDDTPill1.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTPill2.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTPill2 = pickedDateTime.time
                        bdEntry.pill_2_at = bDDTPill2
                        editBDDTPill2.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTPill3.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTPill3 = pickedDateTime.time
                        bdEntry.pill_3_at = bDDTPill3
                        editBDDTPill3.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTPill4.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTPill4 = pickedDateTime.time
                        bdEntry.pill_4_at = bDDTPill4
                        editBDDTPill4.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTPill5.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTPill5 = pickedDateTime.time
                        bdEntry.pill_5_at = bDDTPill5
                        editBDDTPill5.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTSleptAt.setOnClickListener {
            currentDateTime = Calendar.getInstance()
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
                        bDDTSleptAt = pickedDateTime.time
                        bdEntry.slept_at = bDDTSleptAt
                        editBDDTSleptAt.setText(localFormatDateTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
                }, startYear, startMonth, startDay
            ).show()
        }

        editBDDTWakeupAt.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTWakeupAt = pickedDateTime.time
                        bdEntry.wakeup_at = bDDTWakeupAt
                        editBDDTWakeupAt.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        editBDDTBathroom1At.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            //DatePickerDialog(
            //    this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTBathroom1At = pickedDateTime.time
                        bdEntry.bathroom_1_at = bDDTBathroom1At
                        editBDDTBathroom1At.setText(localFormatTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
            //    }, startYear, startMonth, startDay
            //).show()
        }

        /*editBDDTBathroom2At.setOnClickListener {
            currentDateTime = Calendar.getInstance()
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
                        bDDTBathroom2At = pickedDateTime.time
                        bdEntry.bathroom_2_at = bDDTBathroom2At
                        editBDDTBathroom2At.setText(localFormatDateTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
                }, startYear, startMonth, startDay
            ).show()
        }*/

        editBDDTLastMeal.setOnClickListener {
            currentDateTime = Calendar.getInstance()
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
                        bDDTLastMeal = pickedDateTime.time
                        bdEntry.last_meal_at = bDDTLastMeal
                        editBDDTLastMeal.setText(localFormatDateTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
                }, startYear, startMonth, startDay
            ).show()
        }

        editBDDTBreakfastAt.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentDateTime.get(Calendar.MINUTE)
            DatePickerDialog(
                this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(startYear, startMonth, startDay, hour, minute)
                        bDDTBreakfast = pickedDateTime.time
                        bdEntry.breakfast_at = bDDTBreakfast
                        editBDDTBreakfastAt.setText(localFormatDateTime.format(pickedDateTime.time))
                    }, startHour, startMinute, false).show()
                }, startYear, startMonth, startDay
            ).show()
        }

        editBDDTRecordDate.setOnClickListener {
            currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
            //val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
            //val startMinute = currentDateTime.get(Calendar.MINUTE)
            DatePickerDialog(
                this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    //TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(year, month, day, 0, 0)
                        bDDTRecordDate = pickedDateTime.time
                        bdEntry.body_data_date = bDDTRecordDate
                        editBDDTRecordDate.setText(localFormatDate.format(pickedDateTime.time))
                    //}, startHour, startMinute, false).show()
                }, startYear, startMonth, startDay
            ).show()
        }

        val btnBDSetNowDTLastMealClear = findViewById<ImageView>(R.id.btnSetNowDTLastMealClear)
        btnBDSetNowDTLastMealClear.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.last_meal_at = null
            editBDDTLastMeal.setText("")
        }

        val btnSetNowDTBreakfastAtClear = findViewById<ImageView>(R.id.btnSetNowDTBreakfastAtClear)
        btnSetNowDTBreakfastAtClear.setOnClickListener {
            val nowDateTime = Date()
            bdEntry.breakfast_at = null
            editBDDTBreakfastAt.setText("")
        }



    }

    private fun emptyBDEntry(): BodyData {
        currentDateTime = Calendar.getInstance()
        return BodyData(
            0,0,"user_1","",
            0.0,0.0,0.0,0.0,
            0.0,0.0,
            0.0,0.0,
            0.0,0.0,
            0.0,0.0,
            0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
            null,null,null,null,null,
            0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
            null,null,null,null,null,
            null,null,null,null,null,
            0.0,0.0,
            0.0,"",
            null,null,
            null,null,
            null,null,
            currentDateTime.time,currentDateTime.time,currentDateTime.time
        )
    }

    private fun setUIValuesFromBD() {
        editBDWeight.setText(bdEntry.weight.toString())
        editBDHeight.setText(bdEntry.height.toString())
        editBDWaist.setText(bdEntry.waist_size.toString())
        editBDChest.setText(bdEntry.chest_size.toString())

        editBDSystole1.setText(bdEntry.blood_pressure_systole_1.toString())
        editBDDiastole1.setText(bdEntry.blood_pressure_diastole_1.toString())
        editBDSystole2.setText(bdEntry.blood_pressure_systole_2.toString())
        editBDDiastole2.setText(bdEntry.blood_pressure_diastole_2.toString())
        editBDSystole3.setText(bdEntry.blood_pressure_systole_3.toString())
        editBDDiastole3.setText(bdEntry.blood_pressure_diastole_3.toString())
        editBDSystole4.setText(bdEntry.blood_pressure_systole_4.toString())
        editBDDiastole4.setText(bdEntry.blood_pressure_diastole_4.toString())
        editBDSystole5.setText(bdEntry.blood_pressure_systole_5.toString())
        editBDDiastole5.setText(bdEntry.blood_pressure_diastole_5.toString())

        editBDTemperature1.setText(bdEntry.temperature_1.toString())
        editBDTemperature2.setText(bdEntry.temperature_2.toString())
        editBDTemperature3.setText(bdEntry.temperature_3.toString())
        editBDTemperature4.setText(bdEntry.temperature_4.toString())
        editBDTemperature5.setText(bdEntry.temperature_5.toString())

        editBDBloodGlucose1.setText(bdEntry.blood_glucose_1.toString())
        editBDBloodGlucose2.setText(bdEntry.blood_glucose_2.toString())
        editBDBloodGlucose3.setText(bdEntry.blood_glucose_3.toString())
        editBDBloodGlucose4.setText(bdEntry.blood_glucose_4.toString())
        editBDBloodGlucose5.setText(bdEntry.blood_glucose_5.toString())

        editBDHeartRate1.setText(bdEntry.resting_heart_rate_1.toString())
        editBDHeartRate2.setText(bdEntry.resting_heart_rate_2.toString())
        editBDHeartRate3.setText(bdEntry.resting_heart_rate_3.toString())
        editBDHeartRate4.setText(bdEntry.resting_heart_rate_4.toString())
        editBDHeartRate5.setText(bdEntry.resting_heart_rate_5.toString())

        editBDCholesterol.setText(bdEntry.cholesterol.toString())
        editBDCReactiveProtein.setText(bdEntry.c_reactive_protein.toString())
        editBDExerciseMinutes.setText(bdEntry.exercise_minutes.toString())
        editBDExerciseDescription.setText(bdEntry.exercise_description)

        if(bdEntry.temperature_1_at !== null)
            editBDDTTemperature1.setText(localFormatTime.format(bdEntry.temperature_1_at!!.time))
        if(bdEntry.temperature_2_at !== null)
            editBDDTTemperature2.setText(localFormatTime.format(bdEntry.temperature_2_at!!.time))
        if(bdEntry.temperature_3_at !== null)
            editBDDTTemperature3.setText(localFormatTime.format(bdEntry.temperature_3_at!!.time))
        if(bdEntry.temperature_4_at !== null)
            editBDDTTemperature4.setText(localFormatTime.format(bdEntry.temperature_4_at!!.time))
        if(bdEntry.temperature_5_at !== null)
            editBDDTTemperature5.setText(localFormatTime.format(bdEntry.temperature_5_at!!.time))

        if(bdEntry.blood_pressure_1_at !== null)
            editBDDTBloodPressure1.setText(localFormatTime.format(bdEntry.blood_pressure_1_at!!.time))
        if(bdEntry.blood_pressure_2_at !== null)
            editBDDTBloodPressure2.setText(localFormatTime.format(bdEntry.blood_pressure_2_at!!.time))
        if(bdEntry.blood_pressure_3_at !== null)
            editBDDTBloodPressure3.setText(localFormatTime.format(bdEntry.blood_pressure_3_at!!.time))
        if(bdEntry.blood_pressure_4_at !== null)
            editBDDTBloodPressure4.setText(localFormatTime.format(bdEntry.blood_pressure_4_at!!.time))
        if(bdEntry.blood_pressure_5_at !== null)
            editBDDTBloodPressure5.setText(localFormatTime.format(bdEntry.blood_pressure_5_at!!.time))

        if(bdEntry.pill_1_at !== null)
            editBDDTPill1.setText(localFormatTime.format(bdEntry.pill_1_at!!.time))
        if(bdEntry.pill_2_at !== null)
            editBDDTPill2.setText(localFormatTime.format(bdEntry.pill_2_at!!.time))
        if(bdEntry.pill_3_at !== null)
            editBDDTPill3.setText(localFormatTime.format(bdEntry.pill_3_at!!.time))
        if(bdEntry.pill_4_at !== null)
            editBDDTPill4.setText(localFormatTime.format(bdEntry.pill_4_at!!.time))
        if(bdEntry.pill_5_at !== null)
            editBDDTPill5.setText(localFormatTime.format(bdEntry.pill_5_at!!.time))

        if(bdEntry.slept_at !== null)
            editBDDTSleptAt.setText(localFormatDateTime.format(bdEntry.slept_at!!.time))
        if(bdEntry.wakeup_at !== null)
            editBDDTWakeupAt.setText(localFormatTime.format(bdEntry.wakeup_at!!.time))

        if(bdEntry.bathroom_1_at !== null)
            editBDDTBathroom1At.setText(localFormatTime.format(bdEntry.bathroom_1_at!!.time))
        //if(bdEntry.bathroom_2_at !== null)
        //    editBDDTBathroom2At.setText(localFormatDateTime.format(bdEntry.bathroom_2_at!!.time))

        if(bdEntry.last_meal_at !== null)
            editBDDTLastMeal.setText(localFormatDateTime.format(bdEntry.last_meal_at!!.time))
        if(bdEntry.breakfast_at !== null)
            editBDDTBreakfastAt.setText(localFormatDateTime.format(bdEntry.breakfast_at!!.time))
        if(bdEntry.body_data_date !== null)
            editBDDTRecordDate.setText(localFormatDate.format(bdEntry.body_data_date!!.time))
    }

    private fun setBDObjectFromUserInput() {

        val today = Date()

        if(!TextUtils.isEmpty(editBDWeight.text.toString()) && mNumberFormat.parse(editBDWeight.text.toString())!!.toDouble() >= 0 ) bdEntry.weight = mNumberFormat.parse(editBDWeight.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDHeight.text.toString()) && mNumberFormat.parse(editBDHeight.text.toString())!!.toDouble() >= 0 ) bdEntry.height = mNumberFormat.parse(editBDHeight.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDWaist.text.toString()) && mNumberFormat.parse(editBDWaist.text.toString())!!.toDouble() >= 0 ) bdEntry.waist_size = mNumberFormat.parse(editBDWaist.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDChest.text.toString()) && mNumberFormat.parse(editBDChest.text.toString())!!.toDouble() >= 0 ) bdEntry.chest_size = mNumberFormat.parse(editBDChest.text.toString())!!.toDouble()

        if(!TextUtils.isEmpty(editBDSystole1.text.toString()) && mNumberFormat.parse(editBDSystole1.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_pressure_systole_1 = mNumberFormat.parse(editBDSystole1.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDDiastole1.text.toString()) && mNumberFormat.parse(editBDDiastole1.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_pressure_diastole_1 = mNumberFormat.parse(editBDDiastole1.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDSystole2.text.toString()) && mNumberFormat.parse(editBDSystole2.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_pressure_systole_2 = mNumberFormat.parse(editBDSystole2.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDDiastole2.text.toString()) && mNumberFormat.parse(editBDDiastole2.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_pressure_diastole_2 = mNumberFormat.parse(editBDDiastole2.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDSystole3.text.toString()) && mNumberFormat.parse(editBDSystole3.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_pressure_systole_3 = mNumberFormat.parse(editBDSystole3.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDDiastole3.text.toString()) && mNumberFormat.parse(editBDDiastole3.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_pressure_diastole_3 = mNumberFormat.parse(editBDDiastole3.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDSystole4.text.toString()) && mNumberFormat.parse(editBDSystole4.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_pressure_systole_4 = mNumberFormat.parse(editBDSystole4.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDDiastole4.text.toString()) && mNumberFormat.parse(editBDDiastole4.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_pressure_diastole_4 = mNumberFormat.parse(editBDDiastole4.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDSystole5.text.toString()) && mNumberFormat.parse(editBDSystole5.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_pressure_systole_5 = mNumberFormat.parse(editBDSystole5.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDDiastole5.text.toString()) && mNumberFormat.parse(editBDDiastole5.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_pressure_diastole_5 = mNumberFormat.parse(editBDDiastole5.text.toString())!!.toDouble()

        if(!TextUtils.isEmpty(editBDTemperature1.text.toString()) && mNumberFormat.parse(editBDTemperature1.text.toString())!!.toDouble() >= 0 ) bdEntry.temperature_1 = mNumberFormat.parse(editBDTemperature1.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDTemperature2.text.toString()) && mNumberFormat.parse(editBDTemperature2.text.toString())!!.toDouble() >= 0 ) bdEntry.temperature_2 = mNumberFormat.parse(editBDTemperature2.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDTemperature3.text.toString()) && mNumberFormat.parse(editBDTemperature3.text.toString())!!.toDouble() >= 0 ) bdEntry.temperature_3 = mNumberFormat.parse(editBDTemperature3.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDTemperature4.text.toString()) && mNumberFormat.parse(editBDTemperature4.text.toString())!!.toDouble() >= 0 ) bdEntry.temperature_4 = mNumberFormat.parse(editBDTemperature4.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDTemperature5.text.toString()) && mNumberFormat.parse(editBDTemperature5.text.toString())!!.toDouble() >= 0 ) bdEntry.temperature_5 = mNumberFormat.parse(editBDTemperature5.text.toString())!!.toDouble()

        if(!TextUtils.isEmpty(editBDBloodGlucose1.text.toString()) && mNumberFormat.parse(editBDBloodGlucose1.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_glucose_1 = mNumberFormat.parse(editBDBloodGlucose1.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDBloodGlucose2.text.toString()) && mNumberFormat.parse(editBDBloodGlucose2.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_glucose_2 = mNumberFormat.parse(editBDBloodGlucose2.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDBloodGlucose3.text.toString()) && mNumberFormat.parse(editBDBloodGlucose3.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_glucose_3 = mNumberFormat.parse(editBDBloodGlucose3.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDBloodGlucose4.text.toString()) && mNumberFormat.parse(editBDBloodGlucose4.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_glucose_4 = mNumberFormat.parse(editBDBloodGlucose4.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDBloodGlucose5.text.toString()) && mNumberFormat.parse(editBDBloodGlucose5.text.toString())!!.toDouble() >= 0 ) bdEntry.blood_glucose_5 = mNumberFormat.parse(editBDBloodGlucose5.text.toString())!!.toDouble()

        if(!TextUtils.isEmpty(editBDHeartRate1.text.toString()) && mNumberFormat.parse(editBDHeartRate1.text.toString())!!.toDouble() >= 0 ) bdEntry.resting_heart_rate_1 = mNumberFormat.parse(editBDHeartRate1.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDHeartRate2.text.toString()) && mNumberFormat.parse(editBDHeartRate2.text.toString())!!.toDouble() >= 0 ) bdEntry.resting_heart_rate_2 = mNumberFormat.parse(editBDHeartRate2.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDHeartRate3.text.toString()) && mNumberFormat.parse(editBDHeartRate3.text.toString())!!.toDouble() >= 0 ) bdEntry.resting_heart_rate_3 = mNumberFormat.parse(editBDHeartRate3.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDHeartRate4.text.toString()) && mNumberFormat.parse(editBDHeartRate4.text.toString())!!.toDouble() >= 0 ) bdEntry.resting_heart_rate_4 = mNumberFormat.parse(editBDHeartRate4.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDHeartRate5.text.toString()) && mNumberFormat.parse(editBDHeartRate5.text.toString())!!.toDouble() >= 0 ) bdEntry.resting_heart_rate_5 = mNumberFormat.parse(editBDHeartRate5.text.toString())!!.toDouble()

        if(!TextUtils.isEmpty(editBDCholesterol.text.toString()) && mNumberFormat.parse(editBDCholesterol.text.toString())!!.toDouble() >= 0 ) bdEntry.cholesterol = mNumberFormat.parse(editBDCholesterol.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDCReactiveProtein.text.toString()) && mNumberFormat.parse(editBDCReactiveProtein.text.toString())!!.toDouble() >= 0 ) bdEntry.c_reactive_protein = mNumberFormat.parse(editBDCReactiveProtein.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDExerciseMinutes.text.toString()) && mNumberFormat.parse(editBDExerciseMinutes.text.toString())!!.toDouble() >= 0 ) bdEntry.exercise_minutes = mNumberFormat.parse(editBDExerciseMinutes.text.toString())!!.toDouble()
        if(!TextUtils.isEmpty(editBDExerciseDescription.text.toString()))  bdEntry.exercise_description = editBDExerciseDescription.text.toString()

        if(!isEdit){
            bdEntry.created_at = today
            bdEntry.modified_at = today
        } else {
            bdEntry.modified_at = today
        }


    }

    private fun insertUpdateDBRecord() {
        if(!isEdit) {
            bodyDataViewModel.insert(bdEntry)
        } else {
            bodyDataViewModel.update(bdEntry)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.create_edit_body_data_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_export_csv -> {
            setBDObjectFromUserInput()
            exportBDToCSV()
            true
        }
        R.id.action_share -> {
            setBDObjectFromUserInput()
            val textToSend = getBDAsText()
            val sendIntent: Intent = Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT, textToSend)
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, null))
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun exportBDToCSV() {
        val item = bdEntry
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
        fileHeader += "Bathroom 1 At"//,Bathroom 2 At,"
        fileHeader += "Last Meal At,Breakfast At,Body Data Date,Modified At,Created At"
        var fileRows = ""
        mNumberFormat.maximumFractionDigits = 2

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

    private fun getBDAsText():String {
        setBDObjectFromUserInput()
        val item = bdEntry
        mNumberFormat.maximumFractionDigits = 2

        val temperature_1_at = if (item.temperature_1_at !== null) localTimeFormat.format(item.temperature_1_at!!) else ""
        val temperature_2_at = if (item.temperature_2_at !== null) localTimeFormat.format(item.temperature_2_at!!) else ""
        val temperature_3_at = if (item.temperature_3_at !== null) localTimeFormat.format(item.temperature_3_at!!) else ""
        val temperature_4_at = if (item.temperature_4_at !== null) localTimeFormat.format(item.temperature_4_at!!) else ""
        val temperature_5_at = if (item.temperature_5_at !== null) localTimeFormat.format(item.temperature_5_at!!) else ""

        val blood_pressure_1_at = if (item.blood_pressure_1_at !== null) localTimeFormat.format(item.blood_pressure_1_at!!) else ""
        val blood_pressure_2_at = if (item.blood_pressure_2_at !== null) localTimeFormat.format(item.blood_pressure_2_at!!) else ""
        val blood_pressure_3_at = if (item.blood_pressure_3_at !== null) localTimeFormat.format(item.blood_pressure_3_at!!) else ""
        val blood_pressure_4_at = if (item.blood_pressure_4_at !== null) localTimeFormat.format(item.blood_pressure_4_at!!) else ""
        val blood_pressure_5_at = if (item.blood_pressure_5_at !== null) localTimeFormat.format(item.blood_pressure_5_at!!) else ""

        val pill_1_at = if (item.pill_1_at !== null) localTimeFormat.format(item.pill_1_at!!) else ""
        val pill_2_at = if (item.pill_2_at !== null) localTimeFormat.format(item.pill_2_at!!) else ""
        val pill_3_at = if (item.pill_3_at !== null) localTimeFormat.format(item.pill_3_at!!) else ""
        val pill_4_at = if (item.pill_4_at !== null) localTimeFormat.format(item.pill_4_at!!) else ""
        val pill_5_at = if (item.pill_5_at !== null) localTimeFormat.format(item.pill_5_at!!) else ""

        val slept_at = if (item.slept_at !== null) localDateTimeFormat.format(item.slept_at!!) else ""
        val wakeup_at = if (item.wakeup_at !== null) localTimeFormat.format(item.wakeup_at!!) else ""

        val bathroom_1_at = if (item.bathroom_1_at !== null) localTimeFormat.format(item.bathroom_1_at!!) else ""
        //val bathroom2_at = if (item.bathroom_2_at !== null) localDateTimeFormat.format(item.bathroom_2_at!!) else ""

        val last_meal_at = if (item.last_meal_at !== null) localDateTimeFormat.format(item.last_meal_at!!) else ""
        val breakfast_at = if (item.breakfast_at !== null) localDateTimeFormat.format(item.breakfast_at!!) else ""
        val body_data_date = if (item.body_data_date !== null) localDateFormat.format(item.body_data_date!!) else ""
        val modified_at = if (item.modified_at !== null) localDateTimeFormat.format(item.modified_at!!) else ""
        val created_at = if (item.created_at !== null) localDateTimeFormat.format(item.created_at!!) else ""

        var fileRows = "${getString(R.string.create_edit_body_data_record_share_data_title)}\n"
        fileRows += "${getString(R.string.activity_create_edit_record_date)}: ${body_data_date}\n"
        fileRows += "${getString(R.string.activity_create_edit_item_id)}: ${item.id}\n"
        fileRows += "${getString(R.string.activity_create_edit_weight)}: ${mNumberFormat.format(item.weight)} ${getString(R.string.default_weightUnitOfMeasure)}\n"
        fileRows += "${getString(R.string.activity_create_edit_height)}: ${mNumberFormat.format(item.height)} ${getString(R.string.default_lengthUnitOfMeasure)}\n"
        fileRows += "${getString(R.string.activity_create_edit_waist)}: ${mNumberFormat.format(item.waist_size)} ${getString(R.string.default_lengthUnitOfMeasure)}\n"
        fileRows += "${getString(R.string.activity_create_edit_chest)}: ${mNumberFormat.format(item.chest_size)} ${getString(R.string.default_lengthUnitOfMeasure)}\n"

        fileRows += "${getString(R.string.activity_create_edit_systole_1)}: ${mNumberFormat.format(item.blood_pressure_systole_1)} "
        fileRows += "${getString(R.string.activity_create_edit_diastole_1)}: ${mNumberFormat.format(item.blood_pressure_diastole_1)}\n"
        fileRows += "${getString(R.string.activity_create_edit_heart_rate_1)}: ${mNumberFormat.format(item.resting_heart_rate_1)} "
        fileRows += "${getString(R.string.activity_create_edit_blood_pressure_date_1)}: ${blood_pressure_1_at}\n"

        fileRows += "${getString(R.string.activity_create_edit_systole_2)}: ${mNumberFormat.format(item.blood_pressure_systole_2)} "
        fileRows += "${getString(R.string.activity_create_edit_diastole_2)}: ${mNumberFormat.format(item.blood_pressure_diastole_2)}\n"
        fileRows += "${getString(R.string.activity_create_edit_heart_rate_2)}: ${mNumberFormat.format(item.resting_heart_rate_2)} "
        fileRows += "${getString(R.string.activity_create_edit_blood_pressure_date_2)}: ${blood_pressure_2_at}\n"

        fileRows += "${getString(R.string.activity_create_edit_systole_3)}: ${mNumberFormat.format(item.blood_pressure_systole_3)} "
        fileRows += "${getString(R.string.activity_create_edit_diastole_3)}: ${mNumberFormat.format(item.blood_pressure_diastole_3)}\n"
        fileRows += "${getString(R.string.activity_create_edit_heart_rate_3)}: ${mNumberFormat.format(item.resting_heart_rate_3)} "
        fileRows += "${getString(R.string.activity_create_edit_blood_pressure_date_3)}: ${blood_pressure_3_at}\n"

        fileRows += "${getString(R.string.activity_create_edit_systole_4)}: ${mNumberFormat.format(item.blood_pressure_systole_4)} "
        fileRows += "${getString(R.string.activity_create_edit_diastole_4)}: ${mNumberFormat.format(item.blood_pressure_diastole_4)}\n"
        fileRows += "${getString(R.string.activity_create_edit_heart_rate_4)}: ${mNumberFormat.format(item.resting_heart_rate_4)} "
        fileRows += "${getString(R.string.activity_create_edit_blood_pressure_date_4)}: ${blood_pressure_4_at}\n"

        fileRows += "${getString(R.string.activity_create_edit_systole_5)}: ${mNumberFormat.format(item.blood_pressure_systole_5)} "
        fileRows += "${getString(R.string.activity_create_edit_diastole_5)}: ${mNumberFormat.format(item.blood_pressure_diastole_5)}\n"
        fileRows += "${getString(R.string.activity_create_edit_heart_rate_5)}: ${mNumberFormat.format(item.resting_heart_rate_5)} "
        fileRows += "${getString(R.string.activity_create_edit_blood_pressure_date_5)}: ${blood_pressure_5_at}\n"

        fileRows += "${pill1UserValue}: ${pill_1_at!!}\n"
        fileRows += "${pill2UserValue}: ${pill_2_at!!}\n"
        fileRows += "${pill3UserValue}: ${pill_3_at!!}\n"
        fileRows += "${pill4UserValue}: ${pill_4_at!!}\n"
        fileRows += "${pill5UserValue}: ${pill_5_at!!}\n"

        fileRows += "${getString(R.string.activity_create_edit_temperature_1)}: ${mNumberFormat.format(item.temperature_1)} ${getString(R.string.default_temperatureUnitOfMeasure)} ${temperature_1_at}\n"
        fileRows += "${getString(R.string.activity_create_edit_temperature_2)}: ${mNumberFormat.format(item.temperature_2)} ${getString(R.string.default_temperatureUnitOfMeasure)} ${temperature_2_at}\n"
        fileRows += "${getString(R.string.activity_create_edit_temperature_3)}: ${mNumberFormat.format(item.temperature_3)} ${getString(R.string.default_temperatureUnitOfMeasure)} ${temperature_3_at}\n"
        fileRows += "${getString(R.string.activity_create_edit_temperature_4)}: ${mNumberFormat.format(item.temperature_4)} ${getString(R.string.default_temperatureUnitOfMeasure)} ${temperature_4_at}\n"
        fileRows += "${getString(R.string.activity_create_edit_temperature_5)}: ${mNumberFormat.format(item.temperature_5)} ${getString(R.string.default_temperatureUnitOfMeasure)} ${temperature_5_at}\n"

        fileRows += "${getString(R.string.activity_create_edit_blood_glucose_1)}: ${mNumberFormat.format(item.blood_glucose_1)}\n"
        fileRows += "${getString(R.string.activity_create_edit_blood_glucose_2)}: ${mNumberFormat.format(item.blood_glucose_2)}\n"
        fileRows += "${getString(R.string.activity_create_edit_blood_glucose_3)}: ${mNumberFormat.format(item.blood_glucose_3)}\n"
        fileRows += "${getString(R.string.activity_create_edit_blood_glucose_4)}: ${mNumberFormat.format(item.blood_glucose_4)}\n"
        fileRows += "${getString(R.string.activity_create_edit_blood_glucose_5)}: ${mNumberFormat.format(item.blood_glucose_5)}\n"

        fileRows += "${getString(R.string.activity_create_edit_cholesterol)}: ${mNumberFormat.format(item.cholesterol)}\n"
        fileRows += "${getString(R.string.activity_create_edit_c_reactive_protein)}: ${mNumberFormat.format(item.c_reactive_protein)}\n"

        fileRows += "${getString(R.string.activity_create_edit_exercise_minutes)}: ${mNumberFormat.format(item.exercise_minutes)} ${getString(R.string.default_abbreviationForMinutes)}\n"
        fileRows += "${getString(R.string.activity_create_edit_exercise_description)}: ${item.exercise_description}\n"

        fileRows += "${getString(R.string.activity_create_edit_date_and_time_of_last_meal)}: ${last_meal_at}\n"
        fileRows += "${getString(R.string.activity_create_edit_date_and_time_of_breakfast)}: ${breakfast_at}\n"
        fileRows += "${getString(R.string.activity_create_edit_date_and_time_of_slept_at)}: ${slept_at}\n"
        fileRows += "${getString(R.string.activity_create_edit_date_and_time_of_wakeup)}: ${wakeup_at}\n"
        fileRows += "${getString(R.string.activity_create_edit_date_and_time_of_bathroom_1_at)}: ${bathroom_1_at}\n"

        return fileRows

    }

    companion object {
        const val EXTRA_EDIT_REPLY = "org.jdfossapps.android.bodydatarecord.EDIT_ITEM_REPLY"
    }

}