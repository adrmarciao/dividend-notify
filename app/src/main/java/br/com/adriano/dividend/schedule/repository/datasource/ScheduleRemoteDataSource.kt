package br.com.adriano.dividend.schedule.repository.datasource

import br.com.adriano.statusinvest.api.StatusInvestResource
import br.com.adriano.statusinvest.data.response.Events
import br.com.adriano.statusinvest.data.response.Result
import java.lang.Exception

internal class ScheduleRemoteDataSource(private val statusInvestResource: StatusInvestResource) {

    suspend fun getEvents(year: Int, month: Int, type: String): Result<Events> {
        return try {
            Result.Success(statusInvestResource.getEvents(year, month, type).body()!!)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}