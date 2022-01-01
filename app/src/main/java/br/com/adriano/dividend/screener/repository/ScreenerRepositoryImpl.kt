package br.com.adriano.dividend.screener.repository

import br.com.adriano.dividend.screener.repository.datasource.ScreenerRemoteDataSource
import br.com.adriano.statusinvest.data.request.AdvancedSearchRequest
import br.com.adriano.statusinvest.data.response.AdvancedSearchResponse
import br.com.adriano.statusinvest.data.response.NetworkResult

class ScreenerRepositoryImpl(private val screenerRemoteDataSource: ScreenerRemoteDataSource):
    ScreenerRepository {
        override suspend fun advancedSearch(advancedSearchRequest: AdvancedSearchRequest):
                NetworkResult<List<AdvancedSearchResponse>> =
            try {
                val result = screenerRemoteDataSource.advancedSearch(advancedSearchRequest)
                NetworkResult.Success(result.body()!!)
            } catch (e: Exception) {
                NetworkResult.Error(e)
            }
}