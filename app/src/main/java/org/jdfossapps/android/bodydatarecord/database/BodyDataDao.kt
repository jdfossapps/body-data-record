package org.jdfossapps.android.bodydatarecord.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update


@Dao
interface BodyDataDao {

    @Query("SELECT * from body_data_table ORDER BY user_id, body_data_date desc")
    fun getSortedBodyDataItems(): LiveData<List<BodyData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(bodyData: BodyData)

    @Query("DELETE FROM body_data_table")
    fun deleteAll()

    // SQLite is case insensitive
    @Query("SELECT * from body_data_table where name like '%'||:query||'%' " +
            "or description like '%'||:query||'%' ORDER BY id ASC")
    fun getBodyDataItemsFromSearchBar(query: String?): LiveData<List<BodyData>>

    @Delete
    fun delete(bodyData: BodyData)

    @Delete
    fun deleteBodyDataItems(bodyData: List<BodyData>)

    @Update
    fun update(bodyData: BodyData)
}