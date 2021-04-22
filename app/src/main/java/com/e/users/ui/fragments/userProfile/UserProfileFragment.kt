package com.e.users.ui.fragments.userProfile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.users.R
import com.e.users.databinding.FragmentUserProfileBinding
import com.e.users.ui.fragments.postDetails.PostsDetailsFragment
import com.e.users.ui.fragments.todos.TODOsFragment
import com.e.users.ui.fragments.userProfile.infoBottomSheet.InfoBottomSheetFragment
import com.e.users.ui.main.MainActivity
import com.e.users.utils.SharedPref
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class UserProfileFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    private var viewModel: UserProfileViewModel = UserProfileViewModel()
    private var postsAdapter: UsersPostsAdapter = UsersPostsAdapter()
    private lateinit var sharedPref: SharedPref

    // Bottom_Sheet
    private lateinit var behavior: BottomSheetBehavior<LinearLayout>
//    private lateinit var coordinatorLayout: CoordinatorLayout
    // End

    private var getUsername: String? = null
    private var getEmail: String? = null
    private var getUserId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        setupToolbar()
        sharedPref = SharedPref(this.requireContext())
        getUsername = sharedPref.getString("username")
        Timber.d("getUsername::%s", getUsername)
        getEmail = sharedPref.getString("email")
        Timber.d("getEmail::%s", getEmail)
        getUserId = sharedPref.getInt("userId")
        Timber.d("getUserId::%s", getUserId)
        setupViews()
        getUserPosts()
        initRecyclerView()
    }

    private fun getUserPosts() {
        viewModel.getUserPosts(getUserId).observe(this.requireActivity(), { it ->
            postsAdapter.setData(it)
            binding.content.rvPosts.adapter = postsAdapter
            postsAdapter.notifyDataSetChanged()
            postsAdapter.onItemClick = {
                (activity as MainActivity).startFragment(PostsDetailsFragment())
                sharedPref.setInt("postId", it.id)
                sharedPref.setString("postTitle", it.title)
                sharedPref.setString("postBody", it.body)
            }
        })
    }

    private fun initRecyclerView() {
        binding.content.rvPosts.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.appbar.toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.appbar.tvTitleApp.text = "User Profile"
        binding.appbar.toolbar.setNavigationIcon(R.drawable.btn_back_toolbar)
        binding.appbar.toolbar.setNavigationOnClickListener { (activity as MainActivity).supportFragmentManager.popBackStack() }
    }

    private fun setupViews() {
        binding.content.tvUsername.text = getUsername
        binding.content.tvEmail.text = getEmail

        binding.content.btnTodos.setOnClickListener {
            (activity as MainActivity).startFragment(TODOsFragment())
        }

        binding.content.btnInfo.setOnClickListener {
            setupBottomSheet()
            // if (behavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            // behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            // } else {
            // behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            // }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
    }


    private fun setupBottomSheet() {
//        behavior = BottomSheetBehavior.from(binding.llBottomsheet)
//        coordinatorLayout = binding.coordinator

        val infoBottom: InfoBottomSheetFragment = InfoBottomSheetFragment.newInstance()
        infoBottom.show(
            (activity as MainActivity).supportFragmentManager,
            InfoBottomSheetFragment.TAG
        )
    }

/*    private fun bottomSheet() {
        val view: View = layoutInflater.inflate(R.layout.item_bottomsheet_info, null)
        val mBottomSheetDialog = Dialog(this.requireContext(), R.style.MaterialDialogSheet)
        mBottomSheetDialog.setContentView(view)
        mBottomSheetDialog.setCancelable(true)
        mBottomSheetDialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mBottomSheetDialog.window?.setGravity(Gravity.BOTTOM)
        mBottomSheetDialog.window?.attributes?.windowAnimations =
            R.style.MaterialDialogSheetAnimation
        mBottomSheetDialog.show()
    }*/
}