package com.example.loginwithfirebase.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.loginwithfirebase.FragmentNavigation
import com.example.loginwithfirebase.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {
    private lateinit var username: EditText
    private lateinit var firstname: EditText
    private lateinit var secondname: EditText
    private lateinit var password: EditText
    private lateinit var cnfPassword: EditText

    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        username = view.findViewById(R.id.reg_username)
        firstname = view.findViewById(R.id.reg_firstName)
        secondname = view.findViewById(R.id.reg_secondName)
        password = view.findViewById(R.id.reg_password)
        cnfPassword = view.findViewById(R.id.reg_cnf_password)
        auth = Firebase.auth

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("username")

        view.findViewById<Button>(R.id.btn_login_reg).setOnClickListener {
            val navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(LoginFragment(), false)
        }

        view.findViewById<Button>(R.id.btn_register_reg).setOnClickListener {
            validateEmptyForm()
        }
        return view
    }

    private fun firebaseSignUp() {
        val btnRegisterReg = view?.findViewById<Button>(R.id.btn_register_reg)
        btnRegisterReg?.isEnabled = false
        btnRegisterReg?.alpha = 0.5f
        auth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                    currentUserDb?.child("firstname")?.setValue(firstname.text.toString())
                    currentUserDb?.child("secondname")?.setValue(secondname.text.toString())

                    Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                    val navHome = activity as FragmentNavigation
                    navHome.navigateFrag(HomeFragment(), addToStack = true)
                } else {
                    btnRegisterReg?.isEnabled = true
                    btnRegisterReg?.alpha = 1.0f
                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateEmptyForm() {
        val icon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_warning_24)

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