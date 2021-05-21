package com.example.kmmapplication.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val ratings: List<Double>,
    val currentRating: Double,
    val gameType: String
)