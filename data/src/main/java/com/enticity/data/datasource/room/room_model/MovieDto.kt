package com.enticity.data.datasource.room.room_model

data class MovieDto(
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)