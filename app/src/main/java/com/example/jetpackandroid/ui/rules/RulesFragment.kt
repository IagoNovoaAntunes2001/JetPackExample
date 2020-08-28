package com.example.jetpackandroid.ui.rules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackandroid.databinding.FragmentRulesBinding

class RulesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRulesBinding.inflate(inflater)
        return binding.root
    }
}
