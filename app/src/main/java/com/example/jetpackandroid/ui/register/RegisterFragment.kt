package com.example.jetpackandroid.ui.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        val imm: InputMethodManager =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val repository = RegisterRepository(HomeApi.RETROFIT_SERVICE)

        val viewModelFactory =
            RegisterViewModelFactory(
                application,
                repository
            )

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)

        viewModel.statusMessage.observe(this, Observer {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            }
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        })

        viewModel.statusSuccess.observe(this, Observer {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
                imm.hideSoftInputFromWindow(view!!.windowToken, 0)
                findNavController().popBackStack()
            }
        })

        binding.viewModel = viewModel

        return binding.root
    }

}
