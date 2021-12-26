package br.com.adriano.dividend.schedule.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.adriano.dividend.core.util.RegexPattern
import br.com.adriano.dividend.core.view.viewmodel.BaseViewModel
import br.com.adriano.dividend.schedule.repository.ScheduleRepository
import br.com.adriano.dividend.stock.repository.StockRepository
import br.com.adriano.statusinvest.data.response.ProventResponse
import br.com.adriano.statusinvest.data.response.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ScheduleListViewModel(private val scheduleRepository: ScheduleRepository,
                            private val stockRepository: StockRepository) : BaseViewModel() {

    private val stockList: List<String> by lazy { stockRepository.retrieve() }

    fun requestDataComSchedule() = MutableLiveData<List<ProventResponse>>().apply {
        viewModelScope.launch {
            val retrieveList = scheduleRepository.retrieveProventsRepose()
            if (retrieveList.isEmpty()) {
                val newList = scheduleRepository.requestSchedule(stockList)
                scheduleRepository.saveProventsResponse(newList)
                postValue(newList)
            } else {
                postValue(retrieveList)
            }
        }

    }
}