package org.jdfossapps.android.bodydatarecord.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class BodyRepository(private val bodyDataDao: BodyDataDao) {

    val allBodyDataItems: LiveData<List<BodyData>> = bodyDataDao.getSortedBodyDataItems()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(bodyDataItem: BodyData) {
        bodyDataDao.insert(bodyDataItem)
    }

    fun bodyDataItemsFromSearchBar(query: String): LiveData<List<BodyData>> {
        return bodyDataDao.getBodyDataItemsFromSearchBar(query)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(bodyDataItem: BodyData) {
        bodyDataDao.delete(bodyDataItem)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deletebodyDataItems(bodyDataItems: List<BodyData>) {
        bodyDataDao.deleteBodyDataItems(bodyDataItems)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(bodyDataItem: BodyData) {
        bodyDataDao.update(bodyDataItem)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllbodyDataItems() {
        bodyDataDao.deleteAll()
    }

}