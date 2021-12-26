package br.com.adriano.dividend.stock.repository

import br.com.adriano.dividend.stock.repository.datasource.StockLocalDataSource

internal class StockRepositoryImpl(
    private val stockLocalDataSource: StockLocalDataSource,
) : StockRepository {

    override fun save(stocks: List<String>) {
        stockLocalDataSource.save(stocks)
    }

    override fun retrieve(): List<String> = stockLocalDataSource.retrieve()

}