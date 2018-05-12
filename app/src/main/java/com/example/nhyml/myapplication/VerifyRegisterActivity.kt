package com.example.nhyml.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit

class VerifyRegisterActivity : AppCompatActivity() {
    lateinit var toolbar_verify:Toolbar
    lateinit var num_verify_1:EditText
    lateinit var num_verify_2:EditText
    lateinit var num_verify_3:EditText
    lateinit var num_verify_4:EditText
    lateinit var num_verify_5:EditText
    lateinit var num_verify_6:EditText
    lateinit var btnVerify:Button
    lateinit var btnReSendCode:Button
    private var countKeyDel:Int=0
    lateinit var user:User
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var mVerificationId: String
    private var mResendToken:PhoneAuthProvider.ForceResendingToken?=null
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_register)
        init()
        setupToobar(toolbar_verify)
        nextEdittext()
        callbackVerify()
        sendCodeVerify(user.phoneNumber,mCallbacks)
        btnVerify.setOnClickListener(View.OnClickListener {
            registerUser();
        })
        btnReSendCode.setOnClickListener(View.OnClickListener {
            resendVerificationCode(user.phoneNumber,mResendToken)

        })

    }

    private fun sendCodeVerify(phoneNumber:String,mCallbacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }
    private fun resendVerificationCode(phoneNumber: String,
                                       token: PhoneAuthProvider.ForceResendingToken?) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber, // Phone number to verify
                60, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                this, // Activity (for callback binding)
                mCallbacks, // OnVerificationStateChangedCallbacks
                token)             // ForceResendingToken from callbacks
    }

    private fun registerUser() {
        var verifyCode:String = num_verify_1.text.toString()+num_verify_2.text.toString()+num_verify_3.text.toString()+num_verify_4.text.toString()+num_verify_5.text.toString()+num_verify_6.text.toString()
        val credential = PhoneAuthProvider.getCredential(mVerificationId, verifyCode)
        signInWithPhoneAuthCredential(credential)
        val ref = FirebaseDatabase.getInstance().getReference("users")
        val userId = ref.push().key
            ref.child(userId).setValue(user).addOnCompleteListener {
                Toast.makeText(applicationContext,"Đăng ký thành công",Toast.LENGTH_LONG).show()
            }

    }

    private fun init(){
        toolbar_verify = findViewById(R.id.toolbar_verify)
        num_verify_1 = findViewById(R.id.num_verify_1)
        num_verify_2 = findViewById(R.id.num_verify_2)
        num_verify_3 = findViewById(R.id.num_verify_3)
        num_verify_4 = findViewById(R.id.num_verify_4)
        num_verify_5 = findViewById(R.id.num_verify_5)
        num_verify_6 = findViewById(R.id.num_verify_6)
        btnVerify = findViewById(R.id.btnVerify)
        btnReSendCode = findViewById(R.id.btnReSendCode)
        val bundle = intent.getBundleExtra("myBundle")
        user = bundle.getParcelable<User>("user") as User
        mAuth = FirebaseAuth.getInstance()
    }
    private fun setupToobar(toolbar: Toolbar){
        if(toolbar==null)
            return
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
    }
    private fun nextEdittext(){
        num_verify_1.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(num_verify_1.text.toString().length == 1 && num_verify_2.text.toString().length!=1){
                    num_verify_2.requestFocus()
                }

            }

        })
        num_verify_2.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(num_verify_2.text.toString().length == 1 && num_verify_3.text.toString().length!=1){
                    num_verify_3.requestFocus()
                }

            }

        })
        num_verify_3.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(num_verify_3.text.toString().length == 1 && num_verify_4.text.toString().length!=1){
                    num_verify_4.requestFocus()
                }

            }

        })
        num_verify_4.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(num_verify_4.text.toString().length == 1 && num_verify_5.text.toString().length!=1){
                    num_verify_5.requestFocus()
                }

            }

        })
        num_verify_5.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(num_verify_5.text.toString().length == 1 && num_verify_6.text.toString().length!=1){
                    num_verify_6.requestFocus()
                }

            }

        })
    }
//    private fun previousEdittext(){
//        num_verify_4.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
//
//
//
//
//
//            false
//        })
//        num_verify_3.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
//            if(i==KeyEvent.KEYCODE_DEL){
//                countKeyDel = countKeyDel +1
//                if((countKeyDel == 2 &&  num_verify_3.text.toString().equals(""))) {
//                    num_verify_2.requestFocus()
//                    countKeyDel = 0
//                }
//            }
//            false
//        })
//        num_verify_2.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
//            if(i==KeyEvent.KEYCODE_DEL){
//                countKeyDel = countKeyDel +1
//                if((countKeyDel == 2 &&  num_verify_2.text.toString().equals(""))) {
//                    num_verify_1.requestFocus()
//                    countKeyDel = 0
//                }
//            }
//            false
//        })
//    }
    private fun callbackVerify(){
    mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // verification completed
            Toast.makeText(applicationContext,"Đăng ký thành công" + credential,Toast.LENGTH_LONG).show()

        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked if an invalid request for verification is made,
            // for instance if the the phone number format is invalid.
            Toast.makeText(applicationContext,"Gửi mã thất bại ",Toast.LENGTH_LONG).show()

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Toast.makeText(applicationContext,"Số điện thoại không đúng.",Toast.LENGTH_LONG).show()
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(applicationContext,"Quota exceeded.",Toast.LENGTH_LONG).show()
            }

        }
        override fun onCodeSent(verificationId: String?,
                                token: PhoneAuthProvider.ForceResendingToken?) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Toast.makeText(applicationContext,"Đang gửi code đến số điện thoại " + user.phoneNumber,Toast.LENGTH_LONG).show()
            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId as String
            mResendToken = token

        }
        override fun onCodeAutoRetrievalTimeOut(verificationId: String?) {
            // called when the timeout duration has passed without triggering onVerificationCompleted
            super.onCodeAutoRetrievalTimeOut(verificationId)
        }
//        private fun resendVerificationCode(phoneNumber: String,
//                                           token: PhoneAuthProvider.ForceResendingToken?) {
//            PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                    phoneNumber, // Phone number to verify
//                    60, // Timeout duration
//                    TimeUnit.SECONDS, // Unit of timeout
//                    this, // Activity (for callback binding)
//                    mCallbacks, // OnVerificationStateChangedCallbacks
//                    token)             // ForceResendingToken from callbacks
//        }

    }
}
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(applicationContext,"Đăng ký thành công.",Toast.LENGTH_LONG).show()
                                                    } else {
                        // Sign in failed, display a message and update the UI
                        Toast.makeText(applicationContext,"Đăng ký thất bại." + credential,Toast.LENGTH_LONG).show()
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Toast.makeText(applicationContext,"Code nhập vào không đúng." + credential,Toast.LENGTH_LONG).show()
                        }
                        // Sign in failed
                    }
                }
    }

}
