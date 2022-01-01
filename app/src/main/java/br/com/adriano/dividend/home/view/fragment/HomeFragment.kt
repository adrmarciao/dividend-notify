package br.com.adriano.dividend.home.view.fragment

import android.os.Bundle
import android.view.View
import br.com.adriano.dividend.R
import br.com.adriano.dividend.core.view.fragment.BaseFragment
import br.com.adriano.dividend.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::class) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            addStock.setOnClickListener {
                mainNavController()?.navigate(R.id.nav_to_stock_single)
            }

            scheduleStock.setOnClickListener {
                mainNavController()?.navigate(R.id.nav_to_schedule_single)
            }

            fairPrice.setOnClickListener {
                mainNavController()?.navigate(R.id.nav_to_fair_price_single)
            }

            screener.setOnClickListener {
                mainNavController()?.navigate(R.id.nav_to_screener_single)
            }
        }
    }
}