package com.e.users.ui.activity.posts

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import com.e.users.R
import com.e.users.databinding.ActivityMainBinding
import com.e.users.ui.activity.users.UsersFragment
import com.e.users.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val postsViewModel: PostsViewModel by viewModels()
    private var postsAdapter: PostsAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        init()
    }

    private fun init() {
        mBinding.appbar.tvTitleApp.text = getString(R.string.main_activity_title)
        mBinding.appbar.toolbarApp.setNavigationOnClickListener { finish() }
//        postsViewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
//        getPostsData()
        mBinding.btn.setOnClickListener{
            setupFragment()
        }
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, UsersFragment())
            commit()
        }
    }

//    private fun getPostsData() {
//        postsViewModel.getPosts().observe(this, {
//            Timber.d("Data::%s", it)
//            postsAdapter.setData(it)
//
//            mBinding.rv.run {
//                setHasFixedSize(true)
//                layoutManager =
//                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//                adapter = postsAdapter
//            }
//        })
//    }
}