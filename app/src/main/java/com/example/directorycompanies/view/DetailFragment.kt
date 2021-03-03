package com.example.directorycompanies.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.directorycompanies.R
import com.example.directorycompanies.databinding.CompanyDetailBinding
import com.example.directorycompanies.databinding.FragmentListCompaniesBinding
import com.example.directorycompanies.repository.network.entity.CompanyDetail
import com.example.directorycompanies.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import timber.log.Timber
import javax.inject.Inject


class DetailFragment : Fragment()  {
    private lateinit var companyObserver: Observer<CompanyDetail>;
    private var _binding: CompanyDetailBinding? = null
    private val binding get() = _binding!!

    //val companyNumber = safeArgs.idCompanyNumber


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        Timber.i("fragment attach")
        super.onAttach(context)
        (activity as MainActivity).viewComponent.inject(this)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = CompanyDetailBinding.inflate(inflater, container, false)
        val view = binding.root;
        val idCompany = arguments?.getInt("idCompany")
        viewModel.init(idCompany!!);

        companyObserver = Observer<CompanyDetail> { it ->
            it?.let {company->
                Timber.i("company load")
                binding.name.text = company.name
                binding.description.text = company.description
                binding.phone.text = company.phone
                binding.www.text = company.www
                Picasso.get()
                        .load("https://lifehack.studio/test_task/"+company.img)
                        .placeholder(R.drawable.ic_image_place_holder)
                        .placeholder(R.drawable.ic_broken_image)
                        .into(binding.img)
            }
        }
        viewModel.company.observe(viewLifecycleOwner, companyObserver);
        Timber.i("onCreateViewFragment")
        return view
    }
}
