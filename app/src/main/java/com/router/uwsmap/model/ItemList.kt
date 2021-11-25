package com.router.uwsmap.model

data class ItemList(
    val currentCount: Int,
    var `data`: List<Item>,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
)