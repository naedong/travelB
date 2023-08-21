package kr.tr.travelbproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 4:30
 */

@HiltViewModel
class ChangeIcon @Inject constructor(
) : ViewModel() {
    private val _value = MutableLiveData(false)
    val value : LiveData<Boolean> = _value

    fun toggleValue(){
      when(_value.value){
          true -> _value.value = false
          else -> _value.value = true
      }
    }



}