package com.example.hospital.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.network.Hospital
import com.example.hospital.network.HospitalApi
import kotlinx.coroutines.launch

enum class HospitalApiStatus {LOADING, ERROR, DONE}

class HospitalViewModel : ViewModel() {
    private val _status = MutableLiveData<HospitalApiStatus>()
    val status: LiveData<HospitalApiStatus> = _status

    private val _hospitals = MutableLiveData<List<Hospital>>()
    val hospitals: LiveData<List<Hospital>> = _hospitals

    private val _hospital = MutableLiveData<Hospital>()
    val hospital: LiveData<Hospital> = _hospital

    init{
        getHospitalList()
    }

    fun getHospitalList() {
        _status.value = HospitalApiStatus.LOADING
        viewModelScope.launch {
            try {
                _hospitals.value = HospitalApi.retrofitService.getHospitals()
                _status.value = HospitalApiStatus.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = HospitalApiStatus.ERROR
            }
        }
    }

    fun onHospitalClick(hospital: Hospital) {
        _hospital.value = hospital
    }
}