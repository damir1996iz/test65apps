package com.damikkg.test65appsfragments.presentation.personslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damikkg.test65appsfragments.domain.models.Person
import com.damikkg.test65appsfragments.domain.models.Speciality
import com.damikkg.test65appsfragments.domain.usecases.GetPersonsListBySpecialityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class PersonsListViewModel @Inject constructor(
    private val getPersonsListBySpecialityUseCase: GetPersonsListBySpecialityUseCase
) : ViewModel(){
    sealed class Status
    {
        object Loading:Status()
        object Error:Status()
        data class Completed(val result:List<Person>):Status()
    }

    val state = MutableStateFlow<Status>(Status.Loading)

    fun getPersonsListBySpeciality(speciality: Speciality)
    {
        state.value = Status.Loading

        getPersonsListBySpecialityUseCase(speciality, viewModelScope)
        {
            it.fold(
                fnR = { result->
                    state.value = Status.Completed(result)
                    return@fold true
                },
                fnL = {
                    state.value = Status.Error
                    return@fold false
                }
            )
        }
    }
}