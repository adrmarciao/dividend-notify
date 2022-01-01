package br.com.adriano.dividend.price.repository

import br.com.adriano.dividend.price.repository.datasource.FairPriceRemoteSource
import br.com.adriano.statusinvest.data.response.CompanyProventReponse
import br.com.adriano.statusinvest.data.response.NetworkResult

class FairPriceRepositoryImpl(private val fairPriceRemoteSource: FairPriceRemoteSource):
    FairPriceRepository {

        override suspend fun requestTickerProvents(ticker: String):
                NetworkResult<CompanyProventReponse> = fairPriceRemoteSource.requestTickerProvents(ticker)

}