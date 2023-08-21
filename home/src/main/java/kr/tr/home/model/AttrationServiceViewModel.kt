package kr.tr.home.model

import android.util.Log
import androidx.compose.foundation.MutatePriority
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.tr.domain.model.item.GetAttractionKrItem
import kr.tr.domain.repository.AttrationServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-03
 * Time: 오후 3:49
 */

@HiltViewModel
class AttrationServiceViewModel @Inject constructor(
    private val repository: AttrationServiceRepository,
    private val savedStateHandle: SavedStateHandle
    )  : ViewModel(){

    val attrationServiceViewModel  =  repository.getAttrationServiceRepository().cachedIn(viewModelScope)


}