package com.example.directorycompanies.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.usdTrigger.viewmodel.OverviewViewModel
import com.example.directorycompanies.databinding.FragmentListCompaniesBinding
import com.example.directorycompanies.repository.network.entity.CompanyPreview
import timber.log.Timber
import javax.inject.Inject


class OverviewFragment : Fragment()  {
    private lateinit var quotesObserver: Observer<List<CompanyPreview>>;
    private var _binding: FragmentListCompaniesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<OverviewViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        Timber.i("fragment attach")
        super.onAttach(context)
        (activity as MainActivity).viewComponent.inject(this)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentListCompaniesBinding.inflate(inflater, container, false)
        val view = binding.root;
        viewModel.init();
        val adapter = CompaniesLiniearAdapter()
        binding.recyclerview.adapter = adapter


        quotesObserver = Observer<List<CompanyPreview>> { companies ->
            companies?.let {companies->
                Timber.i("add new data in adapter")
                Timber.i("$companies")
                adapter.submitList(companies)
            }
        }
        viewModel.quotes.observe(viewLifecycleOwner, quotesObserver);
        Timber.i("onCreateViewFragment")
        return view
    }
}
