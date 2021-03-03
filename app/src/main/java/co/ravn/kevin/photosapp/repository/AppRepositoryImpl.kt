package co.ravn.kevin.photosapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import co.ravn.kevin.photosapp.database.AppDatabase
import co.ravn.kevin.photosapp.model.Photo
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor (db: AppDatabase) : AppRepository {
    private val photoDao = db.photoDao()

    override fun getPhotos(): LiveData<List<Photo>> = liveData {
        val photos = photoDao.getPhotos()
        emitSource(photos)

        if (photos.value?.isEmpty() == true) {
            // TODO: get from network
            val newPhotos = emptyList<Photo>()
            photoDao.insertPhotos(newPhotos)
        }
    }

    companion object {
        private const val PHOTO_LIMIT = 25
    }

}