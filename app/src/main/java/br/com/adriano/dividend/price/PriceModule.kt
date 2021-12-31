package br.com.adriano.dividend.price

import br.com.adriano.dividend.price.repository.FairPriceRepository
import br.com.adriano.dividend.price.repository.FairPriceRepositoryImpl
import br.com.adriano.dividend.price.repository.datasource.FairPriceRemoteSource
import br.com.adriano.dividend.price.view.viewmodel.FairPriceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val priceModule = module {

    //Repository
    single<FairPriceRepository> { FairPriceRepositoryImpl(get()) }

    //RemoteResource
    single { FairPriceRemoteSource(get()) }
    //LocalResource

    viewModel { FairPriceViewModel(get()) }

}