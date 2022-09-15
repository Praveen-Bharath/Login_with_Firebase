package com.example.loginwithfirebase.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginwithfirebase.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    var productList = ArrayList<Product>()
    val product1 = Product(R.drawable.web_development, "Web Development")
    val product2 = Product(R.drawable.android_app_development, "Android App Development")
    val product3 = Product(R.drawable.ios_app_development, "IOS App Development")
    val product4 = Product(R.drawable.ui_ux_design, "UI/UX Design")
    val product5 = Product(R.drawable.cloud_architect, "Cloud Solutions")
    val product6 = Product(R.drawable.cybersecurity, "Cyber Security")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.findViewById<Button>(R.id.btn_log_out).setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(context, "Logged Out Successfully", Toast.LENGTH_SHORT).show()
            val navLogin = activity as FragmentNavigation
            navLogin.navigateFrag(LoginFragment(), addToStack = true)
        }
        view.findViewById<RecyclerView>(R.id.recycler_view).setOnClickListener {
            val nav = activity as FragmentNavigation
            nav.navigateFrag(DescFragment(), addToStack = true)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        productList.add(product1)
        productList.add(product2)
        productList.add(product3)
        productList.add(product4)
        productList.add(product5)
        productList.add(product6)

        val adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter
        return view
    }

}