package com.example.jetpackandroid.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackandroid.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.recyclerViewPhotos.adapter = HomeAdapter()

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

}
