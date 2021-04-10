package com.countrydetails.ui.main.province

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.countrydetails.data.entities.Province
import com.countrydetails.data.repository.DataRepository
import com.countrydetails.utils.Resource

class ProvinceViewModel @ViewModelInject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()
    private var code: String? = null

    private val _character = _id.switchMap { id ->
        repository.getProvince(id, code)
    }
    val provinces: LiveData<Resource<List<Province>>> = _character

    fun start(id: Int?, code: String? = null) {
        this.code = code
        _id.value = id

    }

}
