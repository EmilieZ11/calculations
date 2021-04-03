package uk.easys.calculations.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.blure.complexview.ComplexView
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import uk.easys.calculations.AnimDepot.Companion.oa
import uk.easys.calculations.Home.Companion.now
import uk.easys.calculations.R
import uk.easys.calculations.Treasury
import uk.easys.calculations.Treasury.Companion.banks
import uk.easys.calculations.Treasury.Companion.dm
import uk.easys.calculations.room.Entities
import uk.easys.calculations.room.Entities.Bank
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.util.*

class T1Adapter(val c: Context, val data: ArrayList<Bank>, val that: AppCompatActivity) :
    RecyclerView.Adapter<T1Adapter.MyViewHolder>() {

    lateinit var mathFont: Typeface
    lateinit var anbFont: Typeface

    class MyViewHolder(val cl: ConstraintLayout) : RecyclerView.ViewHolder(cl)

    override fun onCreateViewHolder(parent: ViewGroup, ciewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bank, parent, false) as ConstraintLayout
        val cv = v.getChildAt(cvPos) as ComplexView
        val body = cv.getChildAt(bodyPos) as ConstraintLayout
        val bg = body.getChildAt(bgPos) as View
        val effect1 = body.getChildAt(effect1Pos) as ImageView
        val clickable = body.getChildAt(clickablePos) as View
        val numbers = body.getChildAt(numbersPos) as LinearLayout
        val number1 = numbers.getChildAt(number1Pos) as EditText
        val number2 = numbers.getChildAt(number2Pos) as EditText
        val number3 = numbers.getChildAt(number3Pos) as EditText
        val number4 = numbers.getChildAt(number4Pos) as EditText
        val numberETs = listOf(number1, number2, number3, number4)
        val expLL = body.getChildAt(expLLPos) as LinearLayout
        val expTV = expLL.getChildAt(expTVPos) as TextView
        val expYear = expLL.getChildAt(expYearPos) as EditText
        val expSlash = expLL.getChildAt(expSlashPos) as TextView
        val expMonth = expLL.getChildAt(expMonthPos) as EditText

        // BACKGROUND
        /*var bgLP = bg.layoutParams as ConstraintLayout.LayoutParams
        bgLP.height = dp(212)//(((dm.widthPixels.toFloat() - (dm.density * 40f)) / 644f) * 600f).toInt()
        bg.layoutParams = bgLP*/

        // CARD NUMBER
        mathFont = Typeface.createFromAsset(c.assets, "fira_sans_regular.otf")
        for (e in numberETs) e.typeface = mathFont
        number1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().length == 4) number2.requestFocus()
            }

            override fun afterTextChanged(s: Editable) {}
        })
        number2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().length == 4) number3.requestFocus()
            }

            override fun afterTextChanged(s: Editable) {}
        })
        number3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().length == 4) number4.requestFocus()
            }

            override fun afterTextChanged(s: Editable) {}
        })

        // EXPIRATION
        expYear.typeface = mathFont
        expMonth.typeface = mathFont
        anbFont = Typeface.createFromAsset(c.assets, c.resources.getString(R.string.anbFont))
        expTV.typeface = anbFont
        expSlash.typeface = anbFont
        expYear.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().length == 4) expMonth.requestFocus()
            }

            override fun afterTextChanged(s: Editable) {}
        })

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(h: MyViewHolder, i: Int) {
        val cv = h.cl.getChildAt(cvPos) as ComplexView
        val body = cv.getChildAt(bodyPos) as ConstraintLayout
        val bg = body.getChildAt(bgPos) as View
        val effect1 = body.getChildAt(effect1Pos) as ImageView
        val clickable = body.getChildAt(clickablePos) as View
        val numbers = body.getChildAt(numbersPos) as LinearLayout
        val number1 = numbers.getChildAt(number1Pos) as EditText
        val number2 = numbers.getChildAt(number2Pos) as EditText
        val number3 = numbers.getChildAt(number3Pos) as EditText
        val number4 = numbers.getChildAt(number4Pos) as EditText
        val numberETs = listOf(number1, number2, number3, number4)
        val expLL = body.getChildAt(expLLPos) as LinearLayout
        val expTV = expLL.getChildAt(expTVPos) as TextView
        val expYear = expLL.getChildAt(expYearPos) as EditText
        val expSlash = expLL.getChildAt(expSlashPos) as TextView
        val expMonth = expLL.getChildAt(expMonthPos) as EditText

        // BACKGROUND
        var whitenTVs =
            listOf(number1, number2, number3, number4, expYear, expTV, expSlash, expMonth)
        setBG(bg, Color.parseColor("#" + data[i].colour), whitenTVs)

        // CARD NUMBER
        if (data[i].cardNumber.length == 16) {
            number1.setText(data[i].cardNumber.substring(0, 4))
            number2.setText(data[i].cardNumber.substring(4, 8))
            number3.setText(data[i].cardNumber.substring(8, 12))
            number4.setText(data[i].cardNumber.substring(12, 16))
        }
        for (et in numberETs) {
            et.setOnFocusChangeListener { view, b ->
                if (!b) saveNumberETs(c, numberETs, h.layoutPosition)
            }
            cardFont(20f, et)
        }

        // EXPIRATION DATE
        expYear.setText(data[i].yearExpired)
        expMonth.setText(data[i].monthExpired)
        expYear.setOnFocusChangeListener { view, b ->
            if (!b) saveExpYear(c, expYear, h.layoutPosition)
        }
        expMonth.setOnFocusChangeListener { view, b ->
            if (!b) saveExpMonth(c, expMonth, h.layoutPosition)
        }
        expTV.setOnClickListener { expYear.requestFocus() }
        cardFont(22f, expYear)
        cardFont(22f, expMonth)
        cardFont(27f, expTV)
        cardFont(27f, expSlash)

        clickable.setOnClickListener {
            if (banks == null) return@setOnClickListener
            if (banks!!.size <= h.layoutPosition || h.layoutPosition < 0) return@setOnClickListener
            Treasury.editor(c, h.layoutPosition, banks!![h.layoutPosition])
        }
        clickable.setOnLongClickListener {
            var popup = PopupMenu(that, h.cl)
            popup.setOnMenuItemClickListener { it ->
                when (it.itemId) {
                    R.id.t1PMIChangeColour -> {
                        ColorPickerDialogBuilder.with(that)
                            .setTitle("Choose color")
                            .initialColor(ContextCompat.getColor(c, R.color.CPD))
                            .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                            .density(6)//12
                            /*.setOnColorSelectedListener(object : OnColorSelectedListener {
                                override fun onColorSelected(selectedColor: Int) {//DON'T CONVERT TO LAMBDA
                                }
                            })*/
                            .setPositiveButton(c.resources.getString(R.string.ok)) { d, lastSelectedColour, allColours ->
                                if (banks == null) return@setPositiveButton
                                if (banks!!.size <= h.layoutPosition || h.layoutPosition < 0) return@setPositiveButton
                                if (banks!![h.layoutPosition].colour ==
                                    strColour(lastSelectedColour).substring(1)
                                ) return@setPositiveButton
                                banks!![h.layoutPosition].colour =
                                    strColour(lastSelectedColour).substring(1)
                                banks!![h.layoutPosition].dateModified = now()
                                Work(
                                    c, Treasury.handler, Work.UPDATE_ONE, Entities.BANK,
                                    listOf(banks!![h.layoutPosition], h.layoutPosition, BANK_NUM)
                                ).start()
                                setBG(bg, lastSelectedColour, whitenTVs)
                            }
                            .setNegativeButton(c.resources.getString(R.string.cancel)) { p0, p1 -> }
                            .build().show()
                        return@setOnMenuItemClickListener true
                    }
                    R.id.t1PMIDelete -> {
                        var ask = AlertDialog.Builder(that).apply {
                            setIcon(Treasury.adBankDrw)
                            setTitle(R.string.deleteOne)
                            setMessage(R.string.deleteOneMsg)
                            setPositiveButton(R.string.yes) { _, _ ->
                                if (banks == null) return@setPositiveButton
                                if (banks!!.size <= h.layoutPosition || h.layoutPosition < 0) return@setPositiveButton
                                Work(
                                    c, Treasury.handler, Work.DELETE_ONE, Entities.BANK,
                                    listOf(banks!![h.layoutPosition], h.layoutPosition, BANK_NUM)
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
            popup.inflate(R.menu.bank_longclick)
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
        Collections.sort(data, Sort.SortBanks())
        notifyDataSetChanged()
    }

    fun cardFont(divideBy: Float, text: TextView) {
        if (dm.widthPixels != 0) text.textSize = dm.widthPixels.toFloat() / (dm.density * divideBy)
    }

    companion object {
        const val BANK_NUM = 0
        const val cvPos = 0
        const val bodyPos = 0
        const val bgPos = 0
        const val effect1Pos = 1
        const val clickablePos = 2
        const val numbersPos = 3
        const val expLLPos = 4
        const val number1Pos = 0
        const val number2Pos = 1
        const val number3Pos = 2
        const val number4Pos = 3
        const val expTVPos = 0
        const val expYearPos = 1
        const val expSlashPos = 2
        const val expMonthPos = 3

        fun findBankById(id: Long, list: List<Bank>): Int? {
            for (i in 0 until list.size) if (list[i].id == id) return i
            return null
        }

        fun getBankById(id: Long, list: List<Bank>): Bank? {
            for (i in list) if (i.id == id) return i
            return null
        }

        fun saved(saved: View, dur: Long = 1000, delay: Long = 0) {
            saved.alpha = 1f
            oa(saved, "alpha", dur, 0f, delay)
        }

        fun setBG(bg: View, colour: Int, tvs: List<TextView>? = null) {
            var drw = GradientDrawable()
            drw.shape = GradientDrawable.RECTANGLE
            drw.cornerRadius = dm.density * 10f
            var colours = IntArray(2)
            colours[0] = colour
            var dist = 65
            colours[1] = if (colour >= -dist) colour - dist else colour + dist
            drw.colors = colours
            drw.orientation = GradientDrawable.Orientation.LEFT_RIGHT
            bg.background = drw
            var whiten =
                Color.red(colour) * 0.299 + Color.green(colour) * 0.587 + Color.blue(colour) * 0.114 <= 186
            if (tvs != null) for (t in tvs) {
                t.setTextColor(Color.parseColor(if (whiten) "#FFFFFF" else "#000000"))
                if (t is EditText) t.setHintTextColor(Color.parseColor(if (whiten) "#FFFFFF" else "#000000"))
            }
        }

        fun strColour(colour: Int) = java.lang.String.format("#%06X", 0xFFFFFF and colour)

        fun areAllFilled(ets: List<EditText>): Boolean {
            var ruturn = !ets.isNullOrEmpty()
            if (ruturn) for (et in ets) if (et.text.toString().length != 4) ruturn = false
            return ruturn
        }

        fun cardNum(ets: List<EditText>): String {
            var ruturn = StringBuffer()
            for (et in ets) ruturn.append(et.text.toString())
            return ruturn.toString()
        }

        fun saveNumberETs(c: Context, numberETs: List<EditText>, pos: Int) {
            if (!areAllFilled(numberETs) || banks == null) return
            if (banks!!.size <= pos || pos < 0) return
            if (banks!![pos].cardNumber == cardNum(numberETs)) return
            banks!![pos].cardNumber = cardNum(numberETs)
            banks!![pos].dateModified = now()
            Work(
                c, Treasury.handler, Work.UPDATE_ONE, Entities.BANK, listOf(banks!![pos], pos, BANK_NUM)
            ).start()
        }

        fun saveExpYear(c: Context, expYear: EditText, pos: Int) {
            if (expYear.text.toString().length != 4 || banks == null) return
            if (banks!!.size <= pos || pos < 0) return
            if (banks!![pos].yearExpired == expYear.text.toString()) return
            banks!![pos].yearExpired = expYear.text.toString()
            banks!![pos].dateModified = now()
            Work(
                c, Treasury.handler, Work.UPDATE_ONE, Entities.BANK, listOf(banks!![pos], pos, BANK_NUM)
            ).start()
        }

        fun saveExpMonth(c: Context, expMonth: EditText, pos: Int) {
            if (expMonth.text.toString().length != 2 || banks == null) return
            if (banks!!.size <= pos || pos < 0) return
            if (banks!![pos].monthExpired == expMonth.text.toString()) return
            banks!![pos].monthExpired = expMonth.text.toString()
            banks!![pos].dateModified = now()
            Work(
                c, Treasury.handler, Work.UPDATE_ONE, Entities.BANK, listOf(banks!![pos], pos, BANK_NUM)
            ).start()
        }
    }
}