package br.com.adriano.dividend.schedule.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.adriano.dividend.core.util.RegexPattern
import br.com.adriano.dividend.core.view.viewmodel.BaseViewModel
import br.com.adriano.dividend.schedule.repository.ScheduleRepository
import br.com.adriano.statusinvest.data.response.ProventResponse
import br.com.adriano.statusinvest.data.response.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ScheduleListViewModel(private val scheduleRepository: ScheduleRepository) : BaseViewModel() {

    private val stockList: List<String> by lazy { scheduleRepository.retrieveStock() }

    fun requestDataComSchedule() = MutableLiveData<List<ProventResponse>>().apply {
        viewModelScope.launch {
            val proventList = mutableListOf<ProventResponse>()
            val dateTime = LocalDateTime.now()
            val deferred = listOf(     //
                async { requestEvents("Acoes", dateTime, proventList) },
                async { requestEvents("FundosImobiliarios", dateTime, proventList) },
                async { requestEvents("Acoes", dateTime
                    .plusMonths(1), proventList) },
                async { requestEvents("FundosImobiliarios", dateTime
                    .plusMonths(1), proventList) }
            )
            deferred.awaitAll()
            postValue(proventList.filter { it.dateCom >= dateTime }.filter {
                stockList.contains(
                    it.code.replace(
                        RegexPattern.REMOVE_NUMBER_PATTERN, ""
                    ).uppercase()
                )
            })
        }

    }

    private suspend fun requestEvents(type: String, time: LocalDateTime, provents: MutableList<ProventResponse>) {
        when (val result =
            scheduleRepository.getEvents(time.year, time.monthValue, type)) {
            is Result.Success -> {
                provents.addAll(result.data.provents)
            }
            is Result.Error -> {
                _failure.postValue(result.exception)
            }
        }
    }
}