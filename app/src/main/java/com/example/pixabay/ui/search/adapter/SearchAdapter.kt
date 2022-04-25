package com.example.pixabay.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay.databinding.LayoutSearchHitItemBinding
import com.example.pixabay.domain.model.Hit
import com.example.pixabay.ui.search.listener.SearchItemClickListener

class SearchAdapter(private val listener: SearchItemClickListener) : ListAdapter<Hit, SearchAdapter.HitItemViewHolder>(object : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun getItemCount() = currentList.size

    override fun getItemId(position: Int) = currentList[position].hashCode().toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HitItemViewHolder(LayoutSearchHitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HitItemViewHolder, position: Int) {
        holder.binding.hit = currentList[position]
        holder.binding.listener = listener
        holder.binding.executePendingBindings()
    }

    inner class HitItemViewHolder(val binding: LayoutSearchHitItemBinding) : RecyclerView.ViewHolder(binding.root)
}