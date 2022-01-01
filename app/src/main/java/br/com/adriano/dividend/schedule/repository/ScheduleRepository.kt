package br.com.adriano.dividend.schedule.repository

import br.com.adriano.statusinvest.data.response.EventsResponse
import br.com.adriano.statusinvest.data.response.ProventResponse
import br.com.adriano.statusinvest.data.response.NetworkResult

interface ScheduleRepository {
    suspend fun getEvents(year: Int, month: Int, type: String): NetworkResult<EventsResponse>
    fun saveProventsResponse(provents: List<ProventResponse>)
    fun retrieveProventsRepose(): List<ProventResponse>
    suspend fun requestSchedule(stockList: List<String>): List<ProventResponse>
}