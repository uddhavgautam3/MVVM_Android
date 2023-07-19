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

interface Producer<out T> {
    fun produce(): T
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