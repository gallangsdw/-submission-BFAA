package com.sdwtech.githubuser.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sdwtech.githubuser.R
import com.sdwtech.githubuser.detail.FollowersFragment
import com.sdwtech.githubuser.detail.FollowingFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private val TAB_TITLES = intArrayOf(R.string.followers_title, R.string.following_title)
    }

    var username: String? = null

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment.getUsername(username.toString())
            1 -> fragment = FollowingFragment.getUsername(username.toString())
        }

        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])
}