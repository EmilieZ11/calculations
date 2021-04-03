package uk.easys.calculations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.LayoutTransition
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.preference.PreferenceManager
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blure.complexview.ComplexView
import uk.easys.calculations.AnimDepot.Companion.isVis
import uk.easys.calculations.AnimDepot.Companion.oa
import uk.easys.calculations.AnimDepot.Companion.va
import uk.easys.calculations.AnimDepot.Companion.noteEditor
import uk.easys.calculations.Home.Companion.dirLtr
import uk.easys.calculations.adapters.CAdapter
import uk.easys.calculations.room.Entities.*
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.util.*
import kotlin.collections.ArrayList

class Contacts : AppCompatActivity() {
    lateinit var body: ConstraintLayout
    lateinit var motor1: View
    lateinit var header: ImageView
    lateinit var headerTV: TextView
    lateinit var headerTVSub: TextView
    lateinit var circleUserShadow: ImageView
    lateinit var circleUserBG: ImageView
    lateinit var circleUser: ImageView
    lateinit var basicRVCL: ConstraintLayout
    lateinit var basicRVHead: ConstraintLayout
    lateinit var cHeadTV1: TextView
    lateinit var cHeadPointer1: ImageView
    lateinit var cHeadTV2: TextView
    lateinit var cHeadPointer2: ImageView
    lateinit var cHeadTV3: TextView
    lateinit var basicRV: RecyclerView
    lateinit var addNewButtonBG: View
    lateinit var addNewButtonTV: TextView
    lateinit var addNewButtonFG: View
    lateinit var cFAB1: ComplexView
    lateinit var cFAB2: ComplexView
    lateinit var cFAB3: ComplexView
    lateinit var cFAB4: ComplexView

    lateinit var c: Context
    lateinit var titleFont: Typeface
    lateinit var anbFont: Typeface
    lateinit var sp: SharedPreferences
    lateinit var cHeadPointers: List<ImageView>

    var sortBy = 1
    var descending = false
    var inserting = false
    var inserted: Contact? = null

    companion object {
        lateinit var addNewButton: ComplexView
        lateinit var cover: View
        lateinit var noteEditor: ConstraintLayout
        lateinit var noteEditorTitle: TextView
        lateinit var noteEditorET: EditText
        lateinit var noteEditorBtn: ImageView

        lateinit var handler: Handler
        lateinit var cFABs: List<ComplexView>
        lateinit var basicAdapter: CAdapter

        var dm = DisplayMetrics()
        var rvlManager: LinearLayoutManager? = null
        var contacts: ArrayList<Contact>? = null
        val itemCreated = ArrayList<Boolean>()
        val itemMarked = ArrayList<Boolean>()
        val savedIds = ArrayList<Int>()
        var marking = false
        var deletingMass = false
        var editingItsNote = -1
        var cSortBy = "cSortBy"
        var cDescending = "cDescending"
        var saveOnBlur = true

        fun dp(px: Int): Int = (px * dm.density).toInt()

        fun marking(b: Boolean) {
            if (marking == b) return
            marking = b
            markAll(false)
            for (m in 0 until itemMarked.size) itemMarked[m] = false
            if (rvlManager != null) for (i in 0 until rvlManager!!.childCount) {
                var cl = rvlManager!!.getChildAt(i) as ConstraintLayout
                var cover = cl.getChildAt(CAdapter.coverPos) as View
                cover.visibility = if (b) View.VISIBLE else View.GONE
                cover.isClickable = b
            }

            val showHideDur: Long = 444
            val transhideX = dm.widthPixels.toFloat()
            if (!b) addNewButton.visibility = View.VISIBLE
            var hide = oa(addNewButton, "translationX", showHideDur, if (b) transhideX else 0f)
            if (b) hide.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    addNewButton.visibility = View.GONE
                }
            })
            if (::cFABs.isInitialized) for (f in 0 until cFABs.size) {
                val transhideY = dm.density * 444f
                if (b) {
                    cFABs[f].translationY = transhideY
                    cFABs[f].visibility = View.VISIBLE
                }
                var show = oa(
                    cFABs[f], "translationY", showHideDur,
                    if (b) 0f else transhideY, (f * 65).toLong()
                )
                if (!b) show.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        cFABs[f].visibility = View.GONE
                    }
                })
            }
        }

        fun markAll(b: Boolean) {
            for (m in 0 until itemMarked.size) itemMarked[m] = b
            if (rvlManager != null) for (i in 0 until rvlManager!!.childCount) {
                var cl = rvlManager!!.getChildAt(i) as ConstraintLayout
                var marker = cl.getChildAt(CAdapter.markerPos) as View
                marker.visibility = if (b) View.VISIBLE else View.GONE
            }
        }

        fun notes(c: Context, i: Int, notes: String?) {
            if (contacts == null) return
            noteEditor(c, noteEditor, true, cover, View.OnClickListener { saveNotes(c) })
            noteEditorET.setText(notes ?: "")
            editingItsNote = i
        }

        fun saveNotes(c: Context) {
            noteEditor(c, noteEditor, false, cover)
            if (contacts == null) return
            if (editingItsNote == -1 || contacts!!.size <= editingItsNote) return
            if (contacts!![editingItsNote].notes == noteEditorET.text.toString()) return
            contacts!![editingItsNote].notes = noteEditorET.text.toString()
            contacts!![editingItsNote].dateModified = Home.now()
            Work(
                c, handler, Work.UPDATE_ONE, CONTACT,
                listOf(contacts!![editingItsNote], editingItsNote)
            ).start()
            editingItsNote = -1
        }

        fun newContact(c: Context, contacts: List<Contact>): Contact {
            val new = c.resources.getString(R.string.cAddNew)
            var n = 1
            for (s in contacts) if (s.firstName.length > new.length)
                if (s.firstName.substring(0, new.length) == new) n += 1
            return Contact(
                new + n, "", "", "",
                "", "", "", Home.now(), Home.now()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contacts)

        body = findViewById(R.id.body)
        motor1 = findViewById(R.id.motor1)
        header = findViewById(R.id.header)
        headerTV = findViewById(R.id.headerTV)
        headerTVSub = findViewById(R.id.headerTVSub)
        circleUserShadow = findViewById(R.id.circleUserShadow)
        circleUserBG = findViewById(R.id.circleUserBG)
        circleUser = findViewById(R.id.circleUser)
        basicRVCL = findViewById(R.id.basicRVCL)
        basicRVHead = findViewById(R.id.basicRVHead)
        cHeadTV1 = findViewById(R.id.cHeadTV1)
        cHeadPointer1 = findViewById(R.id.cHeadPointer1)
        cHeadTV2 = findViewById(R.id.cHeadTV2)
        cHeadPointer2 = findViewById(R.id.cHeadPointer2)
        cHeadTV3 = findViewById(R.id.cHeadTV3)
        basicRV = findViewById(R.id.basicRV)
        addNewButton = findViewById(R.id.addNewButton)
        addNewButtonBG = findViewById(R.id.addNewButtonBG)
        addNewButtonTV = findViewById(R.id.addNewButtonTV)
        addNewButtonFG = findViewById(R.id.addNewButtonFG)
        cFAB1 = findViewById(R.id.cFAB1)
        cFAB2 = findViewById(R.id.cFAB2)
        cFAB3 = findViewById(R.id.cFAB3)
        cFAB4 = findViewById(R.id.cFAB4)
        cover = findViewById(R.id.cover)
        noteEditor = findViewById(R.id.noteEditor)
        noteEditorTitle = findViewById(R.id.noteEditorTitle)
        noteEditorET = findViewById(R.id.noteEditorET)
        noteEditorBtn = findViewById(R.id.noteEditorBtn)

        c = applicationContext
        windowManager.defaultDisplay.getMetrics(dm)
        if (!dirLtr) body.layoutDirection = View.LAYOUT_DIRECTION_RTL
        titleFont = Typeface.createFromAsset(assets, resources.getString(R.string.titleFont))
        anbFont = Typeface.createFromAsset(assets, resources.getString(R.string.anbFont))
        cFABs = listOf(cFAB1, cFAB2, cFAB3, cFAB4)
        sp = PreferenceManager.getDefaultSharedPreferences(c)
        cHeadPointers = listOf(cHeadPointer1, cHeadPointer2)


        // Loading
        if (!dirLtr) {
            header.rotationY = 180f
            headerTVSub.translationX = 0f - headerTVSub.translationX
            headerTVSub.translationY = dm.density * -27f
        }
        val anLoad1 = va(motor1, "translationX", Home.loadDur, 10f, 0f)
        anLoad1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                headerTVSub.visibility = View.VISIBLE
                oa(headerTVSub, "translationX", 555, 0f)
                oa(headerTV, "translationY", 444, dm.density * -10, 111)
            }
        })

        // Header
        headerTV.typeface = titleFont
        headerTVSub.typeface = anbFont
        circleUser.setOnClickListener { onBackPressed() }
        Home.glideAvatar(this, circleUserBG)

        // List
        basicRV.setHasFixedSize(false)
        rvlManager = LinearLayoutManager(c)
        basicRV.layoutManager = rvlManager
        handler = object : Handler() {
            override fun handleMessage(msg: Message?) {
                if (msg == null) return
                when (msg.what) {
                    Work.VIEW_ALL -> {
                        contacts = msg.obj as ArrayList<Contact>
                        arrangeLists()
                    }
                    Work.VIEW_ONE -> when (msg.arg1) {
                        //Work.ADD_NEW_ITEM -> { }
                    }
                    Work.INSERT_ONE -> if (inserted != null) {
                        /*Work(
                            c, handler, Work.VIEW_ONE, CONTACT,
                            listOf(msg.obj as Long, Work.ADD_NEW_ITEM)
                        ).start()*/
                        inserting = false
                        (inserted as Contact).id = msg.obj as Long
                        contacts!!.add(inserted as Contact)
                        itemCreated.add(false)
                        itemMarked.add(false)
                        savedIds.add(View.generateViewId())
                        basicAdapter.notifyItemInserted(contacts!!.size - 1)
                        if (contacts!!.size > 1) basicRV.smoothScrollToPosition(contacts!!.size - 2)

                        val load = va(motor1, "translationX", Home.loadDur, 10f, 0f)
                        load.addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                try {
                                    CAdapter.saved(
                                        findViewById(savedIds[savedIds.size - 1]), 1440, 1000
                                    )
                                } catch (ignored: Exception) {
                                }
                            }
                        })
                        inserted = null
                    }
                    Work.UPDATE_ONE -> try {
                        CAdapter.saved(findViewById(savedIds[msg.arg1]))
                    } catch (ignored: Exception) {
                    }
                    Work.DELETE_MASS -> {
                        deletingMass = false
                        marking(false)
                        loadRoom()
                    }
                }
            }
        }
        loadRoom()
        cHeadTV1.setOnClickListener {
            lastSave()
            saveOnBlur = false
            howToSort(1, !descending)
            arrangeLists()
        }
        cHeadTV2.setOnClickListener {
            lastSave()
            saveOnBlur = false
            howToSort(2, !descending)
            arrangeLists()
        }
        cFAB1.setOnClickListener {
            if (contacts == null || deletingMass) return@setOnClickListener
            var deleteMass = ArrayList<Contact>()
            for (i in 0 until itemMarked.size) if (itemMarked[i]) deleteMass.add(contacts!![i])
            if (deleteMass.isNotEmpty()) {
                if (deleteMass.size == 1) deleteMass(deleteMass)
                else {
                    var ask = AlertDialog.Builder(this).apply {
                        setIcon(R.drawable.contact_1)
                        setTitle(R.string.deleteMass)
                        setMessage(R.string.deleteMassMsg)
                        setPositiveButton(R.string.yes) { _, _ ->
                            deleteMass(deleteMass)
                        }
                        setNegativeButton(R.string.no) { _, _ -> }
                    }
                    ask.create().show()
                }
            }
        }
        cFAB2.setOnClickListener { if (marking && !deletingMass) markAll(true) }
        cFAB3.setOnClickListener { if (marking && !deletingMass) markAll(false) }
        cFAB4.setOnClickListener { if (!deletingMass) marking(false) }
        noteEditorBtn.setOnClickListener { saveNotes(c) }
        for (p in cHeadPointers) p.colorFilter =
            PorterDuffColorFilter(
                ContextCompat.getColor(c, R.color.basicRVHeadPointer),
                PorterDuff.Mode.SRC_IN
            )
        Home.sortPointers(cHeadPointers, sortBy - 1, descending)

        // Add New Item
        addNewButtonTV.typeface = anbFont
        addNewButtonBG.setOnClickListener {
            if (contacts == null) return@setOnClickListener
            inserted = newContact(c, contacts!!)
            Work(c, handler, Work.INSERT_ONE, CONTACT, listOf(inserted!!)).start()
            inserting = true
            object : CountDownTimer(Home.insertionDelay, Home.insertionDelay) {
                override fun onTick(p0: Long) {}
                override fun onFinish() {
                    inserting = false
                }
            }.start()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val anCon = va(motor1, "translationX", Home.loadDur, 10f, 0f)
        anCon.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                windowManager.defaultDisplay.getMetrics(dm)
            }
        })
    }

    override fun onBackPressed() {
        if (isVis(noteEditor)) saveNotes(c)
        else {
            lastSave(); super.onBackPressed(); finish(); }
    }


    fun loadRoom() {
        Work(c, handler, Work.VIEW_ALL, CONTACT).start()
    }

    fun arrangeLists() {
        itemCreated.clear()
        itemMarked.clear()
        savedIds.clear()
        for (m in contacts!!) itemCreated.add(false)
        for (m in contacts!!) itemMarked.add(false)
        for (m in contacts!!) savedIds.add(View.generateViewId())
        Collections.sort(contacts, Sort.SortContacts(0))
        Collections.sort(contacts, Sort.SortContacts(sortBy))
        if (descending) contacts!!.reverse()
        basicAdapter = CAdapter(c, contacts!!)
        basicRV.adapter = basicAdapter
    }

    fun deleteMass(deleteMass: ArrayList<Contact>) {
        deletingMass = true
        Work(c, handler, Work.DELETE_MASS, CONTACT, listOf(deleteMass)).start()
    }

    fun howToSort(by: Int, des: Boolean) {
        sortBy = by
        descending = des
        var editor = sp.edit()
        editor.putInt(cSortBy, sortBy)
        editor.putBoolean(cDescending, descending)
        editor.apply()
        Home.sortPointers(cHeadPointers, sortBy - 1, descending)
    }

    fun lastSave() {
        for (f in 0 until basicRV.childCount) {
            var i = basicRV.getChildAt(f) as ViewGroup
            var ll = i.getChildAt(CAdapter.llPos) as ViewGroup
            var et1 = ll.getChildAt(CAdapter.et1Pos) as EditText
            var et2 = ll.getChildAt(CAdapter.et2Pos) as EditText
            if (et1.hasFocus() || et2.hasFocus()) {
                val pos = basicRV.getChildLayoutPosition(i)
                if (et1.hasFocus()) CAdapter.saveET1(c, et1, pos)
                if (et2.hasFocus()) CAdapter.saveET2(c, et2, pos)
            }
        }
    }
}
