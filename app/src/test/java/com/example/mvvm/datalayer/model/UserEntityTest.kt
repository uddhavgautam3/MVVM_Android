package com.example.mvvm.datalayer.model

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

/**
 * Below Unit test for our Data class (in our case, which is acting just as a data holder class),
 * and simple enough to understand. We could skip unit test for this because somewhere in other classes
 * where this data class is being used will cover the test functionality. For just to make more
 * coverage and to show how we can write test for data class, we are providing test here.
 */
class UserEntityTest {

    private lateinit var user: UserEntity

    @Test
    fun testUserEntityEquality() {
        // Create two UserEntity instances with the same values
        val user1 = UserEntity(1, "John Doe")
        val user2 = UserEntity(1, "John Doe")

        // Assert that the two instances are equal
        assertEquals(user1, user2)
    }

    @Test
    fun testUserEntityInequality() {
        // Create two UserEntity instances with different values
        val user1 = UserEntity(1, "John Doe")
        val user2 = UserEntity(2, "Jane Smith")

        // Assert that the two instances are not equal
        assertNotEquals(user1, user2)
    }

    @Test
    fun testUserEntityProperties() {
        // Create a UserEntity instance
        val (id, name) = UserEntity(1, "John Doe")

        // Assert the individual properties of the UserEntity instance
        assertEquals(1, id)
        assertEquals("John Doe", name)
    }

    @Test
    fun testUserEntityToString() {
        // Create a UserEntity instance
        val user = UserEntity(1, "John Doe")

        // Assert the toString() representation of the UserEntity instance
        assertEquals("UserEntity(id=1, name=John Doe)", user.toString())
    }

    @Before
    fun setUp() {
        user = UserEntity(id = 1, name = "John Doe")
    }

    @After
    fun tearDown() {
        //no specific clean up required
    }

    @Test
    fun getId() {
        assertEquals(1, user.id)
    }

    @Test
    fun getName() {
        assertEquals("John Doe", user.name)
    }

    @Test
    fun component1() {
        assertEquals(1, user.component1())
    }

    @Test
    fun component2() {
        assertEquals("John Doe", user.component2())
    }

    @Test
    fun copy() {
        val copiedUser = user.copy(name = "Jane Smith")
        assertEquals(1, copiedUser.id)
        assertEquals("Jane Smith", copiedUser.name)
    }

    @Test
    fun testToString() {
        assertEquals("UserEntity(id=1, name=John Doe)", user.toString())
    }

    @Test
    fun testHashCode() {
        val otherUser = UserEntity(id = 1, name = "John Doe")
        assertEquals(user.hashCode(), otherUser.hashCode())
    }

    @Test
    fun testEquals() {
        val otherUser = UserEntity(id = 1, name = "John Doe")
        val differentUser = UserEntity(id = 2, name = "Jane Smith")

        assertEquals(user, otherUser)
        assertNotEquals(user, differentUser)
    }

}