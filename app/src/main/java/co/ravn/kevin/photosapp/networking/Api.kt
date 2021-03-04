package co.ravn.kevin.photosapp.networking

import co.ravn.kevin.photosapp.model.Photo
import co.ravn.kevin.photosapp.utils.Result
import co.ravn.kevin.photosapp.utils.runToResult

class Api(private val service: ApiService) {

    suspend fun getPhotos(): Result<List<Photo>> = runToResult {
        service.getPhotos()
    }
}