package com.example.individualandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.individualandroid.Fragments.CancelledOrderFragment
import com.example.individualandroid.Fragments.HomeFragment
import com.example.individualandroid.Fragments.RecentOrderFragment

class DashboardTabAdapter(var fragmentManager: FragmentManager, var lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        // Return the total number of tabs
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 ->  return RecentOrderFragment()
            1 ->  return CancelledOrderFragment()
            else -> return HomeFragment()
        }

    }
}
