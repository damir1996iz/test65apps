package com.damikkg.test65appsfragments.presentation.speclist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damikkg.test65appsfragments.domain.base.UseCase
import com.damikkg.test65appsfragments.domain.models.Speciality
import com.damikkg.test65appsfragments.domain.usecases.GetSpecialityListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class SpecialtyViewModel @Inject constructor(
    private val getSpecialityListUseCase: GetSpecialityListUseCase
) : ViewModel(){

    sealed class Status
    {
        object Loading : Status()
        object Error : Status()
        object NothingFound : Status()
        data class Completed(val result:List<Speciality>): Status()
    }

    val state = MutableStateFlow<Status>(Status.Loading)

    init {
        loadSpecialityList()
    }

    private fun loadSpecialityList()
    {
        state.value = Status.Loading
        getSpecialityListUseCase(UseCase.None(), viewModelScope)
        {
            it.fold(
                fnL = {
                    state.value = Status.Error
                    return@fold false
                },
                fnR = { result->
                    if(result.isEmpty())
                        state.value = Status.NothingFound
                    else
                        state.value = Status.Completed(result)
                    return@fold true
                }
            )
        }
    }
}