package br.com.adriano.dividend.stock.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.adriano.dividend.core.view.fragment.BaseFragment
import br.com.adriano.dividend.databinding.FragmentStockAddBinding
import br.com.adriano.dividend.stock.view.adapter.StockAdapter
import br.com.adriano.dividend.stock.view.viewmodel.StockAddViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StockAddFragment :
    BaseFragment<FragmentStockAddBinding>(FragmentStockAddBinding::class) {

    private val stockAddViewModel: StockAddViewModel by viewModel()

    private val stockAdapter = StockAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserves()
        setupView()
    }

    private fun setupView() {
        binding?.apply {
            stockList.adapter = stockAdapter

            //adicionar stock
            addStock.setOnClickListener {
                stockAdapter.addStock(edtStock.text.toString().uppercase())
            }

            //save stock
            saveStock.setOnClickListener {
                stockAddViewModel.save(stockAdapter.stockList)
            }
        }
    }

    private fun setupObserves() {
        stockAddViewModel.stocksLive.observe(viewLifecycleOwner, stockAdapter::addAllStock)
    }

}