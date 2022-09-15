package com.example.loginwithfirebase.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loginwithfirebase.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserDetailsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    var username = view?.findViewById<TextView>(R.id.userText)
    var username2 = view?.findViewById<TextView>(R.id.userText2)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_details, container, false)



        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("username")
        // Inflate the layout for this fragment

        loadProfile()


        return view
    }

    @SuppressLint("SetTextI18n")
    private fun loadProfile() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        username?.text = "Email-->" + user?.email

        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                username?.text = "Firstname ->" + snapshot.child("firstname").value.toString()
                username2?.text = "Last name - -> "+snapshot.child("secondname").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }


}