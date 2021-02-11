package com.dodolife.weather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dodolife.weather.R
import com.dodolife.weather.common.UiState
import com.dodolife.weather.databinding.FragmentInputCityBottomSheetBinding
import com.dodolife.weather.modules.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputCityBottomSheetFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentInputCityBottomSheetBinding::bind)

    private val viewModel by viewModels<WeatherViewModels>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentInputCityBottomSheetBinding.inflate(layoutInflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputButton.setOnClickListener {
            viewModel.getWeatherLatLong(binding.inputCity.text.toString(), getString(R.string.api_id))
        }

        viewModel.findweatherState.observe(viewLifecycleOwner, Observer {uiState ->
            when (uiState) {
                UiState.Success -> {dismiss()}
                is UiState.Error -> Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}