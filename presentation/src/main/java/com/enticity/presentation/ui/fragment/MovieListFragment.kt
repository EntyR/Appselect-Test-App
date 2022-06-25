package com.enticity.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enticity.appselect.databinding.FragmentMovieListBinding
import com.enticity.presentation.adapter.MovieAdapter
import com.enticity.presentation.ui.view_model.MovieViewModel
import com.enticity.presentation.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    val viewvodel: MovieViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val movieAdapter = MovieAdapter(requireContext())
        binding.rvMovieList.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewvodel.movieList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.btNextPage.visibility = View.GONE
                    binding.btPrevPage.visibility = View.GONE
                    movieAdapter.submitList(it.data)
                }
                Status.ERROR -> Unit
                Status.LOADING -> Unit
            }
        }

        binding.btNextPage.setOnClickListener {
            viewvodel.nextPage()
        }
        binding.btPrevPage.setOnClickListener {
            viewvodel.previousPage()
        }

        viewvodel.page.observe(viewLifecycleOwner) { _ ->
            updateFilmList()
        }

        binding.rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.btNextPage.visibility = View.VISIBLE
                    if (viewvodel.page.value!! != 0)
                        binding.btPrevPage.visibility = View.VISIBLE
                } else {
                    binding.btNextPage.visibility = View.GONE
                    binding.btPrevPage.visibility = View.GONE
                }

            }

        })

        return binding.root
    }

    private fun updateFilmList() {
        viewvodel.receiveFilmList(viewvodel.page.value!!.toInt() * 20)

    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }
}