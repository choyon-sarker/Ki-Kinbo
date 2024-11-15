package com.devdroid.kikinbo.model

data class UserDataModel(
    val userId: String,
    val username: String,
    val email: String,
    val phoneNumber: String?,
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
val demoUser6=UserDataModel(
    userId="User6",
    username="choyon",
    email="choyon123@gmail.com",
    phoneNumber = "01233454565"
)

