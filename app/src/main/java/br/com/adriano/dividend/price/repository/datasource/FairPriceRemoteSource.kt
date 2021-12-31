package br.com.adriano.dividend.price.repository.datasource

import br.com.adriano.statusinvest.api.StatusInvestResource
import br.com.adriano.statusinvest.data.response.CompanyProventReponse
import br.com.adriano.statusinvest.data.response.Result


class FairPriceRemoteSource(private val statusInvestResource: StatusInvestResource) {

    suspend fun requestTickerProvents(ticker: String): Result<CompanyProventReponse> {
        return try {
            val result = statusInvestResource.tickerProvents(ticker)
            Result.Success(result.body()!!)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}