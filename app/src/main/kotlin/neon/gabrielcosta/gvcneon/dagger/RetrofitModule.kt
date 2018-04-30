package neon.gabrielcosta.gvcneon.dagger

import dagger.Module
import dagger.Provides
import neon.gabrielcosta.gvcneon.network.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    @Named("Base")
    fun provideRetrofit(): Retrofit {
        return defaultRetrofitBuilder()
            .baseUrl("http://processoseletivoneon.neonhomol.com.br/")
            .build()
    }

    @Provides
    @Singleton
    @Named("People")
    fun providePeopleRetrofit(): Retrofit {
        return defaultRetrofitBuilder()
            .baseUrl("https://randomapi.com/api/")
            .build()
    }

    private fun defaultRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(createLoggerInterceptor())
    }

    private fun createLoggerInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        return httpClient.addInterceptor(logging).build()
    }

}