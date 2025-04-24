package com.devnguyen2k1.ai_retouch.core.di

import android.content.Context
import com.devnguyen2k1.ai_retouch.data.remote.ApiService
import com.devnguyen2k1.ai_retouch.data.repository.PhotoRepositoryImpl
import com.devnguyen2k1.ai_retouch.domain.repository.PhotoRepository
import com.devnguyen2k1.ai_retouch.domain.usecase.RestoreImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object PhotoModule {

    @Provides
    fun providePhotoRepository(
        @ApplicationContext context: Context,
        @Named("tokenService") tokenService: ApiService,
        @Named("restoreService") restoreService: ApiService

    ): PhotoRepository = PhotoRepositoryImpl(context, tokenService, restoreService)

    @Provides
    fun provideRestoreImageUseCase(
        repository: PhotoRepository
    ): RestoreImageUseCase = RestoreImageUseCase(repository)
}
