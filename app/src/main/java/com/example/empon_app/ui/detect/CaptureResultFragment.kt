package com.example.empon_app.ui.detect

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.empon_app.MainActivity
import com.example.empon_app.MainActivity.Companion.empons
import com.example.empon_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_capture_result.*

class CaptureResultFragment : Fragment() {
    private val navView: BottomNavigationView = MainActivity.binding.navView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_capture_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            val id_empon = CaptureResultFragmentArgs.fromBundle(requireArguments()).emponId
            val accuracy = CaptureResultFragmentArgs.fromBundle(requireArguments()).accuracy
            val selected_empon = empons.filter { empon -> empon.id == id_empon }.single()

            imageViewHasilDeteksi.setImageResource(MainActivity.imageIdList[selected_empon.id!!])
//            textViewHasilDeteksi.text = "Hasil Deteksi: ${selected_empon.jenis}"
            textViewHasilDeteksi.text = Html.fromHtml("Hasil Deteksi: <b>${selected_empon.namaJenis}</b>")
//            textViewAkurasiCR.text = "Akurasi: $accuracy"
            textViewAkurasiCR.text = Html.fromHtml("Akurasi: <b>$accuracy</b>")
            textViewJenisCR.text = selected_empon.namaJenis
            textViewNamaLatinCR.text = selected_empon.namaLatin
            textViewManfaatCR.text = selected_empon.manfaat
            textViewKandunganCR.text = selected_empon.kandungan
        }

        buttonCaptureAgain.setOnClickListener {
            val action = CaptureResultFragmentDirections.actionToNavigationDetect()
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun onResume() {
        super.onResume()

        navView.menu.getItem(1).isChecked = true
    }
}