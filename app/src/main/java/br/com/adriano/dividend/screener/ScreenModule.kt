package br.com.adriano.dividend.screener

import br.com.adriano.dividend.screener.repository.ScreenerRepository
import br.com.adriano.dividend.screener.repository.ScreenerRepositoryImpl
import br.com.adriano.dividend.screener.repository.datasource.ScreenerRemoteDataSource
import br.com.adriano.dividend.screener.view.viewmodel.ScreenerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val screenerModule = module {

    //Repository
    single <ScreenerRepository> { ScreenerRepositoryImpl(get()) }

    //RemoteResource
    single  { ScreenerRemoteDataSource(get(), get()) }

    viewModel { ScreenerViewModel(get()) }

}