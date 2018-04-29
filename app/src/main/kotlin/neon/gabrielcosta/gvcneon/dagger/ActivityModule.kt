package neon.gabrielcosta.gvcneon.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import neon.gabrielcosta.gvcneon.ui.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contribute_MainActivity(): MainActivity
}