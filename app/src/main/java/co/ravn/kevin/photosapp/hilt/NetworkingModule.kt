package co.ravn.kevin.photosapp.hilt

import co.ravn.kevin.photosapp.networking.Api
import co.ravn.kevin.photosapp.networking.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private fun buildClient(): OkHttpClient = OkHttpClient.Builder().run {
        build()
    }

    private fun buildRetrofit(): Retrofit = Retrofit.Builder().run {
        val gson = GsonBuilder().setLenient().create()

        client(buildClient())
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create(gson))
        build()
    }

    @Provides
    fun providesApiService(): ApiService = buildRetrofit().create(ApiService::class.java)

    @Provides
    fun providesApi(service: ApiService): Api = Api(service)
}