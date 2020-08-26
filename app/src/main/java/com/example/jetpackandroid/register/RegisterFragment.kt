package com.example.jetpackandroid.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.jetpackandroid.databinding.FragmentRegisterBinding
import com.example.jetpackandroid.network.service.HomeApi
import com.example.jetpackandroid.repository.register.RegisterRepository

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater)
        val application = requireNotNull(activity).application

        val repository = RegisterRepository(HomeApi.RETROFIT_SERVICE)

        val viewModelFactory = RegisterViewModelFactory(application, repository)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel::class.java)

        viewModel.statusMessage.observe(this, Observer {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.statusSuccess.observe(this, Observer {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        })

        binding.viewModel = viewModel

        return binding.root
    }

}
