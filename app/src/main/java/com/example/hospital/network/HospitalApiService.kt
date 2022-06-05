package com.example.hospital.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://www.healthit.gov/data/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HospitalApiService{
    @GET("open-api?source=hospital-mu-public-health-measures.csv")
    suspend fun getHospitals(): List<Hospital>
}

object HospitalApi{
    val retrofitService: HospitalApiService by lazy{
        retrofit.create(HospitalApiService::class.java)
    }
}