package com.example.mytestingproject.adaptor

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestingproject.databinding.InfoLayoutBinding

typealias clickItem = (data: String) -> Unit

class InfoAdaptor(private val itemClicked: clickItem) :
    ListAdapter<String, InfoAdaptor.InfoViewHolder>(diffUtil) {
    inner class InfoViewHolder(private val binding: InfoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: String, itemClicked: clickItem) {
            binding.root.setOnClickListener {
                itemClicked.invoke(data)
            }
            binding.titleTxt.text = data
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String, newItem: String
            ) = oldItem.equals(newItem, true)

            override fun areContentsTheSame(
                oldItem: String, newItem: String
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding = InfoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val currItem = getItem(position)
        currItem?.let {
            holder.setData(it, itemClicked)
        }
    }

}