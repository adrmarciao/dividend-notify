package br.com.adriano.dividend.stock.repository

import br.com.adriano.dividend.stock.repository.datasource.StockLocalDataSource
import br.com.adriano.dividend.schedule.repository.datasource.ScheduleRemoteDataSource

internal class StockRepositoryImpl(
    private val stockLocalDataSource: StockLocalDataSource,
) : StockRepository {

    override fun save(stocks: List<String>) {
        stockLocalDataSource.save(stocks)
    }

    override fun retrieve(): List<String> = stockLocalDataSource.retrieve()

}