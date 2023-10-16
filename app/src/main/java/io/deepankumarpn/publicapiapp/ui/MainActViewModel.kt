package io.deepankumarpn.publicapiapp.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.deepankumarpn.publicapiapp.data.remote.ChuckNorrisJoke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActViewModel(private val repository: MainRepository) : ViewModel() {

    private val mutableJoke = MutableLiveData<ChuckNorrisJoke>()

    fun getRandomJoke(
        onResponse: (ChuckNorrisJoke) -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getRandomJoke()
                if (result != null) {
                    mutableJoke.postValue(result)
                    onResponse.invoke(result)
                } else {
                    onFailure.invoke("No data received")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onFailure.invoke("An error occurred: ${e.message}")
            }
        }
    }


}