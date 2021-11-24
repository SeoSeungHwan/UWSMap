package com.router.uwsmap.model

data class ItemList(
    val currentCount: Int,
    val `data`: List<Item>,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
)