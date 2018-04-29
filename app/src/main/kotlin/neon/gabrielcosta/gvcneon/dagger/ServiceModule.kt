package neon.gabrielcosta.gvcneon.dagger

import dagger.Module
import dagger.Provides
import neon.gabrielcosta.gvcneon.network.NeonApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideNeonApi(retrofit: Retrofit): NeonApi = retrofit.create(NeonApi::class.java)
}