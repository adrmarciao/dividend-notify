package br.com.adriano.dividend.screener.view.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import br.com.adriano.dividend.R
import br.com.adriano.dividend.core.view.fragment.BaseFragment
import br.com.adriano.dividend.databinding.FragmentScreenerBinding

class SelectScreenerFragment :
    BaseFragment<FragmentScreenerBinding>(FragmentScreenerBinding::class) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            screenerBancos.setOnClickListener {
                navigateToScreenResult("4", "24", "54")
            }
            screenerEletricidade.setOnClickListener {
                navigateToScreenResult("10", "44", "85")
            }
            screenerSaneamento.setOnClickListener {
                navigateToScreenResult("10", "43", "84")
            }
            screenerSeguros.setOnClickListener {
                navigateToScreenResult("4", "26", "59")
            }
            screenerTelecom.setOnClickListener {
                navigateToScreenResult("9", "42", "83")
            }
            screenerFinanceiros.setOnClickListener {
                navigateToScreenResult("4", "", "")
            }
        }
    }

    private fun navigateToScreenResult(sector: String, subSector: String, segment: String) {
        with(Bundle(), {
            putString("setor", sector)
            putString("subSector", subSector)
            putString("segment", segment)
            findNavController()
                .navigate(R.id.to_screener_result, this)
        })
    }
}