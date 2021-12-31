package br.com.adriano.dividend.price.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.adriano.dividend.core.view.viewmodel.BaseViewModel
import br.com.adriano.dividend.price.repository.FairPriceRepository
import br.com.adriano.statusinvest.data.response.Result
import kotlinx.coroutines.launch

class FairPriceViewModel(private val fairPriceRepository: FairPriceRepository) : BaseViewModel() {

    val resultValue = MutableLiveData<String>()

    fun calcFairPrice(tick: String, count: Int, startYear: Long) {
        viewModelScope.launch {
            when(val result = fairPriceRepository.requestTickerProvents(tick)) {
                is Result.Success -> {
                    val list = result.data.assetEarningsYearlyModels.sortedWith(compareByDescending { it.year })
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
                    resultValue.postValue(sumText)
                }
                is Result.Error -> {
                    //TODO Implementar fluxo de erro
                }
            }
        }
    }
}