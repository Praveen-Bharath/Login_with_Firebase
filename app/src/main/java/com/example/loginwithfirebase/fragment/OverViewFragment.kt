package com.example.loginwithfirebase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.loginwithfirebase.R
import com.example.loginwithfirebase.TableAdapter
import com.google.android.material.tabs.TabLayout


class OverViewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_over_view, container, false)
        var tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        var viewPager = view.findViewById<ViewPager>(R.id.viewPager)

        fun table() {
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Home"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("About"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Services"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Contact"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Career"))

            tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

            val adapter = fragmentManager?.let { TableAdapter(this, it, tabLayout!!.tabCount) }
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

        return view
    }


}