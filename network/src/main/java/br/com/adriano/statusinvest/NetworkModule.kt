package br.com.adriano.statusinvest

import br.com.adriano.statusinvest.adapter.LocalDateTimeTypeAdapter
import br.com.adriano.statusinvest.api.StatusInvestResource
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


val networkModule = module {

    single {
        GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter)
            .create()
    }

    single {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            builder.addInterceptor(interceptor)
        }

        Retrofit.Builder()
            .baseUrl("https://statusinvest.com.br/acao/")
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }


    single {
        val retrofit: Retrofit = get(Retrofit::class)
        retrofit.create(StatusInvestResource::class.java)
    }

}

