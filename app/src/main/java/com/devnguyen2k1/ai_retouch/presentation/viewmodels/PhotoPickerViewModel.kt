package com.devnguyen2k1.ai_retouch.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnguyen2k1.ai_retouch.domain.entities.RestoredImage
import com.devnguyen2k1.ai_retouch.domain.usecase.RestoreImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class PhotoUiState {
    object Idle : PhotoUiState()
    object Loading : PhotoUiState()
    data class Success(val image: RestoredImage?) : PhotoUiState()
    data class Error(val message: String) : PhotoUiState()
}

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val restoreImageUseCase: RestoreImageUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PhotoUiState>(PhotoUiState.Idle)
    val uiState: StateFlow<PhotoUiState> = _uiState

    fun restoreImage(uri: Uri) {
        viewModelScope.launch {
            _uiState.value = PhotoUiState.Loading
            try {
                val restoredImage = restoreImageUseCase(uri)
                _uiState.value = PhotoUiState.Success(restoredImage)
            } catch (e: Exception) {
                _uiState.value = PhotoUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _uiState.value = PhotoUiState.Idle
    }

    fun setError(message: String) {
        _uiState.value = PhotoUiState.Error(message)
    }
}