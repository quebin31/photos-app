package co.ravn.kevin.photosapp.networking

import co.ravn.kevin.photosapp.model.Photo
import retrofit2.http.GET

interface ApiService {

    @GET("/photos")
    suspend fun getPhotos(): List<Photo>
}