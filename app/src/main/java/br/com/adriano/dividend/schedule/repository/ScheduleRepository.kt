package br.com.adriano.dividend.schedule.repository

import br.com.adriano.statusinvest.data.response.Events
import br.com.adriano.statusinvest.data.response.Result

interface ScheduleRepository {
    suspend fun getEvents(year: Int, month: Int, type: String): Result<Events>
    fun retrieveStock(): List<String>
}