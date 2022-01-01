package br.com.adriano.dividend.price.repository

import br.com.adriano.statusinvest.data.response.CompanyProventReponse
import br.com.adriano.statusinvest.data.response.NetworkResult

interface FairPriceRepository {
    suspend fun requestTickerProvents(ticker: String): NetworkResult<CompanyProventReponse>
}