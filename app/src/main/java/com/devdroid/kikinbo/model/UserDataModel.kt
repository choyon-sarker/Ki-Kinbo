package com.devdroid.kikinbo.model

/**
 * Represents a user's data information within the system.
 *
 * This data class holds details about a user, including their unique identifier,
 * username, email address, and optionally, their phone number.
 *
 * @property userId The unique identifier for the user.
 * @property username The user's chosen username.
 * @property email The user's email address.
 * @property phoneNumber The user's phone number. This is an optional field and may be null.
 */
data class UserDataModel(
    val userId: String,
    val username: String,
    val email: String,
    val phoneNumber: String?
)
