package com.example.pixabay.core

sealed class GetResult<T> {
    class Failure<S> : GetResult<S>()
    data class Success<V>(val data: V) : GetResult<V>()
}
