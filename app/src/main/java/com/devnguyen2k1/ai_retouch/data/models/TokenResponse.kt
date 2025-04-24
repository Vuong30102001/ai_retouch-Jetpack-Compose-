package com.devnguyen2k1.ai_retouch.data.models

import com.google.gson.annotations.SerializedName


data class TokenResponse(
    val success: Boolean,
    val data: TokenData,
    val message: String
)

data class TokenData(
    @SerializedName("em_token")
    val emToken: String,

    @SerializedName("last_time_save")
    val lastTimeSave: String
)
