package com.amatucci.andrea.beers.data.model

sealed class FlowItem<out T: Any> {
    data class Loading<out T : Any>(val data: T) : FlowItem<Nothing>()
    data class Success<out T : Any>(val data: T) : FlowItem<T>()
    data class Error(val exception: Exception) : FlowItem<Nothing>()
}

