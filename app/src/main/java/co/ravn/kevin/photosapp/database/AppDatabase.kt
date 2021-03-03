package co.ravn.kevin.photosapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.ravn.kevin.photosapp.database.dao.PhotoDao
import co.ravn.kevin.photosapp.model.Photo

@Database(entities = [Photo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object {
        private const val DB_NAME = "PhotosApp"

        fun build(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
    }
}