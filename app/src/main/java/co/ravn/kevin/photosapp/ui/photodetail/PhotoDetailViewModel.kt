package co.ravn.kevin.photosapp.ui.photodetail

import androidx.lifecycle.ViewModel
import co.ravn.kevin.photosapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {
}