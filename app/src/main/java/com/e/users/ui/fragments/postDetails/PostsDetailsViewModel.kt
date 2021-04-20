package com.e.users.ui.fragments.postDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.users.data.pojos.PostCommentsPojo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PostsDetailsViewModel @Inject constructor(): ViewModel() {
    private val postCommentsMutable: MutableLiveData<List<PostCommentsPojo>> = MutableLiveData()
    private val postCommentsRepo = PostCommentsRepo()


    fun getPostsComments(postID: Int): MutableLiveData<List<PostCommentsPojo>> {
        postCommentsRepo.getPostComments(postID).enqueue(object :
            Callback<List<PostCommentsPojo>> {
            override fun onResponse(
                call: Call<List<PostCommentsPojo>>,
                response: Response<List<PostCommentsPojo>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    postCommentsMutable.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<PostCommentsPojo>>, t: Throwable) {
                Timber.d("PostCommentsFailure::%s", t.localizedMessage)
            }

        })
        return postCommentsMutable
    }
}