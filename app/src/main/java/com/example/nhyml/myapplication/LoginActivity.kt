package com.example.nhyml.myapplication

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import java.util.*

class LoginActivity : AppCompatActivity() {
    lateinit var toolbar_login: Toolbar
    lateinit var login_phoneNumber: EditText
    lateinit var login_password: EditText
    lateinit var btnLogin: Button
    lateinit var tvForgetPassword:TextView
    lateinit var btnAlterRegister: Button
    lateinit var mAuth:FirebaseAuth
    lateinit var currentUser: FirebaseUser
    private var RC_SIGN_IN = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        setupToobar(toolbar_login)
        val isUserSignedIn = FirebaseAuth.getInstance().currentUser != null
        btnLogin.setOnClickListener(View.OnClickListener {
            if(isUserSignedIn){
                loginAcount()
            }

        })
        btnAlterRegister.setOnClickListener(View.OnClickListener {
            var intentRegister = Intent(applicationContext,RegisterActivity::class.java)
            startActivity(intentRegister)
            finish()
        })

    }
    private fun setupToobar(toolbar: Toolbar){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
    }
    private fun init(){
        toolbar_login = findViewById(R.id.toolbar_login) as Toolbar
        login_phoneNumber = findViewById(R.id.login_phoneNumber) as EditText
        login_password = findViewById(R.id.login_password) as EditText
        tvForgetPassword = findViewById(R.id.tvForgetPassword) as TextView
        btnLogin = findViewById(R.id.btnLogin) as Button
        btnAlterRegister = findViewById(R.id.btnAlterRegister) as Button
        mAuth = FirebaseAuth.getInstance()
    }
    private fun loginAcount(){
        if(login_phoneNumber.text.isEmpty()){
            Toast.makeText(applicationContext,"Vui lòng nhập số điện thoại", Toast.LENGTH_LONG).show()
            return
        }
        if(login_password.text.isEmpty()){
            Toast.makeText(applicationContext,"Vui lòng nhập mật khẩu", Toast.LENGTH_LONG).show()
            return
        }
        if(NetworkAvailable().isNetWorkAvailable(applicationContext)){
            val phoneNumber = login_phoneNumber.text.toString().toInt()
            val password = login_password.text.toString()

        } else {
            Toast.makeText(applicationContext,"Xem lại kết nối của bạn", Toast.LENGTH_LONG).show()
        }
        
    }

    override fun onStart() {
        super.onStart()
        var currentUser = mAuth.currentUser as FirebaseUser

    }


}
