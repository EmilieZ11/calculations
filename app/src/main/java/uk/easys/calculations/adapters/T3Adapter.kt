package uk.easys.calculations.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.blure.complexview.ComplexView
import uk.easys.calculations.AnimDepot.Companion.oa
import uk.easys.calculations.Home.Companion.now
import uk.easys.calculations.R
import uk.easys.calculations.Treasury
import uk.easys.calculations.Treasury.Companion.cashes
import uk.easys.calculations.room.Entities
import uk.easys.calculations.room.Entities.*
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.util.*

class T3Adapter(val c: Context, val data: ArrayList<Cash>, val that: AppCompatActivity) :
    RecyclerView.Adapter<T3Adapter.MyViewHolder>() {

    class MyViewHolder(val cl: ConstraintLayout) : RecyclerView.ViewHolder(cl)

    override fun onCreateViewHolder(parent: ViewGroup, ciewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cash, parent, false) as ConstraintLayout

        val bg = v.getChildAt(bgPos) as View
        val clickable = v.getChildAt(clickablePos) as View
        val icon = v.getChildAt(iconPos) as ImageView
        val field1 = v.getChildAt(field1Pos) as ComplexView
        val field1CL = field1.getChildAt(0) as ConstraintLayout
        val field1ET = field1CL.getChildAt(0) as EditText
        val field1Spn = field1CL.getChildAt(1) as Spinner
        val field1Hint = v.getChildAt(field1HintPos) as TextView
        val saved = v.getChildAt(savedPos) as View

        // Configurations with Icon
        icon.id = View.generateViewId()
        field1.id = View.generateViewId()
        var field1LP = field1.layoutParams as ConstraintLayout.LayoutParams
        field1LP.startToEnd = icon.id
        field1.layoutParams = field1LP

        // Configurations of Hints
        var field1HintLP = field1Hint.layoutParams as ConstraintLayout.LayoutParams
        field1HintLP.startToStart = field1.id
        field1HintLP.topToTop = field1.id
        field1HintLP.bottomToTop = field1.id
        field1Hint.layoutParams = field1HintLP

        // Currency
        var curAdapter = ArrayAdapter(
            c, R.layout.spn_currency_1,
            c.resources.getStringArray(R.array.currencyNames)
        )
        curAdapter.setDropDownViewResource(R.layout.basic_spn_1_dd)
        field1Spn.adapter = curAdapter

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(h: MyViewHolder, i: Int) {
        val bg = h.cl.getChildAt(bgPos) as View
        val clickable = h.cl.getChildAt(clickablePos) as View
        val icon = h.cl.getChildAt(iconPos) as ImageView
        val field1 = h.cl.getChildAt(field1Pos) as ComplexView
        val field1CL = field1.getChildAt(fieldCLPos) as ConstraintLayout
        val field1ET = field1CL.getChildAt(fieldETPos) as EditText
        val field1Spn = field1CL.getChildAt(field1SpnPos) as Spinner
        val field1Hint = h.cl.getChildAt(field1HintPos) as TextView
        val saved = h.cl.getChildAt(savedPos) as View
        if (Treasury.t3SavedIds.size > i) saved.id = Treasury.t3SavedIds[i]

        // Fields
        field1ET.setText(data[i].initial.toString())
        field1ET.setOnFocusChangeListener { view, b ->
            if (!b) saveField1ET(c, field1ET, h.layoutPosition)
        }

        // Currency
        var spnCurClicked = false
        field1Spn.setOnTouchListener { view, motionEvent ->
            spnCurClicked = true
            return@setOnTouchListener false
        }
        var currencies = c.resources.getStringArray(R.array.currencies)
        field1Spn.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, pos: Int, id: Long
                ) {
                    if (!spnCurClicked || cashes == null) return
                    if (cashes!!.size <= h.layoutPosition || h.layoutPosition < 0) return
                    if (cashes!![h.layoutPosition].initialCurrency == currencies[pos]) return
                    cashes!![h.layoutPosition].initialCurrency = currencies[pos]
                    cashes!![h.layoutPosition].dateModified = now()
                    Work(
                        c, Treasury.handler, Work.UPDATE_ONE, Entities.CASH,
                        listOf(cashes!![h.layoutPosition], h.layoutPosition, 2)
                    ).start()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        field1Spn.setSelection(currencies.indexOf(cashes!![h.layoutPosition].initialCurrency))

        // OnClick
        h.cl.setOnClickListener { }
        h.cl.setOnLongClickListener {
            var popup = PopupMenu(that, h.cl)
            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.t2PMIShowNotes -> {
                        Treasury.notes(c, h.layoutPosition, 2)
                        return@setOnMenuItemClickListener true
                    }
                    R.id.t2PMIDelete -> {
                        var ask = AlertDialog.Builder(that).apply {
                            setIcon(Treasury.adCashDrw)
                            setTitle(R.string.deleteOne)
                            setMessage(R.string.deleteOneMsg)
                            setPositiveButton(R.string.yes) { _, _ ->
                                if (cashes == null) return@setPositiveButton
                                if (cashes!!.size <= h.layoutPosition || h.layoutPosition < 0) return@setPositiveButton
                                Work(
                                    c, Treasury.handler, Work.DELETE_ONE,
                                    Entities.CASH, listOf(cashes!![h.layoutPosition], h.layoutPosition, CASH_NUM)
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
            popup.inflate(R.menu.cash_longclick)
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
        Collections.sort(data, Sort.SortCashes())
        notifyDataSetChanged()
    }

    companion object {
        const val CASH_NUM = 2
        const val bgPos = 0
        const val iconPos = 2
        const val field1Pos = 3
        const val field1HintPos = 4
        const val clickablePos = 5
        const val savedPos = 6
        const val fieldCLPos = 0
        const val fieldETPos = 0
        const val field1SpnPos = 1

        fun findCashById(id: Long, list: List<Cash>): Int? {
            for (i in 0 until list.size) if (list[i].id == id) return i
            return null
        }

        fun getCashById(id: Long, list: List<Cash>): Cash? {
            for (i in list) if (i.id == id) return i
            return null
        }

        fun saved(saved: View, dur: Long = 1000, delay: Long = 0) {
            saved.alpha = 1f
            oa(saved, "alpha", dur, 0f, delay)
        }

        fun saveField1ET(c: Context, field1ET: EditText, pos: Int) {
            if (cashes == null) return
            if (cashes!!.size <= pos || pos < 0) return
            if (cashes!![pos].initial.toString() == field1ET.text.toString()) return
            if (field1ET.text.toString() == "") {
                field1ET.setText("0"); return; }
            try {
                cashes!![pos].initial = (field1ET.text.toString()).toDouble()
            } catch (ignored: Exception) {
                return
            }
            cashes!![pos].dateModified = now()
            Work(
                c, Treasury.handler, Work.UPDATE_ONE, Entities.CASH, listOf(cashes!![pos], pos, CASH_NUM)
            ).start()
        }
    }
}