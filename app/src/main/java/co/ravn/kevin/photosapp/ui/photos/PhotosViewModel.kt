package co.ravn.kevin.photosapp.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.ravn.kevin.photosapp.model.Photo
import co.ravn.kevin.photosapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val photos: LiveData<List<Photo>> by lazy { repository.getPhotos() }
}