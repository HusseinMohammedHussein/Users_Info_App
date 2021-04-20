package com.e.users.ui.fragments.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.users.data.pojos.UsersPojo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor() :
    ViewModel() {
    private val usersRepository = UsersRepository()
    private val getUsersMutableLiveData: MutableLiveData<List<UsersPojo>> = MutableLiveData()

    fun getUsers(): MutableLiveData<List<UsersPojo>> {
        usersRepository.getUsers().enqueue(object : Callback<List<UsersPojo>> {
            override fun onResponse(
                call: Call<List<UsersPojo>>,
                response: Response<List<UsersPojo>>
            ) {
                if (response.isSuccessful && response.body() != null) run {
                    getUsersMutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<UsersPojo>>, t: Throwable) {
                Timber.d("UsersFailure::%s", t.localizedMessage)
            }

        })
        return getUsersMutableLiveData
    }
}