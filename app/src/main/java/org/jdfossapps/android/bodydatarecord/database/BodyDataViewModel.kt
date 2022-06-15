package org.jdfossapps.android.bodydatarecord.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers

import androidx.lifecycle.MutableLiveData
import android.text.TextUtils
import androidx.lifecycle.Transformations
import kotlinx.coroutines.launch

class BodyDataViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BodyRepository

    val allBodyDataItems: LiveData<List<BodyData>>
    private val searchStringLiveData = MutableLiveData<String>("")

    fun searchBodyDatasChanged(query: String) {
        searchStringLiveData.value = query
    }

    init {
        val bodyDataDao = BodyDataRecordDatabase.getDatabase(application, viewModelScope).bodyDataDao()
        repository = BodyRepository(bodyDataDao)

        allBodyDataItems = Transformations.switchMap(searchStringLiveData) { query ->
            if (TextUtils.isEmpty(query)) {
                repository.allBodyDataItems
            } else {
                repository.bodyDataItemsFromSearchBar(query)
            }
        }
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(bodyDataItem: BodyData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(bodyDataItem)
    }

    /**
     * Launching a new coroutine to delete the data in a non-blocking way
     */
    fun remove(bodyDataItem: BodyData) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(bodyDataItem)
    }

    /**
     * Launching a new coroutine to delete the data in a non-blocking way
     */
    fun removeBodyDatas(bodyDataItems: List<BodyData>) = viewModelScope.launch(Dispatchers.IO) {
        repository.deletebodyDataItems(bodyDataItems)
    }

    /**
     * Launching a new coroutine to update the data in a non-blocking way
     */
    fun update(bodyDataItem: BodyData) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(bodyDataItem)
    }

    /**
     * Launching a new coroutine to delete the data in a non-blocking way
     */
    fun removeAllBodyData() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllbodyDataItems()
    }

}