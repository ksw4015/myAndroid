package com.sample.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.presentation.adapter.MoviesRecyclerAdapter
import com.sample.presentation.databinding.ActivityMainBinding
import com.sample.presentation.util.RecyclerItemTouchHelper
import com.sample.presentation.viewmodel.MainUiState
import com.sample.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val mAdapter by lazy {
        MoviesRecyclerAdapter {
            Toast.makeText(this, "무비코드 : $it", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when(it) {
                        is MainUiState.Loading -> {
                            // Progress
                        }
                        is MainUiState.Error -> {
                            Log.d("NetworkError", "$it")
                        }
                        is MainUiState.MovieWeekly -> {
                            mAdapter.submitList(it.moviesWeekly.weeklyBoxOfficeList)
                        }
                    }
                }
            }
        }

        binding.movieList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }

        val itemTouchHelperCallback = ItemTouchHelper(RecyclerItemTouchHelper(binding.movieList))
        itemTouchHelperCallback.attachToRecyclerView(binding.movieList)

        viewModel.getBoxOfficeMovies(targetDt = "20240201")
    }

}