package com.e.users.ui.fragments.userProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.users.data.pojos.PostsPojo
import com.e.users.data.pojos.UsersPojo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor() : ViewModel() {
    private val userPostsRepo = UserRepo()
    private val getUserPostsMutable: MutableLiveData<List<PostsPojo>> = MutableLiveData()

    fun getUserPosts(userId: Int): MutableLiveData<List<PostsPojo>> {
        userPostsRepo.getUserPosts(userId).enqueue(object : Callback<List<PostsPojo>> {
            override fun onResponse(
                call: Call<List<PostsPojo>>,
                response: Response<List<PostsPojo>>
            ) {
                if (response.isSuccessful && response.body() != null) run {
                    getUserPostsMutable.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<PostsPojo>>, t: Throwable) {
                Timber.d("getUserPosts::%s", t.localizedMessage)
            }
        })
        return getUserPostsMutable
    }

}