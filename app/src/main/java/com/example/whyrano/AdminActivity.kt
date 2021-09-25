package com.example.whyrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_admin.*

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar!!
        actionBar.setTitle("")

        navigationMenu.setOnClickListener {
            navigation_layout.openDrawer(GravityCompat.START)
        }

    }

    override fun onBackPressed() {
        if (navigation_layout.isDrawerOpen(GravityCompat.START)){
            navigation_layout.closeDrawers()
        }
        else {
            super.onBackPressed()
        }
    }
}