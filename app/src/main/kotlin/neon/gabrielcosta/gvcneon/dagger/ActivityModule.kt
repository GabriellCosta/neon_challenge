package neon.gabrielcosta.gvcneon.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import neon.gabrielcosta.gvcneon.ui.MainActivity
import neon.gabrielcosta.gvcneon.ui.PeopleListActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contribute_MainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contribute_PeopleListActivity(): PeopleListActivity
}