package com.backbase.data.api

import com.backbase.data.CityEntity

interface Api {

    suspend fun provideData(): List<CityEntity>
}