package co.ravn.kevin.photosapp.repository

import androidx.lifecycle.LiveData
import co.ravn.kevin.photosapp.model.Photo

interface AppRepository {
    fun getPhotos(): LiveData<List<Photo>>
}