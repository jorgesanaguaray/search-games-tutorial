package com.jorgesanaguaray.searchgamestutorial.data.remote

import com.jorgesanaguaray.searchgamestutorial.data.remote.model.GameModel
import com.jorgesanaguaray.searchgamestutorial.util.Constants.Companion.GAMES_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Jorge Sanaguaray
 */

interface GameApi {

    @GET(GAMES_ENDPOINT)
    suspend fun getGames(): Response<List<GameModel>>

}