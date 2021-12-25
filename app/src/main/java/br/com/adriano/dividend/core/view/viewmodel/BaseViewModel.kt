package br.com.adriano.dividend.core.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    protected val _failure = MutableLiveData<Exception>()
    val failure: LiveData<Exception> = _failure

}