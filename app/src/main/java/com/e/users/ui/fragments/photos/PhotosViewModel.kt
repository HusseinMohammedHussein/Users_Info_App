package com.e.users.ui.fragments.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.users.data.pojos.PhotosPojo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class PhotosViewModel @Inject constructor() : ViewModel() {
    private val photosRepo: PhotosRepo = PhotosRepo()
    private val photosMutable: MutableLiveData<List<PhotosPojo>> = MutableLiveData()

    fun getAlbumPhotos(albumId: Int): MutableLiveData<List<PhotosPojo>> {
        photosRepo.getAlbumPhotos(albumId).enqueue(object : Callback<List<PhotosPojo>> {
            override fun onResponse(
                call: Call<List<PhotosPojo>>,
                response: Response<List<PhotosPojo>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    photosMutable.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<PhotosPojo>>, t: Throwable) {
                Timber.d("AlbumPhotosFailure:: %s", t.localizedMessage)
            }
        })
        return photosMutable
    }
}