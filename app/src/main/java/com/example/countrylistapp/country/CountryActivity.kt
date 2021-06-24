package com.example.countrylistapp.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.countrylistapp.R
import com.example.countrylistapp.modelclass.countryResponse


class CountryActivity : AppCompatActivity(), CountryContract.CountryView {
    private var presenter: CountryPresenter? = null
    private var countryCode: TextView? = null
    private var countrySpinner: Spinner? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }


    override fun updateSpinner(list: ArrayList<countryResponse>) {
        loadSpinner(list)
    }


    private fun initialize() {
        presenter = CountryPresenter(this, this)
        presenter?.apiCall()

        countrySpinner = findViewById(R.id.spinnerCountry)
        countryCode = findViewById(R.id.tvCountryCode)
    }


    private fun loadSpinner(list: ArrayList<countryResponse>) {
        val countryName: ArrayList<String> = ArrayList()
        countryName.add("Select Your Country")

        list.forEachIndexed { _, countryResponse ->
            countryName.add(countryResponse.name)
        }

        val dataAdapter = ArrayAdapter(this, R.layout.spinner_text_view, countryName)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner?.adapter = dataAdapter
        countrySpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position > 0) {
                    countryCode?.visibility = View.VISIBLE
                    setCountryCode(list[position - 1].countryCode)
                } else {
                    countryCode?.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }


    private fun setCountryCode(code: String) {
        countryCode?.text = code
    }
}