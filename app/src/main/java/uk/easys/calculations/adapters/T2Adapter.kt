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
import uk.easys.calculations.Treasury.Companion.chests
import uk.easys.calculations.room.Entities
import uk.easys.calculations.room.Entities.*
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.util.*

class T2Adapter(val c: Context, val data: ArrayList<Chest>, val that: AppCompatActivity) :
    RecyclerView.Adapter<T2Adapter.MyViewHolder>() {

    class MyViewHolder(val cl: ConstraintLayout) : RecyclerView.ViewHolder(cl)

    override fun onCreateViewHolder(parent: ViewGroup, ciewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chest, parent, false) as ConstraintLayout

        val bg = v.getChildAt(bgPos) as View
        val clickable = v.getChildAt(clickablePos) as View
        val icon = v.getChildAt(iconPos) as ImageView
        val field1 = v.getChildAt(field1Pos) as ComplexView
        val field1CL = field1.getChildAt(fieldCLPos) as ConstraintLayout
        val field1ET = field1CL.getChildAt(fieldETPos) as EditText
        val field2 = v.getChildAt(field2Pos) as ComplexView
        val field2CL = field2.getChildAt(fieldCLPos) as ConstraintLayout
        val field2ET = field2CL.getChildAt(fieldETPos) as EditText
        val field3 = v.getChildAt(field3Pos) as ComplexView
        val field3CL = field3.getChildAt(fieldCLPos) as ConstraintLayout
        val field3ET = field3CL.getChildAt(fieldETPos) as EditText
        val field3Spn = field3CL.getChildAt(field3Spn) as Spinner
        val field1Hint = v.getChildAt(field1HintPos) as TextView
        val field2Hint = v.getChildAt(field2HintPos) as TextView
        val field3Hint = v.getChildAt(field3HintPos) as TextView
        val saved = v.getChildAt(savedPos) as View

        // Configurations with Icon
        icon.id = View.generateViewId()
        field1.id = View.generateViewId()
        field2.id = View.generateViewId()
        field3.id = View.generateViewId()
        var field1LP = field1.layoutParams as ConstraintLayout.LayoutParams
        field1LP.startToEnd = icon.id
        field1LP.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        field1LP.bottomToTop = field2.id
        field1.layoutParams = field1LP
        var field2LP = field2.layoutParams as ConstraintLayout.LayoutParams
        field2LP.startToEnd = icon.id
        field2LP.topToBottom = field1.id
        field2LP.bottomToTop = field3.id
        field2.layoutParams = field2LP
        var field3LP = field3.layoutParams as ConstraintLayout.LayoutParams
        field3LP.startToEnd = icon.id
        field3LP.topToBottom = field2.id
        field3LP.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        field3.layoutParams = field3LP

        // Configurations of Hints
        var field1HintLP = field1Hint.layoutParams as ConstraintLayout.LayoutParams
        field1HintLP.startToStart = field1.id
        field1HintLP.topToTop = field1.id
        field1HintLP.bottomToTop = field1.id
        field1Hint.layoutParams = field1HintLP
        var field2HintLP = field2Hint.layoutParams as ConstraintLayout.LayoutParams
        field2HintLP.startToStart = field2.id
        field2HintLP.topToTop = field2.id
        field2HintLP.bottomToTop = field2.id
        field2Hint.layoutParams = field2HintLP
        var field3HintLP = field3Hint.layoutParams as ConstraintLayout.LayoutParams
        field3HintLP.startToStart = field3.id
        field3HintLP.topToTop = field3.id
        field3HintLP.bottomToTop = field3.id
        field3Hint.layoutParams = field3HintLP

        // Currency
        var curAdapter = ArrayAdapter(
            c, R.layout.spn_currency_1,
            c.resources.getStringArray(R.array.currencyNames)
        )
        curAdapter.setDropDownViewResource(R.layout.basic_spn_1_dd)
        field3Spn.adapter = curAdapter

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(h: MyViewHolder, i: Int) {
        val bg = h.cl.getChildAt(bgPos) as View
        val clickable = h.cl.getChildAt(clickablePos) as View
        val icon = h.cl.getChildAt(iconPos) as ImageView
        val field1 = h.cl.getChildAt(field1Pos) as ComplexView
        val field1CL = field1.getChildAt(fieldCLPos) as ConstraintLayout
        val field1ET = field1CL.getChildAt(fieldETPos) as EditText
        val field2 = h.cl.getChildAt(field2Pos) as ComplexView
        val field2CL = field2.getChildAt(fieldCLPos) as ConstraintLayout
        val field2ET = field2CL.getChildAt(fieldETPos) as EditText
        val field3 = h.cl.getChildAt(field3Pos) as ComplexView
        val field3CL = field3.getChildAt(fieldCLPos) as ConstraintLayout
        val field3ET = field3CL.getChildAt(fieldETPos) as EditText
        val field3Spn = field3CL.getChildAt(field3Spn) as Spinner
        val field1Hint = h.cl.getChildAt(field1HintPos) as TextView
        val field2Hint = h.cl.getChildAt(field2HintPos) as TextView
        val field3Hint = h.cl.getChildAt(field3HintPos) as TextView
        val saved = h.cl.getChildAt(savedPos) as View
        if (Treasury.t2SavedIds.size > i) saved.id = Treasury.t2SavedIds[i]

        // Fields
        field1ET.setText(data[i].name)
        field1ET.setOnFocusChangeListener { view, b ->
            if (!b) saveField1ET(c, field1ET, h.layoutPosition)
        }
        field2ET.setText(data[i].subscriptionCode)
        field2ET.setOnFocusChangeListener { view, b ->
            if (!b) saveField2ET(c, field2ET, h.layoutPosition)
        }
        field3ET.setText(data[i].initial.toString())
        field3ET.setOnFocusChangeListener { view, b ->
            if (!b) saveField3ET(c, field3ET, h.layoutPosition)
        }

        // Currency
        var spnCurClicked = false
        field3Spn.setOnTouchListener { view, motionEvent ->
            spnCurClicked = true
            return@setOnTouchListener false
        }
        var currencies = c.resources.getStringArray(R.array.currencies)
        field3Spn.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, pos: Int, id: Long
                ) {
                    if (!spnCurClicked || chests == null) return
                    if (chests!!.size <= h.layoutPosition || h.layoutPosition < 0) return
                    if (chests!![h.layoutPosition].initialCurrency == currencies[pos]) return
                    chests!![h.layoutPosition].initialCurrency = currencies[pos]
                    chests!![h.layoutPosition].dateModified = now()
                    Work(
                        c, Treasury.handler, Work.UPDATE_ONE, Entities.CHEST,
                        listOf(chests!![h.layoutPosition], h.layoutPosition, CHEST_NUM)
                    ).start()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        field3Spn.setSelection(currencies.indexOf(chests!![h.layoutPosition].initialCurrency))

        // OnClick
        h.cl.setOnClickListener { }
        h.cl.setOnLongClickListener {
            var popup = PopupMenu(that, h.cl)
            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.t2PMIShowNotes -> {
                        Treasury.notes(c, h.layoutPosition, CHEST_NUM)
                        return@setOnMenuItemClickListener true
                    }
                    R.id.t2PMIDelete -> {
                        var ask = AlertDialog.Builder(that).apply {
                            setIcon(Treasury.adChestDrw)
                            setTitle(R.string.deleteOne)
                            setMessage(R.string.deleteOneMsg)
                            setPositiveButton(R.string.yes) { _, _ ->
                                if (chests == null) return@setPositiveButton
                                if (chests!!.size <= h.layoutPosition || h.layoutPosition < 0) return@setPositiveButton
                                Work(
                                    c, Treasury.handler, Work.DELETE_ONE, Entities.CHEST,
                                    listOf(chests!![h.layoutPosition], h.layoutPosition, CHEST_NUM)
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
            popup.inflate(R.menu.chest_longclick)
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
        Collections.sort(data, Sort.SortChests())
        notifyDataSetChanged()
    }

    companion object {
        const val CHEST_NUM = 1
        const val bgPos = 0
        const val iconPos = 2
        const val field1Pos = 3
        const val field2Pos = 4
        const val field3Pos = 5
        const val field1HintPos = 6
        const val field2HintPos = 7
        const val field3HintPos = 8
        const val clickablePos = 9
        const val savedPos = 10
        const val fieldCLPos = 0
        const val fieldETPos = 0
        const val field3Spn = 1

        fun findChestById(id: Long, list: List<Chest>): Int? {
            for (i in 0 until list.size) if (list[i].id == id) return i
            return null
        }

        fun getChestById(id: Long, list: List<Chest>): Chest? {
            for (i in list) if (i.id == id) return i
            return null
        }

        fun saved(saved: View, dur: Long = 1000, delay: Long = 0) {
            saved.alpha = 1f
            oa(saved, "alpha", dur, 0f, delay)
        }

        fun saveField1ET(c: Context, field1ET: EditText, pos: Int) {
            if (chests == null) return
            if (chests!!.size <= pos || pos < 0) return
            if (chests!![pos].name == field1ET.text.toString()) return
            chests!![pos].name = field1ET.text.toString()
            chests!![pos].dateModified = now()
            Work(
                c, Treasury.handler, Work.UPDATE_ONE, Entities.CHEST, listOf(chests!![pos], pos, CHEST_NUM)
            ).start()
        }

        fun saveField2ET(c: Context, field2ET: EditText, pos: Int) {
            if (chests == null) return
            if (chests!!.size <= pos || pos < 0) return
            if (chests!![pos].subscriptionCode == field2ET.text.toString()) return
            chests!![pos].subscriptionCode = field2ET.text.toString()
            chests!![pos].dateModified = now()
            Work(
                c, Treasury.handler, Work.UPDATE_ONE, Entities.CHEST, listOf(chests!![pos], pos, CHEST_NUM)
            ).start()
        }

        fun saveField3ET(c: Context, field3ET: EditText, pos: Int) {
            if (chests == null) return
            if (chests!!.size <= pos || pos < 0) return
            if (chests!![pos].initial.toString() == field3ET.text.toString()) return
            if (field3ET.text.toString() == "") {
                field3ET.setText("0"); return; }
            try {
                chests!![pos].initial = (field3ET.text.toString()).toDouble()
            } catch (ignored: Exception) {
                return
            }
            chests!![pos].dateModified = now()
            Work(
                c, Treasury.handler, Work.UPDATE_ONE, Entities.CHEST, listOf(chests!![pos], pos, CHEST_NUM)
            ).start()
        }
    }
}