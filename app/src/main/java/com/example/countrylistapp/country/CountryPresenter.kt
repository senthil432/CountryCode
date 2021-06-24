package com.example.countrylistapp.country

import android.content.Context
import com.example.countrylistapp.modelclass.countryResponse

class CountryPresenter(
    context: Context,
    countryView: CountryContract.CountryView) : CountryContract.CountryPresenter {

    private var view: CountryContract.CountryView = countryView
    private var model: CountryContract.CountryModel? = CountryModel(context)


    override fun apiCall() {
        model?.getCountryList(this)
    }

    override fun loadCountryList(list: ArrayList<countryResponse>) {
        view.updateSpinner(list)
    }
}