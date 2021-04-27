package com.e.users.ui.fragments.userProfile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.users.R
import com.e.users.databinding.FragmentUserProfileBinding
import com.e.users.ui.fragments.postDetails.PostsDetailsFragment
import com.e.users.ui.fragments.todos.TODOsFragment
import com.e.users.ui.fragments.userProfile.albums.AlbumsBottomSheet
import com.e.users.ui.fragments.userProfile.infoBottomSheet.InfoBottomSheetFragment
import com.e.users.ui.main.MainActivity
import com.e.users.utils.SharedKeys
import com.e.users.utils.SharedKeys.Companion.EMAIL
import com.e.users.utils.SharedKeys.Companion.POST_BODY
import com.e.users.utils.SharedKeys.Companion.POST_ID
import com.e.users.utils.SharedKeys.Companion.POST_TITLE
import com.e.users.utils.SharedKeys.Companion.USER_NAME
import com.e.users.utils.SharedPref
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
    // private lateinit var behavior: BottomSheetBehavior<LinearLayout>
    // private lateinit var coordinatorLayout: CoordinatorLayout
    // End

    private var getUsername: String? = null
    private var getEmail: String? = null
    private var getUserId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
    }

    private fun init() {
        sharedPref = SharedPref(this.requireContext())
        binding.content.rvPosts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }

        getUsername = sharedPref.getString(USER_NAME)
        Timber.d("getUsername::%s", getUsername)
        getEmail = sharedPref.getString(EMAIL)
        Timber.d("getEmail::%s", getEmail)
        getUserId = sharedPref.getInt(SharedKeys.USER_ID)
        Timber.d("getUserId::%s", getUserId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methods()
    }

    private fun methods() {
        setupToolbar()
        init()
        setupViews()
        getUserPosts()
        onItemClick()
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
            infoBottomSheet()
            // if (behavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            // behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            // } else {
            // behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            // }
        }

        binding.content.btnAlbums.setOnClickListener {
            albumsBottomSheet()
        }
    }

    private fun albumsBottomSheet() {
        val albumsBottomSheet = AlbumsBottomSheet.newInstance()
        albumsBottomSheet.show(
            (activity as MainActivity).supportFragmentManager,
            AlbumsBottomSheet.TAG
        )
    }

    private fun getUserPosts() {
        viewModel.getUserPosts(getUserId).observe(this.requireActivity(), { it ->
            postsAdapter.setData(it)
            binding.content.rvPosts.adapter = postsAdapter
            postsAdapter.notifyDataSetChanged()
        })
    }

    private fun infoBottomSheet() {
//        behavior = BottomSheetBehavior.from(binding.llBottomsheet)
//        coordinatorLayout = binding.coordinator

        val infoBottom: InfoBottomSheetFragment = InfoBottomSheetFragment.newInstance()
        infoBottom.show(
            (activity as MainActivity).supportFragmentManager,
            InfoBottomSheetFragment.TAG
        )
    }

    private fun onItemClick() {
        postsAdapter.onItemClick = {
            (activity as MainActivity).startFragment(PostsDetailsFragment())
            sharedPref.setInt(POST_ID, it.id)
            sharedPref.setString(POST_TITLE, it.title)
            sharedPref.setString(POST_BODY, it.body)
        }
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