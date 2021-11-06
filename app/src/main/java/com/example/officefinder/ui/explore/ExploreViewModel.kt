package com.example.officefinder.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.officefinder.api.RetrofitBuilder
import com.example.officefinder.models.OfficeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel : ViewModel() {

    var liveOffice: MutableLiveData<List<OfficeModel>> = MutableLiveData()


    fun getLiveOfficeObserver(): MutableLiveData<List<OfficeModel>> {
        return liveOffice
    }

    fun makeOfficeApiCall() {
        val officeCall  = RetrofitBuilder.apiService.getOffices("user-a")
        officeCall.enqueue(object : Callback<List<OfficeModel>> {
            override fun onFailure(call: Call<List<OfficeModel>>, t: Throwable) {
                liveOffice.postValue(emptyList())
            }

            override fun onResponse(
                call: Call<List<OfficeModel>>,
                response: Response<List<OfficeModel>>
            ) {
                liveOffice.postValue(response.body())
            }
        })
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is favorite Fragment"
    }
    val text: LiveData<String> = _text


}