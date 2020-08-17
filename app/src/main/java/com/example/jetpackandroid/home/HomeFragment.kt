package com.example.jetpackandroid.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.jetpackandroid.R
import com.example.jetpackandroid.databinding.FragmentHomeBinding
import com.example.jetpackandroid.network.service.HomeApi
import com.example.jetpackandroid.network.service.HomeRepository

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        val repository = HomeRepository(HomeApi.retrofitService)
        val factory = HomeViewModelFactory(repository)
        ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.recyclerViewPhotos.adapter = HomeAdapter(HomeAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        whichViewWillCall(item)
        return super.onOptionsItemSelected(item)
    }

    private fun whichViewWillCall(item: MenuItem) {
        when(item.itemId) {
            R.id.show_rules -> this.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToRulesFragment())
            R.id.about -> this.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
        }
    }
}
