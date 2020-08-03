package com.ublipi.questionAnswer.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ublipi.questionAnswer.data.model.Answer

data class AnswerResponse(
    @Expose @SerializedName("success") val success: Boolean?,
    @Expose @SerializedName("message") val message: String?,
    @Expose @SerializedName("data") val data: Answer
)