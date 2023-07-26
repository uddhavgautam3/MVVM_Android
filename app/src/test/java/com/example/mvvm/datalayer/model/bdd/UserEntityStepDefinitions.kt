package com.example.mvvm.datalayer.model.bdd

import com.example.mvvm.datalayer.model.UserEntity
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert.assertEquals
import kotlin.properties.Delegates

class UserEntityStepDefinitions {
    private var userId by Delegates.notNull<Int>()
    private lateinit var userName: String
    private lateinit var userEntity: UserEntity

    @Given("a user ID")
    fun givenUserId() {
        userId = 123
    }

    @Given("a user name")
    fun givenUserName() {
        userName = "John Doe"
    }

    @When("a UserEntity is created")
    fun createUserEntity() {
        userEntity = UserEntity(userId, userName)
    }

    @Then("the UserEntity should have the given ID and name")
    fun checkUserEntity() {
        assertEquals(userId, userEntity.id)
        assertEquals(userName, userEntity.name)
    }
}