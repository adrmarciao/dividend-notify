package br.com.adriano.dividend.screener.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import br.com.adriano.dividend.databinding.ListScreenerItemBinding
import br.com.adriano.dividend.databinding.ListScreenerTitleBinding
import br.com.adriano.statusinvest.data.response.AdvancedSearchResponse
import com.bumptech.glide.Glide
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat


class ScreenerAdapter(private val onClick: (AdvancedSearchResponse) -> Unit,
                      private val statusClick: (AdvancedSearchResponse) -> Unit) : RecyclerView.Adapter<ScreenerAdapter.ScreenerViewHolder>() {

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
            (list[position] as AdvancedSearchResponse).run {
                holder.itemView.setOnClickListener {
                    onClick.invoke(this)
                }
                holder.biding.itemScreenerTick.text = ticker
                holder.biding.itemScreenerName.text = companyName
                holder.biding.itemScreenerValue.text = "R$: $price"
                holder.biding.itemScreenerDirectStatusInvest.setOnClickListener {
                    statusClick.invoke(this)
                }
                Glide
                    .with(holder.biding.itemScreenerImage.context)
                    .load("https://statusinvest.com.br" +
                            "/img/company/avatar/${companyId}.jpg")
                    .into(holder.biding.itemScreenerImage)
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