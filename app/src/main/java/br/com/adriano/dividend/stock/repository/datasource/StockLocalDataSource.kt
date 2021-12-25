package br.com.adriano.dividend.stock.repository.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal class StockLocalDataSource(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        StockLocalDataSource::javaClass.name,
        Context.MODE_PRIVATE
    )

    fun save(stocks: List<String>) {
        sharedPreferences.edit().putString(
            STOCK_PREFERENCE_KEY,
            Gson().toJson(stocks)
        ).apply()
    }

    fun retrieve() = try {
        Gson()
            .fromJson(
                sharedPreferences.getString(STOCK_PREFERENCE_KEY, ""),
                object : TypeToken<List<String>>() {}.type
            )
    } catch (e: Exception) {
        emptyList<String>()
    }

    companion object {
        private const val STOCK_PREFERENCE_KEY = "stocks"
    }
}