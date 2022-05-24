package com.example.empon_app.model

data class Empon(
    val id: Int?,
    val kodeJenis: String?,
    val namaJenis: String?,
    val namaLatin: String?,
    val manfaat: String?,
    val kandungan: String?
)

data class Question(
    val id: Int?,
    val title: String?,
    val description: String?
)

data class Onboarding(
    val title: String,
    val description: String,
    val image: Int
)