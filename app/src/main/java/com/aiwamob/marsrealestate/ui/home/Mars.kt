package com.aiwamob.marsrealestate.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.aiwamob.marsrealestate.R
import com.aiwamob.marsrealestate.databinding.FragmentMarsBinding

/**
 * A simple [Fragment] subclass.
 */
class Mars : Fragment() {

    private lateinit var binding: FragmentMarsBinding

    private val marsViewModel: MarsViewModel by lazy {
        ViewModelProvider(this).get(MarsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mars, container, false)
        binding.apply {
            lifecycleOwner = this@Mars
            viewModel = marsViewModel
        }
        setHasOptionsMenu(true)

        return binding.root
    }

}
