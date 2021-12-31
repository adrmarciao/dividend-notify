package br.com.adriano.dividend.schedule.repository

import br.com.adriano.dividend.core.util.RegexPattern
import br.com.adriano.dividend.schedule.repository.datasource.ScheduleLocalDataSource
import br.com.adriano.dividend.schedule.repository.datasource.ScheduleRemoteDataSource
import br.com.adriano.statusinvest.data.response.ProventResponse
import br.com.adriano.statusinvest.data.response.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.time.LocalDateTime

internal class ScheduleRepositoryImpl(
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource,
    private val scheduleLocalDataSource: ScheduleLocalDataSource
) : ScheduleRepository {
    override suspend fun getEvents(year: Int, month: Int, type: String) =
        scheduleRemoteDataSource.getEvents(year, month, type)

    override fun saveProventsResponse(provents: List<ProventResponse>) =
        scheduleLocalDataSource.save(provents)

    override fun retrieveProventsRepose(): List<ProventResponse> =
        scheduleLocalDataSource.retrieve()

    override suspend fun requestSchedule(stockList: List<String>): List<ProventResponse> {
        return coroutineScope {
            val proventList = mutableListOf<ProventResponse>()
            val dateTime = LocalDateTime.now()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
            val deferred = listOf(     //
                async { requestEvents("Acoes", dateTime, proventList) },
                async { requestEvents("FundosImobiliarios", dateTime, proventList) },
                async {
                    requestEvents(
                        "Acoes", dateTime
                            .plusMonths(1), proventList
                    )
                },
                async {
                    requestEvents(
                        "FundosImobiliarios", dateTime
                            .plusMonths(1), proventList
                    )
                }
            )
            deferred.awaitAll()
            return@coroutineScope proventList.filter { it.dateCom >= dateTime }.filter {
                stockList.contains(
                    it.code.replace(
                        RegexPattern.REMOVE_NUMBER_PATTERN, ""
                    ).uppercase()
                )
            }
        }
    }

    private suspend fun requestEvents(
        type: String,
        time: LocalDateTime,
        provents: MutableList<ProventResponse>
    ) {
        when (val result =
            getEvents(time.year, time.monthValue, type)) {
            is Result.Success -> {
                provents.addAll(result.data.provents)
            }
        }
    }
}