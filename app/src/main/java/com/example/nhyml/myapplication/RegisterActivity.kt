package com.example.nhyml.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    lateinit var toolbar_register: Toolbar
    lateinit var regis_phoneNumber:EditText
    lateinit var regis_password:EditText
    lateinit var btnRegister:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
        setupToolbar(toolbar_register)
        btnRegister.setOnClickListener(View.OnClickListener {
            registerUser()
        })
    }
    private fun setupToolbar(toolbar: android.support.v7.widget.Toolbar){
        if(toolbar == null) return
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
    }
    private fun init(){
        NetworkAvailable().isNetWorkAvailable(this)
        toolbar_register = findViewById(R.id.toolbar_register) as Toolbar
        regis_phoneNumber = findViewById(R.id.regis_phoneNumber) as EditText
        regis_password = findViewById(R.id.regis_password) as EditText
        btnRegister = findViewById(R.id.btnRegister) as Button
    }
    private fun registerUser(){
        if(regis_phoneNumber.text.isEmpty()){
            Toast.makeText(applicationContext,"Vui lòng nhập số điện thoại", Toast.LENGTH_LONG).show()
            return
        }
        if (regis_password.text.isEmpty()){
            Toast.makeText(applicationContext,"Vui lòng nhập mật khẩu", Toast.LENGTH_LONG).show()
            return
        }
        if(NetworkAvailable().isNetWorkAvailable(applicationContext)){
            val phoneNumber: String = regis_phoneNumber.text.toString()
            val password: String = regis_password.text.toString()
            val user = User(phoneNumber, password)
            val intent = Intent(applicationContext,VerifyRegisterActivity::class.java)
            var bundle = Bundle()
            bundle.putParcelable("user",user)
            intent.putExtra("myBundle",bundle)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(applicationContext,"Xem lại kết nối của bạn", Toast.LENGTH_LONG).show()
        }


    }

}
