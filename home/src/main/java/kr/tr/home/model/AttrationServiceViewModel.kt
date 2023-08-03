package kr.tr.home.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.tr.domain.repository.AttrationServiceRepository
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-03
 * Time: 오후 3:49
 */

@HiltViewModel
class AttrationServiceViewModel @Inject constructor(
    private val repository: AttrationServiceRepository
)  : ViewModel(){
    val attrationServiceViewModel = repository.getAttrationServiceRepository().cachedIn(viewModelScope)
}