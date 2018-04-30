package neon.gabrielcosta.gvcneon.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.View
import neon.gabrielcosta.gvcneon.R
import neon.gabrielcosta.gvcneon.vm.MainViewModel

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModelProviders.of(this, factory)
            .get(MainViewModel::class.java)
            .authenticate("Gabriel", "gabrielcosta08@gmail.com")
            .observe(this, Observer {

            })

        findViewById<View>(R.id.btn_send_money)
            .setOnClickListener {
                startActivity(Intent(baseContext, PeopleListActivity::class.java))
            }
    }
}
