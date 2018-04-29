package neon.gabrielcosta.gvcneon.ui

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    protected lateinit var factory: ViewModelProvider.Factory

}