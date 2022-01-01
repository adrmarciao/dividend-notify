package br.com.adriano.dividend.screener.repository.datasource

import br.com.adriano.statusinvest.api.StatusInvestResource
import br.com.adriano.statusinvest.data.request.AdvancedSearchRequest
import com.google.gson.Gson

class ScreenerRemoteDataSource(
    private val statusInvestResource: StatusInvestResource,
    private val gson: Gson
) {

    suspend fun advancedSearch(advancedSearchRequest: AdvancedSearchRequest) =
        statusInvestResource.advancedSearch(gson.toJson(advancedSearchRequest))

}