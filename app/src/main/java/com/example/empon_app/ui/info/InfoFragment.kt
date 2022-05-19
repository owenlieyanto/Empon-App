package com.example.empon_app.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.empon_app.ui.MainActivity
import com.example.empon_app.R
import com.example.empon_app.ui.ListDataViewModel
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {
    private lateinit var viewModel: ListDataViewModel
    private val emponListAdapter = InfoEmponAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListDataViewModel::class.java]
        viewModel.insertEmpons(MainActivity.empons)
        recycleViewEmpon.layoutManager = LinearLayoutManager(context)
        recycleViewEmpon.adapter = emponListAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.listEmponLD.observe(viewLifecycleOwner) {
            emponListAdapter.updateEmponList(it)
        }
    }

}