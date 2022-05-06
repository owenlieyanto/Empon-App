package com.example.empon_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.empon_app.databinding.ActivityMainBinding
import com.example.empon_app.model.Empon
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    companion object {
        var empons = arrayListOf<Empon>()

        var imageIdList = arrayOf<Int>(
            R.drawable.kunyit_kuning, //0
            R.drawable.kunyit_hitam, //1
            R.drawable.jahe_putih, //2
            R.drawable.jahe_merah, //0R
            R.drawable.jahe_emprit, //0
            R.drawable.kunyit_putih, //0
            R.drawable.kunyit_merah, //0
            R.drawable.kencur, //
            R.drawable.temulawak, //
            R.drawable.lengkuas //
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navController = Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(navView, navController)


        val navController = findNavController(R.id.hostFragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_info, R.id.navigation_detect, R.id.navigation_about
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val assetManager = resources.assets
        var inputStream: InputStream?

        try {
            inputStream = assetManager.open("data_empon.tsv")
            val inputAsString = inputStream.bufferedReader()

            val csvParser = CSVParser(inputAsString, CSVFormat.newFormat('\t'));
            for (csvRecord in csvParser) {
                val id = csvRecord.get(0)
                val jenis = csvRecord.get(1)
                val namaLatin = csvRecord.get(2)
                val manfaat = csvRecord.get(3)
                val kandungan = csvRecord.get(4)
                val gambar = csvRecord.get(5)
                empons.add(Empon(id.toInt(), jenis, namaLatin, manfaat, kandungan, gambar))
                Log.d("read", Empon(id.toInt(), jenis, namaLatin, manfaat, kandungan, gambar).toString())
            }

            Log.d("read", "It worked!")
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawer_layout)
    }

}