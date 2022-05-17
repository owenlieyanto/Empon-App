package com.example.empon_app.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.empon_app.MainActivity
import com.example.empon_app.R
import com.example.empon_app.ui.info.ListDataViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_faq.*

class FAQFragment : Fragment() {
    private val navView: BottomNavigationView = MainActivity.binding.navView
    private lateinit var viewModel: ListDataViewModel
    private val questionListAdapter = AboutQuestionAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListDataViewModel::class.java]
        viewModel.insertQuestions()
        recyclerViewQuestion.layoutManager = LinearLayoutManager(context)
        recyclerViewQuestion.adapter = questionListAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.listQuestionLD.observe(viewLifecycleOwner) {
            questionListAdapter.updateQuestionList(it)
        }
    }

    override fun onResume() {
        super.onResume()

        navView.menu.getItem(2).isChecked = true
    }
}