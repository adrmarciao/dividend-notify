package br.com.adriano.dividend.schedule.repository.datasource

import br.com.adriano.statusinvest.api.StatusInvestResource
import br.com.adriano.statusinvest.data.response.EventsResponse
import br.com.adriano.statusinvest.data.response.NetworkResult
import java.lang.Exception

internal class ScheduleRemoteDataSource(private val statusInvestResource: StatusInvestResource) {

    suspend fun getEvents(year: Int, month: Int, type: String): NetworkResult<EventsResponse> {
        return try {
            NetworkResult.Success(statusInvestResource.getEvents(year, month, type).body()!!)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }
}