package br.com.adriano.dividend.stock.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.adriano.dividend.databinding.ListStockItemBinding

class StockAdapter : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    val stockList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StockViewHolder(
            ListStockItemBinding
                .inflate(
                    LayoutInflater
                        .from(parent.context), parent, false
                )
        )

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(stockList[position])
    }

    override fun getItemCount(): Int = stockList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addStock(item: String) {
        if (stockList.contains(item).not()) {
            stockList.add(item)
            notifyItemInserted(stockList.size - 1)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAllStock(stocks: List<String>) {
        stockList.addAll(stocks)
        notifyItemRangeChanged(0,stockList.size)
    }

    inner class StockViewHolder(val binding: ListStockItemBinding) :
        RecyclerView.ViewHolder(
            binding
                .root
        ) {
        fun bind(stock: String) {
            binding.stockName.text = stock
            binding.removeStock.setOnClickListener {
                notifyItemRemoved(stockList.indexOf(stock).also(stockList::removeAt))
            }
        }
    }
}