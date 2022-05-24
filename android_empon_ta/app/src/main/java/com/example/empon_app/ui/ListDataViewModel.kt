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

    var kumpulanQuestions = arrayListOf(
        Question(
            0,
            "Apa itu Empon-Empon?",
            "Empon-empon adalah tanaman akar tinggal atau rimpang yang digunakan sebagai bahan dalam membuat jamu-jamuan tradisional dan bumbu masakan."
        ),
        Question(
            1,
            "Bagaimana Cara Aplikasi ini Bekerja?",
            "Aplikasi ini bekerja dengan mengambil citra empon-empon yang diunggah pengguna menggunakan kamera ataupun gambar yang sudah ada di galeri smartphone pengguna, lalu diproses dengan deep learning, sehingga menghasilkan hasil prediksi yang berupa nama jenis empon-empon."
        ),
        Question(
            2,
            "Jenis Empon-Empon apa saja yang Dapat Dideteksi?",
            "Ada 9 jenis empon-empon yang dapat dideteksi oleh aplikasi ini sebagai berikut:" +
                    "\n- Kunyit Kuning\n- Kunyit / Temu Hitam\n- Jahe Putih\n- Jahe Merah\n- Jahe Emprit\n- Kunyit / Temu Putih\n- Kencur\n- Temulawak\n- Lengkuas / Laos"
        )
    )

    fun insertEmpons(empons: ArrayList<Empon>) {
        listEmponLD.value = empons
    }

    fun insertQuestions() {
        listQuestionLD.value = kumpulanQuestions
    }
}

