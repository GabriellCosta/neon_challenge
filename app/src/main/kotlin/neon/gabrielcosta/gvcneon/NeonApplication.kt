package neon.gabrielcosta.gvcneon

import android.app.Activity
import android.app.Application
import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import neon.gabrielcosta.gvcneon.dagger.AppInjector
import javax.inject.Inject

class NeonApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector().init(this)
        Hawk.init(this).build()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}