package com.example.individualandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.individualandroid.Fragments.CancelledOrderFragment
import com.example.individualandroid.Fragments.HomeFragment
import com.example.individualandroid.Fragments.ProfileFragment
import com.example.individualandroid.Fragments.RecentOrderFragment
import com.example.individualandroid.Fragments.SearchFragment
import com.example.individualandroid.databinding.ActivityDashboardBinding
import com.google.android.material.tabs.TabLayout

class DashboardActivity : AppCompatActivity() {
    lateinit var dashboardBinding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)


        // Handle bottom navigation item selection
        replaceFragment(HomeFragment())
        dashboardBinding.dashboardNavigationBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Home -> {replaceFragment(HomeFragment())}
                R.id.Search -> {replaceFragment(SearchFragment())}
                R.id.Profile -> {replaceFragment(ProfileFragment())}
                else -> false
            }
        }

        // Tab Layout Part
        dashboardBinding.dashboardTabLayout.
        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { replaceFragment(getFragment(it.position)) }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


    }

    private fun replaceFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.dashboardFrameLayout, fragment)
            .commit()
        return true
    }

    private fun getFragment(position: Int): Fragment {
        return when (position) {
            0 -> RecentOrderFragment()
            1 -> CancelledOrderFragment()
            else -> HomeFragment() // Default to HomeFragment if position is out of bounds
        }
    }
}

