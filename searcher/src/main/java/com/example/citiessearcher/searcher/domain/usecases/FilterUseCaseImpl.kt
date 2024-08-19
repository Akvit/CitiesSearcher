package com.example.citiessearcher.searcher.domain.usecases

import com.example.citiessearcher.searcher.domain.models.CityModel
import com.example.citiessearcher.searcher.domain.models.ParameterForQuery
import com.example.citiessearcher.searcher.domain.dispatcherprovider.DispatcherProvider
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext

class FilterUseCaseImpl(private val dispatcherProvider: DispatcherProvider) :
    FilterUseCase {

    override suspend fun filter(
        rawList: List<CityModel>,
        parameterForQuery: ParameterForQuery
    ): List<CityModel> {
        return if (parameterForQuery.query.isNotEmpty()) {
            binaryFilter(rawList, parameterForQuery.query)
        } else rawList
    }

    private suspend fun binaryFilter(sortedList: List<CityModel>, query: String) =
        withContext(dispatcherProvider.computation()) {

            //looking for the first index of sublist
            val firstIndex: Int = sortedList.modifiedBinarySearch { city, index ->
                ensureActive()

                val startsWith: Boolean = city.name.startsWith(query, ignoreCase = true)

                val previousMatch: Boolean = if (index == 0) {
                    false
                } else {
                    sortedList.previousElement(index).name.startsWith(query, ignoreCase = true)
                }

                if (startsWith) {
                    if (previousMatch.not()) {
                        SAME_POSITION_IN_ALPHABET
                    } else {
                        LEFT_POSITION_IN_ALPHABET
                    }
                } else {
                    city.name.compareTo(query, ignoreCase = true)
                }
            }

            //looking for the last index of sublist
            val lastIndex = sortedList.modifiedBinarySearch { city, index ->
                ensureActive()

                val startsWith: Boolean = city.name.startsWith(query, ignoreCase = true)

                val nextMatch = if (index == sortedList.lastIndex) {
                    false
                } else {
                    sortedList.nextElement(index).name.startsWith(query, ignoreCase = true)
                }

                if (startsWith) {
                    if (nextMatch.not()) {
                        SAME_POSITION_IN_ALPHABET
                    } else {
                        RIGHT_POSITION_IN_ALPHABET
                    }
                } else {
                    city.name.compareTo(query, ignoreCase = true)
                }
            }

            //forming filtered subList
            when {
                firstIndex < 0 || lastIndex < 0 -> emptyList()
                firstIndex == lastIndex -> listOf(sortedList[firstIndex])
                firstIndex >= 0 && lastIndex <= sortedList.size -> sortedList.subList(
                    firstIndex,
                    lastIndex + 1
                )
                else -> {
                    emptyList()
                }
            }
        }

    private fun <T> List<T>.modifiedBinarySearch(comparison: (T, Int) -> Int): Int{
        var low = 0
        var high = lastIndex

        while (low <= high) {
            val mid = (low + high).ushr(1)
            val midVal = get(mid)

            val cmp = comparison(midVal, mid)

            when {
                (cmp < 0) -> low = mid + 1
                (cmp > 0) -> high = mid - 1
                else -> return mid
            }
        }
        return -(low + 1)
    }

    private fun <T> List<T>.nextElement(currentPosition: Int) = this[currentPosition + 1]

    private fun <T> List<T>.previousElement(currentPosition: Int) = this[currentPosition - 1]

    companion object {

        private const val RIGHT_POSITION_IN_ALPHABET = -1
        private const val SAME_POSITION_IN_ALPHABET = 0
        private const val LEFT_POSITION_IN_ALPHABET = 1
    }
}