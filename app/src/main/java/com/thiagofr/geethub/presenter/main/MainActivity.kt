package com.thiagofr.geethub.presenter.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thiagofr.geethub.databinding.ActivityMainBinding
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.util.gone
import com.thiagofr.geethub.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.viewState.observe(this) {
            when (it) {
                is MainViewState.Loading -> setLoading(it.isLoading)
                is MainViewState.SetUserList -> setUserList(it.userList)
                MainViewState.Error -> setError()
            }
        }
    }

    private fun setUserList(userList: List<User>) {
        with(binding) {
            rvUserList.visible()
            loading.gone()
        }
    }

    private fun setError() {
        with(binding) {
            loading.gone()
            rvUserList.gone()
            error.visible()
        }
    }

    private fun setLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                loading.visible()
                rvUserList.gone()
                error.gone()
            } else {
                loading.gone()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getUsers()
    }
}