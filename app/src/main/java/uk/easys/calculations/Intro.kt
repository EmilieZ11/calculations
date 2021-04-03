package uk.easys.calculations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import uk.easys.calculations.AnimDepot.Companion.oa
import uk.easys.calculations.AnimDepot.Companion.va
import java.lang.Exception
import java.util.*

class Intro : AppCompatActivity() {
    lateinit var body: ConstraintLayout
    lateinit var motor1: View
    lateinit var iGrad: ConstraintLayout
    lateinit var iCircles: ConstraintLayout
    lateinit var iHologram: View
    lateinit var iSeatLine: View
    lateinit var iCaption: TextView
    lateinit var iWaves: ConstraintLayout
    lateinit var iWavesGradFix: View
    lateinit var iPoints: ConstraintLayout
    lateinit var iPoint1: ImageView
    lateinit var iPoint2: ImageView
    lateinit var iPoint3: ImageView
    lateinit var iPoint4: ImageView
    lateinit var iPoint5: ImageView
    lateinit var iPoint6: ImageView
    lateinit var iNext: ConstraintLayout
    lateinit var iNextTV: TextView

    lateinit var sp: SharedPreferences
    lateinit var captionFont: Typeface
    lateinit var hologramTexts: Array<String>
    lateinit var appearViews: List<View>
    lateinit var points: List<ImageView>

    var imgHologram: ImageView? = null
    val holograms = intArrayOf(
        R.drawable.intro_1, R.drawable.intro_2, R.drawable.intro_3,
        R.drawable.intro_4, R.drawable.intro_5, R.drawable.intro_6
    )
    val hologramWidths = floatArrayOf(0.6666f, 0.8933f, 0.8200f, 0.7533f, 0.6706f, 0.8126f)
    val hologramBiases = floatArrayOf(0.5f, 0.35f, 0.69f, 0.5f, 0.55f, 0.77f)
    val hologramTransY = floatArrayOf(0.1f, 0.3f, 0f, 0.1f, 0.075f, 0.15f)
    var hologram = 0

    companion object {
        lateinit var c: Context

        const val exFirstLaunch = "firstLaunch"
        var dm = DisplayMetrics()
        var dirLtr = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro)

        body = findViewById(R.id.body)
        motor1 = findViewById(R.id.motor1)
        iGrad = findViewById(R.id.iGrad)
        iCircles = findViewById(R.id.iCircles)
        iHologram = findViewById(R.id.iHologram)
        iSeatLine = findViewById(R.id.iSeatLine)
        iCaption = findViewById(R.id.iCaption)
        iWaves = findViewById(R.id.iWaves)
        iWavesGradFix = findViewById(R.id.iWavesGradFix)
        iPoints = findViewById(R.id.iPoints)
        iPoint1 = findViewById(R.id.iPoint1)
        iPoint2 = findViewById(R.id.iPoint2)
        iPoint3 = findViewById(R.id.iPoint3)
        iPoint4 = findViewById(R.id.iPoint4)
        iPoint5 = findViewById(R.id.iPoint5)
        iPoint6 = findViewById(R.id.iPoint6)
        iNext = findViewById(R.id.iNext)
        iNextTV = findViewById(R.id.iNextTV)

        c = applicationContext
        windowManager.defaultDisplay.getMetrics(dm)
        for (lang in Home.supportedRtlLangs)
            if (Locale.getDefault().language == lang) {
                body.layoutDirection = View.LAYOUT_DIRECTION_RTL
                dirLtr = false
            }
        sp = PreferenceManager.getDefaultSharedPreferences(c)
        captionFont = Typeface.createFromAsset(assets, "arial.ttf")
        hologramTexts = resources.getStringArray(R.array.hologramTexts)
        appearViews = listOf(iGrad, iWaves, iWavesGradFix, iPoints, iNext)
        points = listOf(iPoint1, iPoint2, iPoint3, iPoint4, iPoint5, iPoint6)

        // Is FIRST LAUNCH?
        if (sp.contains(exFirstLaunch) && sp.getBoolean(exFirstLaunch, false)) {
            startActivity(Intent(c, Home::class.java))/// FIRST LOGIN THEN...
            return
        } else {
            var editor = sp.edit()
            editor.putBoolean(exFirstLaunch, true)
            editor.apply()
        }

        // Background Gradient
        var bgGrad = GradientDrawable()
        bgGrad.shape = GradientDrawable.RECTANGLE
        bgGrad.colors = intArrayOf(
            ContextCompat.getColor(c, R.color.iBGGrad1), ContextCompat.getColor(c, R.color.iBGGrad2)
        )
        bgGrad.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        iGrad.background = bgGrad
        var wavesGradFix = GradientDrawable()
        wavesGradFix.shape = GradientDrawable.RECTANGLE
        wavesGradFix.colors =
            intArrayOf(ContextCompat.getColor(c, R.color.iBGGrad2), Color.TRANSPARENT)
        wavesGradFix.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        iWavesGradFix.background = wavesGradFix

        // Other iGrad
        iCaption.typeface = captionFont
        iCaption.textSize = dynamicFontSize(111f)
        if (iCaption.textSize < 5f * dm.density) iCaption.textSize = 5f * dm.density

        // Loading
        val anLoad1 = va(motor1, "translationX", Home.loadDur, 10f, 0f, 790)
        anLoad1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                hologram(0)
                appear()
            }
        })

        // Navigation
        iNext.setOnClickListener {
            if (hologram < holograms.size - 1) hologram(hologram + 1)
            else startActivity(Intent(c, Home::class.java))
        }
        iNextTV.textSize = dynamicFontSize(111f)
    }

    fun appear() {
        for (v in appearViews) oa(v, "alpha", 222, 1f)
    }

    fun makeImgHologram(
        src: Int, wPercent: Float = 0.75f, bias: Float = 0.5f, parent: ViewGroup = iGrad
    ): ImageView {
        var iv = ImageView(c)
        var ivLP = ConstraintLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        ivLP.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
        ivLP.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
        ivLP.bottomToTop = iSeatLine.id
        ivLP.matchConstraintPercentWidth = wPercent
        ivLP.horizontalBias = bias
        iv.layoutParams = ivLP
        iv.setImageResource(src)
        iv.adjustViewBounds = true
        parent.addView(iv)
        return iv
    }

    fun hologram(w: Int) {
        try {
            iGrad.removeView(imgHologram)
        } catch (ignored: Exception) {
        }
        imgHologram = null
        hologram = w
        imgHologram = makeImgHologram(holograms[hologram], hologramWidths[w], hologramBiases[w])
        var TY = 0f
        if (iHologram.height > 0) TY = iHologram.height * hologramTransY[w]
        imgHologram!!.translationY = TY
        iCaption.text = hologramTexts[hologram]
        for (p in points) p.clearColorFilter()
        points[w].setColorFilter(ContextCompat.getColor(c, R.color.iPointOn))
    }

    fun dynamicFontSize(divide: Float) =
        (if (dm.widthPixels < dm.heightPixels) dm.heightPixels else dm.widthPixels).toFloat() / divide
}
