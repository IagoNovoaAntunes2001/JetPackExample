package com.example.jetpackandroid.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jetpackandroid.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUpdateBinding.inflate(inflater)

        val photoItemReceived = UpdateFragmentArgs.fromBundle(requireArguments()).longClickItem
        Toast.makeText(activity, photoItemReceived.title, Toast.LENGTH_SHORT).show()

        return binding.root
    }

}
