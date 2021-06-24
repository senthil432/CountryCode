package com.example.countrylistapp.restapi

import com.example.countrylistapp.modelclass.CountryListRes
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET(ApiConstantsUrl.COUNTRY_LIST)
    fun getCountryList(): Call<CountryListRes>
}