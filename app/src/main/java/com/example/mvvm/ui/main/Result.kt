package com.example.mvvm.ui.main

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()

    inline fun <R> map(transform: (T) -> R): Result<R> {
        return when (this) {
            is Success -> Success(transform(value))
            is Failure -> this
        }
    }

    inline fun <R> flatMap(transform: (T) -> Result<R>): Result<R> {
        return when (this) {
            is Success -> transform(value)
            is Failure -> this
        }
    }

    inline fun onFailure(action: (Exception) -> Unit): Result<T> {
        if (this is Failure) {
            action(exception)
        }
        return this
    }

    inline fun onSuccess(action: (T) -> Unit): Result<T> {
        if (this is Success) {
            action(value)
        }
        return this
    }

    companion object {
        fun <T> success(value: T): Result<T> {
            return Success(value)
        }

        fun failure(exception: Exception): Result<Nothing> {
            return Failure(exception)
        }
    }
}