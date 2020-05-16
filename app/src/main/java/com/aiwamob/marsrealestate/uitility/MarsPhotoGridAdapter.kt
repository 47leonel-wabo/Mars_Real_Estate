package com.aiwamob.marsrealestate.uitility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aiwamob.marsrealestate.R
import com.aiwamob.marsrealestate.databinding.GridviewItemBinding
import com.aiwamob.marsrealestate.model.MarsProperty

class MarsPhotoGridAdapter(private val onClickListener: OnClickListener): ListAdapter<MarsProperty, MarsPhotoGridAdapter.MarsPropertyViewHolder>(MarsDiffCallBack) {

    //private lateinit var gridviewItemBinding: GridviewItemBinding

    companion object MarsDiffCallBack : DiffUtil.ItemCallback<MarsProperty>(){
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class MarsPropertyViewHolder(private val itemBinding: GridviewItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(marsProperty: MarsProperty){
            itemBinding.apply {
                property = marsProperty
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        //gridviewItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.gridview_item, parent, false)
        return MarsPropertyViewHolder(GridviewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }
}
class OnClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit){
    fun onClick(marsProperty: MarsProperty) = clickListener(marsProperty)
}