package com.devdroid.kikinbo.model.repository

import com.devdroid.kikinbo.model.ProductDataModel

class DummyProductInfoDataStore {
    /**
     * Sample product instance representing a headphone in the Electronics category.
     */
    val product1 = ProductDataModel(
        productId = "Headphoneid",
        productName = "Headphone",
        productPrice = 200,
        productRating = 4.5,
        productDetail = "Crystal Clear Sound: Enjoy immersive audio with high-definition sound quality, " +
                "ensuring deep bass and crisp highs.\nComfort & Durability: Designed with cushioned ear pads " +
                "and adjustable headbands for extended comfort during long listening sessions.",
        productReview = "Very Good",
        productCategory = "Electronics"
    )

    /**
     * Sample product instance representing a mouse in the Electronics category.
     */
    val product2 = ProductDataModel(
        productId = "Mouseid",
        productName = "Mouse",
        productPrice = 150,
        productRating = 3.5,
        productDetail = "Precision & Control: Experience smooth tracking and precise cursor control with high DPI sensitivity, " +
                "perfect for gaming or professional use.\nErgonomic Design: Crafted for comfort with a contoured shape that " +
                "fits naturally in your hand, reducing strain during extended use.",
        productReview = "Well made",
        productCategory = "Electronics"
    )

    /**
     * Sample product instance representing a pen in the Stationary category.
     */
    val product3 = ProductDataModel(
        productId = "Penid",
        productName = "Pen",
        productPrice = 15,
        productRating = 5.0,
        productDetail = "Smooth Writing: Delivers consistent ink flow for effortless, precise writing.\n" +
                "Comfort Grip: Designed with a soft grip for easy handling during long writing sessions.",
        productReview = "Well made",
        productCategory = "Stationary"
    )

    /**
     * Sample product instance representing a pencil in the Stationary category.
     */
    val product4 = ProductDataModel(
        productId = "Pencilid",
        productName = "Pencil",
        productPrice = 5,
        productRating = 5.0,
        productDetail = "Smooth & Precise: Offers clean, consistent lines ideal for writing and sketching.\n" +
                "Comfortable Grip: Lightweight and easy to hold, perfect for extended use.",
        productReview = "Works perfectly",
        productCategory = "Stationary"
    )

    /**
     * Sample product instance representing juice in the Food category.
     */
    val product5 = ProductDataModel(
        productId = "Juiceid",
        productName = "Juice",
        productPrice = 25,
        productRating = 4.5,
        productDetail = "Refreshing Taste: Made from fresh, ripe fruits for a natural and vibrant flavor.\n" +
                "Nutrient-Rich: Packed with essential vitamins and antioxidants to boost your energy and well-being.",
        productReview = "Very Refreshing",
        productCategory = "Food"
    )

    /**
     * Sample product instance representing candy in the Food category.
     */
    val product6 = ProductDataModel(
        productId = "Candyid",
        productName = "Candy",
        productPrice = 4,
        productRating = 5.0,
        productDetail = "Deliciously Sweet: Bursting with rich, satisfying flavors that melt in your mouth.\n" +
                "Perfectly Portable: Individually wrapped for easy, on-the-go enjoyment anytime.",
        productReview = "Perfectly sweet",
        productCategory = "Food"
    )

}