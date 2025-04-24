package com.devnguyen2k1.ai_retouch.data.remote

import com.devnguyen2k1.ai_retouch.data.models.RestoredImageResponse
import com.devnguyen2k1.ai_retouch.data.models.TokenResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @GET("get-token")
    suspend fun getToken(): TokenResponse  // TokenResponse là data class mà bạn phải tạo để nhận thông tin từ API

    @Multipart
    @POST("restore/v1/")
    suspend fun restoreImage(
        @Part input_image: MultipartBody.Part,
        @Part("lang") lang: RequestBody,
        @Header("Authorization") token: String,
        @Header("User-Agent") userAgent: String = "SnapEdit/5.2.9 (com.sfun.snapedit; build:626; iOS 17.5.1) Alamofire/5.9.1",
        @Header("Accept") accept: String = "*/*",
        @Header("Accept-Language") acceptLanguage: String = "en-VN;q=1.0, vi-VN;q=0.9, vi-JP;q=0.8, zh-Hans-VN;q=0.7"
    ): RestoredImageResponse
}
