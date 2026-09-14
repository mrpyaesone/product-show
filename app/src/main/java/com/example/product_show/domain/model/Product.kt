package com.example.product_show.domain.model

data class Product(
    val id: Int, val title: String,
    val description: String, val price: Double,
    val rating: Double, val thumbnail: String,
    val images: List<String>
) {
    companion object {
        val mock = Product(
            id = 1,
            title = "title",
            description = "description",
            price = 1000.0,
            rating = 3.2,
            thumbnail = "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp",
            images = List(10) { "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp" }
        )

        val empty = Product(
            id = 0,
            title = "",
            description = "",
            price = 0.0,
            rating = 0.0,
            thumbnail = "",
            images = emptyList()
        )
    }
}

