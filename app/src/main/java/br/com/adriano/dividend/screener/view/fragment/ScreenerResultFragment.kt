package br.com.adriano.dividend.screener.view.fragment

import android.os.Bundle
import android.view.View
import br.com.adriano.dividend.R
import br.com.adriano.dividend.core.view.fragment.BaseFragment
import br.com.adriano.dividend.databinding.FragmentScreenerResultBinding
import br.com.adriano.dividend.screener.view.adapter.ScreenerAdapter
import br.com.adriano.dividend.screener.view.viewmodel.ScreenerViewModel
import br.com.adriano.statusinvest.data.response.AdvancedSearchResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScreenerResultFragment :
    BaseFragment<FragmentScreenerResultBinding>(FragmentScreenerResultBinding::class) {

    private val screenerViewModel: ScreenerViewModel by viewModel()
    private val adapter = ScreenerAdapter(::click)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            screenFound.adapter = adapter
            arguments?.apply {
                val args = ScreenerResultFragmentArgs.fromBundle(this)
                screenerViewModel.requestScreener(
                    args.setor,
                    args.subSector,
                    args.segment
                ).observe(viewLifecycleOwner, {
                    adapter.addAll(it)
                })

            }
        }
    }

    private fun click(advancedSearchResponse: AdvancedSearchResponse) {
        with(Bundle(), {
            putString("stock", advancedSearchResponse.ticker)
            mainNavController()?.navigate(R.id.nav_to_fair_price, this)
        })
    }

}