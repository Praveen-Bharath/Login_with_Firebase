package com.example.loginwithfirebase.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginwithfirebase.ProfileActivity
import com.example.loginwithfirebase.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {//FragmentNavigation {

    private lateinit var fAuth: FirebaseAuth

    //  var tabLayout: TabLayout? = null
    //  var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fAuth = Firebase.auth
        /*  val move= findViewById<ImageView>(R.id.product_image)!!
              move.setOnClickListener {
               replaceFragment(DescFragment())
         }*/
        //if the user is already logged in , they will be redirected to home screen
        val currentUser = fAuth.currentUser
        if (currentUser != null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.container, HomeFragment()).addToBackStack(null)
//                .commit()
            startActivity(Intent(this, ProfileActivity::class.java))
           // startActivity(intent)
        } else {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.container, LoginFragment())
//                .commit()
            startActivity(Intent(this, ProfileActivity::class.java))
          //  startActivity(intent)
        }

        //  tabLayout = findViewById(R.id.tabLayout)
        //  viewPager = findViewById(R.id.viewPager)
        //  table()

    }

    /*   private fun table() {
           tabLayout!!.addTab(tabLayout!!.newTab().setText("Home"))
           tabLayout!!.addTab(tabLayout!!.newTab().setText("About"))
           tabLayout!!.addTab(tabLayout!!.newTab().setText("Services"))
           tabLayout!!.addTab(tabLayout!!.newTab().setText("Contact"))
           tabLayout!!.addTab(tabLayout!!.newTab().setText("Career"))

           tabLayout!!.tabGravity =TabLayout.GRAVITY_FILL

           val adapter = TableAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
           viewPager!!.adapter = adapter

           viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

           tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
               override fun onTabSelected(tab: TabLayout.Tab) {
                   viewPager!!.currentItem = tab.position
               }
               override fun onTabUnselected(tab: TabLayout.Tab) {

               }
               override fun onTabReselected(tab: TabLayout.Tab) {

               }
           })
       }
   */
    /*  private fun replaceFragment(fragment: Fragment) {
          val fragmentManager = supportFragmentManager
          val fragmentTransaction = fragmentManager.beginTransaction()
          fragmentTransaction.replace(R.id.desc_view, fragment)
          fragmentTransaction.commit()
      }*/

//    override fun navigateFrag(fragment: Fragment, addToStack: Boolean) {
//        val transaction = supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, fragment)
//
//        if (addToStack) {
//            transaction.addToBackStack(null)
//        }
//        transaction.commit()
//    }
}