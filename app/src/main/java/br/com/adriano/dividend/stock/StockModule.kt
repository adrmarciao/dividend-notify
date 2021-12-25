package br.com.adriano.dividend.stock

import br.com.adriano.dividend.stock.repository.StockRepository
import br.com.adriano.dividend.stock.repository.StockRepositoryImpl
import br.com.adriano.dividend.stock.repository.datasource.StockLocalDataSource
import br.com.adriano.dividend.stock.view.viewmodel.StockAddViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val stockModule = module {

    //LocalResource
    factory { StockLocalDataSource(androidContext()) }

    //Repository
    factory<StockRepository> { StockRepositoryImpl(get()) }

    viewModel { StockAddViewModel(get()) }

}