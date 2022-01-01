package br.com.adriano.dividend.price.view.fragment

import android.os.Bundle
import android.text.Editable
import android.view.View
import br.com.adriano.dividend.core.view.fragment.BaseFragment
import br.com.adriano.dividend.databinding.FragmentFairPriceBinding
import br.com.adriano.dividend.price.view.viewmodel.FairPriceViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

class FairPriceFragment : BaseFragment<FragmentFairPriceBinding>(FragmentFairPriceBinding::class) {

    private val fairPriceViewModel: FairPriceViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val args = FairPriceFragmentArgs.fromBundle(it)
            binding?.edtStock?.text = Editable.Factory.getInstance().newEditable(args.stock)
        }
        binding?.apply {
            calcFairPrice.setOnClickListener {
                fairPriceViewModel.calcFairPrice(
                    edtStock.text.toString(),
                    edtCount.text.toString().toInt(),
                    startDate.text.toString().run {
                        if (isBlank() || isEmpty())
                            LocalDate.now().year.toLong()
                        else
                            toLong()
                    }
                )
            }
        }

        fairPriceViewModel.resultValue.observe(viewLifecycleOwner, {
            binding?.fairPriceResult?.text = it
        })
    }
}