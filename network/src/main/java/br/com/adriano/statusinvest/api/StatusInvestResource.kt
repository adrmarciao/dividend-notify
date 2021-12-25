package br.com.adriano.statusinvest.api

import br.com.adriano.statusinvest.data.response.Events
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StatusInvestResource {
    @GET("/calendar/getevents")
    suspend fun getEvents(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("type") type: String = "Acoes",
        @Query("country") country: String = "Brasil",
    ): Response<Events>

}