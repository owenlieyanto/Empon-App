package com.example.empon_app.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.empon_app.R
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {
    private lateinit var viewModel: ListEmponViewModel
    private val emponListAdapter = InfoEmponListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListEmponViewModel::class.java)
        viewModel.insert()
        recycleViewEmpon.layoutManager = LinearLayoutManager(context)
        recycleViewEmpon.adapter = emponListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.listEmponLD.observe(viewLifecycleOwner, Observer {
            emponListAdapter.updateEmponList(it)
        })

//        viewModel.paketLoadingError.observe(viewLifecycleOwner, Observer {
//            if(it == true) {
//                txtError.visibility = View.VISIBLE
//            } else {
//                txtError.visibility = View.GONE
//            }
//        })
//
//        viewModel.paket_is_loading.observe(viewLifecycleOwner, Observer {
//            if(it == true) {
//                recyclerViewPaketTryout.visibility = View.GONE
//                progressLoad.visibility = View.VISIBLE
//            } else {
//                recyclerViewPaketTryout.visibility = View.VISIBLE
//                progressLoad.visibility = View.GONE
//            }
//        })
    }
}