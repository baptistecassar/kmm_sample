package com.example.kmmapplication.androidApp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.kmmapplication.androidApp.ui.games.GameList
import com.example.kmmapplication.androidApp.ui.games.GamesFragment.Companion.GameListTag
import com.example.kmmapplication.androidApp.ui.games.GamesViewModel
import org.junit.Rule
import org.junit.Test

class GamesTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val gamesRepository = GamesRepositoryFake()
    private val gamesViewModel = GamesViewModel(gamesRepository)

    @Test
    fun testGameListScreen() {
        composeTestRule.setContent {
            GameList(gamesViewModel = gamesViewModel)
        }

        val gameList = gamesRepository.gameList
        val gameListNode = composeTestRule.onNodeWithTag(GameListTag)
        gameListNode.assertIsDisplayed()
            .onChildren()
            .assertCountEquals(gameList.size)

        gameList.forEachIndexed { index, game ->
            val rowNode = gameListNode.onChildAt(index)
            rowNode.onChildren().assertAny(hasText(game.gameType, ignoreCase = true))
        }
    }

}
