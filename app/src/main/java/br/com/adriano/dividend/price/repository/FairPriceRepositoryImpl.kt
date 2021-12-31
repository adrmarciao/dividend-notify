package br.com.adriano.dividend.price.repository

import br.com.adriano.dividend.price.repository.datasource.FairPriceRemoteSource
import br.com.adriano.statusinvest.data.response.CompanyProventReponse
import br.com.adriano.statusinvest.data.response.Result

class FairPriceRepositoryImpl(private val fairPriceRemoteSource: FairPriceRemoteSource):
    FairPriceRepository {

        override suspend fun requestTickerProvents(ticker: String):
                Result<CompanyProventReponse> = fairPriceRemoteSource.requestTickerProvents(ticker)

}