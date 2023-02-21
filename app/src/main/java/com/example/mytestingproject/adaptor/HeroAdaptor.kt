package com.example.mytestingproject.adaptor

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestingproject.databinding.MarvelHeroItemBinding
import com.example.mytestingproject.model.MarvelHeroesItem

typealias listener = (data: MarvelHeroesItem) -> Unit

class HeroAdaptor(private val itemClicked: listener) :
    ListAdapter<MarvelHeroesItem, HeroAdaptor.HeroAdaptorViewHolder>(diffUtil) {
    inner class HeroAdaptorViewHolder(private val binding: MarvelHeroItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: MarvelHeroesItem, itemClicked: listener) {
            binding.heroNmTxt.text = data.name
            binding.root.setOnClickListener {
                itemClicked.invoke(data)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MarvelHeroesItem>() {
            override fun areItemsTheSame(
                oldItem: MarvelHeroesItem,
                newItem: MarvelHeroesItem
            ) = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: MarvelHeroesItem,
                newItem: MarvelHeroesItem
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroAdaptorViewHolder {
        val binding =
            MarvelHeroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroAdaptorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroAdaptorViewHolder, position: Int) {
        val currItem = getItem(position)
        currItem?.let {
            holder.setData(it, itemClicked)
        }
    }

}