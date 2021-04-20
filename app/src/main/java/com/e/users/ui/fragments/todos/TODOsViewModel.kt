package com.e.users.ui.fragments.todos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.users.data.pojos.TodoPojo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TODOsViewModel @Inject constructor() : ViewModel() {
    private val todosRepo = TodosRepo()
    private val todosListMutable: MutableLiveData<List<TodoPojo>> = MutableLiveData()


    fun getTodos(userId: Int): MutableLiveData<List<TodoPojo>> {
        todosRepo.getTodos(userId).enqueue(object : Callback<List<TodoPojo>> {
            override fun onResponse(
                call: Call<List<TodoPojo>>,
                response: Response<List<TodoPojo>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    todosListMutable.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<TodoPojo>>, t: Throwable) {
                Timber.d("GetTodosFailure::%s", t.localizedMessage)
            }

        })
        return todosListMutable
    }
}