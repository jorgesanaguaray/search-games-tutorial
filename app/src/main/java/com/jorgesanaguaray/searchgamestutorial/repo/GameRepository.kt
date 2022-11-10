package com.jorgesanaguaray.searchgamestutorial.repo

import com.jorgesanaguaray.searchgamestutorial.data.remote.GameService
import com.jorgesanaguaray.searchgamestutorial.domain.item.GameItem
import com.jorgesanaguaray.searchgamestutorial.domain.item.toGameItem
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GameRepository @Inject constructor(private val gameService: GameService) {

    suspend fun getGames(): List<GameItem> {

        return gameService.getGames().map {
            it.toGameItem()
        }

    }

}