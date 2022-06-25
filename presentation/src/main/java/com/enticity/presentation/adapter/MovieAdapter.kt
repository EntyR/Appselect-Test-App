package com.enticity.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.enticity.appselect.databinding.FilmItemBinding
import com.enticity.domain.model.FilmModel

class MovieAdapter(): ListAdapter<FilmModel, MovieAdapter.MovieViewHolder>(Companion) {
    class MovieViewHolder(val binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<FilmModel>() {
        override fun areItemsTheSame(oldItem: FilmModel, newItem: FilmModel): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: FilmModel, newItem: FilmModel): Boolean {
            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(FilmItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

    }
}