package com.example.concepts

open class Animal {
    open fun makeSound() {
        println("Animal making sound")
    }
}

class Cat : Animal() {
    override fun makeSound() {
        println("Meow!")
    }
}

//it's lambda expression function (or variable) should be called from it's instance  and return T
interface Producer<out T> {
    fun produce(): T
}

/*
This lambda expression has the function type Producer<T>.() -> T, which means it's a function
that can be called on an instance of Producer<T> and returns a value of type T.
 */
fun <T> producerLambda(produceFunc: Producer<T>.() -> T): Producer<T> {
    return object : Producer<T> {
        override fun produce(): T {
            return produceFunc()
        }
    }
}

interface Consumer<in T> {
    fun consume(item: T)
}

fun main() {
    // Using lambda expressions for interfaces
    val catProducer: Producer<Cat> = object : Producer<Cat> {
        override fun produce(): Cat {
            return Cat()
        }
    }

    //With the help of producerLambda(), I can use lambda expression even for SAM generic interface
    val catProducer2: Producer<Cat> = producerLambda { Cat() }
    /*val catProducer2: Producer<Cat> = producerLambda(fun Producer<Cat>.(): Cat {
        return Cat()
    })*/

    val cat: Cat = catProducer.produce()
    val animalProducer: Producer<Animal> = catProducer // Covariant assignment
    val animal: Animal = animalProducer.produce()
    animal.makeSound()
    val catConsumer: Consumer<Cat> = object : Consumer<Animal> {
        override fun consume(item: Animal) {
            item.makeSound()
        }
    } // Contravariant assignment
    catConsumer.consume(cat)
}