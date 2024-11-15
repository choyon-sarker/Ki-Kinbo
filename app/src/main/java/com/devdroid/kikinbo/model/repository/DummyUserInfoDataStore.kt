package com.devdroid.kikinbo.model.repository

import com.devdroid.kikinbo.model.UserDataModel

/**
 * A dummy data store for storing mock user information.
 *
 * This class provides a set of predefined `UserDataModel` objects representing
 * dummy users with basic information such as user ID, username, email, and phone number.
 * It is used for testing purposes and simulates a data source that could be
 * replaced with a real backend or database in a production environment.
 *
 * Each user is represented by a `UserDataModel` with the following properties:
 * - `userId`: Unique identifier for the user.
 * - `username`: Full name of the user.
 * - `email`: Email address of the user.
 * - `phoneNumber`: Contact number of the user.
 *
 * @constructor Creates an instance of the `DummyUserInfoDataStore` class.
 */
class DummyUserInfoDataStore {

    /**
     * A sample user representing Choyon Sarker.
     */
    val demoUser1 = UserDataModel(
        userId = "User1",
        username = "Choyon Sarker",
        email = "choyon@gmail.com",
        phoneNumber = "01756387253"
    )

    /**
     * A sample user representing Lamia Binta Latif.
     */
    val demoUser2 = UserDataModel(
        userId = "User2",
        username = "Lamia Binta Latif",
        email = "Lamia@gmail.com",
        phoneNumber = "01856534263"
    )

    /**
     * A sample user representing Raian Rashid.
     */
    val demoUser3 = UserDataModel(
        userId = "User3",
        username = "Raian Rashid",
        email = "Raian@gmail.com",
        phoneNumber = "01776534763"
    )

    /**
     * A sample user representing Yumna Tasneem.
     */
    val demoUser4 = UserDataModel(
        userId = "User4",
        username = "Yumna Tasneem",
        email = "yumna@gmail.com",
        phoneNumber = "018-456-78902"
    )

    /**
     * A sample user representing Nuzhat Net.
     */
    val demoUser5 = UserDataModel(
        userId = "User5",
        username = "Nuzhat Net",
        email = "nuzhat@gmail.com",
        phoneNumber = "01965788243"
    )
}
