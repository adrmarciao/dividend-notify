package br.com.adriano.dividend.core.service

import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import br.com.adriano.dividend.R
import br.com.adriano.dividend.core.view.activity.MainActivity
import br.com.adriano.dividend.schedule.repository.ScheduleRepository
import br.com.adriano.dividend.stock.repository.StockRepository
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class StatusInvestUpdateJobService : KoinComponent, JobService() {

    private val scheduleRepository: ScheduleRepository by inject()
    private val stockRepository: StockRepository by inject()
    private val stockList: List<String> by lazy { stockRepository.retrieve() }

    private val serviceScope = CoroutineScope(Dispatchers.IO)

    @DelicateCoroutinesApi
    override fun onStartJob(p0: JobParameters?): Boolean {
        serviceScope
            .launch {
                val cacheList = scheduleRepository.retrieveProventsRepose()
                if (stockList.isEmpty()) {
                    jobFinished(p0, true)
                    return@launch
                }
                val newList = scheduleRepository.requestSchedule(stockList)
                if ((newList == cacheList).not()) {
                    scheduleRepository.saveProventsResponse(newList)
                    createNotification("Status Invest", "Calendário Atualizado...")
                }
                jobFinished(p0, true)
            }
        return true
    }

    private fun createNotification(title: String, message: String) {
        with(NotificationManagerCompat.from(applicationContext)) {
            val intent = Intent(applicationContext, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(applicationContext, 0, intent, 0)

            val builder = NotificationCompat.Builder(applicationContext, "dividend")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            notify(321, builder.build())
        }
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }

    companion object {
        fun scheduleJob(context: Context, componentName: ComponentName) {
            val jobScheduler =
                context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            if (jobScheduler.getPendingJob(123) == null) {
                Log.i("teste", "testeeeeeeeeeeeeeeeee")
                val jobInfo =
                    JobInfo.Builder(123, componentName)
                val job = jobInfo.setRequiresCharging(false)
                    .setMinimumLatency(TimeUnit.SECONDS.toMillis(1))
                    .setPersisted(true)
                    .setOverrideDeadline(TimeUnit.MINUTES.toMillis(5))
                    .setBackoffCriteria(TimeUnit.HOURS.toMillis(1), JobInfo.BACKOFF_POLICY_LINEAR)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).build()
                jobScheduler.schedule(job)
            }
        }
    }
}