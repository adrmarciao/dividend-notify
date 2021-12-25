package br.com.adriano.dividend.stock.repository

interface StockRepository {
    fun save(stocks: List<String>)
    fun retrieve(): List<String>
}