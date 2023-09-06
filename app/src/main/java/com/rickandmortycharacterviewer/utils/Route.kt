package com.rickandmortycharacterviewer.utils

import com.rickandmortycharacterviewer.utils.Const.CHARACTER_SCREEN
import com.rickandmortycharacterviewer.utils.Const.DETAIL_ARG_CHARACTER_ID
import com.rickandmortycharacterviewer.utils.Const.DETAIL_SCREEN

sealed class Route(val route: String) {
    object Character: Route(CHARACTER_SCREEN)
    object Detail: Route("$DETAIL_SCREEN/{$DETAIL_ARG_CHARACTER_ID}") {
        fun createRoute(Id: Int) = "$DETAIL_SCREEN/$Id"
    }
}