package br.com.adriano.dividend.screener.repository

import br.com.adriano.statusinvest.data.request.AdvancedSearchRequest
import br.com.adriano.statusinvest.data.response.AdvancedSearchResponse
import br.com.adriano.statusinvest.data.response.NetworkResult

interface ScreenerRepository {
    suspend fun advancedSearch(advancedSearchRequest: AdvancedSearchRequest):
            NetworkResult<List<AdvancedSearchResponse>>
}