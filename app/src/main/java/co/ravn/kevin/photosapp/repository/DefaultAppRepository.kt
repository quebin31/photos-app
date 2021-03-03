package co.ravn.kevin.photosapp.repository

import co.ravn.kevin.photosapp.database.AppDatabase

class DefaultAppRepository(db: AppDatabase) : AppRepository {
    private val photoDao = db.photoDao()

}