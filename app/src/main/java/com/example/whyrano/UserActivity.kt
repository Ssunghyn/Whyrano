package com.example.whyrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.example.whyrano.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(){

    private lateinit var binding : ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_user)
        val toolbar = binding.toolbar
        val navigationMenuUser = binding.navigationMenuUser
        val navigation_layout_user = binding.navigationLayoutUser

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar!!
        actionBar.setTitle("")

        navigationMenuUser.setOnClickListener {
            navigation_layout_user.openDrawer(GravityCompat.START)
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameMap, MapsFragment())
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {

        binding = DataBindingUtil.setContentView(this,R.layout.activity_user)
        val navigation_layout_user = binding.navigationLayoutUser

        if (navigation_layout_user.isDrawerOpen(GravityCompat.START)){
            navigation_layout_user.closeDrawers()
        }
        else {
            super.onBackPressed()
        }
    }


}
