package com.devnguyen2k1.ai_retouch.presentation.navigation

sealed class NavScreen(val route: String) {
    object HomeScreen: NavScreen("home")
    object RestoreOldPic : NavScreen("restore_old_pic")
    object RemoveBackground : NavScreen("remove_background")
    object EnhancePhoto : NavScreen("enhance_photo")
}