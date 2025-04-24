package com.devnguyen2k1.ai_retouch.domain.entities

data class RestoredImage(
    val originalUri: android.net.Uri,
    val restoredImageUrl: String,
)