package com.ublipi.questionAnswer.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ublipi.questionAnswer.R
import com.ublipi.questionAnswer.data.model.Answer
import com.ublipi.questionAnswer.databinding.ActivityMainBinding
import com.ublipi.questionAnswer.util.hide
import com.ublipi.questionAnswer.util.show
import com.ublipi.questionAnswer.util.snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QaActivity : AppCompatActivity() {

    private val viewModel by viewModel<QaViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetBehaviour: BottomSheetBehavior<ViewGroup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        observeLiveData()
    }

    private fun initView() {
        with(binding) {
            bottomSheetBehaviour = BottomSheetBehavior.from(layoutBottomSheet)
            btnTanya.setOnClickListener { ask() }
            layoutContent.setOnClickListener { collapseBottomSheet() }
        }
    }

    private fun observeLiveData() {
        viewModel.answer.observe(this, Observer {
            onDataLoaded(it.data)
        })
    }

    private fun onDataLoaded(answer: Answer) {
        progressBar.hide()
        expandBottomSheet()
        tv_answer.text = answer.answer
        tv_answer_type.text = answer.answer_type
        tv_key_words.text = answer.key_words.toString()
        tv_result_sentence.text = answer.result_sentence!!.trim()
    }

    private fun ask() {
        val question: String = edt_input.text.toString()
        if (question.isEmpty().not()) {
            progressBar.show()
            viewModel.getAnswer(question)
        } else {
            layout_content.snackbar(getString(R.string.masukan_pertanyaan))
        }
    }

    private fun expandBottomSheet() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun collapseBottomSheet() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onBackPressed() {
        if (bottomSheetBehaviour.state == BottomSheetBehavior.STATE_EXPANDED) {
            collapseBottomSheet()
        } else {
            super.onBackPressed()
        }
    }

}
