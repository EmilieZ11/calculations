package uk.easys.calculations

import android.animation.*
import android.content.Context
import android.view.View
import kotlin.math.abs

class AnimDepot {
    companion object {
        fun vis(v: View, b: Boolean = true) {
            if (b) v.visibility = View.VISIBLE
            else v.visibility = View.GONE
        }

        fun isVis(v: View) = v.visibility == View.VISIBLE

        fun rev() = TimeInterpolator { abs(it - 1f) }

        fun va(
            v: View, prop: String, dur: Long, val1: Float, val2: Float, delay: Long = 0
        ): ValueAnimator {
            val va = ObjectAnimator.ofFloat(v, prop, val1, val2).setDuration(dur)
            va.startDelay = delay
            va.start()
            return va
        }

        fun oa(v: View, prop: String, dur: Long, value: Float, delay: Long = 0): ObjectAnimator {
            val oa = ObjectAnimator.ofFloat(v, prop, value).setDuration(dur)
            oa.startDelay = delay
            oa.start()
            return oa
        }


        // Special
        fun cover(cover: View, b: Boolean, dur: Long = 222) {
            if (b) vis(cover)
            var alpha = oa(cover, "alpha", dur, if (b) 1f else 0f)
            if (!b) alpha.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    vis(cover, false)
                }
            })
        }

        fun noteEditor(
            c: Context, noteEditor: View, b: Boolean, cover: View,
            covClick: View.OnClickListener? = null
        ) {
            var anim = AnimatorInflater.loadAnimator(c, R.animator.notes) as AnimatorSet
            anim.setTarget(noteEditor)
            if (b) {
                vis(noteEditor)
                cover.setOnClickListener(covClick)
            } else {
                anim.interpolator = rev()
                anim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        vis(noteEditor, false)
                    }
                })
                cover.setOnClickListener { }
            }
            cover(cover, b)
            anim.start()
        }
    }
}