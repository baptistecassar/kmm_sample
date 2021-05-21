package com.example.kmmapplication.shared.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val name: String,
    val email: String,
    val imageUrl: String? = null
)