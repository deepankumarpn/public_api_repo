package io.deepankumarpn.publicapiapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import io.deepankumarpn.publicapiapp.databinding.ActivityMainBinding
import io.deepankumarpn.publicapiapp.network.RetrofitClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainActViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apiManager = RetrofitClient.apiService
        val repository = MainRepository(apiManager)
        mainViewModel = MainActViewModel(repository)

        init()
        setListner()

    }

    fun init() {
        gettingJoke()
    }

    fun setListner() {
        binding.swipeToRefreshLis.setOnRefreshListener {
            gettingJoke()
        }
    }

    fun gettingJoke() {
        mainViewModel.getRandomJoke(
            onResponse = { joke ->
                runOnUiThread {
                    binding.randomJokeTxtView.text = joke.value
                    binding.swipeToRefreshLis.isRefreshing = false
                }
            },
            onFailure = { error ->
                runOnUiThread {
                    Log.e("MainActivity", "Error: $error")
                    binding.swipeToRefreshLis.isRefreshing = false
                }
            }
        )
    }

}