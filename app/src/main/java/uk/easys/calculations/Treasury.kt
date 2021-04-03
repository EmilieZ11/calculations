package uk.easys.calculations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blure.complexview.ComplexView
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar
import com.google.android.material.bottomnavigation.BottomNavigationView
import uk.easys.calculations.AnimDepot.Companion.oa
import uk.easys.calculations.AnimDepot.Companion.va
import uk.easys.calculations.Home.Companion.dirLtr
import uk.easys.calculations.Home.Companion.now
import uk.easys.calculations.adapters.PAdapter
import uk.easys.calculations.adapters.T1Adapter
import uk.easys.calculations.adapters.T1Adapter.Companion.BANK_NUM
import uk.easys.calculations.adapters.T2Adapter
import uk.easys.calculations.adapters.T2Adapter.Companion.CHEST_NUM
import uk.easys.calculations.adapters.T3Adapter
import uk.easys.calculations.adapters.T3Adapter.Companion.CASH_NUM
import uk.easys.calculations.hijridatepicker.date.gregorian.GregorianDatePickerDialog
import uk.easys.calculations.hijridatepicker.date.hijri.HijriDatePickerDialog
import uk.easys.calculations.room.Entities.*
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.lang.Exception
import java.lang.Math.abs
import java.util.*
import kotlin.collections.ArrayList
import uk.easys.calculations.AnimDepot.Companion.noteEditor

class Treasury : AppCompatActivity() {
    lateinit var body: ConstraintLayout
    lateinit var header: ImageView
    lateinit var headerTV: TextView
    lateinit var headerTVSub: TextView
    lateinit var circleUserShadow: ImageView
    lateinit var circleUserBG: ImageView
    lateinit var circleUser: ImageView
    lateinit var tDiv1: ConstraintLayout
    lateinit var tRV1: RecyclerView
    lateinit var tDiv2: ConstraintLayout
    lateinit var tRV2: RecyclerView
    lateinit var tDiv3: ConstraintLayout
    lateinit var tRV3: RecyclerView
    lateinit var tAdd: ComplexView
    lateinit var tAddIV: ImageView
    lateinit var navigator: View
    lateinit var t1BankNameSaved: View
    lateinit var t1AccountNumberSaved: View
    lateinit var t1InitialSaved: View
    lateinit var t1DateFoundedSaved: View
    lateinit var t1NotesSaved: View
    lateinit var addNewButton: ComplexView
    lateinit var addNewButtonBG: View
    lateinit var addNewButtonTV: TextView
    lateinit var addNewButtonFG: View

    lateinit var c: Context
    lateinit var titleFont: Typeface
    lateinit var anbFont: Typeface
    lateinit var divisions: List<ConstraintLayout>
    lateinit var tBNVIds: List<Int>

    var curDiv = 0
    var anNavig: AnimatorSet? = null
    var inserting = false
    var inserted: Any? = null

    companion object {
        lateinit var motor1: View
        lateinit var tBNV: BottomNavigationView
        lateinit var cover: View
        lateinit var noteEditor: ConstraintLayout
        lateinit var noteEditorTitle: TextView
        lateinit var noteEditorET: EditText
        lateinit var noteEditorBtn: ImageView
        lateinit var editorShadow: View
        lateinit var editor: ConstraintLayout
        lateinit var bankEditor: ScrollView
        lateinit var bankEditorCL: ConstraintLayout
        lateinit var chestEditor: ScrollView
        lateinit var chestEditorCL: ConstraintLayout
        lateinit var cashEditor: ScrollView
        lateinit var cashEditorCL: ConstraintLayout
        lateinit var t1BankNameET: EditText
        lateinit var t1BankNameHint: TextView
        lateinit var t1AccountNumberET: EditText
        lateinit var t1AccountNumberHint: TextView
        lateinit var t1InitialET: EditText
        lateinit var t1InitialCur: Spinner
        lateinit var t1InitialHint: TextView
        lateinit var t1DateFoundedTV: TextView
        lateinit var t1DateFoundedHint: TextView
        lateinit var t1NotesET: TextView
        lateinit var t1NotesHint: TextView

        lateinit var handler: Handler
        lateinit var bankAdapter: T1Adapter
        lateinit var chestAdapter: T2Adapter
        lateinit var cashAdapter: T3Adapter
        lateinit var editorSVs: List<ScrollView>
        lateinit var currencies: Array<String>
        lateinit var currencyNames: Array<String>
        lateinit var adBankDrw: Drawable
        lateinit var adChestDrw: Drawable
        lateinit var adCashDrw: Drawable

        var dm = DisplayMetrics()
        var tRV1LManager: LinearLayoutManager? = null
        var tRV2LManager: LinearLayoutManager? = null
        var tRV3LManager: LinearLayoutManager? = null
        var banks: ArrayList<Bank>? = null
        var chests: ArrayList<Chest>? = null
        var cashes: ArrayList<Cash>? = null
        val item1Created = ArrayList<Boolean>()
        val item2Created = ArrayList<Boolean>()
        val item3Created = ArrayList<Boolean>()
        val t2SavedIds = ArrayList<Int>()
        val t3SavedIds = ArrayList<Int>()
        var editingIt = -1
        var showingEditor = false
        var showEditorDur: Long = 650
        var spnCur1Clicked = false
        var showingNotes = false
        var editingItsNote = -1
        var editingItsNoteType = -1
        val defCardColours = listOf("27337D", "FDAB01")

        fun dp(px: Int): Int = (px * dm.density).toInt()

        fun editor(c: Context, i: Int, data: Any?) {
            if (data == null || showingEditor) return
            if (data is Bank && banks != null) {
                showEditor(true)
                showEditorSV(0)
                editingIt = i
                object : CountDownTimer(showEditorDur + 10, showEditorDur + 10) {
                    override fun onTick(p0: Long) {}
                    override fun onFinish() {
                        t1BankNameET.setText(data.name)
                        animateHint(data.name != "", t1BankNameET, t1BankNameHint)

                        t1AccountNumberET.setText(data.accountNumber)
                        animateHint(
                            data.accountNumber != "", t1AccountNumberET, t1AccountNumberHint
                        )

                        t1InitialET.setText(data.initial.toString())
                        animateHint(true, t1InitialET, t1InitialHint)

                        t1DateFoundedTV.text = if (data.dateFounded != (-1).toLong())
                            PAdapter.compileTime(c, data.dateFounded, true) else ""
                        animateHint(
                            data.dateFounded != (-1).toLong(), t1DateFoundedTV, t1DateFoundedHint
                        )

                        t1InitialCur.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?, view: View?, pos: Int, id: Long
                                ) {
                                    if (!spnCur1Clicked || banks == null) return
                                    if (editingIt == -1 || banks!!.size <= editingIt) return
                                    banks!![editingIt].initialCurrency = currencies[pos]
                                    banks!![editingIt].dateModified = now()
                                    Work(
                                        c, handler, Work.UPDATE_ONE, BANK,
                                        listOf(banks!![editingIt], editingIt, 6)
                                    ).start()
                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {
                                }
                            }
                        t1InitialCur.setSelection(currencies.indexOf(banks!![editingIt].initialCurrency))

                        t1NotesET.setText(data.notes)
                        animateHint(true, t1BankNameET, t1NotesHint)// WATCH IT MAN!!!
                    }
                }.start()
            }
            if (data is Chest && chests != null) {
                showEditor(true)
                showEditorSV(1)
                editingIt = i
            }
            if (data is Cash && cashes != null) {
                showEditor(true)
                showEditorSV(2)
                editingIt = i
            }
        }

        fun showEditorSV(i: Int) {
            for (e in editorSVs) e.visibility = View.GONE
            editorSVs[i].visibility = View.VISIBLE
        }

        fun showEditor(b: Boolean) {
            if (b == showingEditor) return
            showingEditor = b
            var dest = dm.heightPixels.toFloat() * 1.11f
            var scroll = AnimatorSet().setDuration(showEditorDur)
            scroll.playTogether(
                ObjectAnimator.ofFloat(
                    editor, "translationY", if (b) dest else 0f, if (b) 0f else dest
                ), ObjectAnimator.ofFloat(
                    editorShadow, "translationY", if (b) dest else 0f, if (b) 0f else dest
                )
            )
            scroll.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    if (b) {
                        var hideBMW = oa(tBNV, "alpha", 22, 0f)
                        hideBMW.addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                tBNV.translationY = dm.heightPixels.toFloat()
                            }
                        })
                        editor.visibility = View.VISIBLE
                        editorShadow.visibility = View.VISIBLE
                    }
                }

                override fun onAnimationEnd(animation: Animator?) {
                    if (!b) {
                        tBNV.alpha = 0f
                        tBNV.translationY = 0f
                        oa(tBNV, "alpha", 22, 1f)
                        editor.visibility = View.GONE
                        editorShadow.visibility = View.GONE
                    }
                }
            })
            scroll.start()
        }

        fun animateHint(b: Boolean, et: TextView, hint: TextView) {
            var an = AnimatorSet().setDuration(111)
            var transX = hint.width.toFloat() / -5f
            if (!dirLtr) transX = 0f - transX
            an.playTogether(
                ObjectAnimator.ofFloat(hint, "scaleX", if (b) 0.65f else 1f),
                ObjectAnimator.ofFloat(hint, "scaleY", if (b) 0.65f else 1f),
                ObjectAnimator.ofFloat(
                    hint, "translationX", if (b) transX else 0f
                ), ObjectAnimator.ofFloat(
                    hint, "translationY", if (b) et.height.toFloat() / -1.35f else 0f//-2.65f
                )
            )
            an.start()
        }


        fun notes(c: Context, i: Int, type: Int) {
            if (type == CHEST_NUM) {
                if (chests == null) return
                noteEditor(c, noteEditor, true, cover, View.OnClickListener { saveNotes(c) })
                noteEditorET.setText(chests!![i].notes ?: "")
                editingItsNote = i
                editingItsNoteType = type
            }
            if (type == CASH_NUM) {
                if (cashes == null) return
                noteEditor(c, noteEditor, true, cover, View.OnClickListener { saveNotes(c) })
                noteEditorET.setText(cashes!![i].notes ?: "")
                editingItsNote = i
                editingItsNoteType = type
            }
        }

        fun saveNotes(c: Context) {
            noteEditor(c, noteEditor, false, cover)
            if (editingItsNoteType == 1 && chests != null && chests!!.size > editingItsNote && editingItsNote != -1 &&
                chests!![editingItsNote].notes != noteEditorET.text.toString()
            ) {
                chests!![editingItsNote].notes = noteEditorET.text.toString()
                chests!![editingItsNote].dateModified = now()
                Work(
                    c, handler, Work.UPDATE_ONE, CHEST,
                    listOf(chests!![editingItsNote], editingItsNote)
                ).start()
            }
            if (editingItsNoteType == 2 && cashes != null && cashes!!.size > editingItsNote && editingItsNote != -1 &&
                cashes!![editingItsNote].notes != noteEditorET.text.toString()
            ) {
                cashes!![editingItsNote].notes = noteEditorET.text.toString()
                cashes!![editingItsNote].dateModified = now()
                Work(
                    c, handler, Work.UPDATE_ONE, CASH,
                    listOf(cashes!![editingItsNote], editingItsNote)
                ).start()
            }
            editingItsNote = -1
            editingItsNoteType = -1
        }


        fun newBank(c: Context, banks: List<Bank>): Bank {
            val new = c.resources.getString(R.string.t1AddNew)
            var n = 1
            for (s in banks) if (s.name.length > new.length)
                if (s.name.substring(0, new.length) == new) n += 1
            return Bank(
                new + n, "", "", 0.0, "USD", -1,
                "", "", defCardColours[Random().nextInt(defCardColours.size)], "", now(), now()
            )
        }

        fun newChest(c: Context, chests: List<Chest>): Chest {
            val new = c.resources.getString(R.string.t2AddNew)
            var n = 1
            for (s in chests) if (s.name.length > new.length)
                if (s.name.substring(0, new.length) == new) n += 1
            return Chest(new + n, "", 0.0, "USD", -1, "", now(), now())
        }

        fun newCash() = Cash(0.0, "USD", -1, "", now(), now())
        // BE CAREFUL WITH "-1"

        fun entityType(i: Int = BANK_NUM): String = when (i) {
            CHEST_NUM -> CHEST; CASH_NUM -> CASH; else -> BANK
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.treasury)

        body = findViewById(R.id.body)
        motor1 = findViewById(R.id.motor1)
        header = findViewById(R.id.header)
        headerTV = findViewById(R.id.headerTV)
        headerTVSub = findViewById(R.id.headerTVSub)
        circleUserShadow = findViewById(R.id.circleUserShadow)
        circleUserBG = findViewById(R.id.circleUserBG)
        circleUser = findViewById(R.id.circleUser)
        tDiv1 = findViewById(R.id.tDiv1)
        tRV1 = findViewById(R.id.tRV1)
        tDiv2 = findViewById(R.id.tDiv2)
        tRV2 = findViewById(R.id.tRV2)
        tDiv3 = findViewById(R.id.tDiv3)
        tRV3 = findViewById(R.id.tRV3)
        tAdd = findViewById(R.id.tAdd)
        tAddIV = findViewById(R.id.tAddIV)
        tBNV = findViewById(R.id.tBNV)
        navigator = findViewById(R.id.navigator)
        cover = findViewById(R.id.cover)
        noteEditor = findViewById(R.id.noteEditor)
        noteEditorTitle = findViewById(R.id.noteEditorTitle)
        noteEditorET = findViewById(R.id.noteEditorET)
        noteEditorBtn = findViewById(R.id.noteEditorBtn)
        editorShadow = findViewById(R.id.editorShadow)
        editor = findViewById(R.id.editor)
        bankEditor = editor.getChildAt(0) as ScrollView
        bankEditorCL = bankEditor.getChildAt(0) as ConstraintLayout
        chestEditor = editor.getChildAt(1) as ScrollView
        chestEditorCL = chestEditor.getChildAt(0) as ConstraintLayout
        cashEditor = editor.getChildAt(2) as ScrollView
        cashEditorCL = cashEditor.getChildAt(0) as ConstraintLayout
        t1BankNameET = findViewById(R.id.t1BankNameET)
        t1BankNameSaved = findViewById(R.id.t1BankNameSaved)
        t1BankNameHint = findViewById(R.id.t1BankNameHint)
        t1AccountNumberET = findViewById(R.id.t1AccountNumberET)
        t1AccountNumberSaved = findViewById(R.id.t1AccountNumberSaved)
        t1AccountNumberHint = findViewById(R.id.t1AccountNumberHint)
        t1InitialET = findViewById(R.id.t1InitialET)
        t1InitialCur = findViewById(R.id.t1InitialCur)
        t1InitialSaved = findViewById(R.id.t1InitialSaved)
        t1InitialHint = findViewById(R.id.t1InitialHint)
        t1DateFoundedTV = findViewById(R.id.t1DateFoundedTV)
        t1DateFoundedSaved = findViewById(R.id.t1DateFoundedSaved)
        t1DateFoundedHint = findViewById(R.id.t1DateFoundedHint)
        t1NotesET = findViewById(R.id.t1NotesET)
        t1NotesSaved = findViewById(R.id.t1NotesSaved)
        t1NotesHint = findViewById(R.id.t1NotesHint)
        addNewButton = findViewById(R.id.addNewButton)
        addNewButtonBG = findViewById(R.id.addNewButtonBG)
        addNewButtonTV = findViewById(R.id.addNewButtonTV)
        addNewButtonFG = findViewById(R.id.addNewButtonFG)

        c = applicationContext
        windowManager.defaultDisplay.getMetrics(dm)
        if (!dirLtr) body.layoutDirection = View.LAYOUT_DIRECTION_RTL
        titleFont = Typeface.createFromAsset(assets, resources.getString(R.string.titleFont))
        anbFont = Typeface.createFromAsset(assets, resources.getString(R.string.anbFont))
        divisions = listOf(tDiv1, tDiv2, tDiv3)
        editorSVs = listOf(bankEditor, chestEditor, cashEditor)
        currencyNames = resources.getStringArray(R.array.currencyNames)
        currencies = resources.getStringArray(R.array.currencies)


        // Loading
        if (!dirLtr) {
            header.rotationY = 180f
            headerTVSub.translationX = 0f - headerTVSub.translationX
            headerTVSub.translationY = dm.density * -27f
        }
        val anLoad1 = va(motor1, "translationX", Home.loadDur, 10f, 0f, 1970)
        anLoad1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                headerTVSub.visibility = View.VISIBLE
                oa(headerTVSub, "translationX", 555, 0f)
                oa(headerTV, "translationY", 444, dm.density * -10, 111)

                Home.updateNavigator(navigator, false, curDiv)
            }
        })

        // Header
        headerTV.typeface = titleFont//, Typeface.BOLD
        headerTVSub.typeface = anbFont
        circleUser.setOnClickListener { onBackPressed() }
        Home.glideAvatar(this, circleUserBG)

        // Lists
        tRV1.setHasFixedSize(false)
        tRV2.setHasFixedSize(false)
        tRV3.setHasFixedSize(false)
        tRV1LManager = LinearLayoutManager(c)
        tRV2LManager = LinearLayoutManager(c)
        tRV3LManager = LinearLayoutManager(c)
        tRV1.layoutManager = tRV1LManager
        tRV2.layoutManager = tRV2LManager
        tRV3.layoutManager = tRV3LManager
        tRV1.setOnTouchListener { view, event -> swipe(event); return@setOnTouchListener false }
        tRV2.setOnTouchListener { view, event -> swipe(event); return@setOnTouchListener false }
        tRV3.setOnTouchListener { view, event -> swipe(event); return@setOnTouchListener false }
        handler = object : Handler() {
            override fun handleMessage(msg: Message?) {
                if (msg == null) return
                when (msg.what) {
                    Work.VIEW_ALL -> arrangeList(msg.obj as List<Any>, msg.arg1)
                    Work.VIEW_ONE -> when (msg.arg1) {
                    }
                    Work.INSERT_ONE -> {
                        inserting = false
                        if (inserted != null) when (msg.arg1) {
                            BANK_NUM -> if (banks != null && inserted is Bank) {
                                (inserted as Bank).id = msg.obj as Long
                                banks!!.add(inserted as Bank)
                                item1Created.add(false)
                                //savedIds.add(View.generateViewId())
                                bankAdapter.notifyItemInserted(banks!!.size - 1)
                                if (banks!!.size > 1) tRV1.smoothScrollToPosition(banks!!.size - 2)
                            }
                            CHEST_NUM -> if (chests != null) {
                                (inserted as Chest).id = msg.obj as Long
                                chests!!.add(inserted as Chest)
                                item2Created.add(false)
                                t2SavedIds.add(View.generateViewId())
                                chestAdapter.notifyItemInserted(chests!!.size - 1)
                                if (chests!!.size > 1) tRV2.smoothScrollToPosition(chests!!.size - 2)
                                val load = va(motor1, "translationX", Home.loadDur, 10f, 0f)
                                load.addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator) {
                                        try {
                                            T2Adapter.saved(
                                                findViewById(t2SavedIds[t2SavedIds.size - 1]),
                                                1440, 1000
                                            )
                                        } catch (ignored: Exception) {
                                        }
                                    }
                                })
                            }
                            CASH_NUM -> if (cashes != null) {
                                (inserted as Cash).id = msg.obj as Long
                                cashes!!.add(inserted as Cash)
                                item3Created.add(false)
                                t3SavedIds.add(View.generateViewId())
                                cashAdapter.notifyItemInserted(cashes!!.size - 1)
                                if (cashes!!.size > 1) tRV3.smoothScrollToPosition(cashes!!.size - 2)
                                val load = va(motor1, "translationX", Home.loadDur, 10f, 0f)
                                load.addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator) {
                                        try {
                                            T1Adapter.saved(
                                                findViewById(t3SavedIds[t3SavedIds.size - 1]),
                                                1440, 1000
                                            )
                                        } catch (ignored: Exception) {
                                        }
                                    }
                                })
                            }
                        }
                        inserted = null
                    }
                    Work.UPDATE_ONE -> when (msg.arg2) {
                        BANK_NUM -> try {
                            //T1Adapter.saved(findViewById(savedIds[msg.arg1]))
                        } catch (ignored: Exception) {
                        }
                        CHEST_NUM -> try {
                            T2Adapter.saved(findViewById(t2SavedIds[msg.arg1]))
                        } catch (ignored: Exception) {
                        }
                        CASH_NUM -> try {
                            T3Adapter.saved(findViewById(t3SavedIds[msg.arg1]))
                        } catch (ignored: Exception) {
                        }
                        3 -> T1Adapter.saved(t1BankNameSaved)
                        4 -> T1Adapter.saved(t1AccountNumberSaved)
                        5 -> T1Adapter.saved(t1InitialSaved)
                        6 -> T1Adapter.saved(t1InitialSaved)
                        7 -> {
                            if (banks != null) {
                                t1DateFoundedTV.text =
                                    PAdapter.compileTime(c, banks!![msg.arg1].dateFounded, true)
                                animateHint(
                                    banks!![msg.arg1].dateFounded != (-1).toLong(),
                                    t1DateFoundedTV, t1DateFoundedHint
                                )
                            }
                            T1Adapter.saved(t1DateFoundedSaved)
                        }
                        8 -> {
                        }
                        9 -> T1Adapter.saved(t1NotesSaved)
                    }
                    Work.DELETE_ONE -> when (msg.arg2) {
                        BANK_NUM -> if (banks != null && banks!!.size > msg.arg1) {
                            banks!!.remove(banks!![msg.arg1])
                            bankAdapter.notifyItemRemoved(msg.arg1)
                        }
                        CHEST_NUM -> if (chests != null && chests!!.size > msg.arg1) {
                            chests!!.remove(chests!![msg.arg1])
                            chestAdapter.notifyItemRemoved(msg.arg1)
                        }
                        CASH_NUM -> if (cashes != null && cashes!!.size > msg.arg1) {
                            cashes!!.remove(cashes!![msg.arg1])
                            cashAdapter.notifyItemRemoved(msg.arg1)
                        }
                    }
                }
            }
        }
        loadRoom()

        // ADD NEW
        tAddIV.setOnClickListener {
            if (curDiv < 0 || curDiv > 2 || inserting) return@setOnClickListener
            inserted = when (curDiv) {
                1 -> newChest(c, chests!!); 2 -> newCash(); else -> newBank(c, banks!!); }
            Work(
                c, handler, Work.INSERT_ONE, entityType(curDiv), listOf(inserted!!, curDiv)
            ).start()
            inserting = true
            object : CountDownTimer(Home.insertionDelay, Home.insertionDelay) {
                override fun onTick(p0: Long) {}
                override fun onFinish() {
                    inserting = false
                }
            }.start()
        }

        // BottomNavigationView
        tBNVIds = listOf(R.id.tBNVBanks, R.id.tBNVChests, R.id.tBNVCashes)
        tBNV.setOnNavigationItemSelectedListener {
            val which = tBNVIds.indexOf(it.itemId)
            if (which != -1) navigate(which)
            return@setOnNavigationItemSelectedListener which != -1
        }
        navigate(0)
        for (p in 1 until divisions.size) divisions[p].visibility = View.VISIBLE

        // OK Button (NOT "AddNew" Button)
        addNewButtonTV.typeface = anbFont
        addNewButtonBG.setOnClickListener { saveChanges() }

        // Editor Fields
        t1BankNameET.setOnFocusChangeListener { view, b ->
            if (t1BankNameET.text.toString() == "")
                animateHint(b, t1BankNameET, t1BankNameHint)
            if (!b) saveBankName()
        }
        t1AccountNumberET.setOnFocusChangeListener { view, b ->
            if (t1AccountNumberET.text.toString() == "")
                animateHint(b, t1AccountNumberET, t1AccountNumberHint)
            if (!b) saveAccountNumber()
        }
        t1InitialET.setOnFocusChangeListener { view, b ->
            if (t1InitialET.text.toString() == "")
                animateHint(b, t1InitialET, t1InitialHint)
            if (!b) saveInitial()
        }
        t1DateFoundedTV.setOnClickListener {
            if (banks == null) return@setOnClickListener
            if (editingIt == -1 || banks!!.size <= editingIt) return@setOnClickListener
            if (dirLtr) {
                val cal = Calendar.getInstance()
                cal.timeInMillis =
                    if (banks!![editingIt].dateFounded != (-1).toLong())
                        banks!![editingIt].dateFounded
                    else now()
                val dpd = GregorianDatePickerDialog.newInstance(
                    { view, year, monthOfYear, dayOfMonth ->
                        if (banks == null) return@newInstance
                        if (editingIt == -1 || banks!!.size <= editingIt) return@newInstance
                        val cul = Calendar.getInstance()
                        cul.set(year, monthOfYear, dayOfMonth)
                        banks!![editingIt].dateFounded = cul.timeInMillis
                        banks!![editingIt].dateModified = now()
                        Work(
                            c, handler, Work.UPDATE_ONE,
                            BANK, listOf(banks!![editingIt], editingIt, 7)
                        ).start()
                    }, cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DAY_OF_MONTH]
                )
                dpd.show(supportFragmentManager, "t1DateFoundedTV")
            } else {
                val cal = UmmalquraCalendar()// DON'T USE getInstance() method!
                cal.timeInMillis =
                    if (banks!![editingIt].dateFounded != (-1).toLong())
                        banks!![editingIt].dateFounded
                    else now()
                val dpd = HijriDatePickerDialog.newInstance(
                    { view, year, monthOfYear, dayOfMonth ->
                        if (banks == null) return@newInstance
                        if (editingIt == -1 || banks!!.size <= editingIt) return@newInstance
                        banks!![editingIt].dateFounded =
                            UmmalquraCalendar(year, monthOfYear, dayOfMonth).timeInMillis
                        banks!![editingIt].dateModified = now()
                        Work(
                            c, handler, Work.UPDATE_ONE,
                            BANK, listOf(banks!![editingIt], editingIt, 7)
                        ).start()
                    }, cal.get(UmmalquraCalendar.YEAR),
                    cal.get(UmmalquraCalendar.MONTH),
                    cal.get(UmmalquraCalendar.DAY_OF_MONTH)
                )
                dpd.show(supportFragmentManager, "t1DateFoundedTV")
            }
        }
        var curAdapter = ArrayAdapter(c, R.layout.spn_currency_1, currencyNames)
        curAdapter.setDropDownViewResource(R.layout.basic_spn_1_dd)
        t1InitialCur.adapter = curAdapter
        t1InitialCur.setOnTouchListener { view, motionEvent ->
            spnCur1Clicked = true
            return@setOnTouchListener false
        }
        t1NotesET.setOnFocusChangeListener { view, b ->
            if (t1NotesET.text.toString() == "") animateHint(b, t1BankNameET, t1NotesHint)
            if (!b) saveBankNotes()
        }

        // Notte Editor
        noteEditorBtn.setOnClickListener { saveNotes(c) }

        // Deleting Drawables
        adBankDrw = ContextCompat.getDrawable(c, R.drawable.card_icon_2)!!.mutate()
        adBankDrw.setColorFilter(
            ContextCompat.getColor(c, R.color.tADDrw), PorterDuff.Mode.SRC_IN
        )
        adChestDrw = ContextCompat.getDrawable(c, R.drawable.chest_icon_2)!!.mutate()
        adChestDrw.setColorFilter(
            ContextCompat.getColor(c, R.color.tADDrw), PorterDuff.Mode.SRC_IN
        )
        adCashDrw = ContextCompat.getDrawable(c, R.drawable.cash_icon_1)!!.mutate()
        adCashDrw.setColorFilter(
            ContextCompat.getColor(c, R.color.tADDrw), PorterDuff.Mode.SRC_IN
        )
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val anCon = va(motor1, "translationX", Home.loadDur, 10f, 0f)
        anCon.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                windowManager.defaultDisplay.getMetrics(dm)
                Home.updateNavigator(navigator, false, curDiv)
            }
        })
    }

    override fun onBackPressed() {
        if (showingEditor) saveChanges()
        else {
            if (showingNotes) saveNotes(c)
            else {
                saveThisList()
                super.onBackPressed()
                finish()
            }
        }
    }


    fun navigate(into: Int, changeBNV: Boolean = false) {
        var to = into
        if (to < 0) to = 0
        if (to > 2) to = 2
        saveThisList()
        var wScreen = dm.widthPixels.toFloat()
        if (!dirLtr) wScreen = 0f - wScreen
        if (anNavig != null) try {
            anNavig!!.cancel()
        } catch (ignored: Exception) {
        }
        anNavig = null
        anNavig = AnimatorSet().setDuration(Home.navigateDur)
        anNavig!!.playTogether(
            ObjectAnimator.ofFloat(
                divisions[0], "translationX", when (to) {
                    1 -> -wScreen; 2 -> -wScreen * 2f; else -> 0f; }
            ), ObjectAnimator.ofFloat(
                divisions[1], "translationX", when (to) {
                    1 -> 0f; 2 -> -wScreen; else -> wScreen; }
            ), ObjectAnimator.ofFloat(
                divisions[2], "translationX", when (to) {
                    1 -> wScreen; 2 -> 0f; else -> wScreen * 2f; }
            )
        )
        anNavig!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                curDiv = to
                anNavig = null
            }
        })
        anNavig!!.start()
        Home.updateNavigator(navigator, true, to)
        if (changeBNV) tBNV.selectedItemId = tBNVIds[to]
    }

    fun loadRoom() {
        Work(c, handler, Work.VIEW_ALL, BANK, listOf(BANK_NUM)).start()
        Work(c, handler, Work.VIEW_ALL, CHEST, listOf(CHEST_NUM)).start()
        Work(c, handler, Work.VIEW_ALL, CASH, listOf(CASH_NUM)).start()
    }

    fun arrangeList(list: List<Any>?, type: Int) {
        if (list == null) return
        when (type) {
            BANK_NUM -> {
                banks = list as ArrayList<Bank>
                item1Created.clear()
                for (m in banks!!) item1Created.add(false)
                Collections.sort(banks, Sort.SortBanks())
                bankAdapter = T1Adapter(c, banks!!, this)
                tRV1.adapter = bankAdapter
            }
            CHEST_NUM -> {
                chests = list as ArrayList<Chest>
                item2Created.clear()
                t2SavedIds.clear()
                for (m in chests!!) item2Created.add(false)
                for (m in chests!!) t2SavedIds.add(View.generateViewId())
                Collections.sort(chests, Sort.SortChests())
                chestAdapter = T2Adapter(c, chests!!, this)
                tRV2.adapter = chestAdapter
            }
            CASH_NUM -> {
                cashes = list as ArrayList<Cash>
                item3Created.clear()
                t3SavedIds.clear()
                for (m in cashes!!) item3Created.add(false)
                for (m in cashes!!) t3SavedIds.add(View.generateViewId())
                Collections.sort(cashes, Sort.SortCashes())
                cashAdapter = T3Adapter(c, cashes!!, this)
                tRV3.adapter = cashAdapter
            }
        }
    }

    fun saveChanges() {
        if (t1BankNameET.hasFocus()) saveBankName()
        if (t1AccountNumberET.hasFocus()) saveAccountNumber()
        if (t1InitialET.hasFocus()) saveInitial()
        if (t1NotesET.hasFocus()) saveBankNotes()
        showEditor(false)
        editingIt = -1
    }

    fun saveBankName() {
        if (banks == null) return
        if (editingIt == -1 || banks!!.size <= editingIt) return
        banks!![editingIt].name = t1BankNameET.text.toString()
        banks!![editingIt].dateModified = now()
        Work(
            c, handler, Work.UPDATE_ONE, BANK, listOf(banks!![editingIt], editingIt, 3)
        ).start()
    }

    fun saveAccountNumber() {
        if (banks == null) return
        if (editingIt == -1 || banks!!.size <= editingIt) return
        banks!![editingIt].accountNumber = t1AccountNumberET.text.toString()
        banks!![editingIt].dateModified = now()
        Work(
            c, handler, Work.UPDATE_ONE, BANK,
            listOf(banks!![editingIt], editingIt, 4)
        ).start()
    }

    fun saveInitial() {
        if (banks == null) return
        if (editingIt == -1 || banks!!.size <= editingIt) return
        if (t1InitialET.text.toString() == "") {
            t1InitialET.setText("0")
            return
        }
        try {
            banks!![editingIt].initial = (t1InitialET.text.toString()).toDouble()
        } catch (ignored: Exception) {
            return
        }
        banks!![editingIt].dateModified = now()
        Work(
            c, handler, Work.UPDATE_ONE, BANK, listOf(banks!![editingIt], editingIt, 5)
        ).start()
    }

    fun saveBankNotes() {
        if (banks == null) return
        if (editingIt == -1 || banks!!.size <= editingIt) return
        banks!![editingIt].notes = t1NotesET.text.toString()
        banks!![editingIt].dateModified = now()
        Work(
            c, handler, Work.UPDATE_ONE, BANK, listOf(banks!![editingIt], editingIt, 9)
        ).start()
    }

    fun saveThisList(which: Int = curDiv) {
        when (which) {
            0 -> for (f in 0 until tRV1.childCount) {
                val i = tRV1.getChildAt(f) as ConstraintLayout
                val cv = i.getChildAt(T1Adapter.cvPos) as ComplexView
                val body = cv.getChildAt(T1Adapter.bodyPos) as ConstraintLayout
                val numbers = body.getChildAt(T1Adapter.numbersPos) as LinearLayout
                val number1 = numbers.getChildAt(T1Adapter.number1Pos) as EditText
                val number2 = numbers.getChildAt(T1Adapter.number2Pos) as EditText
                val number3 = numbers.getChildAt(T1Adapter.number3Pos) as EditText
                val number4 = numbers.getChildAt(T1Adapter.number4Pos) as EditText
                val expLL = body.getChildAt(T1Adapter.expLLPos) as LinearLayout
                val expYear = expLL.getChildAt(T1Adapter.expYearPos) as EditText
                val expMonth = expLL.getChildAt(T1Adapter.expMonthPos) as EditText

                val pos = tRV1.getChildLayoutPosition(i)
                if (number1.hasFocus() || number2.hasFocus() || number3.hasFocus() || number4.hasFocus())
                    T1Adapter.saveNumberETs(c, listOf(number1, number2, number3, number4), pos)
                if (expYear.hasFocus()) T1Adapter.saveExpYear(c, expYear, pos)
                if (expMonth.hasFocus()) T1Adapter.saveExpMonth(c, expMonth, pos)
            }
            1 -> for (f in 0 until tRV2.childCount) {
                val i = tRV2.getChildAt(f) as ConstraintLayout
                val field1ET =
                    ((i.getChildAt(T2Adapter.field1Pos) as ComplexView).getChildAt(T2Adapter.fieldCLPos) as ConstraintLayout
                            ).getChildAt(T2Adapter.fieldETPos) as EditText
                val field2ET =
                    ((i.getChildAt(T2Adapter.field2Pos) as ComplexView).getChildAt(T2Adapter.fieldCLPos) as ConstraintLayout
                            ).getChildAt(T2Adapter.fieldETPos) as EditText
                val field3ET =
                    ((i.getChildAt(T2Adapter.field3Pos) as ComplexView).getChildAt(T2Adapter.fieldCLPos) as ConstraintLayout
                            ).getChildAt(T2Adapter.fieldETPos) as EditText
                val pos = tRV2.getChildLayoutPosition(i)
                if (field1ET.hasFocus()) T2Adapter.saveField1ET(c, field1ET, pos)
                if (field2ET.hasFocus()) T2Adapter.saveField2ET(c, field2ET, pos)
                if (field3ET.hasFocus()) T2Adapter.saveField3ET(c, field3ET, pos)
            }
            2 -> for (f in 0 until tRV3.childCount) {
                val i = tRV3.getChildAt(f) as ConstraintLayout
                val field1ET =
                    ((i.getChildAt(T3Adapter.field1Pos) as ComplexView).getChildAt(T3Adapter.fieldCLPos) as ConstraintLayout
                            ).getChildAt(T3Adapter.fieldETPos) as EditText

                val pos = tRV3.getChildLayoutPosition(i)
                if (field1ET.hasFocus()) T3Adapter.saveField1ET(c, field1ET, pos)
            }
        }
    }

    var x1 = 0f
    var x2 = 0f
    var y1 = 0f
    var y2 = 0f
    fun swipe(event: MotionEvent) {
        if (event.pointerCount != 2) when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x
                y1 = event.y
            }
            MotionEvent.ACTION_UP -> {
                x2 = event.x
                y2 = event.y
                if (abs(x2 - x1) > dp(50) && abs(y2 - y1) < dp(40)) navigate(
                    if (x2 > x1) (if (dirLtr) curDiv - 1 else curDiv + 1)
                    else (if (dirLtr) curDiv + 1 else curDiv - 1), true
                )
            }
        }
    }
}
