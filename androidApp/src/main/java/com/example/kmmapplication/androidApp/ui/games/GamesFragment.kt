package com.example.kmmapplication.androidApp.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmmapplication.androidApp.R
import com.example.kmmapplication.androidApp.databinding.FragmentGameListBinding
import com.example.kmmapplication.androidApp.ui.GameAdapter

class GamesFragment : Fragment(R.layout.fragment_game_list) {

    private val gameAdapter = GameAdapter()

    private var _binding: FragmentGameListBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val gamesViewModel: GamesViewModel by viewModels()

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
        setupUI()
        gamesViewModel.games.observe(viewLifecycleOwner) {
            gameAdapter.submitList(it)
            binding.progressBar.isVisible = false
        }
    }

    private fun setupUI() {
        binding.progressBar.isVisible = true
        binding.gameListRv.adapter = gameAdapter
        binding.gameListRv.layoutManager = LinearLayoutManager(requireContext())

        binding.swipeContainer.setOnRefreshListener {
            binding.swipeContainer.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}