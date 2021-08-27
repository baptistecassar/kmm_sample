package com.example.kmmapplication.androidApp.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmmapplication.androidApp.R
import com.example.kmmapplication.androidApp.databinding.FragmentGameListBinding
import com.example.kmmapplication.shared.model.Game
import com.example.kmmapplication.shared.utils.concatenatePlayers
import org.koin.androidx.viewmodel.ext.android.getViewModel

class GamesFragment : Fragment(R.layout.fragment_game_list) {

    private val gameAdapter = GameAdapter()

    private var _binding: FragmentGameListBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var gamesViewModel: GamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gamesViewModel = getViewModel()
        setupUI()
        setupCompose()
        gamesViewModel.gameListEvent.observe(viewLifecycleOwner) {
            handleGameListEvent(it)
            binding.progressBar.isVisible = false
        }
    }

    private fun handleGameListEvent(gameListEvent: GameListEvent) {
        when (gameListEvent) {
            is GameListEvent.GameListLoading -> {
                binding.progressBar.isVisible = true
            }
            is GameListEvent.GameListLoaded -> {
                binding.progressBar.isVisible = false
                gameAdapter.submitList(gameListEvent.games)
            }
            is GameListEvent.GameListFailed -> {
                binding.progressBar.isVisible = false
                Toast.makeText(
                    requireContext(),
                    gameListEvent.throwable.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupUI() {
        binding.gameListRv.adapter = gameAdapter
        binding.gameListRv.layoutManager = LinearLayoutManager(requireContext())

        binding.swipeContainer.setOnRefreshListener {
            binding.swipeContainer.isRefreshing = false
            gamesViewModel.refreshGames()
        }
    }

    private fun setupCompose() {
        binding.gameListCompose.setContent {
            MaterialTheme {
                GameList()
            }
        }
    }

    @Composable
    private fun Greeting() {
        Text(
            text = "Hello Word",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.margin_small))
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }

    @Composable
    private fun GameList() {
        val games: List<Game> by gamesViewModel.gameList.observeAsState(emptyList())
        LazyColumn(modifier = Modifier.testTag(GameListTag)) {
            items(games) { game ->
                GameView(game = game)
            }
        }
    }

    @Composable
    private fun GameView(game: Game) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = game.gameType.uppercase(),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                    ) {
                        Column(modifier = Modifier.weight(2f)) {
                            Text(
                                text = game.winningScore.toString(),
                                style = TextStyle(fontSize = 24.sp),
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            )
                            Text(
                                text = game.winningPlayers.concatenatePlayers(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .padding(top = 8.dp),
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Column(modifier = Modifier.weight(2f)) {
                            Text(
                                text = game.loosingScore.toString(),
                                style = TextStyle(fontSize = 24.sp),
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            )
                            Text(
                                text = game.loosingPlayers.concatenatePlayers(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .padding(top = 8.dp),
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val GameListTag = "GameList"
    }


}