package uk.easys.calculations.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar
import uk.easys.calculations.AnimDepot.Companion.oa
import uk.easys.calculations.Home.Companion.dirLtr
import uk.easys.calculations.Home.Companion.now
import uk.easys.calculations.R
import uk.easys.calculations.Projects
import uk.easys.calculations.Projects.Companion.itemCreated
import uk.easys.calculations.Projects.Companion.itemMarked
import uk.easys.calculations.Projects.Companion.marking
import uk.easys.calculations.Projects.Companion.projects
import uk.easys.calculations.Projects.Companion.saveOnBlur
import uk.easys.calculations.hijridatepicker.date.gregorian.GregorianDatePickerDialog
import uk.easys.calculations.hijridatepicker.date.hijri.HijriDatePickerDialog
import uk.easys.calculations.room.Entities
import uk.easys.calculations.room.Entities.Project
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.lang.Exception
import java.util.*

class PAdapter(val c: Context, val data: ArrayList<Project>, val that: AppCompatActivity) :
    RecyclerView.Adapter<PAdapter.MyViewHolder>(), HijriDatePickerDialog.OnDateSetListener,
    GregorianDatePickerDialog.OnDateSetListener {

    class MyViewHolder(val cl: ConstraintLayout) : RecyclerView.ViewHolder(cl)

    override fun onCreateViewHolder(parent: ViewGroup, ciewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project, parent, false) as ConstraintLayout

        val marker = v.getChildAt(markerPos) as View
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
        val et = ll.getChildAt(etPos) as EditText
        val start = ll.getChildAt(startPos) as ConstraintLayout
        val startTV = start.getChildAt(0) as TextView
        val end = ll.getChildAt(endPos) as ConstraintLayout
        val endTV = end.getChildAt(0) as TextView
        val iv = h.cl.getChildAt(ivPos) as ImageView
        val marker = h.cl.getChildAt(markerPos) as View
        val cover = h.cl.getChildAt(coverPos) as View
        if (Projects.savedIds.size > i) saved.id = Projects.savedIds[i]

        // Name
        et.setText("${data[i].name}")
        et.setOnFocusChangeListener { view, b ->
            if (!b && saveOnBlur) saveET(c, et, h.layoutPosition)
        }

        // START
        startTV.text = compileTime(c, data[i].dateStarted)
        start.setOnClickListener {
            if (projects == null) return@setOnClickListener
            if (projects!!.size <= h.layoutPosition || h.layoutPosition < 0) return@setOnClickListener
            if (dirLtr) provideGregorianCalendar(
                projects!![h.layoutPosition].dateStarted, tagStart + h.layoutPosition
            ) else provideUmmalquraCalendar(
                projects!![h.layoutPosition].dateStarted, tagStart + h.layoutPosition
            )
        }

        // END
        endTV.text = compileTime(c, data[i].dateEnded)
        end.setOnClickListener {
            if (projects == null) return@setOnClickListener
            if (projects!!.size <= h.layoutPosition || h.layoutPosition < 0) return@setOnClickListener
            if (dirLtr) provideGregorianCalendar(
                projects!![h.layoutPosition].dateEnded, tagEnd + h.layoutPosition
            ) else provideUmmalquraCalendar(
                projects!![h.layoutPosition].dateEnded, tagEnd + h.layoutPosition
            )
        }

        // Notes
        iv.setOnClickListener {
            if (projects == null) return@setOnClickListener
            if (projects!!.size <= h.layoutPosition || h.layoutPosition < 0) return@setOnClickListener
            Projects.notes(c, h.layoutPosition, projects!![h.layoutPosition].notes)
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
        et.setOnLongClickListener(olcl)
        start.setOnLongClickListener(olcl)
        end.setOnLongClickListener(olcl)
        iv.setOnLongClickListener(olcl)
    }

    override fun getItemCount() = data.size

    override fun onDateSet(
        view: HijriDatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int
    ) {
        if (view == null || view.tag == null || projects == null) return
        val tag = view.tag!!.substring(0, 5)
        var pos = -1
        try {
            pos = Integer.parseInt(view.tag!!.substring(5))
        } catch (ignored: Exception) {
            return
        }
        if (pos == -1) return
        val date = UmmalquraCalendar(year, monthOfYear, dayOfMonth).timeInMillis
        when (tag) {
            tagStart -> {
                projects!![pos].dateStarted = date
                projects!![pos].dateModified = now()
            }
            tagEnd -> {
                projects!![pos].dateEnded = date
                projects!![pos].dateModified = now()
            }
            else -> return
        }
        Work(
            c, Projects.handler, Work.UPDATE_ONE, Entities.PROJECT, listOf(projects!![pos], pos)
        ).start()
        notifyItemChanged(pos)
    }

    override fun onDateSet(
        view: GregorianDatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int
    ) {
        if (view == null || view.tag == null || projects == null) return
        val tag = view.tag!!.substring(0, 5)
        var pos = -1
        try {
            pos = Integer.parseInt(view.tag!!.substring(5))
        } catch (ignored: Exception) {
            return
        }
        if (pos == -1) return
        val cal = Calendar.getInstance()
        cal.set(year, monthOfYear, dayOfMonth)
        val date = cal.timeInMillis
        when (tag) {
            tagStart -> {
                projects!![pos].dateStarted = date
                projects!![pos].dateModified = now()
            }
            tagEnd -> {
                projects!![pos].dateEnded = date
                projects!![pos].dateModified = now()
            }
            else -> return
        }
        Work(
            c, Projects.handler, Work.UPDATE_ONE, Entities.PROJECT, listOf(projects!![pos], pos)
        ).start()
        notifyItemChanged(pos)
    }


    fun clear() {
        var size = data.size
        data.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun sort() {
        Collections.sort(data, Sort.SortProjects())
        notifyDataSetChanged()
    }

    fun mark(i: Int, b: Boolean, marker: View) {
        if (Projects.deletingMass) return
        try {
            itemMarked[i] = b
        } catch (ignored: IndexOutOfBoundsException) {
        }
        marker.visibility = if (b) View.VISIBLE else View.INVISIBLE
    }

    fun provideGregorianCalendar(date: Long, tag: String) {
        val cal = Calendar.getInstance()
        cal.timeInMillis = date
        val dpd = GregorianDatePickerDialog.newInstance(
            this, cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DAY_OF_MONTH]
        )
        dpd.show(that.supportFragmentManager, tag)
    }

    fun provideUmmalquraCalendar(date: Long, tag: String) {
        val cal = UmmalquraCalendar()// DON'T USE getInstance() method!
        cal.timeInMillis = date
        val dpd = HijriDatePickerDialog.newInstance(
            this,
            cal.get(UmmalquraCalendar.YEAR),
            cal.get(UmmalquraCalendar.MONTH),
            cal.get(UmmalquraCalendar.DAY_OF_MONTH)
        )
        dpd.show(that.supportFragmentManager, tag)
    }

    companion object {
        const val bgPos = 0
        const val savedPos = 2
        const val llPos = 3
        const val ivPos = 4
        const val markerPos = 5
        const val coverPos = 6
        const val tagStart = "start"
        const val tagEnd = "finsh"
        const val etPos = 0
        const val startPos = 1
        const val endPos = 2

        fun findProjectById(id: Long, list: List<Project>): Int? {
            for (i in 0 until list.size) if (list[i].id == id) return i
            return null
        }

        fun getProjectById(id: Long, list: List<Project>): Project? {
            for (i in list) if (i.id == id) return i
            return null
        }

        fun saved(saved: View, dur: Long = 1000, delay: Long = 0) {
            saved.alpha = 1f
            oa(saved, "alpha", dur, 0f, delay)
        }

        fun compileGregorianTime(c: Context, date: Long, full: Boolean = false): String {
            val array =
                c.resources.getStringArray(if (!full) R.array.calendar else R.array.calendarFull)
            val cal = Calendar.getInstance()
            cal.timeInMillis = date
            return "${cal[Calendar.DAY_OF_MONTH]} ${array[cal[Calendar.MONTH]]}" +
                    if (!full) " '" + "${cal[Calendar.YEAR]}".substring(2, 4)
                    else " ${cal[Calendar.YEAR]}"
        }

        fun compileHijriTime(c: Context, date: Long, full: Boolean = false): String {
            val array =
                c.resources.getStringArray(if (!full) R.array.calendar else R.array.calendarFull)
            val cal = UmmalquraCalendar()
            cal.timeInMillis = date
            return "${cal.get(Calendar.DAY_OF_MONTH)} ${array[cal.get(Calendar.MONTH)]}" +
                    if (!full) " '" + "${cal.get(Calendar.YEAR)}".substring(2, 4)
                    else " ${cal.get(Calendar.YEAR)}"
        }

        fun compileTime(c: Context, date: Long, full: Boolean = false) =
            if (dirLtr) compileGregorianTime(c, date, full) else compileHijriTime(c, date, full)

        fun saveET(c: Context, et: EditText, pos: Int) {
            if (projects == null) return
            if (projects!!.size <= pos || pos < 0) return
            if (projects!![pos].name == et.text.toString()) return
            projects!![pos].name = et.text.toString()
            projects!![pos].dateModified = now()
            Work(
                c, Projects.handler, Work.UPDATE_ONE, Entities.PROJECT, listOf(projects!![pos], pos)
            ).start()
        }
    }
}