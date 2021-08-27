package com.example.kmmapplication.androidApp.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmmapplication.androidApp.R
import com.example.kmmapplication.androidApp.databinding.FragmentGameListBinding
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}