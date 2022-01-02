package br.com.adriano.dividend.core.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import br.com.adriano.dividend.R
import br.com.adriano.dividend.core.application.DividendApplication
import org.koin.android.ext.android.inject
import kotlin.reflect.KClass

open class BaseFragment<T : ViewBinding>(private val cls: KClass<T>) : Fragment() {

    protected val progressLiveData: DividendApplication.ProgressLiveData by inject()

    private var _binding: T? = null
    protected val binding get() = _binding

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        progressLiveData.postValue(false)
        val method = cls.members.filter { it.name == INFLATE_METHOD_KEY }[1]
        _binding =  method.call(inflater, container, false) as T?
        return _binding!!.root
    }

    fun mainNavController() = activity
        ?.findNavController(R.id.nav_host_fragment_content_main)

    companion object {
        const val INFLATE_METHOD_KEY = "inflate"
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}