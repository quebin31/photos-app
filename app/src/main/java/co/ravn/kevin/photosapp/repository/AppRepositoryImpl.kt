package co.ravn.kevin.photosapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import co.ravn.kevin.photosapp.database.AppDatabase
import co.ravn.kevin.photosapp.model.Photo
import javax.inject.Inject
import kotlinx.coroutines.delay

class AppRepositoryImpl @Inject constructor (db: AppDatabase) : AppRepository {
    private val photoDao = db.photoDao()

    override fun getPhotos(): LiveData<List<Photo>> = liveData {
        val photos = photoDao.getPhotos()
        emitSource(photos)

        delay(50) // wait until `photos` has emitted a value
        Log.d(TAG, "getPhotos: latestValue = $latestValue")

        if (latestValue?.isEmpty() == true) {
            // TODO: get from network
            val newPhotos = emptyList<Photo>()
            photoDao.insertPhotos(newPhotos)
        }
    }

    companion object {
        private const val PHOTO_LIMIT = 25
        private const val TAG = "AppRepositoryImpl"
    }

}