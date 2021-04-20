package com.e.users.ui.activity.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.e.users.R
import com.e.users.databinding.ActivityMainBinding
import com.e.users.ui.base.BaseActivity
import com.e.users.ui.fragments.users.UsersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        init()
    }

    private fun init() {
        supportFragmentManager.commit {
            replace(R.id.nav_host, UsersFragment())
            addToBackStack(null)
        }
    }

    fun startFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.nav_host, fragment)
            addToBackStack(null)
        }
    }
}