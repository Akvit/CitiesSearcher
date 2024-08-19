package com.example.citiessearcher.searcher.data.api

import com.example.citiessearcher.searcher.data.CityEntity


interface Api {

    suspend fun provideData(): List<CityEntity>
}