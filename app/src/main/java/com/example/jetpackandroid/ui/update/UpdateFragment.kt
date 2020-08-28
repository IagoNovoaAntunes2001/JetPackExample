package com.example.jetpackandroid.ui.update

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.jetpackandroid.databinding.FragmentUpdateBinding
import com.example.jetpackandroid.network.service.HomeApi
import com.example.jetpackandroid.repository.update.UpdateRepository

class UpdateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUpdateBinding.inflate(inflater)
        val application = requireNotNull(activity).application
        val imm: InputMethodManager =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val photoItemReceived = UpdateFragmentArgs.fromBundle(
            requireArguments()
        ).longClickItem

        val repository = UpdateRepository(HomeApi.RETROFIT_SERVICE)

        val viewModelFactory =
            UpdateViewModelFactory(
                application,
                repository,
                photoItemReceived
            )

        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(UpdateViewModel::class.java)

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

        binding.myViewModel = viewModel

        return binding.root
    }

}
