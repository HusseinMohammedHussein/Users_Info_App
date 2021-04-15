package com.e.users.ui.activity.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.users.data.pojos.PostsPojo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Hussein on 4/10/2021
 */
@HiltViewModel
class PostsViewModel @Inject constructor(private val getPostsRepo: PostsRepo) :
    ViewModel() {
    var postViewModel: MutableLiveData<List<PostsPojo>> = MutableLiveData()

    fun getPosts(): MutableLiveData<List<PostsPojo>> {
        getPostsRepo.getPosts().enqueue(object : Callback<List<PostsPojo>> {
            override fun onResponse(
                call: Call<List<PostsPojo>>,
                response: Response<List<PostsPojo>>
            ) {
                if (response.isSuccessful && response.body() != null) run {
                    postViewModel.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<PostsPojo>>, t: Throwable) {
                Timber.d("PostsResponseError::%s", t.localizedMessage)
            }
        })
        return postViewModel
    }
}