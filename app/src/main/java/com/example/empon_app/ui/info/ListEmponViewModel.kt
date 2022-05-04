package com.example.empon_app.ui.info

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.empon_app.model.Empon
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.ArrayList

class ListEmponViewModel(application: Application): AndroidViewModel(application) {
    val listEmponLD = MutableLiveData<List<Empon>>()
    val kumpulanEmpon = arrayListOf<Empon>()

    fun insert(empons: ArrayList<Empon>) {
        kumpulanEmpon.clear()
        listEmponLD.value = empons
    }
}

