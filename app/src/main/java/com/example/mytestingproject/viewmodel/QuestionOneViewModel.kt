package com.example.mytestingproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytestingproject.network.NetworkCall
import com.example.mytestingproject.repo.QuestionOneRepo
import com.example.mytestingproject.utils.ApiResponse
import com.example.mytestingproject.utils.Event
import com.example.mytestingproject.utils.getEmojiByUnicode
import com.example.mytestingproject.utils.isNetworkAvailable
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class QuestionOneViewModel(application: Application) : AndroidViewModel(application) {

    private val networkCall = NetworkCall.getInstance()
    private val repo = QuestionOneRepo(networkCall.getRetrofit())


    private val _marvelHero = MutableLiveData<ApiResponse<out Any>>()
    val marvelHero: LiveData<ApiResponse<out Any>>
        get() = _marvelHero


    private val _event = MutableLiveData<Event<String>>()
    val event: LiveData<Event<String>>
        get() = _event

    init {
        if (application.isNetworkAvailable()) {
            getHeroes()
        } else {
            addError("No Internet Connection Found!! ${getEmojiByUnicode(0x1F615)}")
        }
    }

    private fun getHeroes() {
        viewModelScope.launch {
            repo.getMarvelHeroes().collectLatest {
                _marvelHero.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    private fun addError(msg: String) {
        _event.postValue(Event(msg))
    }

}