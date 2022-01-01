package br.com.adriano.dividend.screener.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.adriano.dividend.core.view.viewmodel.BaseViewModel
import br.com.adriano.dividend.screener.repository.ScreenerRepository
import br.com.adriano.statusinvest.data.request.AdvancedSearchRequest
import br.com.adriano.statusinvest.data.request.IndicatorRequest
import br.com.adriano.statusinvest.data.response.AdvancedSearchResponse
import br.com.adriano.statusinvest.data.response.NetworkResult
import kotlinx.coroutines.launch
import kotlin.math.sqrt

class ScreenerViewModel(private val screenerRepository: ScreenerRepository) : BaseViewModel() {

    private val screenerLiveData = MutableLiveData<List<AdvancedSearchResponse>>()

    fun requestScreener(sector: String, subSector: String, segment: String):
            MutableLiveData<List<AdvancedSearchResponse>> {
        screenerLiveData.value ?: apply {
            viewModelScope.launch {
                when (val result = screenerRepository.advancedSearch(
                    AdvancedSearchRequest(
                        sector,
                        subSector,
                        segment,
                        pl = IndicatorRequest(item2 = 15.0)
                    )
                )) {
                    is NetworkResult.Success -> {
                        val response = result.data.map {
                            it.fairPriceBG = sqrt(it.lpa * it.vpa * 22.5)
                            it.margemBG = (it.fairPriceBG!! / it.price) - 1
                            it
                        }.filter { it.margemBG!! > 0.2 }
                        screenerLiveData.postValue(response)
                    }
                    is NetworkResult.Error -> {
                        _failure.postValue(result.exception)
                    }
                }
            }
        }
        return screenerLiveData
    }
}