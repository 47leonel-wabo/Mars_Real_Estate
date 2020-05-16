package com.aiwamob.marsrealestate.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.aiwamob.marsrealestate.databinding.FragmentDetailBinding
import com.aiwamob.marsrealestate.model.MarsProperty

/**
 * A simple [Fragment] subclass.
 */
class Detail : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val arg = arguments?.let {DetailArgs.fromBundle(it)}
        val marsProperty: MarsProperty = arg!!.selectedMarsPrp
        val viewModelFactory = DetailViewModelFactory(marsProperty, application)
        val detailViewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.apply {
            viewModel = detailViewModel
        }

        return binding.root
    }

}
