package com.devmina.droid_ant.carpark.occupancyLevel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OccupancyLevelViewModel :ViewModel(){

 private val _totalValue = MutableLiveData<String>()

    val totalValue: LiveData<String>
        get() = _totalValue

    private val _blueBadgeValue = MutableLiveData<String>()

    val blueBadgeValue: LiveData<String>
        get() = _blueBadgeValue

    private val _totalof = MutableLiveData<String>()

    val totalof: LiveData<String>
        get() = _totalof

    fun setTotalValue(totalValue: String){
        _totalValue.postValue(totalValue)
        _totalof.postValue("of $totalValue")
    }
    fun setBadgeValue(badgeValue: String){
        _blueBadgeValue.postValue(badgeValue)
    }


}