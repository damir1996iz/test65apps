package com.damikkg.test65appsfragments.presentation.welcome

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.damikkg.test65appsfragments.domain.base.UseCase
import com.damikkg.test65appsfragments.domain.usecases.UpdateDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val updateDatabaseUseCase: UpdateDatabaseUseCase,
    application: Application
) : AndroidViewModel(application){

    sealed class Status{
        object Loading:Status()
        object Error:Status()
        object Completed:Status()
    }

    val state = MutableStateFlow<Status>(Status.Loading)

    fun updateDatabase()
    {
        state.value = Status.Loading
        updateDatabaseUseCase(UseCase.None(), viewModelScope)
        {
            it.fold(
                fnL = {
                    state.value = Status.Error
                    return@fold false
                },
                fnR = {
                    state.value = Status.Completed
                    return@fold true
                }
            )
        }
    }
}