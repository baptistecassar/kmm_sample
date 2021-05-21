package com.example.kmmapplication.androidApp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kmmapplication.androidApp.databinding.ActivityMainBinding
import com.example.kmmapplication.androidApp.games.GamesFragment
import com.ncapdevi.fragnav.FragNavController

private const val INDEX_GAMES = 0
private const val INDEX_STANDINGS = 1
private const val TOTAL_TAB_COUNT = 2

class MainActivity : AppCompatActivity(), FragNavController.RootFragmentListener {

    private lateinit var binding: ActivityMainBinding

    private val fragNavController: FragNavController =
        FragNavController(supportFragmentManager, R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        title = "Games"
        setContentView(binding.root)

        fragNavController.rootFragmentListener = this
        // default tab is news (left)
        fragNavController.initialize(INDEX_GAMES, savedInstanceState)
        // Note: IMPORTANT! Prevents fragments restored on config changes from creating their views twice
        fragNavController.executePendingTransactions()

        binding.bottomNavigation.setOnNavigationItemSelectedListener(::onBottomBarItemSelected)
    }

    override val numberOfRootFragments: Int
        get() = TOTAL_TAB_COUNT

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            INDEX_GAMES -> GamesFragment()
            INDEX_STANDINGS -> StandingsFragment()
            else -> throw IllegalArgumentException()
        }
    }

    /**
     * Sets the selected position in the bottom bar
     *
     * @param position to select
     */
    private fun onBottomBarItemSelected(menuItem: MenuItem): Boolean {
        val itemId = menuItem.itemId
        fragNavController.switchTab(itemId.toTabPosition())
        return true
    }
}

private fun Int.toTabPosition(): Int {
    return when (this) {
        R.id.menu_games -> INDEX_GAMES
        R.id.menu_standings -> INDEX_STANDINGS
        else -> throw IllegalArgumentException("Unsupported id: $this")
    }
}