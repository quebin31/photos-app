package co.ravn.kevin.photosapp.hilt

import co.ravn.kevin.photosapp.repository.AppRepository
import co.ravn.kevin.photosapp.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAppRepository(impl: AppRepositoryImpl): AppRepository
}