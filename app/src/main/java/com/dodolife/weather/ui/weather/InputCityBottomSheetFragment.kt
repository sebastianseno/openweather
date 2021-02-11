package com.dodolife.weather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dodolife.weather.R
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
            dismiss()
            viewModel.getWeatherLatLong(binding.inputCity.text.toString(), getString(R.string.api_id))
        }
    }
}