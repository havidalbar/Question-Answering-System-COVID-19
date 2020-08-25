package com.ublipi.questionAnswer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ublipi.questionAnswer.data.response.AnswerResponse
import com.ublipi.questionAnswer.data.repository.AppRepository

class QaViewModel constructor(
    private val repository: AppRepository
) : ViewModel() {

    var answer : MutableLiveData<AnswerResponse> = MutableLiveData()

    fun getAnswer(query: String){
        repository.getAnswers(query) {
            answer.postValue(it)
        }
    }

}