package com.example.jetpackandroid.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackandroid.databinding.ListItemBinding
import com.example.jetpackandroid.network.model.PhotosItem

//private val onClickListener: OnClickListener
class HomeAdapter(): ListAdapter<PhotosItem, HomeAdapter.HomeViewHolder>(DiffCallBack) {
   
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        return HomeViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val photosItem = getItem(position)
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(PhotosItem)
//        }
        holder.bind(photosItem)
    }

    class HomeViewHolder(private var binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(photosItem: PhotosItem) {
            binding.photoItem = photosItem
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<PhotosItem>() {
        override fun areItemsTheSame(oldItem: PhotosItem, newItem: PhotosItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotosItem, newItem: PhotosItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

//    class OnClickListener(val clickListener: (PhotosItem: PhotosItem) -> Unit) {
//        fun onClick(PhotosItem: PhotosItem) = clickListener(PhotosItem)
//    }

}
