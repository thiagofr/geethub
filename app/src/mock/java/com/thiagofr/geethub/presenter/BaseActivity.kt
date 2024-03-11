package com.thiagofr.geethub.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

abstract class BaseActivity: AppCompatActivity() {

    private val uiFragmentManagerHolder: UiFragmentManagerHolder by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiFragmentManagerHolder.fragmentManager = supportFragmentManager
    }

}