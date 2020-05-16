package com.aiwamob.marsrealestate.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aiwamob.marsrealestate.R
import com.aiwamob.marsrealestate.databinding.FragmentMarsBinding
import com.aiwamob.marsrealestate.uitility.MarsPhotoGridAdapter
import com.aiwamob.marsrealestate.uitility.OnClickListener

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
        binding = FragmentMarsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.apply {
            viewModel = marsViewModel
            marsRecycler.adapter = MarsPhotoGridAdapter(OnClickListener {
                marsViewModel.displayPropertyDetail(it)
            })
        }

        marsViewModel.apply {
            selectedProp.observe(viewLifecycleOwner, Observer {
                if (it != null){
                    findNavController().navigate(MarsDirections.actionMarsToDetail(it))
                    this.displayPropertyDetailComplete()
                }
            })
        }
        setHasOptionsMenu(true)

        return binding.root
    }

}
