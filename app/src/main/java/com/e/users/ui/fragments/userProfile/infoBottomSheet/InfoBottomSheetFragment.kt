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
        init()
        return binding.root
    }

    private fun init() {
        sharedPref = SharedPref(this.requireContext())
        getUserId = sharedPref.getInt("userId")
        viewModel.getUserInfo(getUserId).observe(this.requireActivity(), {
            Timber.d("UserID::%s", getUserId)
            userPojo = UsersPojo(
                id = it.id,
                name = it.name,
                username = it.username,
                email = it.email,
                phone = it.phone
            )
            binding.tvName.text = userPojo.name
            Timber.d("tvName::%s", binding.tvName.text.toString())
            binding.tvUsername.text = userPojo.username
            Timber.d("tvUsername::%s", binding.tvUsername.text.toString())
            binding.tvEmail.text = userPojo.email
            Timber.d("tvEmail::%s", binding.tvEmail.text.toString())
            binding.tvPhone.text = userPojo.phone
            Timber.d("tvPhone::%s", binding.tvPhone.text.toString())
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InfoBottomSheetViewModel::class.java)
        dialog?.window?.attributes?.windowAnimations = R.style.MaterialDialogSheet
    }
}