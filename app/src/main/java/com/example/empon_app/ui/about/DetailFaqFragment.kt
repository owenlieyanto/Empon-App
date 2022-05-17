package com.example.empon_app.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.empon_app.MainActivity
import com.example.empon_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_detail_empon.*
import kotlinx.android.synthetic.main.fragment_detail_faq.*
import kotlinx.android.synthetic.main.fragment_info.*

class DetailFaqFragment : Fragment() {
    private val navView: BottomNavigationView = MainActivity.binding.navView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_faq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val questionTitle = DetailFaqFragmentArgs.fromBundle(requireArguments()).questionTitle
            val questionDesc =
                DetailFaqFragmentArgs.fromBundle(requireArguments()).questionDescription

            textViewQuestionTitleFaq.text = questionTitle
            textViewDetailFaqDescription.text = questionDesc
        }
    }

    override fun onResume() {
        super.onResume()

        navView.menu.getItem(2).isChecked = true
    }
}