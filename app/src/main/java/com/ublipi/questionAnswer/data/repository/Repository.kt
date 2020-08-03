package com.ublipi.questionAnswer.data.repository

import com.ublipi.questionAnswer.data.response.AnswerResponse

interface Repository {

    fun getAnswers(
        query: String,
        onResult: (AnswerResponse) -> Unit
    )
}