package com.example.empon_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.empon_app.databinding.ActivityMainBinding
import com.example.empon_app.model.Empon
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
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
        var inputStream: InputStream? = null

        try {
            inputStream = assetManager.open("data_empon.tsv")
            if (inputStream != null) {
//                val inputAsString = inputStream.bufferedReader().use { it.readText() }
                val inputAsString = inputStream.bufferedReader()

                val csvParser = CSVParser(inputAsString, CSVFormat.newFormat('\t'));
                for (csvRecord in csvParser) {
                    val jenis = csvRecord.get(0)
                    val namaLatin = csvRecord.get(1)
                    val manfaat = csvRecord.get(2)
                    val kandungan = csvRecord.get(3)
                    val gambar = ""
                    Log.d("read", Empon(jenis, namaLatin, manfaat, kandungan, gambar).toString())
                }

                Log.d("read", "It worked!")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFromAsset(): String {
        val file_name = "data_empon.tsv"
        val bufferReader = application.assets.open(file_name).bufferedReader()
        val data = bufferReader.use {
            Log.d("read", it.readText())
            it.readText()
        }

        return data
    }
}