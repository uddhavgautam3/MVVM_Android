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
    /*
    class CatProducer : Producer<Cat> {
        override fun produce(): Cat {
            return Cat()
        }
    }
    val catProducer: Producer<Cat> = CatProducer()
     */
    //I could do as above for these three lines below
    val catProducer: Producer<Cat> = object : Producer<Cat> { // Cat is out T, it means Cat will get produced
        override fun produce(): Cat {
            return Cat()
        }
    }
    val cat: Cat = catProducer.produce() // Cat is produced as expected

    val animalProducer: Producer<Animal> = catProducer // Covariant assignment because of out
    //because of out, polymorphism is possible, Power of possible in Container class is because of out. WOW!
    val animal: Animal = animalProducer.produce() //equals to catProducer.produce()
    animal.makeSound() //this, this is like calling from cat.makeSound()

    /*val animalConsumer: Consumer<Animal> = object : Consumer<Animal> {
        override fun consume(item: Animal) {
            // Consume the animal
            item.makeSound() // animal is consumed
        }
    }


    val catConsumer: Consumer<Cat> = animalConsumer // Contravariant assignment*/
    val catConsumer: Consumer<Cat> = object: Consumer<Animal> { //Contravariant assignment
        override fun consume(item: Animal) {
            item.makeSound() //item although Animal type goes as a Cat
        }
    }
    catConsumer.consume(cat) //Consumer<in T>
}