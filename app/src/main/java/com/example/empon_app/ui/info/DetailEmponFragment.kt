package com.example.empon_app.ui.info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.empon_app.MainActivity
import com.example.empon_app.R
import kotlinx.android.synthetic.main.fragment_detail_empon.*
import kotlinx.android.synthetic.main.fragment_list_empon_card.*
import kotlinx.android.synthetic.main.fragment_list_empon_card.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailEmponFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailEmponFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_empon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null){
            val idEmpon = DetailEmponFragmentArgs.fromBundle(requireArguments()).idEmpon
            Log.d("id Empon, detail frag", idEmpon.toString())

            val empons = MainActivity.empons
            for (empon in empons){
                if (empon.id!!.equals(idEmpon)){
                    textViewKandunganDetail.text = empon.kandungan
                    textViewNamaEmponDetail.text = empon.jenis
                    textViewNamaLatinDetail.text = empon.namaLatin
                    textViewManfaatDetail.text = empon.manfaat
                    imageViewEmponDetail.setImageResource(MainActivity.imageIdList[empon.id])
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailEmponFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailEmponFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}