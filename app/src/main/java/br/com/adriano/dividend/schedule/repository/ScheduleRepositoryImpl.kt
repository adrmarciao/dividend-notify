package br.com.adriano.dividend.schedule.repository

import br.com.adriano.dividend.schedule.repository.datasource.ScheduleRemoteDataSource
import br.com.adriano.dividend.stock.repository.StockRepository

internal class ScheduleRepositoryImpl(
    private val stockRepository: StockRepository,
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource
) : ScheduleRepository {
    override suspend fun getEvents(year: Int, month: Int, type: String) =
        scheduleRemoteDataSource.getEvents(year, month, type)
    override fun retrieveStock(): List<String> = stockRepository.retrieve()
}