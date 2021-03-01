package com.example.directorycompanies.repository.network.entity

data class CompanyDetail(
    val id: Int,
    val name: String,
    val img: String,
    val description: String,
    val lat: Float,
    val lot: Float,
    val www: String,
    val phone: String
) {

}