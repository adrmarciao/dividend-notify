package br.com.adriano.dividend.core.application

import android.app.Application
import androidx.lifecycle.MutableLiveData
import br.com.adriano.dividend.price.priceModule
import br.com.adriano.dividend.schedule.scheduleModule
import br.com.adriano.dividend.screener.screenerModule
import br.com.adriano.dividend.stock.stockModule
import br.com.adriano.statusinvest.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class DividendApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DividendApplication)
            modules(
                networkModule,
                stockModule,
                scheduleModule,
                priceModule,
                screenerModule,
                module {
                    single { ProgressLiveData() }
                }
            )
        }
    }

    class ProgressLiveData: MutableLiveData<Boolean>()
}