package com.devnguyen2k1.ai_retouch.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devnguyen2k1.ai_retouch.data.repository.PhotoRepositoryImpl
import com.devnguyen2k1.ai_retouch.domain.usecase.RestoreImageUseCase
import com.devnguyen2k1.ai_retouch.presentation.screens.EnhancePhotoScreen
import com.devnguyen2k1.ai_retouch.presentation.screens.HomeScreen
import com.devnguyen2k1.ai_retouch.presentation.screens.RestoreOldPicScreen
import com.devnguyen2k1.ai_retouch.presentation.viewmodels.PhotoViewModel

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.route,
    ) {
        composable(NavScreen.HomeScreen.route){
            HomeScreen(
                onNavigateToRestore = {navController.navigate(NavScreen.RestoreOldPic.route)},
                onNavigateToEnhance = {navController.navigate(NavScreen.EnhancePhoto.route)},
                onNavigateToRemoveBg = {navController.navigate(NavScreen.RemoveBackground.route)},
            )
        }

        composable("restore_old_pic"){
//            val context = LocalContext.current
//            val viewModel: PhotoViewModel = remember {
//                val repository = PhotoRepositoryImpl(context)
//                val useCase = RestoreImageUseCase(repository)
//                PhotoViewModel(useCase)
//            }
            val viewModel: PhotoViewModel = hiltViewModel()
            RestoreOldPicScreen(viewModel)
        }

        composable("enhance_photo") {
            EnhancePhotoScreen()
        }
    }
}