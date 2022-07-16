package com.example.astontask01.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astontask01.R
import com.example.astontask01.data.Model
import com.example.astontask01.databinding.RecyclerViewItemBinding

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Model>() {

    override fun areItemsTheSame(oldItem: Model, newItem: Model):
            Boolean = oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Model, newItem: Model):
            Boolean = oldItem == newItem
}

class MusicGroupListAdapter
        : ListAdapter<Model,MusicGroupListAdapter.ModelViewHolder>(DIFF_CALLBACK) {

    var listener: (Model, Int) -> Unit = { numberItem: Model, i: Int -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
       return ModelViewHolder(
           RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {

        holder.bind(getItem(position))

        holder.itemView.setOnClickListener {
            listener(getItem(position), position)

        }
    }

    class ModelViewHolder(rvItemLayoutBinding: RecyclerViewItemBinding)
        : RecyclerView.ViewHolder(rvItemLayoutBinding.root){

        private val binding = rvItemLayoutBinding

        @SuppressLint("SetTextI18n")
        fun bind(model: Model){
            binding.name.text = "Название: " + model.name
            binding.genre.text = "Жанр: " + model.genre
            binding.country.text = "Страна: " + model.country
            binding.banner.load(model.image) {
                placeholder(R.drawable.noimage)
            }
        }
    }
}
