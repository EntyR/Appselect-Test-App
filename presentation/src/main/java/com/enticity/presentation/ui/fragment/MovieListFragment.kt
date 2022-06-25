package com.enticity.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.enticity.appselect.R
import com.enticity.appselect.databinding.FragmentMovieListBinding
import com.enticity.domain.model.FilmModel
import com.enticity.presentation.adapter.MovieAdapter


class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val movieAdapter = MovieAdapter()
        binding.rvMovieList.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        movieAdapter.submitList(
            listOf(
                FilmModel("","","")
            )
        )

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }
}