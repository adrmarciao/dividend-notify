package br.com.adriano.dividend.price.repository

import br.com.adriano.dividend.price.repository.datasource.FairPriceRemoteSource
import br.com.adriano.statusinvest.data.response.CompanyProventReponse
import br.com.adriano.statusinvest.data.response.NetworkResult
import br.com.adriano.statusinvest.data.response.PayoutResultResponse

class FairPriceRepositoryImpl(private val fairPriceRemoteSource: FairPriceRemoteSource):
    FairPriceRepository {

        override suspend fun requestTickerProvents(ticker: String):
                NetworkResult<CompanyProventReponse> = fairPriceRemoteSource.requestTickerProvents(ticker)

    override suspend fun requestPayout(ticker: String):
            NetworkResult<PayoutResultResponse> = fairPriceRemoteSource.requestPayout(ticker)

}