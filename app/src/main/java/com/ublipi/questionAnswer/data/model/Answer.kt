package com.ublipi.questionAnswer.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Answer(
    @Expose @SerializedName("answer") val answer: String? = null,
    @Expose @SerializedName("user") val user: Int? = null
)