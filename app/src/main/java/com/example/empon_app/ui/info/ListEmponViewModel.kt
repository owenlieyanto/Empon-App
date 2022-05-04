package com.example.empon_app.ui.info

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.empon_app.model.Empon
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class ListEmponViewModel(application: Application): AndroidViewModel(application) {
    val listEmponLD = MutableLiveData<List<Empon>>()
    val kumpulanEmpon = arrayListOf<Empon>()

    fun insert() {
        kumpulanEmpon.clear()
        val empon1 = Empon(
            "Kunyit Kuning",
            "Curcuma longa Linn. Syn. Curcuma domestica Val.",
            "", "", ""
        )
        val empon2 = Empon(
            "Kunyit Putih",
            "Curcuma longa Linn. Syn. Curcuma domestica Val.",
            "", "", ""
        )

        kumpulanEmpon.add(empon1)
        kumpulanEmpon.add(empon2)

        listEmponLD.value = kumpulanEmpon
    }
}

