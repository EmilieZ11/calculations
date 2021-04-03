package uk.easys.calculations.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.view.ContextThemeWrapper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import uk.easys.calculations.AnimDepot.Companion.oa
import uk.easys.calculations.Home.Companion.dirLtr
import uk.easys.calculations.Home.Companion.now
import uk.easys.calculations.R
import uk.easys.calculations.Relatives
import uk.easys.calculations.Relatives.Companion.dp
import uk.easys.calculations.Relatives.Companion.itemCreated
import uk.easys.calculations.Relatives.Companion.itemMarked
import uk.easys.calculations.Relatives.Companion.marking
import uk.easys.calculations.Relatives.Companion.relatives
import uk.easys.calculations.Relatives.Companion.saveOnBlur
import uk.easys.calculations.room.Entities
import uk.easys.calculations.room.Entities.Relative
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.util.*

class RAdapter(val c: Context, val data: ArrayList<Relative>) :
    RecyclerView.Adapter<RAdapter.MyViewHolder>() {

    class MyViewHolder(val cl: ConstraintLayout) : RecyclerView.ViewHolder(cl)

    override fun onCreateViewHolder(parent: ViewGroup, ciewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_relative, parent, false) as ConstraintLayout
        val ll = v.getChildAt(llPos) as LinearLayout
        val spnCl = ll.getChildAt(spnClPos) as ConstraintLayout
        val spn = spnCl.getChildAt(0) as Spinner
        val marker = v.getChildAt(markerPos) as View

        // Repetition
        spn.id = View.generateViewId()
        var dataAdapter =
            ArrayAdapter(c, R.layout.basic_spn_1, c.resources.getStringArray(R.array.rRelativity))
        dataAdapter.setDropDownViewResource(R.layout.basic_spn_1_dd)
        spn.adapter = dataAdapter

        // Spinner Mark
        var mark = ImageView(ContextThemeWrapper(c, R.style.basicSPNMark), null, 0)
        var markLP = ConstraintLayout.LayoutParams(dp(30), dp(30))
        markLP.endToEnd = spn.id
        markLP.topToTop = spn.id
        markLP.bottomToBottom = spn.id
        mark.setColorFilter(ContextCompat.getColor(c, R.color.basicSPNMark))
        spnCl.addView(mark, markLP)

        // Marker
        if (!dirLtr) marker.rotationY = 180f

        saveOnBlur = true
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(h: MyViewHolder, i: Int) {
        if (itemCreated[i]) {
            h.cl.scaleX = 1f
            h.cl.scaleY = 1f
            h.cl.translationX = 0f
        } else {
            // Loading
            val ass = AnimatorSet().setDuration(222)
            ass.startDelay = (i * 33).toLong()
            ass.playTogether(
                ObjectAnimator.ofFloat(h.cl, "scaleX", 1f),
                ObjectAnimator.ofFloat(h.cl, "scaleY", 1f)
            )
            ass.start()
            oa(h.cl, "translationX", ass.duration * 3, 0f, ass.startDelay)
            itemCreated[i] = true
        }

        val bg = h.cl.getChildAt(bgPos) as View
        val saved = h.cl.getChildAt(savedPos) as View
        val ll = h.cl.getChildAt(llPos) as LinearLayout
        val et1 = ll.getChildAt(et1Pos) as EditText
        val et2 = ll.getChildAt(et2Pos) as EditText
        val spnCl = ll.getChildAt(spnClPos) as ConstraintLayout
        val spn = spnCl.getChildAt(0) as Spinner
        val iv = h.cl.getChildAt(ivPos) as ImageView
        val marker = h.cl.getChildAt(markerPos) as View
        val cover = h.cl.getChildAt(coverPos) as View
        if (Relatives.savedIds.size > i) saved.id = Relatives.savedIds[i]

        // First Name
        et1.setText("${data[i].firstName}")
        et1.setOnFocusChangeListener { view, b ->
            if (!b && saveOnBlur) saveET1(c, et1, h.layoutPosition)
        }

        // Last Name
        et2.setText("${data[i].lastName}")
        et2.setOnFocusChangeListener { view, b ->
            if (!b && saveOnBlur) saveET2(c, et2, h.layoutPosition)
        }

        // Relativity
        var spnClicked = false
        spn.setOnTouchListener { view, motionEvent ->
            spnClicked = true
            return@setOnTouchListener false
        }
        spn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                if (!spnClicked || relatives == null) return
                if (relatives!!.size <= h.layoutPosition || h.layoutPosition < 0) return
                if (relatives!![h.layoutPosition].relativity == pos.toByte()) return
                relatives!![h.layoutPosition].relativity = pos.toByte()
                relatives!![h.layoutPosition].dateModified = now()
                Work(
                    c, Relatives.handler, Work.UPDATE_ONE, Entities.RELATIVE,
                    listOf(relatives!![h.layoutPosition], h.layoutPosition)
                ).start()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        spn.setSelection(data[i].relativity.toInt())

        // Notes
        iv.setOnClickListener {
            if (relatives == null) return@setOnClickListener
            if (relatives!!.size <= h.layoutPosition || h.layoutPosition < 0) return@setOnClickListener
            Relatives.notes(c, h.layoutPosition, relatives!![h.layoutPosition].notes)
        }

        // Marking
        cover.visibility = if (marking) View.VISIBLE else View.GONE
        cover.isClickable = marking
        if (marking) mark(h.adapterPosition, itemMarked[h.adapterPosition], marker)
        cover.setOnClickListener {
            if (marking) mark(h.adapterPosition, marker.visibility == View.INVISIBLE, marker)
        }
        val olcl = View.OnLongClickListener {
            if (!marking) marking(true)
            mark(h.adapterPosition, true, marker)
            true
        }
        ll.setOnLongClickListener(olcl)
        et1.setOnLongClickListener(olcl)
        et2.setOnLongClickListener(olcl)
        spn.setOnLongClickListener(olcl)
        iv.setOnLongClickListener(olcl)
    }

    override fun getItemCount() = data.size

    fun clear() {
        var size = data.size
        data.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun sort() {
        Collections.sort(data, Sort.SortRelatives())
        notifyDataSetChanged()
    }

    fun mark(i: Int, b: Boolean, marker: View) {
        if (Relatives.deletingMass) return
        try {
            itemMarked[i] = b
        } catch (ignored: IndexOutOfBoundsException) {
        }
        marker.visibility = if (b) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        const val bgPos = 0
        const val savedPos = 2
        const val llPos = 3
        const val ivPos = 4
        const val markerPos = 5
        const val coverPos = 6
        const val et1Pos = 0
        const val et2Pos = 1
        const val spnClPos = 2

        fun findRelativeById(id: Long, list: List<Relative>): Int? {
            for (i in 0 until list.size) if (list[i].id == id) return i
            return null
        }

        fun getRelativeById(id: Long, list: List<Relative>): Relative? {
            for (i in list) if (i.id == id) return i
            return null
        }

        fun saved(saved: View, dur: Long = 1000, delay: Long = 0) {
            saved.alpha = 1f
            oa(saved, "alpha", dur, 0f, delay)
        }

        fun saveET1(c: Context, et1: EditText, pos: Int) {
            if (relatives == null) return
            if (relatives!!.size <= pos || pos < 0) return
            if (relatives!![pos].firstName == et1.text.toString()) return
            relatives!![pos].firstName = et1.text.toString()
            relatives!![pos].dateModified = now()
            Work(
                c, Relatives.handler, Work.UPDATE_ONE, Entities.RELATIVE, listOf(relatives!![pos], pos)
            ).start()
        }

        fun saveET2(c: Context, et2: EditText, pos: Int) {
            if (relatives == null) return
            if (relatives!!.size <= pos || pos < 0) return
            if (relatives!![pos].lastName == et2.text.toString()) return
            relatives!![pos].lastName = et2.text.toString()
            relatives!![pos].dateModified = now()
            Work(
                c, Relatives.handler, Work.UPDATE_ONE, Entities.RELATIVE, listOf(relatives!![pos], pos)
            ).start()
        }
    }
}