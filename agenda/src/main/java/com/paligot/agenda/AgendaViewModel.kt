package com.paligot.agenda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paligot.shared.extensions.plusAssign
import com.paligot.shared.repositories.DevFestLilleRepository
import com.paligot.shared.repositories.Talk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AgendaViewModel(private val repository: DevFestLilleRepository) : ViewModel() {
    private val subscription = CompositeDisposable()

    private val _talks: MutableLiveData<List<Talk>> by lazy {
        MutableLiveData<List<Talk>>()
    }

    val talks: LiveData<List<Talk>>
        get() = _talks

    init {
        updateTalks()
    }

    private fun updateTalks() {
        subscription += repository.talks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _talks.setValue(it)
            }, {
                Timber.e(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass != AgendaViewModel::class.java) {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
            return AgendaViewModel(DevFestLilleRepository.create()) as T
        }
    }
}
