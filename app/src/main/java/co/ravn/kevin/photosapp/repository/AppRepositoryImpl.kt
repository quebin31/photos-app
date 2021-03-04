package co.ravn.kevin.photosapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import co.ravn.kevin.photosapp.database.AppDatabase
import co.ravn.kevin.photosapp.model.Photo
import co.ravn.kevin.photosapp.networking.Api
import co.ravn.kevin.photosapp.utils.Result
import kotlinx.coroutines.delay
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val api: Api, db: AppDatabase) : AppRepository {
    private val photoDao = db.photoDao()

    override fun getPhotos(): LiveData<List<Photo>> = liveData {
        val photos = photoDao.getPhotos()
        emitSource(photos)

        while (latestValue == null)
            delay(50)

        if (latestValue?.isEmpty() == true) {
            val newPhotos = when (val result = api.getPhotos()) {
                is Result.Ok -> {
                    result.value.subList(0, PHOTO_LIMIT)
                }

                is Result.Err<*> -> {
                    Log.e(TAG, "getPhotos: failed to fetch photos!")
                    emptyList()
                }
            }

            photoDao.insertPhotos(newPhotos)
        }
    }

    companion object {
        private const val PHOTO_LIMIT = 25
        private const val TAG = "AppRepositoryImpl"
    }

}