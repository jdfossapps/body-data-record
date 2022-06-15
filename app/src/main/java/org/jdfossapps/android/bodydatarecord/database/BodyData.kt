package org.jdfossapps.android.bodydatarecord.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import android.os.Parcelable
import android.os.Parcel

import java.util.Date

// Parcel extensions
fun Parcel.writeDate(date: Date?) {
    writeLong(date?.time ?: -1)
}

fun Parcel.readDate(): Date? {
    val long = readLong()
    return if (long != -1L) Date(long) else null
}
// End Parcel extensions

@Entity(tableName = "body_data_table")
data class BodyData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "user_id") val user_id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "weight") var weight: Double,
    @ColumnInfo(name = "height") var height: Double,
    @ColumnInfo(name = "waist_size") var waist_size: Double,
    @ColumnInfo(name = "chest_size") var chest_size: Double,
    @ColumnInfo(name = "blood_pressure_systole_1") var blood_pressure_systole_1: Double,
    @ColumnInfo(name = "blood_pressure_diastole_1") var blood_pressure_diastole_1: Double,
    @ColumnInfo(name = "blood_pressure_systole_2") var blood_pressure_systole_2: Double,
    @ColumnInfo(name = "blood_pressure_diastole_2") var blood_pressure_diastole_2: Double,
    @ColumnInfo(name = "blood_pressure_systole_3") var blood_pressure_systole_3: Double,
    @ColumnInfo(name = "blood_pressure_diastole_3") var blood_pressure_diastole_3: Double,
    @ColumnInfo(name = "blood_pressure_systole_4") var blood_pressure_systole_4: Double,
    @ColumnInfo(name = "blood_pressure_diastole_4") var blood_pressure_diastole_4: Double,
    @ColumnInfo(name = "blood_pressure_systole_5") var blood_pressure_systole_5: Double,
    @ColumnInfo(name = "blood_pressure_diastole_5") var blood_pressure_diastole_5: Double,
    @ColumnInfo(name = "temperature_1") var temperature_1: Double,
    @ColumnInfo(name = "temperature_2") var temperature_2: Double,
    @ColumnInfo(name = "temperature_3") var temperature_3: Double,
    @ColumnInfo(name = "temperature_4") var temperature_4: Double,
    @ColumnInfo(name = "temperature_5") var temperature_5: Double,
    @ColumnInfo(name = "temperature_1_at") var temperature_1_at: Date?,
    @ColumnInfo(name = "temperature_2_at") var temperature_2_at: Date?,
    @ColumnInfo(name = "temperature_3_at") var temperature_3_at: Date?,
    @ColumnInfo(name = "temperature_4_at") var temperature_4_at: Date?,
    @ColumnInfo(name = "temperature_5_at") var temperature_5_at: Date?,
    @ColumnInfo(name = "blood_glucose_1") var blood_glucose_1: Double,
    @ColumnInfo(name = "blood_glucose_2") var blood_glucose_2: Double,
    @ColumnInfo(name = "blood_glucose_3") var blood_glucose_3: Double,
    @ColumnInfo(name = "blood_glucose_4") var blood_glucose_4: Double,
    @ColumnInfo(name = "blood_glucose_5") var blood_glucose_5: Double,
    @ColumnInfo(name = "resting_heart_rate_1") var resting_heart_rate_1: Double,
    @ColumnInfo(name = "resting_heart_rate_2") var resting_heart_rate_2: Double,
    @ColumnInfo(name = "resting_heart_rate_3") var resting_heart_rate_3: Double,
    @ColumnInfo(name = "resting_heart_rate_4") var resting_heart_rate_4: Double,
    @ColumnInfo(name = "resting_heart_rate_5") var resting_heart_rate_5: Double,
    @ColumnInfo(name = "blood_pressure_1_at") var blood_pressure_1_at: Date?,
    @ColumnInfo(name = "blood_pressure_2_at") var blood_pressure_2_at: Date?,
    @ColumnInfo(name = "blood_pressure_3_at") var blood_pressure_3_at: Date?,
    @ColumnInfo(name = "blood_pressure_4_at") var blood_pressure_4_at: Date?,
    @ColumnInfo(name = "blood_pressure_5_at") var blood_pressure_5_at: Date?,
    @ColumnInfo(name = "pill_1_at") var pill_1_at: Date?,
    @ColumnInfo(name = "pill_2_at") var pill_2_at: Date?,
    @ColumnInfo(name = "pill_3_at") var pill_3_at: Date?,
    @ColumnInfo(name = "pill_4_at") var pill_4_at: Date?,
    @ColumnInfo(name = "pill_5_at") var pill_5_at: Date?,
    @ColumnInfo(name = "cholesterol") var cholesterol: Double,
    @ColumnInfo(name = "c_reactive_protein") var c_reactive_protein: Double,
    @ColumnInfo(name = "exercise_minutes") var exercise_minutes: Double,
    @ColumnInfo(name = "exercise_description") var exercise_description: String,
    @ColumnInfo(name = "last_meal_at") var last_meal_at: Date?,
    @ColumnInfo(name = "breakfast_at") var breakfast_at: Date?,
    @ColumnInfo(name = "slept_at") var slept_at: Date?,
    @ColumnInfo(name = "wakeup_at") var wakeup_at: Date?,
    @ColumnInfo(name = "bathroom_1_at") var bathroom_1_at: Date?,
    @ColumnInfo(name = "bathroom_2_at") var bathroom_2_at: Date?,
    @ColumnInfo(name = "body_data_date") var body_data_date: Date?,
    @ColumnInfo(name = "modified_at") var modified_at: Date?,
    @ColumnInfo(name = "created_at") var created_at: Date?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,

        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),

        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),

        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDate(),
        parcel.readDate(),
        parcel.readDate(),
        parcel.readDate(),
        parcel.readDate(),

        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),

        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),

        parcel.readDate(),
        parcel.readDate(),
        parcel.readDate(),
        parcel.readDate(),
        parcel.readDate(),

        parcel.readDate(),
        parcel.readDate(),
        parcel.readDate(),
        parcel.readDate(),
        parcel.readDate(),

        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!,

        parcel.readDate(),
        parcel.readDate(),

        parcel.readDate(),
        parcel.readDate(),

        parcel.readDate(),
        parcel.readDate(),

        parcel.readDate(),
        parcel.readDate(),
        parcel.readDate()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeInt(id)
        parcel.writeInt(user_id)
        parcel.writeString(name)
        parcel.writeString(description)

        parcel.writeDouble(weight)
        parcel.writeDouble(height)
        parcel.writeDouble(waist_size)
        parcel.writeDouble(chest_size)

        parcel.writeDouble(blood_pressure_systole_1)
        parcel.writeDouble(blood_pressure_diastole_1)
        parcel.writeDouble(blood_pressure_systole_2)
        parcel.writeDouble(blood_pressure_diastole_2)
        parcel.writeDouble(blood_pressure_systole_3)
        parcel.writeDouble(blood_pressure_diastole_3)
        parcel.writeDouble(blood_pressure_systole_4)
        parcel.writeDouble(blood_pressure_diastole_4)
        parcel.writeDouble(blood_pressure_systole_5)
        parcel.writeDouble(blood_pressure_diastole_5)

        parcel.writeDouble(temperature_1)
        parcel.writeDouble(temperature_2)
        parcel.writeDouble(temperature_3)
        parcel.writeDouble(temperature_4)
        parcel.writeDouble(temperature_5)
        parcel.writeDate(temperature_1_at)
        parcel.writeDate(temperature_2_at)
        parcel.writeDate(temperature_3_at)
        parcel.writeDate(temperature_4_at)
        parcel.writeDate(temperature_5_at)

        parcel.writeDouble(blood_glucose_1)
        parcel.writeDouble(blood_glucose_2)
        parcel.writeDouble(blood_glucose_3)
        parcel.writeDouble(blood_glucose_4)
        parcel.writeDouble(blood_glucose_5)

        parcel.writeDouble(resting_heart_rate_1)
        parcel.writeDouble(resting_heart_rate_2)
        parcel.writeDouble(resting_heart_rate_3)
        parcel.writeDouble(resting_heart_rate_4)
        parcel.writeDouble(resting_heart_rate_5)

        parcel.writeDate(blood_pressure_1_at)
        parcel.writeDate(blood_pressure_2_at)
        parcel.writeDate(blood_pressure_3_at)
        parcel.writeDate(blood_pressure_4_at)
        parcel.writeDate(blood_pressure_5_at)

        parcel.writeDate(pill_1_at)
        parcel.writeDate(pill_2_at)
        parcel.writeDate(pill_3_at)
        parcel.writeDate(pill_4_at)
        parcel.writeDate(pill_5_at)

        parcel.writeDouble(cholesterol)
        parcel.writeDouble(c_reactive_protein)
        parcel.writeDouble(exercise_minutes)
        parcel.writeString(exercise_description)

        parcel.writeDate(last_meal_at)
        parcel.writeDate(breakfast_at)

        parcel.writeDate(slept_at)
        parcel.writeDate(wakeup_at)

        parcel.writeDate(bathroom_1_at)
        parcel.writeDate(bathroom_2_at)

        parcel.writeDate(body_data_date)
        parcel.writeDate(modified_at)
        parcel.writeDate(created_at)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BodyData> {
        override fun createFromParcel(parcel: Parcel): BodyData {
            return BodyData(parcel)
        }

        override fun newArray(size: Int): Array<BodyData?> {
            return arrayOfNulls(size)
        }
    }

}