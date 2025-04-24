package com.devnguyen2k1.ai_retouch.core.hilt

import android.content.Context
import com.devnguyen2k1.ai_retouch.data.repository.PhotoRepositoryImpl
import com.devnguyen2k1.ai_retouch.domain.repository.PhotoRepository
import com.devnguyen2k1.ai_retouch.domain.usecase.RestoreImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object PhotoModule {

    @Provides
    fun providePhotoRepository(
        @ApplicationContext context: Context
    ): PhotoRepository = PhotoRepositoryImpl(context)

    @Provides
    fun provideRestoreImageUseCase(
        repository: PhotoRepository
    ): RestoreImageUseCase = RestoreImageUseCase(repository)
}
