package com.example.loginwithfirebase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.loginwithfirebase.ProfileActivity
import com.example.loginwithfirebase.R
import com.example.loginwithfirebase.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var firstname: EditText
    private lateinit var secondname: EditText
    private lateinit var password: EditText
    private lateinit var cnfPassword: EditText

    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        username = findViewById(R.id.reg_username)
        firstname = findViewById(R.id.reg_firstName)
        secondname = findViewById(R.id.reg_secondName)
        password = findViewById(R.id.reg_password)
        cnfPassword = findViewById(R.id.reg_cnf_password)
        auth = Firebase.auth

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Users")
        val user = User(username.toString(), firstname.toString(), secondname.toString())

        findViewById<Button>(R.id.btn_login_reg).setOnClickListener {
//            val navRegister = activity as FragmentNavigation
//            navRegister.navigateFrag(LoginFragment(), false)
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            intent.putExtra("user_id", fireb)

        }

        findViewById<Button>(R.id.btn_register_reg).setOnClickListener {
            validateEmptyForm()
        }
    }


    private fun firebaseSignUp() {
        val btnRegisterReg = findViewById<Button>(R.id.btn_register_reg)
        btnRegisterReg?.isEnabled = false
        btnRegisterReg?.alpha = 0.5f
        auth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val currentUser = auth.currentUser
                    val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                    currentUserDb?.child("firstname")?.setValue(firstname.text.toString())
                    currentUserDb?.child("secondname")?.setValue(secondname.text.toString())

                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
//                    val navHome = activity as FragmentNavigation
//                    navHome.navigateFrag(HomeFragment(), addToStack = true)
//                    val t: FragmentTransaction = supportFragmentManager.beginTransaction()
//                    t.replace(R.id.container2, HomeFragment()).commit()
                    startActivity(Intent(this@RegisterActivity, ProfileActivity::class.java))
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    intent.putExtra("user_id", firebaseUser.uid)
//                    intent.putExtra("email_id", firebaseUser.email)
                    startActivity(intent)
//                    finish()


                } else {
                    btnRegisterReg?.isEnabled = true
                    btnRegisterReg?.alpha = 1.0f
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateEmptyForm() {
        val icon =
            AppCompatResources.getDrawable(this, R.drawable.ic_baseline_warning_24)

        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
        when {
            TextUtils.isEmpty(username.text.toString().trim()) -> {
                username.setError("Please Enter Username", icon)
            }
            TextUtils.isEmpty(firstname.text.toString().trim()) -> {
                firstname.setError("Please Enter FirstName", icon)
            }
            TextUtils.isEmpty(secondname.text.toString().trim()) -> {
                secondname.setError("Please Enter SecondName", icon)
            }
            TextUtils.isEmpty(password.text.toString().trim()) -> {
                password.setError("Please Enter Password", icon)
            }
            TextUtils.isEmpty(cnfPassword.text.toString().trim()) -> {
                cnfPassword.setError("Please Enter Conform Password", icon)
                //Toast.makeText(context, "Please Enter Conform Password", Toast.LENGTH_SHORT).show()
            }

            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() &&
                    cnfPassword.text.toString().isNotEmpty() -> {
                if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    if (password.text.toString().length >= 5) {
                        if (password.text.toString() == cnfPassword.text.toString()) {
                            firebaseSignUp()
                            //Toast.makeText(context, "Register Successful", Toast.LENGTH_SHORT).show()
                        } else {
                            cnfPassword.setError("Password didn't Match", icon)
                        }
                    } else {
                        password.setError("Please Enter password more than 5 char", icon)
                    }
                } else {
                    username.setError("Please Enter a Valid ID", icon)
                }
            }
        }
    }
}