package com.example.kmmapplication.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class PlayerWithRating(
    val player: Player,
    val rating: Rating
)