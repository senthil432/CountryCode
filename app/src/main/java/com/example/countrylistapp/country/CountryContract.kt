package com.example.countrylistapp.country

import com.example.countrylistapp.modelclass.countryResponse

class CountryContract {

    interface CountryModel {
        fun getCountryList(presenter: CountryPresenter)
    }

    interface CountryView {
        fun updateSpinner(list: ArrayList<countryResponse>)
    }

    interface CountryPresenter {
        fun apiCall()
        fun loadCountryList(list: ArrayList<countryResponse>)
    }

}