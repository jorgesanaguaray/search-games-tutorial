package com.jorgesanaguaray.searchgamestutorial.domain.item

import com.jorgesanaguaray.searchgamestutorial.data.remote.model.GameModel

/**
 * Created by Jorge Sanaguaray
 */

data class GameItem(

    val id: Int,
    val title: String,
    val thumbnail: String,
    val short_description: String,
    val game_url: String

)

fun GameModel.toGameItem() = GameItem(id, title, thumbnail, short_description, game_url)
