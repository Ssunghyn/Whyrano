package com.example.whyrano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.core.view.MenuCompat
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whyrano.databinding.ActivityAdminBinding
import com.google.android.material.navigation.NavigationView

class AdminActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var dataList = ArrayList<Deliverly>()
    private lateinit var binding : ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_admin)

        val toolbar = binding.toolbar
        val navigationMenu = binding.navigationMenu
        val navigation_layout = binding.navigationLayout

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar!!
        actionBar.setTitle("")

        navigationMenu.setOnClickListener {
            navigation_layout.openDrawer(GravityCompat.START)

        }

        binding.navigationView.setNavigationItemSelectedListener(this)

        recyclerViewSetting()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.admin_menu,menu)
        MenuCompat.setGroupDividerEnabled(menu,true)
        return true
    }

    private fun recyclerViewSetting() {
        var overList = ArrayList<Int>()
        var dateList = ArrayList<String>()
        overList.add(0)
        overList.add(0)
        overList.add(2)
        dateList.add("2021.10.02")
        dateList.add("2021.09.04")
        dateList.add("2021.08.16")
        dataList.add(Deliverly("김예림","충청북도 청주시 상당구", overList,dateList,100,220))
        overList.clear()
        overList.add(1)
        overList.add(5)
        overList.add(2)
        dateList.clear()
        dateList.add("2021.08.06")
        dateList.add("2021.04.19")
        dateList.add("2021.11.27")
        dataList.add(Deliverly("김수아","전라남도 순천시 장천동", overList,dateList,127,300))
        overList.clear()
        overList.add(3)
        overList.add(2)
        overList.add(7)
        dateList.clear()
        dateList.add("2021.04.01")
        dateList.add("2021.01.15")
        dateList.add("2021.07.22")
        dataList.add(Deliverly("위건","경기도 평택시 안중읍", overList,dateList,88,150))

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@AdminActivity,LinearLayoutManager.VERTICAL,false)
        var recyclerViewAdapter = RecyclerViewAdapter(recyclerView)
        recyclerViewAdapter.addItem(dataList)
        recyclerView.adapter = recyclerViewAdapter

    }


    override fun onBackPressed() {

        val navigation_layout = binding.navigationLayout

        if (navigation_layout.isDrawerOpen(GravityCompat.START)){
            navigation_layout.closeDrawers()
        }
        else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navigation_layout = binding.navigationLayout
        when(item.itemId){
            R.id.manone -> {
                Toast.makeText(this,"배송원 1을 클릭하셨습니다.",Toast.LENGTH_SHORT).show()
                navigation_layout.closeDrawers()
            }
            R.id.mantwo -> {
                Toast.makeText(this,"배송원 2을 클릭하셨습니다.",Toast.LENGTH_SHORT).show()
                navigation_layout.closeDrawers()
            }
            R.id.manthree -> {
                Toast.makeText(this,"배송원 3을 클릭하셨습니다.",Toast.LENGTH_SHORT).show()
                navigation_layout.closeDrawers()
            }
            R.id.manfour -> {
                Toast.makeText(this,"배송원 4을 클릭하셨습니다.",Toast.LENGTH_SHORT).show()
                navigation_layout.closeDrawers()
            }
            R.id.adlogout -> {
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