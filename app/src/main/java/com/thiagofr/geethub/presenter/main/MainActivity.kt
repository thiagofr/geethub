package com.thiagofr.geethub.presenter.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.geethub.databinding.ActivityMainBinding
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.presenter.main.adapter.UsersAdapter
import com.thiagofr.geethub.util.gone
import com.thiagofr.geethub.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        mainViewModel.viewState.observe(this) {
            when (it) {
                is MainViewState.Loading -> setLoading(it.isLoading)
                is MainViewState.SetUserList -> setUserList(it.userList)
                MainViewState.Error -> setError()
            }
        }
    }

    private fun setUserList(userList: List<User>) {
        binding?.let {
            with(it.rvUserList) {
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                adapter = UsersAdapter(userList) { user ->
                    Toast.makeText(
                        this@MainActivity,
                        "Click no usuário ${user.login}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                visible()
            }
            it.loading.gone()
        }
    }

    private fun setError() {
        binding?.let {
            it.loading.gone()
            it.rvUserList.gone()
            it.error.visible()
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding?.let {
            if (isLoading) {
                it.loading.visible()
                it.rvUserList.gone()
                it.error.gone()
            } else {
                it.loading.gone()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getUsers()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}