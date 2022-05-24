package com.example.empon_app.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.empon_app.R
import com.example.empon_app.databinding.ActivityMainBinding
import com.example.empon_app.model.Empon
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    companion object {
        lateinit var binding: ActivityMainBinding
        const val TAG = "asdf"
        var empons = arrayListOf<Empon>()
        var imageIdList = arrayOf(
            R.drawable.kunyit_kuning, //0
            R.drawable.kunyit_hitam, //1
            R.drawable.jahe_putih, //2
            R.drawable.jahe_merah, //0R
            R.drawable.jahe_emprit, //0
            R.drawable.kunyit_putih, //0
            R.drawable.kencur, //
            R.drawable.temulawak, //
            R.drawable.lengkuas //
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** region OnboardingConfig */
        val sharedPref = this.getSharedPreferences("onboarding_accessed", Context.MODE_PRIVATE)
        val defaultValue = resources.getBoolean(R.bool.onboarding_accessed)
        val onboardingAccessed = sharedPref.getBoolean("onboarding_accessed", defaultValue)

        if (!onboardingAccessed) {
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
        }
        /** endregion */

        /** region setting up ActionBar, BotNav */
        val navView: BottomNavigationView = binding.navView
        navController = Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(navView, navController)

        val navController = findNavController(R.id.hostFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_info, R.id.navigation_detect, R.id.navigation_about
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        /** endregion */

        loadEmponFromTsv()

    }

    private fun loadEmponFromTsv() {
        val assetManager = resources.assets
        val inputStream: InputStream

        try {
            inputStream = assetManager.open("data_empon.tsv")
            val inputAsString = inputStream.bufferedReader()

            val csvParser = CSVParser(inputAsString, CSVFormat.newFormat('\t'));
            for (csvRecord in csvParser) {
                val id = csvRecord.get(0)
                val kodeJenis = csvRecord.get(1)
                val namaJenis = csvRecord.get(2)
                val namaLatin = csvRecord.get(3)
                val manfaat = csvRecord.get(4)
                val kandungan = csvRecord.get(5)
                empons.add(Empon(id.toInt(), kodeJenis, namaJenis, namaLatin, manfaat, kandungan))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawer_layout)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

}