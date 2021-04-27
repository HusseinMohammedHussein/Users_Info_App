package com.e.users.ui.fragments.userProfile.albums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.users.data.pojos.AlbumsPojo
import com.e.users.ui.fragments.userProfile.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor() : ViewModel() {
    private val albumsMutable: MutableLiveData<List<AlbumsPojo>> = MutableLiveData()
    private val albumsRepo: UserRepo = UserRepo()


    fun getUserAlbums(userId: Int): MutableLiveData<List<AlbumsPojo>> {
        albumsRepo.getUserAlbums(userId).enqueue(object : Callback<List<AlbumsPojo>> {
            override fun onResponse(
                call: Call<List<AlbumsPojo>>,
                response: Response<List<AlbumsPojo>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    albumsMutable.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<AlbumsPojo>>, t: Throwable) {
                Timber.d("getUserAlbums::%s", t.localizedMessage)
            }

        })

        return albumsMutable
    }
}