package com.ublipi.questionAnswer.data.response

import com.ublipi.questionAnswer.data.model.Answer

data class AnswerResponse(
    val data: Answer,
    val execution_time: Double,
    val message: String,
    val success: Boolean
)