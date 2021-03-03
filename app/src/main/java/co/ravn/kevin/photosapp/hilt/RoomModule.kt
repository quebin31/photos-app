package co.ravn.kevin.photosapp.hilt

import android.content.Context
import co.ravn.kevin.photosapp.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.build(context)
}