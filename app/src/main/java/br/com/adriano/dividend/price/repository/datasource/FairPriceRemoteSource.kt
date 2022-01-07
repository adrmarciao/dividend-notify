package br.com.adriano.dividend.price.repository.datasource

import br.com.adriano.statusinvest.api.StatusInvestResource
import br.com.adriano.statusinvest.data.response.CompanyProventReponse
import br.com.adriano.statusinvest.data.response.NetworkResult
import br.com.adriano.statusinvest.data.response.PayoutResultResponse


class FairPriceRemoteSource(private val statusInvestResource: StatusInvestResource) {

    suspend fun requestTickerProvents(ticker: String): NetworkResult<CompanyProventReponse> {
        return try {
            val result = statusInvestResource.tickerProvents(ticker)
            NetworkResult.Success(result.body()!!)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    suspend fun requestPayout(ticker: String): NetworkResult<PayoutResultResponse> {
        return try {
            val result = statusInvestResource.payoutResult(ticker)
            NetworkResult.Success(result.body()!!)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

}