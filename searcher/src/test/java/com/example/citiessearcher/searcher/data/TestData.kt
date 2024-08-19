package com.example.citiessearcher.searcher.data

import com.example.citiessearcher.searcher.domain.models.CityModel
import com.example.citiessearcher.searcher.domain.models.CoordinatesModel


val sortedModels = listOf(
    CityModel("RU", "Astrakhanskaya Oblast’", CoordinatesModel(48.0, 47.0)),
    CityModel("BY", "Baranovichi", CoordinatesModel(28.983334, 53.166668)),
    CityModel("ZA", "Beaufort West", CoordinatesModel(22.582951, -32.356709)),
    CityModel("AU", "Bingera Plantation", CoordinatesModel(152.199997, -24.933331)),
    CityModel("RU", "Bugor", CoordinatesModel(46.915558, 47.56139)),
    CityModel("US", "Calcasieu Parish", CoordinatesModel(-93.350159, 30.23354)),
    CityModel("US", "Cameron Parish", CoordinatesModel(-93.21682, 29.86688)),
    CityModel("US", "Cedro", CoordinatesModel(-106.353638, 35.02227)),
    CityModel("CF", "Central African Republic", CoordinatesModel(21.0, 7.0)),
    CityModel("KE", "Central Province", CoordinatesModel(37.0, -0.75)),
    CityModel("GR", "Dimos Delta", CoordinatesModel(22.74551, 40.621239)),
    CityModel("GR", "Dimos Thasos", CoordinatesModel(24.653231, 40.68531)),
    CityModel("GR", "Dimos Thermaikos", CoordinatesModel(22.924339, 40.435089)),
    CityModel("IN", "Mahrauli", CoordinatesModel(77.183334, 28.516666)),
    CityModel("BY", "Minsk", CoordinatesModel(27.566668, 53.900002)),
    CityModel("RU", "Velikiy Novgorod", CoordinatesModel(31.283331, 58.51667)),
    CityModel("RU", "Zavidovo", CoordinatesModel(36.533329, 56.533329)),
    CityModel("BG", "Zlatni Pyasatsi", CoordinatesModel(28.0418, 43.285)),
)

val unsortedEntities = listOf(
    CityEntity("IN", "Mahrauli", 25222, CoordEntity(77.183334, 28.516666)),
    CityEntity("BY", "Minsk", 254535, CoordEntity(27.566668, 53.900002)),
    CityEntity("US", "Cedro", 567, CoordEntity(-106.353638, 35.02227)),
    CityEntity("GR", "Dimos Thasos", 4345, CoordEntity(24.653231, 40.68531)),
    CityEntity("RU", "Zavidovo", 75435, CoordEntity(36.533329, 56.533329)),
    CityEntity("GR", "Dimos Thermaikos", 2352, CoordEntity(22.924339, 40.435089)),
    CityEntity("US", "Calcasieu Parish", 23, CoordEntity(-93.350159, 30.23354)),
    CityEntity("RU", "Bugor", 523, CoordEntity(46.915558, 47.56139)),
    CityEntity("US", "Cameron Parish", 534, CoordEntity(-93.21682, 29.86688)),
    CityEntity("AU", "Bingera Plantation", 345, CoordEntity(152.199997, -24.933331)),
    CityEntity("RU", "Astrakhanskaya Oblast’", 1, CoordEntity(48.0, 47.0)),
    CityEntity("BY", "Baranovichi", 234, CoordEntity(28.983334, 53.166668)),
    CityEntity("ZA", "Beaufort West", 563, CoordEntity(22.582951, -32.356709)),
    CityEntity("CF", "Central African Republic", 57857, CoordEntity(21.0, 7.0)),
    CityEntity("KE", "Central Province", 65756, CoordEntity(37.0, -0.75)),
    CityEntity("GR", "Dimos Delta", 245, CoordEntity(22.74551, 40.621239)),
    CityEntity("RU", "Velikiy Novgorod", 65363, CoordEntity(31.283331, 58.51667)),
    CityEntity("BG", "Zlatni Pyasatsi", 3453, CoordEntity(28.0418, 43.285)),
)
