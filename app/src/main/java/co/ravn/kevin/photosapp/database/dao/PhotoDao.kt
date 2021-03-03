package co.ravn.kevin.photosapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.ravn.kevin.photosapp.model.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<Photo>)

    @Query("SELECT * FROM photos")
    fun getPhotos(): LiveData<List<Photo>>
}