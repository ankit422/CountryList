package com.countrydetails.ui.main.country

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.countrydetails.data.repository.DataRepository

class CountryViewModel @ViewModelInject constructor(
    repository: DataRepository
) : ViewModel() {

    val articles = repository.getCountries()
}
