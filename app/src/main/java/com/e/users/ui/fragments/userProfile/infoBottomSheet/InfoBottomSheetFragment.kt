package com.e.users.ui.fragments.userProfile.infoBottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.e.users.R
import com.e.users.data.pojos.UsersPojo
import com.e.users.databinding.FragmentInfoBottomSheetBinding
import com.e.users.utils.SharedPref
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class InfoBottomSheetFragment @Inject constructor() : BottomSheetDialogFragment() {
    private var viewModel: InfoBottomSheetViewModel = InfoBottomSheetViewModel()
    private lateinit var binding: FragmentInfoBottomSheetBinding
    private var getUserId: Int = 0
    private lateinit var sharedPref: SharedPref
    private lateinit var userPojo: UsersPojo

    companion object {
        fun newInstance() = InfoBottomSheetFragment()
        const val TAG = "Info_Bottom_Sheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init() {
        sharedPref = SharedPref(this.requireContext())
        getUserId = sharedPref.getInt("userId")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methods()
    }

    private fun methods() {
        init()
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModel.getUserInfo(getUserId).observe(this.requireActivity(), {
            Timber.d("UserID::%s", getUserId)
            userPojo = UsersPojo(
                id = it.id,
                email = it.email,
                phone = it.phone,
                website = it.website,
                address = it.address,
                company = it.company,
                username = it.username
            )
            setupViews(userPojo)
        })
    }

    private fun setupViews(usersPojo: UsersPojo) {
        binding.tvEmail.text = usersPojo.email
        Timber.d("tvName::%s", binding.tvEmail.text.toString())

        binding.tvLocation.text = usersPojo.address.city
        Timber.d("tvUsername::%s", binding.tvLocation.text.toString())

        binding.tvPhone.text = usersPojo.phone
        Timber.d("tvPhone::%s", binding.tvPhone.text.toString())

        binding.tvWebsite.text = usersPojo.website
        Timber.d("tvEmail::%s", binding.tvWebsite.text.toString())

        binding.tvCoName.text = usersPojo.company.name
        Timber.d("tvEmail::%s", binding.tvCoName.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InfoBottomSheetViewModel::class.java)
        dialog?.window?.attributes?.windowAnimations = R.style.MaterialDialogSheet
    }
}