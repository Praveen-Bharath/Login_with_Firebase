package com.example.loginwithfirebase

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.loginwithfirebase.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var databaseReference: DatabaseReference? = null
    private var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var userText = findViewById<TextView>(R.id.userText)
        var userText2 = findViewById<TextView>(R.id.userText2)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("/username/MDEgwJmi79QoDxxkgbv4iiy4D7j1")
//                .get().addOnSuccessListener {
//                if(it.exists()){
//                    val firstname = it.child("firstname").value
//                    userText.text = firstname.toString()
//                }
//                else{
//                    Toast.makeText(this,"Not Exist",Toast.LENGTH_SHORT).show()
//                }

        // Inflate the layout for this fragment

//        val userId = intent.getStringExtra("user_id")
//        val emailId = intent.getStringExtra("email_id")
//
//        userText.text = userId
//        userText2.text = emailId

    //        val getdata = object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                var sb = StringBuffer()
//                for(i in snapshot.children){
//                    var ename = i.child("firstname").getValue()
//                    var ename2 = i.child("secondname").getValue()
//                    sb.append("${i.key} $ename")
//                }
//                userText.setText(sb)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//
//
//        }
//        databaseReference?.addValueEventListener(getdata)
//        databaseReference?.addListenerForSingleValueEvent(getdata)
        loadProfile()

    }

    @SuppressLint("SetTextI18n")
    private fun loadProfile() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        var userText = findViewById<TextView>(R.id.userText)
        var userText2 = findViewById<TextView>(R.id.userText2)

//       userText?.text = "Email-->" + user?.email
//        userText2?.text =user?.uid
        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userText?.text = "Firstname ->" + snapshot.child("/username/MDEgwJmi79QoDxxkgbv4iiy4D7j1").value.toString()
                userText2?.text = "Last name - -> " + snapshot.child("secondname").value.toString()
//                val value = snapshot.getValue<String>()
//                Log.d(TAG, "Value is: " + value)
                Log.d(TAG, "First" + snapshot.child("firstname").value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}