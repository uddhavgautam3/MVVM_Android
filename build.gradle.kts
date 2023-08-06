// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false
    id("maven-publish")
}

/*
org.jetbrains.kotlin.android vs. androidx.core:core-ktx
org.jetbrains.kotlin.android and androidx.core:core-ktx serve different purposes in Android development.

org.jetbrains.kotlin.android:
This is a Kotlin plugin provided by JetBrains, the creators of Kotlin. It's designed to help with Android development using Kotlin as the programming language. The plugin provides various extensions, tools, and utilities to enhance the Kotlin development experience on Android.
It allows you to write more concise and idiomatic Kotlin code for Android applications. Some of the features it offers include:

Kotlin Android Extensions: This allows you to directly access Android views defined in XML without the need for findViewById.
Parcelize: It simplifies the process of making data classes Parcelable, which is useful for passing data between activities or fragments.
Anko: A set of Kotlin DSLs (Domain-Specific Languages) that make Android layouts and other resources declaration easier.
androidx.core:core-ktx:
This is an AndroidX library that provides Kotlin extensions for the Android framework. AndroidX is a package of Android libraries introduced to replace the original Android Support Library.
core-ktx specifically focuses on Kotlin extensions for the core Android components, including:

Extension functions for Android views and other components to perform tasks more conveniently.
Helper functions to work with concurrency, resources, preferences, and other common Android tasks in a more Kotlin-friendly way.
To summarize, org.jetbrains.kotlin.android provides Kotlin-specific tooling and features for Android development,
while androidx.core:core-ktx is an AndroidX library that offers Kotlin extensions for core Android components to simplify common tasks and improve Kotlin development on Android. You can use them together to enhance your Kotlin-based Android projects.
 */