package neon.gabrielcosta.gvcneon.dagger

import dagger.Module
import dagger.Provides
import neon.gabrielcosta.gvcneon.network.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://processoseletivoneon.neonhomol.com.br")
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }
}