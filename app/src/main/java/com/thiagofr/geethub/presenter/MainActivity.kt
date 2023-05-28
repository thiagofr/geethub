package com.thiagofr.geethub.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thiagofr.geethub.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.state.observe(this) {
            if(it.isEmpty()) {
                Toast.makeText(this@MainActivity, "Lista vazia", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, it.first().login, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getUsers()
    }
}