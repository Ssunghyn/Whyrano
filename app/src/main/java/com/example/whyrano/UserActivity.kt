package com.example.whyrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_admin.toolbar
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar!!
        actionBar.setTitle("")

        navigationMenuUser.setOnClickListener {
            navigation_layout_user.openDrawer(GravityCompat.START)
        }

    }

    override fun onBackPressed() {
        if (navigation_layout_user.isDrawerOpen(GravityCompat.START)){
            navigation_layout_user.closeDrawers()
        }
        else {
            super.onBackPressed()
        }
    }
}
