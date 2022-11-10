package com.jorgesanaguaray.searchgamestutorial.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.jorgesanaguaray.searchgamestutorial.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameViewModel = ViewModelProvider(this).get()
        gameAdapter = GameAdapter()

        gameViewModel.games.observe(this) {

            gameAdapter.setGames(it)
            binding.mRecyclerView.adapter = gameAdapter
            gameAdapter.setOnButtonClick(object : GameAdapter.OnButtonClick {
                override fun onClick(gameUrl: String) {
                    val uri = Uri.parse(gameUrl)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            })

        }

        gameViewModel.searchViewVisibility.observe(this) {
            binding.mSearchView.visibility = if (it) View.VISIBLE else View.GONE
        }

        gameViewModel.recyclerViewVisibility.observe(this) {
            binding.mRecyclerView.visibility = if (it) View.VISIBLE else View.GONE
        }

        gameViewModel.textViewNoGamesVisibility.observe(this) {
            binding.mTextViewNoGames.visibility = if (it) View.VISIBLE else View.GONE
        }

        gameViewModel.textViewNoInternetVisibility.observe(this) {
            binding.mTextViewNoInternet.visibility = if (it) View.VISIBLE else View.GONE
        }

        gameViewModel.progressBarVisibility.observe(this) {
            binding.mProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        binding.mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                gameViewModel.getSearchedGames(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        binding.mSwipeRefreshLayout.setOnRefreshListener {
            gameViewModel.getSearchedGames("")
            binding.mSearchView.setQuery("", false)
            binding.mSwipeRefreshLayout.isRefreshing = false
        }

    }

}