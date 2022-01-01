package br.com.adriano.dividend.screener.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import br.com.adriano.dividend.databinding.ListScreenerItemBinding
import br.com.adriano.dividend.databinding.ListScreenerTitleBinding
import br.com.adriano.statusinvest.data.response.AdvancedSearchResponse

class ScreenerAdapter(private val onClick: (AdvancedSearchResponse) -> Unit) : RecyclerView.Adapter<ScreenerAdapter.ScreenerViewHolder>() {

    private val list = mutableListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenerViewHolder {
        if (viewType == 0) {
            return ScreenerTitleViewHolder(
                ListScreenerTitleBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        } else {
            return ScreenerItemViewHolder(
                ListScreenerItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ScreenerViewHolder, position: Int) {
        if (holder is ScreenerTitleViewHolder) {
            holder.biding.screenerTitle.text = list[position] as String
        } else if (holder is ScreenerItemViewHolder) {
            holder.biding.screenerItem.text = (list[position] as AdvancedSearchResponse).run {
                holder.itemView.setOnClickListener {
                    onClick.invoke(this)
                }
                return@run "$ticker - $companyName"
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is String) 0 else 1
    }

    fun add(data: String) {
        list.add(data)
        notifyItemInserted(0)
    }

    fun addAll(data: List<AdvancedSearchResponse>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    open class ScreenerViewHolder(itemView: ViewBinding) : RecyclerView.ViewHolder(itemView.root)

    class ScreenerTitleViewHolder(val biding: ListScreenerTitleBinding) : ScreenerViewHolder(biding)

    class ScreenerItemViewHolder(val biding: ListScreenerItemBinding) : ScreenerViewHolder(biding)

}