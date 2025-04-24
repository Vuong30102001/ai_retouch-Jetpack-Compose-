package com.devnguyen2k1.ai_retouch.data.models

import com.google.gson.annotations.SerializedName

data class RestoredImageResponse(
    @SerializedName("image_id")
    val imageId: String,

    @SerializedName("output_image")
    val outputImage: OutputImage
){
    data class OutputImage(
        val image: String // Day la chuoi anh dang Base64
    )
}