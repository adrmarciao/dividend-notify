package br.com.adriano.dividend.price.repository

import br.com.adriano.statusinvest.data.response.CompanyProventReponse
import br.com.adriano.statusinvest.data.response.Result

interface FairPriceRepository {
    suspend fun requestTickerProvents(ticker: String): Result<CompanyProventReponse>
}