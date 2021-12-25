package br.com.adriano.dividend.stock.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.adriano.dividend.stock.repository.StockRepository

internal class StockAddViewModel(private val stockRepository: StockRepository) : ViewModel() {
    private val _stocksLive = MutableLiveData<List<String>>()
        get() {
            field.value ?: let {
                field.postValue(stockRepository.retrieve())
            }
            return field
        }
    val stocksLive: LiveData<List<String>> = _stocksLive

    fun save(stockList: List<String>) {
        stockRepository.save(stockList)
    }

}