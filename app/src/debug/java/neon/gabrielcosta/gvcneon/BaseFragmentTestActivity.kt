package neon.gabrielcosta.gvcneon

import android.os.Bundle
import neon.gabrielcosta.gvcneon.ui.BaseActivity

class BaseFragmentTestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

}