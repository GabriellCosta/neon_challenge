package neon.gabrielcosta.gvcneon.util

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import neon.gabrielcosta.gvcneon.R

fun AppCompatActivity.configureDefaultToolbar(toolbarId: Int, title: String) {
    val toolbar = findViewById<Toolbar>(toolbarId)
    val titleView = toolbar.findViewById<TextView>(R.id.toolbar_title)
    titleView.text = title
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.title = ""
}