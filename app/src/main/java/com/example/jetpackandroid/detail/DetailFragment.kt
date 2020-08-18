package com.example.jetpackandroid.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.jetpackandroid.databinding.FragmentDetailBinding
import com.example.jetpackandroid.network.service.HomeApi
import com.example.jetpackandroid.repository.detail.DetailRepository

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)

        val photosProperty = DetailFragmentArgs.fromBundle(requireArguments()).seleceted

        val repository =
            DetailRepository(HomeApi.RETROFIT_SERVICE)

        val viewModelFactory = DetailViewModelFactory(application, photosProperty, repository)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        viewModel.statusMessage.observe(this, Observer { messsage ->
            messsage.getContentIfNotHandled()?.let {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        })

        binding.setLifecycleOwner(this)
        binding.myViewModel = viewModel

        return binding.root
    }
}
