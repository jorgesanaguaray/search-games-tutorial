package com.jorgesanaguaray.searchgamestutorial.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jorgesanaguaray.searchgamestutorial.R
import com.jorgesanaguaray.searchgamestutorial.databinding.ItemGameBinding
import com.jorgesanaguaray.searchgamestutorial.domain.item.GameItem

/**
 * Created by Jorge Sanaguaray
 */

class GameAdapter : RecyclerView.Adapter<GameAdapter.MyGameViewHolder>() {

    private var games: List<GameItem> = ArrayList()
    private lateinit var onButtonClick: OnButtonClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGameViewHolder {
        return MyGameViewHolder(ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyGameViewHolder, position: Int) {

        val game = games[position]

        holder.binding.apply {

            mTitle.text = game.title
            mImage.load(game.thumbnail) {
                placeholder(R.drawable.ic_image)
                error(R.drawable.ic_image)
                crossfade(true)
                crossfade(400)
            }
            mShortDescription.text = game.short_description
            mButtonGoToTheGamePage.setOnClickListener {
                onButtonClick.onClick(game.game_url)
            }

        }

    }

    override fun getItemCount(): Int {
        return games.size
    }

    class MyGameViewHolder(val binding: ItemGameBinding): RecyclerView.ViewHolder(binding.root)

    fun setGames(games: List<GameItem>) {
        this.games = games
    }


    interface OnButtonClick {
        fun onClick(gameUrl: String)
    }

    fun setOnButtonClick(onButtonClick: OnButtonClick) {
        this.onButtonClick = onButtonClick
    }


}