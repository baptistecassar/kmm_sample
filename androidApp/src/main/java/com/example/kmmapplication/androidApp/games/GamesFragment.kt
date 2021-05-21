package com.example.kmmapplication.androidApp.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmmapplication.androidApp.GameAdapter
import com.example.kmmapplication.androidApp.R
import com.example.kmmapplication.androidApp.databinding.FragmentGameListBinding
import com.example.kmmapplication.shared.repository.GameRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class GamesFragment : Fragment(R.layout.fragment_game_list) {

    private val mainScope = MainScope()
    private lateinit var gameRepository: GameRepository

    private val gameAdapter = GameAdapter()

    private var _binding: FragmentGameListBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

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
//        sdk = PlayerRepository(DatabaseDriverFactory(requireContext()))
        gameRepository = GameRepository()
        setupUI()
        displayPlayers(false)
    }

    private fun setupUI() {
        binding.gameListRv.adapter = gameAdapter
        binding.gameListRv.layoutManager = LinearLayoutManager(requireContext())

        binding.swipeContainer.setOnRefreshListener {
            binding.swipeContainer.isRefreshing = false
            displayPlayers(true)
        }
    }

    private fun displayPlayers(needReload: Boolean) {
        binding.progressBar.isVisible = true
        mainScope.launch {
            kotlin.runCatching {
                gameRepository.getGames()
            }.onSuccess {
                gameAdapter.submitList(it)
            }.onFailure {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}