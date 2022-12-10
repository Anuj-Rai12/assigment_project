package com.example.mytestingproject.adaptor

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestingproject.databinding.ContentSrcLayoutBinding
import com.example.mytestingproject.model.Content

typealias clickListener = (data: Content) -> Unit

class ContentRecycleData(private val itemClicked:clickListener) : ListAdapter<Content, ContentRecycleData.ContentViewHolder>(diffUtil) {

    inner class ContentViewHolder(private val binding: ContentSrcLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: Content,itemClicked:clickListener) {
            binding.chatBtn.setOnClickListener {
                itemClicked.invoke(data)
            }
            binding.titleTxt.text=data.title
            binding.img.setImageResource(data.id)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(
                oldItem: Content,
                newItem: Content
            ) = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: Content,
                newItem: Content
            )= oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ContentViewHolder{
        val binding=ContentSrcLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val currItem=getItem(position)
        currItem?.let {
        holder.setData(it,itemClicked)
        }
    }

}