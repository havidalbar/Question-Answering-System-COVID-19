package com.ublipi.questionAnswer.data.model

data class Answer(
    val answer: String,
    val end: Int,
    val score: Double,
    val start: Int,
    val user: Int
)