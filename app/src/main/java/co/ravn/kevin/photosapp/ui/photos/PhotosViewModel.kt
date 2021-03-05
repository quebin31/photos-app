package co.ravn.kevin.photosapp.ui.photos

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import co.ravn.kevin.photosapp.model.Photo
import co.ravn.kevin.photosapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    val photos: LiveData<List<Photo>> by lazy { repository.getPhotos() }

    val noDataIsVisible: LiveData<Int> by lazy {
        photos.map {
            if (it.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}