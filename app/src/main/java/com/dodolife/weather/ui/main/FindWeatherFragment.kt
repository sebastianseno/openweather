package com.dodolife.weather.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.dodolife.weather.R
import com.dodolife.weather.databinding.FragmentFindWeatherBinding
import com.dodolife.weather.modules.BaseFragment
import com.dodolife.weather.modules.viewBinding
import com.google.android.gms.location.FusedLocationProviderClient


class FindWeatherFragment : BaseFragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val viewModel by viewModels<WeatherViewModels>()

    private val binding by viewBinding(FragmentFindWeatherBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentFindWeatherBinding.inflate(layoutInflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = FusedLocationProviderClient(requireActivity())

        val permList = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        binding.started.setOnClickListener {
            requestPermissions(permList, 102)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            102 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                        viewModel.getWeatherLatLong(location?.latitude, location?.longitude, getString(
                            R.string.api_id))
                        Log.d("senooo", location?.latitude.toString())
                    }
                }
            }

            else -> {
                Toast.makeText(
                    requireContext(), "Permission denied to read your External storage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return
    }

}