package neon.gabrielcosta.gvcneon.dagger

import dagger.Module
import dagger.Provides
import neon.gabrielcosta.gvcneon.TokenManager
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesTokenManager() = TokenManager()

}