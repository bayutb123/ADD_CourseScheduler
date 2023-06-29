package com.dicoding.courseschedule.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.data.DataRepository
import com.dicoding.courseschedule.util.QueryType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DataRepository) : ViewModel() {

    private val _queryType = MutableLiveData<QueryType>()

    init {
        _queryType.value = QueryType.CURRENT_DAY
    }

    fun setQueryType(queryType: QueryType) {
        _queryType.value = queryType
    }

    fun getTodaySchedule(): Course? {
        var course: Course ?= Course(id=17, courseName="hh", day=1, startTime="04:30", endTime="05:30", lecturer="vf", note="vg")
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getTodaySchedule()
            course = data[0]
            Log.d("QTest", course.toString())
        }
        return course
    }
}
