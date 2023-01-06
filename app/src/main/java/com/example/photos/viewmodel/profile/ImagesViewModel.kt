package com.example.photos.viewmodel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photos.model.profile.Image
import com.example.photos.usecase.profile.ImagesPageAvailabilityUseCase
import com.example.photos.usecase.profile.ImagesPageUseCase
import com.example.photos.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imagesPageUseCase: ImagesPageUseCase,
    private val imagesPageAvailabilityUseCase: ImagesPageAvailabilityUseCase
): ViewModel() {

    enum class ImageLoadingStatus {
        INIT,
        LOADING,
        CONTENT,
        ERROR
    }

    private val _imageLoadingStatus = MutableLiveData(ImageLoadingStatus.INIT)
    val imageLoadingStatus: LiveData<ImageLoadingStatus> = _imageLoadingStatus

    private val _images = mutableListOf<Image>()
    val images: List<Image> = _images

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _imagesAddedEvent = MutableLiveData<Event<Int>>()
    val imagesAddedEvent: LiveData<Event<Int>> = _imagesAddedEvent

    fun loadNextPage() {
        if (_imageLoadingStatus.value == ImageLoadingStatus.LOADING) {
            return
        }
        if (!imagesPageAvailabilityUseCase.isNextPageAvailable()) {
            return
        }
        _imageLoadingStatus.value = ImageLoadingStatus.LOADING
        viewModelScope.launch {
            try {
                val nextImagesPage = imagesPageUseCase.getNextImagesPage()
                withContext(Dispatchers.Main) {
                    _images.addAll(nextImagesPage)
                    _imagesAddedEvent.value = Event(nextImagesPage.size)
                    _imageLoadingStatus.value = ImageLoadingStatus.CONTENT
                    _error.value = null
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = e.localizedMessage
                    _imageLoadingStatus.value = ImageLoadingStatus.ERROR
                }
            }
        }
    }
}
