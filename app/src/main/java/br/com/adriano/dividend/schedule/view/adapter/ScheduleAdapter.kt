package br.com.adriano.dividend.schedule.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.adriano.dividend.R
import br.com.adriano.dividend.core.util.ApplicationDateFormat
import br.com.adriano.dividend.databinding.ListScheduleItemDateBinding
import br.com.adriano.dividend.databinding.ListScheduleItemStockBinding
import br.com.adriano.statusinvest.data.response.ProventResponse
import com.bumptech.glide.Glide
import java.time.LocalDateTime

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleAdapterViewHolder>() {

    private val scheduleList = mutableListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == CODE_TYPE) {
            ScheduleStockViewHolder(
                ListScheduleItemStockBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            ScheduleDataViewHolder(
                ListScheduleItemDateBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

    override fun onBindViewHolder(holderData: ScheduleAdapterViewHolder, position: Int) {
        holderData.bind(scheduleList[position])
    }

    override fun getItemCount() = scheduleList.size


    override fun getItemViewType(position: Int): Int {
        return when (scheduleList[position]) {
            is ScheduleItem -> CODE_TYPE
            else -> DATE_TYPE
        }
    }

    fun addAll(list: List<ProventResponse>) {
        scheduleList.clear()
        list.map { it.dateCom }.toHashSet().sorted().forEach { date ->
            scheduleList.add(ScheduleDate(date))
            scheduleList.addAll(list
                .asSequence()
                .filter { it.dateCom == date }
                .map { ScheduleItem(it.code, it.companyName, it.companyId, it.paymentDividend, it.resultAbsoluteValue) }
            )
        }
        notifyItemRangeChanged(0, scheduleList.size)
    }

    abstract class ScheduleAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: Any)
    }

    inner class ScheduleDataViewHolder(private val binding: ListScheduleItemDateBinding) :
        ScheduleAdapterViewHolder(binding.root) {
        override fun bind(item: Any) {
            if (item is ScheduleDate) {
                binding.itemDate.text = item.date.format(ApplicationDateFormat
                    .BRAZIL_SIMPLE_DATE_FORMATTER)
            }
        }
    }

    inner class ScheduleStockViewHolder(private val binding: ListScheduleItemStockBinding) :
        ScheduleAdapterViewHolder(binding.root) {
        override fun bind(item: Any) {
            if (item is ScheduleItem) {
                Glide
                    .with(itemView)
                    .load("https://statusinvest.com.br" +
                            "/img/company/avatar/${item.companyId}.jpg")
                    .into(binding.itemStockImage)
                binding.itemStock.text = item.code
                binding.itemStockName.text = item.companyName
                val payment = item.paymentDateTime
                    ?.run { format(ApplicationDateFormat.BRAZIL_SIMPLE_DATE_FORMATTER) } ?: ""
                binding.itemStockPaymentValue.text = itemView
                    .resources.getString(R.string.schedule_payment_value, item.paymentValue)
                binding.itemStockPayment.text = itemView
                    .resources.getString(R.string.schedule_payment, payment)
            }
        }
    }

    data class ScheduleItem(
        val code: String,
        val companyName: String,
        val companyId: Long,
        val paymentDateTime: LocalDateTime?,
        val paymentValue: String?
    )

    data class ScheduleDate(
        val date: LocalDateTime
    )

    companion object {
        private const val CODE_TYPE = 0
        private const val DATE_TYPE = 1
    }
}