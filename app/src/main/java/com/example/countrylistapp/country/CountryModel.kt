package com.example.countrylistapp.country

import android.content.Context
import com.example.countrylistapp.R
import com.example.countrylistapp.modelclass.CountryListRes
import com.example.countrylistapp.restapi.ApiClient
import com.example.countrylistapp.restapi.ApiServiceInterface
import com.example.countrylistapp.utility.Util
import retrofit2.Call
import retrofit2.Response

class CountryModel(private val context: Context) : CountryContract.CountryModel {

    private var api: ApiServiceInterface? = null

    init {
        api = ApiClient().callApiService()
    }


    override fun getCountryList(presenter: CountryContract.CountryPresenter) {

        if (Util.networkCheck(context)) {

            api?.getCountryList()?.enqueue(object : retrofit2.Callback<CountryListRes> {
                override fun onResponse(
                    call: Call<CountryListRes>,
                    response: Response<CountryListRes>
                ) {

                    with(response) {

                        if (isSuccessful && body() != null) {

                            when (body()?.resultCode) {

                                0 -> {

                                    if (!body()?.countryResponse.isNullOrEmpty()) {
                                       presenter.loadCountryList(body()?.countryResponse!!)
                                    }

                                }
                                else -> {
                                    Util.toast(context, body()?.desc ?: context.getString(R.string.try_again))
                                }

                            }

                        }

                    }

                }

                override fun onFailure(call: Call<CountryListRes>, t: Throwable) {
                    Util.toast(context, t.message ?: context.getString(R.string.try_again))
                }
            })

        } else {
            Util.toast(context, context.getString(R.string.no_internet))
        }

    }
}