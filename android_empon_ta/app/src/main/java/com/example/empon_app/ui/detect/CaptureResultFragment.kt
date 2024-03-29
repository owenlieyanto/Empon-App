package com.example.empon_app.ui.detect

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.empon_app.ui.MainActivity
import com.example.empon_app.ui.MainActivity.Companion.empons
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
            val idEmpon = CaptureResultFragmentArgs.fromBundle(requireArguments()).emponId
            val accuracy = CaptureResultFragmentArgs.fromBundle(requireArguments()).accuracy
            val selectedEmpon = empons.single { empon -> empon.id == idEmpon }

            if (accuracy < 0.6) {
                layoutLowAcc.visibility = View.VISIBLE
            } else {
                imageViewHasilDeteksi.setImageResource(MainActivity.imageIdList[selectedEmpon.id!!])
                textViewHasilDeteksi.text = Html.fromHtml(
                    "Hasil Prediksi" +
                            ": <b>${selectedEmpon.namaJenis}</b>"
                )
                textViewAkurasiCR.text = Html.fromHtml("Akurasi: <b>$accuracy</b>")
                textViewJenisCR.text = selectedEmpon.namaJenis
                textViewNamaLatinCR.text = selectedEmpon.namaLatin
                textViewManfaatCR.text = selectedEmpon.manfaat
                textViewKandunganCR.text = selectedEmpon.kandungan
            }
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