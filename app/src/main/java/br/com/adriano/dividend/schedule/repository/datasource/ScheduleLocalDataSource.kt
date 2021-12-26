package br.com.adriano.dividend.schedule.repository.datasource

import android.content.Context
import br.com.adriano.statusinvest.data.response.ProventResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal class ScheduleLocalDataSource(context: Context, private val gson: Gson) {

    private val sharedPreferences = context.getSharedPreferences(
        ScheduleLocalDataSource::javaClass.name,
        Context.MODE_PRIVATE
    )

    fun save(provents: List<ProventResponse>) {
        sharedPreferences.edit().putString(
            PROVENTS_PREFERENCE_KEY,
            gson.toJson(provents)
        ).apply()
    }

    fun retrieve(): List<ProventResponse> = try {
        gson.fromJson(
                sharedPreferences.getString(PROVENTS_PREFERENCE_KEY, ""),
                object : TypeToken<List<ProventResponse>>() {}.type
            )
    } catch (e: Exception) {
        emptyList()
    }

    companion object {
        private const val PROVENTS_PREFERENCE_KEY = "provents"
    }
}