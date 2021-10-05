package com.example.whyrano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.example.whyrano.databinding.ActivityUserBinding
import com.google.android.material.navigation.NavigationView

class UserActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

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

        binding.navigationViewUser.setNavigationItemSelectedListener(this)

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navigation_layout = binding.navigationLayoutUser
        when(item.itemId){
            R.id.one -> {
                Toast.makeText(this,"개인정보변경",Toast.LENGTH_SHORT).show()
                navigation_layout.closeDrawers()
            }
            R.id.two -> {
                Toast.makeText(this,"배송정보확인",Toast.LENGTH_SHORT).show()
                navigation_layout.closeDrawers()
            }
            R.id.three -> {
                Toast.makeText(this,"문의하기",Toast.LENGTH_SHORT).show()
                navigation_layout.closeDrawers()
            }
            R.id.logout -> {
                Toast.makeText(this,"로그아웃 되었습니다.",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
                return false
            }
        }
        return false
    }
}
