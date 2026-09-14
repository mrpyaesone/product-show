package com.example.product_show.domain.model

data class Page<T>(val items: List<T>, val nextPage: Int?)