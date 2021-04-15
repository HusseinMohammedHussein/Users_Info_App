package com.e.users.ui.activity.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.users.data.pojos.UsersPojo
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class UsersViewModel : ViewModel() {
    private val usersRepository = UsersRepository()
    private val getUsersMutableLiveData: MutableLiveData<List<UsersPojo>> = MutableLiveData()

    fun getUsers(): MutableLiveData<List<UsersPojo>> {
        usersRepository.getUsers().enqueue(object : retrofit2.Callback<List<UsersPojo>> {
            override fun onResponse(
                call: Call<List<UsersPojo>>,
                response: Response<List<UsersPojo>>
            ) {
                getUsersMutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<UsersPojo>>, t: Throwable) {
                Timber.d("UsersFailure::%s", t.localizedMessage)
            }

        })
        return getUsersMutableLiveData
    }
}