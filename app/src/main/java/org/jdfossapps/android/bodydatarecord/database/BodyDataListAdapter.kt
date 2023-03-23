package org.jdfossapps.android.bodydatarecord.database

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import androidx.appcompat.view.ActionMode
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import java.text.NumberFormat

import androidx.preference.PreferenceManager
import org.jdfossapps.android.bodydatarecord.CreateEditBodyDataActivity
import org.jdfossapps.android.bodydatarecord.R
import java.text.DateFormat
import java.util.Date
import java.util.Locale

class BodyDataListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<BodyDataListAdapter.BodyDataViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = emptyList<BodyData>() // Cached copy of items
    private var currentContext: Context = context
    private var cabSelectedItems = mutableListOf<BodyData>()
    private var actionMode: ActionMode? = null
    private var multiSelectOn: Boolean = false
    private var itemViewModel: BodyDataViewModel = ViewModelProvider((context as AppCompatActivity)).get(BodyDataViewModel::class.java)
    //private val shoppingItemActivityRequestCode = 3
    //private val editShoppingItemActivityRequestCode = 4
    var itemsTotal: Double = 0.0
    private var mNumberFormat : NumberFormat = NumberFormat.getInstance()

    private val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

    inner class BodyDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvInfoTitle: TextView = itemView.findViewById(R.id.tvInfoTitle)
        val tvInfoTitle2: TextView = itemView.findViewById(R.id.tvInfoTitle2)
        val tvInfoTitle3: TextView = itemView.findViewById(R.id.tvInfoTitle3)
        val tvInfoSubTitle: TextView = itemView.findViewById(R.id.tvInfoSubTitle)
        val tvInfoSubTitle2: TextView = itemView.findViewById(R.id.tvInfoSubTitle2)
        val tvInfoSubTitle3: TextView = itemView.findViewById(R.id.tvInfoSubTitle3)
        val tvInfoSubTitle4: TextView = itemView.findViewById(R.id.tvInfoSubTitle4)

        init {
            mNumberFormat.maximumFractionDigits = 2

            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition//bindingAdapterPosition
                val intent = Intent( (v.context as AppCompatActivity) , CreateEditBodyDataActivity::class.java).apply {
                    putExtra(CreateEditBodyDataActivity.EXTRA_EDIT_REPLY, items[position])
                }
                (v.context as AppCompatActivity).startActivity(intent)

                //val startEditBodyData = (v.context as ComponentActivity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        /*if (it.resultCode == RESULT_OK) {
                            val list = it.data
                            // do whatever with the data in the callback
                        }*/
                //}

                //(v.getContext() as AppCompatActivity).startActivityForResult(intent, editShoppingItemActivityRequestCode)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyDataViewHolder {
        val itemView = inflater.inflate(R.layout.bd_recycler_view, parent, false)
        return BodyDataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BodyDataViewHolder, position: Int) {
        val current = items[position]
        //val today = Date()

        /*if (current.breakfast_at === null) {
            current.breakfast_at = today
        }
        if (current.last_meal_at === null) {
            current.last_meal_at = today
        }*/
        var restDateinMillis: Long = 0
        var newBreakfastAt: Date? = if (current.breakfast_at !== null) current.breakfast_at else Date()
        if (newBreakfastAt !== null && current.last_meal_at !== null && sharedPref.getBoolean("compareEmptyLastMealWithCurrentDate", true))
            //restDateinMillis = current.breakfast_at!!.time - current.last_meal_at!!.time
            restDateinMillis = newBreakfastAt.time - current.last_meal_at!!.time

        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24

        val elapsedDays: Long = restDateinMillis / daysInMilli
        restDateinMillis = restDateinMillis % daysInMilli

        val elapsedHours: Long = restDateinMillis / hoursInMilli
        restDateinMillis = restDateinMillis % hoursInMilli

        val elapsedMinutes: Long = restDateinMillis / minutesInMilli
        restDateinMillis = restDateinMillis % minutesInMilli

        val elapsedSeconds: Long = restDateinMillis / secondsInMilli

        val localDateTimeFormat: DateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault())
        val localDateFormat: DateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
        mNumberFormat.maximumFractionDigits = 2

        /*var tvNewSubTitle = ""
        if(sharedPref.getBoolean("compareEmptyLastMealWithCurrentDate", false)) {
            tvNewSubTitle = currentContext.getString(
                R.string.human_time_elapsed,
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds
            )
        }*/
        var tvNewTitle3 = ""
        if (current.body_data_date !== null) tvNewTitle3 = localDateFormat.format(current.body_data_date!!)

        var tvNewSubTitle2 = ""
        if(current.last_meal_at !== null) tvNewSubTitle2 = currentContext.getString(R.string.human_last_meal_at, localDateTimeFormat.format(current.last_meal_at!!))

        var tvNewSubTitle3 = ""
        if(current.breakfast_at !== null) tvNewSubTitle3 = currentContext.getString(R.string.human_breakfast_at, localDateTimeFormat.format(current.breakfast_at!!))

        var restDateinMillisBathroom1: Long = 0
        var dateNowBathroom1 = Date()
        var newBathroomAt: Date? = if (current.bathroom_1_at !== null) current.bathroom_1_at else Date()
        restDateinMillisBathroom1 = dateNowBathroom1.time - newBathroomAt!!.time

        val secondsInMilliBathroom1: Long = 1000
        val minutesInMilliBathroom1 = secondsInMilliBathroom1 * 60
        val hoursInMilliBathroom1 = minutesInMilliBathroom1 * 60
        val daysInMilliBathroom1 = hoursInMilliBathroom1 * 24

        val elapsedDaysBathroom1: Long = restDateinMillisBathroom1 / daysInMilliBathroom1
        restDateinMillisBathroom1 = restDateinMillisBathroom1 % daysInMilliBathroom1

        val elapsedHoursBathroom1: Long = restDateinMillisBathroom1 / hoursInMilliBathroom1
        restDateinMillisBathroom1 = restDateinMillisBathroom1 % hoursInMilliBathroom1

        val elapsedMinutesBathroom1: Long = restDateinMillisBathroom1 / minutesInMilliBathroom1
        restDateinMillisBathroom1 = restDateinMillisBathroom1 % minutesInMilliBathroom1

        val elapsedSecondsBathroom1: Long = restDateinMillisBathroom1 / secondsInMilliBathroom1

        //var tvNewSubTitle4 = ""
        //if(current.bathroom_1_at !== null) tvNewSubTitle4 = localDateTimeFormat.format(current.bathroom_1_at)

        var tvNewSubTitle4 = currentContext.getString(
            R.string.body_data_list_adapter_bathroom_human,
            elapsedDaysBathroom1, elapsedHoursBathroom1, elapsedMinutesBathroom1, elapsedSecondsBathroom1
        )
            //"${tvNewSubTitle4_1} / ${tvNewSubTitle4_2}"

        if(current.breakfast_at !== null) tvNewSubTitle3 = currentContext.getString(R.string.human_breakfast_at, localDateTimeFormat.format(current.breakfast_at!!))

        holder.tvInfoTitle.text = mNumberFormat.format(current.weight) + " " + sharedPref.getString("weightUnitOfMeasure", currentContext.resources.getString(R.string.default_weightUnitOfMeasure))
        holder.tvInfoTitle2.text = mNumberFormat.format(current.blood_pressure_systole_1) +
                sharedPref.getString("defaultBloodPressureSeparator", currentContext.resources.getString(R.string.preferences_activity_default_bp_separator)) +
                mNumberFormat.format(current.blood_pressure_diastole_1)
        holder.tvInfoTitle3.text = tvNewTitle3
        holder.tvInfoSubTitle.text = currentContext.getString(
            R.string.human_time_elapsed,
            elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds
        )
        holder.tvInfoSubTitle2.text = tvNewSubTitle2
        holder.tvInfoSubTitle3.text = tvNewSubTitle3
        holder.tvInfoSubTitle4.text = tvNewSubTitle4
    }

    internal fun setItems(bdItem: List<BodyData>) {
        this.items = bdItem
        //itemsTotal = 0.0
        //this.items.forEach {
        //    itemsTotal += it.item_total
        //}
        notifyDataSetChanged()
    }

    fun getItems(): List<BodyData> {
        return this.items
    }

}