package com.e.users.ui.fragments.userProfile.infoBottomSheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.users.data.pojos.UsersPojo
import com.e.users.ui.fragments.userProfile.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InfoBottomSheetViewModel @Inject constructor() : ViewModel() {
    private val getUserInfoMutable: MutableLiveData<UsersPojo> = MutableLiveData()
    private val userPostsRepo = UserRepo()


    fun getUserInfo(userId: Int): MutableLiveData<UsersPojo> {
        userPostsRepo.getUserInfo(userId).enqueue(object : Callback<UsersPojo> {
            override fun onResponse(
                call: Call<UsersPojo>,
                response: Response<UsersPojo>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    getUserInfoMutable.value = response.body()
                }
            }

            override fun onFailure(call: Call<UsersPojo>, t: Throwable) {
                Timber.d("GetUserInfoFailure::%s", t.localizedMessage)
            }

        })

        return getUserInfoMutable
    }

}