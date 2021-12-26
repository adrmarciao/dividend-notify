package br.com.adriano.dividend.schedule.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import br.com.adriano.dividend.core.view.fragment.BaseFragment
import br.com.adriano.dividend.databinding.FragmentScheduleListBinding
import br.com.adriano.dividend.schedule.view.adapter.ScheduleAdapter
import br.com.adriano.dividend.schedule.view.viewmodel.ScheduleListViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleListFragment :
    BaseFragment<FragmentScheduleListBinding>(FragmentScheduleListBinding::class) {

    private val scheduleListViewModel: ScheduleListViewModel by viewModel()
    private val adapter = ScheduleAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        binding?.apply {
            scheduleList.adapter = adapter
            requestDataComSchedule()
        }
    }

    private fun setupObservers() {
        scheduleListViewModel.failure.observe(viewLifecycleOwner, {
            binding?.apply {
                progress.isVisible = false
                root.let { it1 ->
                        Snackbar.make(it1, "Falha de conexão!", Snackbar.LENGTH_LONG)
                            .setAction("Repetir") {
                                requestDataComSchedule()
                            }.show()
                    }
            }
        })
    }

    private fun requestDataComSchedule() {
        binding?.progress?.isVisible = true
        scheduleListViewModel.requestDataComSchedule()
            .observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    binding?.apply {
                        holderEmpty.isVisible = false
                        progress.isVisible = false
                        adapter.addAll(it)
                    }
                } else {
                    binding?.apply {
                        holderEmpty.isVisible = true
                        progress.isVisible = false
                    }
                }
            })
    }
}