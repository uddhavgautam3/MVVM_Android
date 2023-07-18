package com.example.mvvm.datalayer.model

sealed class MyResult<out T> {
    data class Success<out T>(val value: T) : MyResult<T>()
    data class Failure(val exception: Exception) : MyResult<Nothing>()

    inline fun <R> map(transform: (T) -> R): MyResult<R> {
        return when (this) {
            is Success -> Success(transform(value))
            is Failure -> this
        }
    }

    inline fun <R> flatMap(transform: (T) -> MyResult<R>): MyResult<R> {
        return when (this) {
            is Success -> transform(value)
            is Failure -> this
        }
    }

    inline fun onFailure(action: (Exception) -> Unit): MyResult<T> {
        if (this is Failure) {
            action(exception)
        }
        return this
    }

    inline fun onSuccess(action: (T) -> Unit): MyResult<T> {
        if (this is Success) {
            action(value)
        }
        return this
    }

    companion object {
        fun <T> success(value: T): MyResult<T> {
            return Success(value)
        }

        fun failure(exception: Exception): MyResult<Nothing> {
            return Failure(exception)
        }
    }
}