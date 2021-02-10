package com.dodolife.weather.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dodolife.weather.R
import com.dodolife.weather.databinding.ActivityMainBinding
import com.dodolife.weather.modules.BaseActivity
import com.google.android.gms.location.FusedLocationProviderClient

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navController = findNavController(R.id.navMain)

        navController.setGraph(R.navigation.nav_main)

        binding.toolbar.setupWithNavController(navController)

    }


}