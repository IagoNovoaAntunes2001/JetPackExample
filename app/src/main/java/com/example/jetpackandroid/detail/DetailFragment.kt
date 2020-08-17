package com.example.jetpackandroid.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.jetpackandroid.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)

        val photosProperty = DetailFragmentArgs.fromBundle(requireArguments()).seleceted

        val viewModelFactory = DetailViewModelFactory(application, photosProperty)

        binding.setLifecycleOwner(this)
        binding.myViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        return binding.root
    }
}
