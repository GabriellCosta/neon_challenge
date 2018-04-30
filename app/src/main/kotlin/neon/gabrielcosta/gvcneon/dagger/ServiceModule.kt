package neon.gabrielcosta.gvcneon.dagger

import dagger.Module
import dagger.Provides
import neon.gabrielcosta.gvcneon.network.NeonApi
import neon.gabrielcosta.gvcneon.network.PeopleApi
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideNeonApi(@Named("Base") retrofit: Retrofit):
        NeonApi = retrofit.create(NeonApi::class.java)

    @Provides
    @Singleton
    fun providePeopleApi(@Named("People") retrofit: Retrofit):
        PeopleApi = retrofit.create(PeopleApi::class.java)
}