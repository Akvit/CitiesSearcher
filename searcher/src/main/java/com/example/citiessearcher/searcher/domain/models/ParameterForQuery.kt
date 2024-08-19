package com.example.citiessearcher.searcher.domain.models

import com.example.citiessearcher.searcher.domain.utilits.EMPTY_STRING

/**
 * this wrapper allows put some extra parameters we need in future
 */
data class ParameterForQuery(var query: String = EMPTY_STRING)
