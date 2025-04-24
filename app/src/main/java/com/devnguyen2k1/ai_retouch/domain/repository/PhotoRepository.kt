package com.devnguyen2k1.ai_retouch.domain.repository

import android.net.Uri
import com.devnguyen2k1.ai_retouch.domain.entities.RestoredImage

interface PhotoRepository{
    suspend fun restoreImage(imageUri: Uri): RestoredImage?
}