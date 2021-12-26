package br.com.adriano.dividend.schedule

import br.com.adriano.dividend.schedule.repository.ScheduleRepository
import br.com.adriano.dividend.schedule.repository.ScheduleRepositoryImpl
import br.com.adriano.dividend.schedule.repository.datasource.ScheduleLocalDataSource
import br.com.adriano.dividend.schedule.repository.datasource.ScheduleRemoteDataSource
import br.com.adriano.dividend.schedule.view.viewmodel.ScheduleListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scheduleModule = module {

    //Repository
    single <ScheduleRepository> { ScheduleRepositoryImpl(get(), get()) }

    //RemoteResource
    single  { ScheduleRemoteDataSource(get()) }

    //LocalResource
    single { ScheduleLocalDataSource(androidContext(), get()) }

    viewModel { ScheduleListViewModel(get(), get())}

}