package com.example.directorycompanies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.directorycompanies.App
import com.example.directorycompanies.R
import com.example.directorycompanies.databinding.ActivityMainBinding
import com.example.directorycompanies.di.modules.view.ViewComponent
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewComponent: ViewComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("TestLogEmulator");

        viewComponent = (applicationContext as App)
            .appComponent.viewComponent().create()
        viewComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

}
