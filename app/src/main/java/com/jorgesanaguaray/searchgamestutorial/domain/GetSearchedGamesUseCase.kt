package com.jorgesanaguaray.searchgamestutorial.domain

import com.jorgesanaguaray.searchgamestutorial.domain.item.GameItem
import com.jorgesanaguaray.searchgamestutorial.repo.GameRepository
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GetSearchedGamesUseCase @Inject constructor(private val gameRepository: GameRepository) {

    private lateinit var searchedGames: MutableList<GameItem>

    suspend operator fun invoke(query: String): List<GameItem> {

        val games = gameRepository.getGames()
        searchedGames = ArrayList()

        for (game in games) {
            if (game.title.lowercase().contains(query.lowercase())) searchedGames.add(game)
        }

        return searchedGames.shuffled()

    }

}