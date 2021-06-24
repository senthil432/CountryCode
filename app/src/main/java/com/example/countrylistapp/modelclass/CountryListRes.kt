package com.example.countrylistapp.modelclass

data class CountryListRes(
    val resultCode: Int,
    val desc: String,
    val countryResponse: ArrayList<countryResponse>
)

data class countryResponse(
    val id: Int,
    val createdAt: createdAt,
    val name: String,
    val isActive: Boolean,
    val isDeleted: Boolean,
    val countryCode: String
)

data class createdAt(val nanos: Int)