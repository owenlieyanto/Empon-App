package com.example.empon_app.ui.info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.empon_app.ui.MainActivity
import com.example.empon_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail_empon.*

class DetailEmponFragment : Fragment() {

    private val navView: BottomNavigationView = MainActivity.binding.navView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_empon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val idEmpon = DetailEmponFragmentArgs.fromBundle(requireArguments()).idEmpon
            Log.d("id Empon, detail frag", idEmpon.toString())

            val empons = MainActivity.empons
            for (empon in empons) {
                if (empon.id!!.equals(idEmpon)) {
                    textViewKandunganDetail.text = empon.kandungan
                    textViewNamaEmponDetail.text = empon.namaJenis
                    textViewNamaLatinDetail.text = empon.namaLatin
                    textViewManfaatDetail.text = empon.manfaat
                    imageViewEmponDetail.setImageResource(MainActivity.imageIdList[empon.id])
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        navView.menu.getItem(0).isChecked = true
    }

}