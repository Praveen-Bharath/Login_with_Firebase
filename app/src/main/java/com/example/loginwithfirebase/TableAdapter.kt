package com.example.loginwithfirebase

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.loginwithfirebase.fragment.*

class TableAdapter(
    private val myContext: OverViewFragment,
    fm: FragmentManager,
    internal var totalTabs: Int
) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return DescFragment()
            }
            1 -> {
                return DescFragment()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                return DescFragment()
            }
            else -> {throw IllegalStateException("$position is illegal") }
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}
