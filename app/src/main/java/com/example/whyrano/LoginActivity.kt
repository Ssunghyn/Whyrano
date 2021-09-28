package com.example.whyrano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.whyrano.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val button = binding.button
        val id = binding.id
        val password = binding.password

        button.setOnClickListener {
            // 관리자 로그인 조건
            if(id.text.toString() == "admin" && password.text.toString() == "0000") {
                val intent = Intent(this,AdminActivity::class.java)
                startActivity(intent)
                finish()
            }

            // 배송기사 로그인 조건
            else if(id.text.toString() == "user" && password.text.toString() == "2580") {
                val intent = Intent(this,UserActivity::class.java)
                startActivity(intent)
                finish()
            }

            else {
                Toast.makeText(this,"아이디 또는 비밀번호가 다릅니다.\n 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}