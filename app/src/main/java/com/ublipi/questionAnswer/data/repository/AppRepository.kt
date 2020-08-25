package com.ublipi.questionAnswer.data.repository

import android.util.Log
import com.ublipi.questionAnswer.data.ApiObserver
import com.ublipi.questionAnswer.data.response.AnswerResponse
import com.ublipi.questionAnswer.data.source.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AppRepository(private val api: Api) : Repository {
    private val compositeDisposable = CompositeDisposable()

    override fun getAnswers(
        query: String,
        onResult: (AnswerResponse) -> Unit
    ) {
        api.getAnswers(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<AnswerResponse>(compositeDisposable) {
                override fun onApiSuccess(data: AnswerResponse) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

}