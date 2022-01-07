package br.com.adriano.dividend.price.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.adriano.dividend.core.view.viewmodel.BaseViewModel
import br.com.adriano.dividend.price.repository.FairPriceRepository
import br.com.adriano.statusinvest.data.response.NetworkResult
import kotlinx.coroutines.launch

class FairPriceViewModel(private val fairPriceRepository: FairPriceRepository) : BaseViewModel() {

    val resultValueLiveData = MutableLiveData<String>()
    val probabilityLiveData = MutableLiveData<ProfitData>()

    fun calcFairPrice(tick: String, count: Int, startYear: Long) {
        viewModelScope.launch {
            when (val result = fairPriceRepository.requestTickerProvents(tick)) {
                is NetworkResult.Success -> {
                    val list =
                        result.data.assetEarningsYearlyModels.sortedWith(compareByDescending { it.year })
                    var sum = 0F
                    val value = if (count < list.size) count else list.size
                    var sumText = ""
                    for (i in 0 until value) {
                        if (list[i].year <= startYear) {
                            sumText += "${list[i].value} (${list[i].year})\n"
                            sum = sum.plus(list[i].value).toFloat()
                        }
                    }
                    sumText += "= ${((sum / value) / 0.06).toFloat()}"
                    resultValueLiveData.postValue(sumText)
                }
                is NetworkResult.Error -> {
                    _failure.postValue(result.exception)
                }
            }
        }
        requestPayoutData(tick)
    }

    private fun requestPayoutData(tick: String) {
        viewModelScope.launch {
            when (val result = fairPriceRepository.requestPayout(tick)) {
                is NetworkResult.Success -> {
                    var countNegativeEquity = 0
                    result.data.chart.series.lucroLiquido.forEach {
                        if (it.value < 0) {
                            ++countNegativeEquity
                        }
                    }
                    val size = result.data.chart.series.lucroLiquido.size
                    val probability =
                        (1 - (countNegativeEquity.toDouble() /
                                size.toDouble())) * 100
                    probabilityLiveData.postValue(ProfitData(probability, size))
                }
                is NetworkResult.Error -> {
                    _failure.postValue(result.exception)
                }
            }
        }
    }

    data class ProfitData(
        val value: Double,
        val quantity: Int
    )
}