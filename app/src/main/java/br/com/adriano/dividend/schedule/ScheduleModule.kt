package br.com.adriano.dividend.schedule

import br.com.adriano.dividend.schedule.repository.ScheduleRepository
import br.com.adriano.dividend.schedule.repository.ScheduleRepositoryImpl
import br.com.adriano.dividend.schedule.repository.datasource.ScheduleRemoteDataSource
import br.com.adriano.dividend.schedule.view.viewmodel.ScheduleListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scheduleModule = module {

    //Repository
    factory<ScheduleRepository> { ScheduleRepositoryImpl(get(), get()) }

    //RemoteResource
    factory { ScheduleRemoteDataSource(get()) }

    viewModel { ScheduleListViewModel(get())}

}