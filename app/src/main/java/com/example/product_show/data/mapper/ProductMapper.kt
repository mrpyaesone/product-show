package com.example.product_show.data.mapper

import com.example.product_show.domain.model.Product
import com.example.product_show.data.network.response.ProductResponse

fun ProductResponse.toModel() = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    rating = rating,
    thumbnail = thumbnail,
    images = images
)

@JvmName("toModelList")
fun List<ProductResponse>.toModel() = map(ProductResponse::toModel)
