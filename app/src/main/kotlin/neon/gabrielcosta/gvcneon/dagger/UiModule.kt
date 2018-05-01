package neon.gabrielcosta.gvcneon.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import neon.gabrielcosta.gvcneon.BaseFragmentTestActivity
import neon.gabrielcosta.gvcneon.ui.MainActivity
import neon.gabrielcosta.gvcneon.ui.PeopleListActivity
import neon.gabrielcosta.gvcneon.ui.SendMoneyDialogFragment

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contribute_MainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contribute_PeopleListActivity(): PeopleListActivity

    @ContributesAndroidInjector
    internal abstract fun contribute_BaseFragmentTestActivity(): BaseFragmentTestActivity
}

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contribute_SendMoneyDialogFragment(): SendMoneyDialogFragment

}