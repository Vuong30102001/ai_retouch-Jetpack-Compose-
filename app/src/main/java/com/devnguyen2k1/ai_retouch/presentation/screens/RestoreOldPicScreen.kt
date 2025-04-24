package com.devnguyen2k1.ai_retouch.presentation.screens

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.devnguyen2k1.ai_retouch.presentation.viewmodels.PhotoUiState
import com.devnguyen2k1.ai_retouch.presentation.viewmodels.PhotoViewModel

@Composable
fun RestoreOldPicScreen(viewModel: PhotoViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Image picker
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            viewModel.restoreImage(it)
        }
    }
    // Runtime permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            imagePickerLauncher.launch("image/*")
        } else {
            // Xử lý từ chối quyền
            viewModel.setError("Bạn cần cấp quyền để chọn ảnh.")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Manifest.permission.READ_MEDIA_IMAGES
            } else {
                Manifest.permission.READ_EXTERNAL_STORAGE
            }
            permissionLauncher.launch(permission)
        }) {
            Text("Chọn ảnh")
        }

        Spacer(modifier = Modifier.height(20.dp))

        when (val state = uiState) {
            is PhotoUiState.Loading -> {
                CircularProgressIndicator()
            }

            is PhotoUiState.Success -> {
                val imageUrl = state.image?.restoredImageUrl
                Log.d("RestoredImageURL", "URL nhận được: $imageUrl")
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }

            is PhotoUiState.Error -> {
                Text(text = "Lỗi: ${state.message}")
            }

            PhotoUiState.Idle -> {
                Text(text = "Vui lòng chọn ảnh để xử lý")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        selectedImageUri?.let {
            Text("Ảnh gốc:")
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}
