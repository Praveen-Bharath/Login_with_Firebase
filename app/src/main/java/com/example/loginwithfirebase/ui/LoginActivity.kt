package com.example.loginwithfirebase.ui

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.loginwithfirebase.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var fAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.log_username)
        password = findViewById(R.id.log_password)
        fAuth = Firebase.auth

        findViewById<Button>(R.id.btn_register).setOnClickListener {
//            val navRegister = activity as FragmentNavigation
//            navRegister.navigateFrag(RegisterFragment(), false)
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn_login).setOnClickListener {
            validateForm()
        }
    }

    private fun firebaseSignIn() {
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin?.isEnabled = false
        btnLogin?.alpha = 0.5f
        fAuth.signInWithEmailAndPassword(
            username.text.toString(),
            password.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
//                val navHome = activity as FragmentNavigation
//                navHome.navigateFrag(UserDetailsFragment(), addToStack = true)
//                val t: FragmentTransaction = supportFragmentManager.beginTransaction()
//                t.replace(R.id.container, UserDetailsFragment()).commit()

                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                startActivity(intent)
                Log.d(TAG,"Passed ok done")


            } else {
                btnLogin?.isEnabled = true
                btnLogin?.alpha = 1.0f
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm() {
        val icon =
            AppCompatResources.getDrawable(this, R.drawable.ic_baseline_warning_24)

        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
        when {
            TextUtils.isEmpty(username.text.toString().trim()) -> {
                username.setError("Please Enter Username", icon)
            }
            TextUtils.isEmpty(password.text.toString().trim()) -> {
                username.setError("Please Enter Password", icon)
            }


            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() -> {
                if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    firebaseSignIn()
                } else {
                    username.setError("Please Enter a Valid ID", icon)
                }
            }
        }
    }
}