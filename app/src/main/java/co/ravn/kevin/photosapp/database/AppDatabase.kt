package co.ravn.kevin.photosapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.ravn.kevin.photosapp.database.dao.PhotoDao
import co.ravn.kevin.photosapp.model.Photo

@Database(entities = [Photo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
}