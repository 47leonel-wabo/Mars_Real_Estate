package com.aiwamob.marsrealestate.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aiwamob.marsrealestate.R
import com.aiwamob.marsrealestate.databinding.FragmentMarsBinding
import com.aiwamob.marsrealestate.network.MarsApiFilter
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.mars_option, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        marsViewModel.updateProperties(
            when(item.itemId){
                R.id.rentItems -> MarsApiFilter.SHOW_RENT
                R.id.saleItems -> MarsApiFilter.SHOW_BUY
                else -> MarsApiFilter.SHOW_ALL
            }
        )
        return true
    }
}
