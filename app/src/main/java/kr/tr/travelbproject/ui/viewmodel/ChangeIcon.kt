package kr.tr.travelbproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 4:30
 */
class ChangeIcon : ViewModel() {
    private val _value = MutableLiveData(0)
    val value : LiveData<Int> = _value

    fun increaseOne(){
        _value.value = 1
    }

    fun decreaseZero(){
        _value.value = 0
    }
}