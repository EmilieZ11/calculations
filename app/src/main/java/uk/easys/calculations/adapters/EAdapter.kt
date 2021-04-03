package uk.easys.calculations.adapters

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar
import uk.easys.calculations.AnimDepot.Companion.oa
import uk.easys.calculations.Home
import uk.easys.calculations.Home.Companion.dirLtr
import uk.easys.calculations.Home.Companion.dp
import uk.easys.calculations.Home.Companion.expenses
import uk.easys.calculations.Home.Companion.subjects
import uk.easys.calculations.R
import uk.easys.calculations.room.Entities
import uk.easys.calculations.room.Entities.Expense
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.util.*

class EAdapter(val c: Context, val data: ArrayList<Expense>, val that: AppCompatActivity) :
    RecyclerView.Adapter<EAdapter.MyViewHolder>() {

    class MyViewHolder(val cl: ConstraintLayout) : RecyclerView.ViewHolder(cl)

    override fun onCreateViewHolder(parent: ViewGroup, ciewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false) as ConstraintLayout

        val tab = v.getChildAt(tabPos) as View
        if (!dirLtr) tab.rotationY = 180f

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(h: MyViewHolder, i: Int) {
        val bg = h.cl.getChildAt(bgPos) as View
        val tab = h.cl.getChildAt(tabPos) as View
        val ll = h.cl.getChildAt(llPos) as LinearLayout
        val tv1 = ll.getChildAt(0) as TextView
        val tv2 = ll.getChildAt(1) as TextView
        val saved = h.cl.getChildAt(savedPos) as View
        val clickable = h.cl.getChildAt(clickablePos) as View
        saved.id = Home.eSavedIds[i]

        // Texts
        var subjectPos = SAdapter.findSubjectById(data[i].subjectId, subjects!!)
        tv1.text = if (subjectPos != null) subjects!![subjectPos].name else ""
        tv2.text = "-" + Home.fixPrice(data[i].amount.toString())

        // OnClick
        clickable.setOnClickListener {
            if (expenses == null) return@setOnClickListener
            if (expenses!!.size <= h.layoutPosition || h.layoutPosition < 0) return@setOnClickListener
            Home.editor(c, expenses!![h.layoutPosition], h.layoutPosition)
        }
        clickable.setOnLongClickListener {
            var popup = PopupMenu(that, h.cl)
            popup.setOnMenuItemClickListener { it ->
                when (it.itemId) {
                    R.id.hPMIDelete -> {
                        var ask = AlertDialog.Builder(that).apply {
                            setIcon(Home.adIncomeDrw)
                            setTitle(R.string.deleteOne)
                            setMessage(R.string.deleteOneMsg)
                            setPositiveButton(R.string.yes) { _, _ ->
                                if (expenses == null) return@setPositiveButton
                                if (expenses!!.size <= h.layoutPosition || h.layoutPosition < 0)
                                    return@setPositiveButton
                                Work(
                                    c, Home.handler, Work.DELETE_ONE, Entities.EXPENSE,
                                    listOf(expenses!![h.layoutPosition], h.layoutPosition, 1)
                                ).start()
                            }
                            setNegativeButton(R.string.no) { _, _ -> }
                        }
                        ask.create().show()
                        return@setOnMenuItemClickListener true
                    }
                    else -> return@setOnMenuItemClickListener false
                }
            }
            popup.inflate(R.menu.transaction_longclick)
            popup.show()
            false
        }
    }

    override fun getItemCount() = data.size

    fun clear() {
        var size = data.size
        data.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun sort() {
        Collections.sort(data, Sort.SortExpenses())
        notifyDataSetChanged()
    }

    companion object {
        const val bgPos = 0
        const val tabPos = 2
        const val llPos = 3
        const val savedPos = 4
        const val clickablePos = 5
        var oYear = -1
        var oMonth = -1
        var oDay = -1
        var dYear = -1
        var dMonth = -1
        var dDay = -1

        fun findExpenseById(id: Long, list: List<Expense>): Int? {
            for (i in 0 until list.size) if (list[i].id == id) return i
            return null
        }

        fun getExpenseById(id: Long, list: List<Expense>): Expense? {
            for (i in list) if (i.id == id) return i
            return null
        }

        fun findExpenseByDay(date: Long, list: List<Expense> = expenses!!): List<Expense> {
            var inc = ArrayList<Expense>()
            for (i in list) if (IAdapter.isSameDay(i.date, date)) inc.add(i)
            return inc.toList()
        }

        fun sum(list: List<Expense>): Double {
            var sum: Double = 0.0
            for (e in list) sum += e.amount
            return sum
        }

        fun saved(saved: View, dur: Long = 1000, delay: Long = 0) {
            saved.alpha = 1f
            oa(saved, "alpha", dur, 0f, delay)
        }

        val itemDecoration = object : RecyclerView.ItemDecoration() {
            private var groupSpacing = dp(48)
            private var paint = Paint()

            override fun getItemOffsets(
                outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
            ) {
                if (expenses == null) return
                var i = parent.getChildAdapterPosition(view)
                if (i == -1 || i >= expenses!!.size) return
                var year = -1
                var month = -1
                var day = -1
                if (dirLtr) {
                    var cal = Calendar.getInstance()
                    cal.timeInMillis = expenses!![i].date
                    year = cal[Calendar.YEAR]
                    month = cal[Calendar.MONTH]
                    day = cal[Calendar.DAY_OF_MONTH]
                } else {
                    var cal = UmmalquraCalendar()
                    cal.timeInMillis = expenses!![i].date
                    year = cal[UmmalquraCalendar.YEAR]
                    month = cal[UmmalquraCalendar.MONTH]
                    day = cal[UmmalquraCalendar.DAY_OF_MONTH]
                }
                var changed = oYear != year || oMonth != month || oDay != day
                if (changed || i == 0) {
                    oYear = year
                    oMonth = month
                    oDay = day
                    outRect.set(0, groupSpacing, 0, 0)
                }
            }

            override fun onDrawOver(
                canvas: Canvas, parent: RecyclerView, state: RecyclerView.State
            ) {
                if (expenses != null) for (ch in 0 until parent.childCount) {
                    var view = parent.getChildAt(ch)
                    var i = parent.getChildAdapterPosition(view)
                    if (i == -1 || i >= expenses!!.size) return

                    var year = -1
                    var month = -1
                    var day = -1
                    var week = -1
                    if (dirLtr) {
                        var cal = Calendar.getInstance()
                        cal.timeInMillis = expenses!![i].date
                        year = cal[Calendar.YEAR]
                        month = cal[Calendar.MONTH]
                        day = cal[Calendar.DAY_OF_MONTH]
                        week = cal[Calendar.DAY_OF_WEEK]
                    } else {
                        var cal = UmmalquraCalendar()
                        cal.timeInMillis = expenses!![i].date
                        year = cal[UmmalquraCalendar.YEAR]
                        month = cal[UmmalquraCalendar.MONTH]
                        day = cal[UmmalquraCalendar.DAY_OF_MONTH]
                        week = cal[UmmalquraCalendar.DAY_OF_WEEK]
                    }
                    var changed = dYear != year || dMonth != month || dDay != day
                    if (changed || i == 0) {
                        dYear = year
                        dMonth = month
                        dDay = day

                        paint.textSize = 20f
                        paint.isFakeBoldText = true
                        paint.color = ContextCompat.getColor(Home.c, R.color.hRVSpaceText)
                        paint.isAntiAlias = true
                        canvas.drawText(
                            "${Home.c.resources.getStringArray(R.array.daysOfWeek)[week - 1]}, " +
                                    "$day ${Home.c.resources.getStringArray(R.array.calendarFull)[month]} $year",
                            Home.dm.density * 20, view.top.toFloat() - paint.textSize, paint
                        )
                    }
                }
            }
        }
    }
}