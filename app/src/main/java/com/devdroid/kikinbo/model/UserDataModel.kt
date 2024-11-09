package com.devdroid.kikinbo.model

data class UserDataModel(
    val userId: String,          // Unique ID for the user (e.g., Firebase UID or custom ID)
    val username: String,        // Username of the user
    val email: String,           // Email address of the user
    val phoneNumber: String?,    // Optional phone number
)

val demoUser1 = UserDataModel(
    userId = "User1",
    username = "Choyon Sarker",
    email = "choyon@gmail.com",
    phoneNumber = "01756387253"
)
val demoUser2 = UserDataModel(
    userId = "User2",
    username = "Lamia Binta Latif",
    email = "Lamia@gmail.com",
    phoneNumber = "01856534263"
)
val demoUser3 = UserDataModel(
    userId = "User3",
    username = "Raian Rashid",
    email = "Raian@gmail.com",
    phoneNumber = "01776534763"
)
val demoUser4 = UserDataModel(
    userId = "User4",
    username = "Yumna Tasneem",
    email = "yumna@gmail.com",
    phoneNumber = "018-456-78902"
)
val demoUser5 = UserDataModel(
    userId = "User5",
    username = "Nuzhat Net",
    email = "nuzhat@gmail.com",
    phoneNumber = "01965788243"
)

