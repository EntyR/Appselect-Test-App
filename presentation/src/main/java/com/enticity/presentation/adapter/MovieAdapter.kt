package com.enticity.presentation.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.enticity.appselect.databinding.FilmItemBinding
import com.enticity.domain.model.FilmModel

class MovieAdapter(private val ctx: Context): ListAdapter<FilmModel, MovieAdapter.MovieViewHolder>(Companion) {
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
        val width = Resources.getSystem().displayMetrics.widthPixels
        val height = Resources.getSystem().displayMetrics.heightPixels
        val elemSize = width / 3
        val item = currentList[position]
        holder.binding.tvTitle.text = item.tittle
        holder.binding.tvDescroption.text = item.description

        val  previewLayoutParams = holder.binding.ivFilmPreview.layoutParams as ConstraintLayout.LayoutParams
        previewLayoutParams.width = elemSize
        holder.binding.ivFilmPreview.layoutParams = previewLayoutParams

        if (position == currentList.size-1)
            (holder.binding.clFilmReview.layoutParams as ViewGroup.MarginLayoutParams).updateMargins(bottom = height / 10)
        else (holder.binding.clFilmReview.layoutParams as ViewGroup.MarginLayoutParams).updateMargins(bottom = 0)

        Glide
            .with(ctx)
            .load(item.previewUri)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    e?.cause?.printStackTrace()
                    return false
                }


                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.llWaiting.visibility = View.GONE
                    holder.binding.ivFilmPreview.setImageDrawable(resource)
                    return false
                }
            })
            .centerCrop()
            .into(holder.binding.ivFilmPreview)
    }
}