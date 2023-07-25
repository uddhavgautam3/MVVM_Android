package com.example.hiltDiLearning

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

interface SomeInterface1 {
    fun doSomething(): String
}

interface SomeInterface2 {
    fun doSomethingElse(): String
}

class SomeImplementation1 @Inject constructor() : SomeInterface1 {
    override fun doSomething(): String {
        return "Doing Something!"
    }
}

class SomeImplementation2 @Inject constructor() : SomeInterface2 {
    override fun doSomethingElse(): String {
        return "Doing Something Else!"
    }
}

class SomeConsumerClass @Inject constructor(
    private val someInterface1: SomeInterface1,
    private val someInterface2: SomeInterface2
) {

    fun run() {
        println(someInterface1.doSomething())
        println(someInterface2.doSomethingElse())
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SomeModuleUsingBinds {
    @Binds
    abstract fun bindSomeInterface1(implementation1: SomeImplementation1): SomeInterface1

    @Binds
    abstract fun bindSomeInterface2(implementation2: SomeImplementation2): SomeInterface2
}

