package com.example.directorycompanies.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.directorycompanies.databinding.FragmentListCompaniesBinding
import com.example.directorycompanies.repository.network.entity.CompanyPreview
import com.example.directorycompanies.view.OverviewFragmentDirections.Companion.actionOverviewFragmentToDetailFragment
import com.example.directorycompanies.viewmodel.OverviewViewModel
import timber.log.Timber
import javax.inject.Inject


class OverviewFragment : Fragment()  {
    private lateinit var companiesObserver: Observer<List<CompanyPreview>>;
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
        val adapter = CompaniesLiniearAdapter( CompanyListener {  companyId ->   viewModel.onDetailClicked(companyId)} )
        binding.recyclerview.adapter = adapter

        companiesObserver = Observer<List<CompanyPreview>> { companies ->
            companies?.let {values->
                Timber.i("add new data in adapter")
                Timber.i("$companies")
                adapter.submitList(values)
            }
        }
        viewModel.companies.observe(viewLifecycleOwner, companiesObserver);
        // Add an Observer on the state variable for Navigating when and item is clicked.
        viewModel.navigateToIdDetail.observe(viewLifecycleOwner, Observer { it ->
            it?.let {companyId ->

                val action = OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(companyId)
                this.findNavController().navigate(action)
            }
        })
        Timber.i("onCreateViewFragment")
        return view
    }
}
