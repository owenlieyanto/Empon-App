package com.example.empon_app.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.empon_app.R
import com.example.empon_app.model.Onboarding
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    private lateinit var indicatorsContainer: LinearLayout
    private lateinit var onboardingList: List<Onboarding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        var curOnboardingPage = 0
        setupOnboarding()
        setupIndicators()
        setCurrentOnboardingContent(curOnboardingPage)
        setCurrentIndicator(curOnboardingPage)

        buttonSkip.setOnClickListener {
            onboardingIsAccessed()
        }

        buttonNext.setOnClickListener {
            curOnboardingPage += 1

            if (curOnboardingPage <= 2) {
                setCurrentOnboardingContent(curOnboardingPage)
                setCurrentIndicator(curOnboardingPage)
            }
            if (curOnboardingPage == 2) buttonSkip.visibility = View.INVISIBLE
            if (curOnboardingPage >= 3) onboardingIsAccessed()

        }

    }

    private fun onboardingIsAccessed() {
        val sharedPref = this.getSharedPreferences("onboarding_accessed", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("onboarding_accessed", true)
            commit()
        }

        finish()
    }

    private fun setupOnboarding() {
        onboardingList = listOf(
            Onboarding(
                "Kenali!",
                "Ketahui berbagai jenis empon-empon serta informasinya.",
                R.drawable.ic_undraw_searching_re_3ra9
            ),
            Onboarding(
                "Prediksi!",
                "Unggah/ambil foto empon-empon, dan kami akan menebak jenisnya untuk anda.",
                R.drawable.ic_undraw_predictive_analytics_re_wxt8
            ),
            Onboarding(
                "Manfaatkan!",
                "Gunakan informasi yang tersedia dari berbagai jenis empon-empon sesuai dengan kebutuhan.",
                R.drawable.ic_manfaatkan
            ),
        )
    }

    /** source: https://youtu.be/5p59XpDUKhg */
    private fun setupIndicators() {
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(3)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentOnboardingContent(position: Int) {
        val onboardingObj = onboardingList[position]
        imageViewOnboarding.setImageResource(onboardingObj.image)
        textViewOnboardingTitle.text = onboardingObj.title
        textViewOnboardingDescription.text = onboardingObj.description
    }

    /** source: https://youtu.be/5p59XpDUKhg */
    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}