package com.devnguyen2k1.ai_retouch.domain.usecase

import android.net.Uri
import com.devnguyen2k1.ai_retouch.domain.entities.RestoredImage
import com.devnguyen2k1.ai_retouch.domain.repository.PhotoRepository

class RestoreImageUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(imageUri: Uri): RestoredImage? {
        return repository.restoreImage(imageUri)
    }
}