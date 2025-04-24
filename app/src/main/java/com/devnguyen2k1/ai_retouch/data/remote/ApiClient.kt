//package com.devnguyen2k1.ai_retouch.data.remote
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object ApiClient {
//    private const val TOKEN_BASE_URL = "https://picfixer.app/api/em-token/" // For token
//    private const val RESTORE_BASE_URL = "https://be-prod-1.snapedit.app/api/" // For image restoration
//
//    // Retrofit cho token
//    private val tokenRetrofit = Retrofit.Builder()
//        .baseUrl(TOKEN_BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    // Retrofit cho image restoration
//    private val restoreRetrofit = Retrofit.Builder()
//        .baseUrl(RESTORE_BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    // Tao service interface da khai bao getToken trong ApiService
//    val tokenService: ApiService = tokenRetrofit.create(ApiService::class.java)
//
//    // Tao service interface da khai bao restoreImage trong ApiService
//    val restoreService: ApiService = restoreRetrofit.create(ApiService::class.java)
//}
