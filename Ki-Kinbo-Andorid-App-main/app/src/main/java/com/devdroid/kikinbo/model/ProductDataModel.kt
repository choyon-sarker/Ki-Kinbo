package com.devdroid.kikinbo.model

/**
 * Data model representing a product in the Ki-Kinbo!! online shop.
 *
 * This class contains all the essential details of a product, such as its ID,
 * name, price, rating, description, user reviews, and the category it belongs to.
 *
 * @property productId The unique identifier of the product.
 * @property productName The name of the product.
 * @property productPrice The price of the product in integer format (e.g., 100).
 * @property productRating The average user rating for the product, represented as a double.
 * @property productDetail A detailed description of the product, including features and specifications.
 * @property productReview User reviews or feedback provided for the product.
 * @property productCategory The category or type the product belongs to (e.g., Electronics, Fashion).
 */
data class ProductDataModel(
    val productId: String? = null,
    val productName: String? = null,
    val productPrice: Int? = null,
    val productRating: Double? = null,
    val productDetail: String? = null,
    val productReview: String? = null,
    val productCategory: String? = null
)

/*
val product1 = ProductDataModel(
    productId = "Headphoneid",
    productName = "Headphone",
    productPrice = 200,
    productRating = 4.5,
    productDetail = "Crystal Clear Sound: Enjoy immersive audio with high-definition sound quality, ensuring deep bass and crisp highs.\n" +
            "\n" +
            "Comfort & Durability: Designed with cushioned ear pads and adjustable headbands for extended comfort during long listening sessions.",
    productReview = "Very Good",
    productCategory = "Electronics"
)
val product2 = ProductDataModel(
    productId = "Mouseid",
    productName = "Mouse",
    productPrice = 150,
    productRating = 3.5,
    productDetail = "Precision & Control: Experience smooth tracking and precise cursor control with high DPI sensitivity, perfect for gaming or professional use.\n" +
            "\n" +
            "Ergonomic Design: Crafted for comfort with a contoured shape that fits naturally in your hand, reducing strain during extended use.\n",
    productReview = "Well made",
    productCategory = "Electronics"
)
val product3 = ProductDataModel(
    productId = "Penid",
    productName = "Pen",
    productPrice = 15,
    productRating = 5.0,
    productDetail = "Smooth Writing: Delivers consistent ink flow for effortless, precise writing.\n" +
            "\n" +
            "Comfort Grip: Designed with a soft grip for easy handling during long writing sessions.",
    productReview = "Well made",
    productCategory = "Stationary"
)
val product4 = ProductDataModel(
    productId = "Pencilid",
    productName = "Pencil",
    productPrice = 5,
    productRating = 5.0,
    productDetail = "Smooth & Precise: Offers clean, consistent lines ideal for writing and sketching.\n" +
            "\n" +
            "Comfortable Grip: Lightweight and easy to hold, perfect for extended use.",
    productReview = "Works perfectly",
    productCategory = "Stationary"
)
val product5 = ProductDataModel(
    productId = "Juiceid",
    productName = "Juice",
    productPrice = 25,
    productRating = 4.5,
    productDetail = "Refreshing Taste: Made from fresh, ripe fruits for a natural and vibrant flavor.\n" +
            "\n" +
            "Nutrient-Rich: Packed with essential vitamins and antioxidants to boost your energy and well-being.",
    productReview = "Very Refreshing",
    productCategory = "Food"
)
val product6 = ProductDataModel(
    productId = "Candyid",
    productName = "Candy",
    productPrice = 4,
    productRating = 5.0,
    productDetail = "Deliciously Sweet: Bursting with rich, satisfying flavors that melt in your mouth.\n" +
            "\n" +
            "Perfectly Portable: Individually wrapped for easy, on-the-go enjoyment anytime.",
    productReview = "Perfectly sweet",
    productCategory = "Food"
)

 */




