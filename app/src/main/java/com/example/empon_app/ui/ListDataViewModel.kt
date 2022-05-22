package com.example.empon_app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.empon_app.model.Empon
import com.example.empon_app.model.Question
import java.util.ArrayList

class ListDataViewModel(application: Application): AndroidViewModel(application) {
    val listEmponLD = MutableLiveData<List<Empon>>()
    val listQuestionLD = MutableLiveData<List<Question>>()

    val kumpulanEmpon = arrayListOf<Empon>()
    var kumpulanQuestions = arrayListOf(
        Question(
            0,
            "Apa itu Empon-Empon?",
            "Empon-empon adalah tanaman akar tinggal atau rimpang yang digunakan sebagai bahan dalam membuat jamu-jamuan tradisional dan bumbu masakan."
        ),
        Question(
            1,
            "Bagaimana Aplikasi ini Bekerja?",
            "Aplikasi ini bekerja dengan mengambil citra empon-empon yang diunggah pengguna menggunakan kamera ataupun gambar yang sudah ada di galeri smartphone pengguna, lalu diproses dengan deep learning, sehingga menghasilkan hasil prediksi yang berupa nama jenis empon-empon."
        ),
        Question(
            2,
            "Jenis Empon-Empon apa saja yang Dapat Dideteksi?",
            "Ada 9 jenis empon-empon yang dapat dideteksi oleh aplikasi ini." +
                    "\n- jahe_emprit\n- jahe_merah\n- jahe_putih\n- kencur\n- kunyit_hitam\n- kunyit_kuning\n- kunyit_putih\n- lengkuas\n- temulawak"
        )
    )

    fun insertEmpons(empons: ArrayList<Empon>) {
        kumpulanEmpon.clear()
        listEmponLD.value = empons
    }

    fun insertQuestions() {
        listQuestionLD.value = kumpulanQuestions
    }
}

