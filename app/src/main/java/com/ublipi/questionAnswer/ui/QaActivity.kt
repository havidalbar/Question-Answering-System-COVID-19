package com.ublipi.questionAnswer.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ublipi.questionAnswer.R
import com.ublipi.questionAnswer.data.model.Answer
import com.ublipi.questionAnswer.databinding.ActivityMainBinding
import com.ublipi.questionAnswer.ui.adapter.ChatAdapter
import com.ublipi.questionAnswer.util.Constant.MY_ID
import com.ublipi.questionAnswer.util.hide
import com.ublipi.questionAnswer.util.snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QaActivity : AppCompatActivity() {

    private val viewModel by viewModel<QaViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetBehaviour: BottomSheetBehavior<ViewGroup>
    private lateinit var adapter: ChatAdapter

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
            btnTanya.setOnClickListener { ask(MY_ID) }
            testasd.setOnClickListener { ask(2) }
            layoutContent.setOnClickListener { collapseBottomSheet() }
            rvChat.layoutManager = LinearLayoutManager(this@QaActivity)
            adapter = ChatAdapter(this@QaActivity)
            rvChat.adapter = adapter
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
    }

    private fun ask(a: Int) {
        val question: String = edt_input.text.toString()
        if (question.isEmpty().not()) {
//            viewModel.getAnswer(question)
            edt_input.setText("")
            adapter.addAnswers(Answer(question, a))
            expandBottomSheet()
        } else {
            layout_content.snackbar(getString(R.string.masukan_pertanyaan))
        }
    }

    private fun expandBottomSheet() {
        bottomSheetBehaviour.peekHeight = 187
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun collapseBottomSheet() {
        bottomSheetBehaviour.peekHeight = 187
    }

    override fun onBackPressed() {
        if (bottomSheetBehaviour.state == BottomSheetBehavior.STATE_EXPANDED) {
            collapseBottomSheet()
        } else {
            super.onBackPressed()
        }
    }

}
