package uk.easys.calculations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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
import uk.easys.calculations.Home.Companion.dirLtr
import uk.easys.calculations.Home.Companion.now
import uk.easys.calculations.adapters.SAdapter
import uk.easys.calculations.room.Entities.SUBJECT
import uk.easys.calculations.room.Entities.Subject
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.util.*
import kotlin.collections.ArrayList
import uk.easys.calculations.AnimDepot.Companion.noteEditor

class Subjects : AppCompatActivity() {
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
    lateinit var sHeadTV1: TextView
    lateinit var sHeadPointer1: ImageView
    lateinit var sHeadTV2: TextView
    lateinit var sHeadPointer2: ImageView
    lateinit var sHeadTV3: TextView
    lateinit var basicRV: RecyclerView
    lateinit var addNewButtonBG: View
    lateinit var addNewButtonTV: TextView
    lateinit var addNewButtonFG: View
    lateinit var sFAB1: ComplexView
    lateinit var sFAB2: ComplexView
    lateinit var sFAB3: ComplexView
    lateinit var sFAB4: ComplexView

    lateinit var titleFont: Typeface
    lateinit var anbFont: Typeface
    lateinit var sp: SharedPreferences
    lateinit var sHeadPointers: List<ImageView>

    var sortBy = 1
    var descending = false
    var inserting = false
    var inserted: Subject? = null

    companion object {
        lateinit var addNewButton: ComplexView
        lateinit var cover: View
        lateinit var noteEditor: ConstraintLayout
        lateinit var noteEditorTitle: TextView
        lateinit var noteEditorET: EditText
        lateinit var noteEditorBtn: ImageView

        lateinit var c: Context
        lateinit var handler: Handler
        lateinit var sFABs: List<ComplexView>
        lateinit var basicAdapter: SAdapter

        var dm = DisplayMetrics()
        var rvlManager: LinearLayoutManager? = null
        var subjects: ArrayList<Subject>? = null
        val itemCreated = ArrayList<Boolean>()
        val itemMarked = ArrayList<Boolean>()
        val savedIds = ArrayList<Int>()
        var marking = false
        var deletingMass = false
        var editingItsNote = -1
        var sSortBy = "sSortBy"
        var sDescending = "sDescending"
        var saveOnBlur = true

        fun dp(px: Int): Int = (px * dm.density).toInt()

        fun marking(b: Boolean) {
            if (marking == b) return
            marking = b
            markAll(false)
            for (m in 0 until itemMarked.size) itemMarked[m] = false
            if (rvlManager != null) for (i in 0 until rvlManager!!.childCount) {
                var cl = rvlManager!!.getChildAt(i) as ConstraintLayout
                var cover = cl.getChildAt(SAdapter.coverPos) as View
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
            if (::sFABs.isInitialized) for (f in 0 until sFABs.size) {
                val transhideY = dm.density * 444f
                if (b) {
                    sFABs[f].translationY = transhideY
                    sFABs[f].visibility = View.VISIBLE
                }
                var show = oa(
                    sFABs[f], "translationY", showHideDur,
                    if (b) 0f else transhideY, (f * 65).toLong()
                )
                if (!b) show.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        sFABs[f].visibility = View.GONE
                    }
                })
            }
        }

        fun markAll(b: Boolean) {
            for (m in 0 until itemMarked.size) itemMarked[m] = b
            if (rvlManager != null) for (i in 0 until rvlManager!!.childCount) {
                var cl = rvlManager!!.getChildAt(i) as ConstraintLayout
                var marker = cl.getChildAt(SAdapter.markerPos) as View
                marker.visibility = if (b) View.VISIBLE else View.GONE
            }
        }

        fun cover(b: Boolean, dur: Long = 222) {
            if (b) cover.visibility = View.VISIBLE
            var alpha = oa(cover, "alpha", dur, if (b) 1f else 0f)
            if (!b) alpha.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    cover.visibility = View.GONE
                }
            })
        }

        fun notes(c: Context, i: Int, notes: String?) {
            if (subjects == null) return
            noteEditor(c, noteEditor, true, cover, View.OnClickListener { saveNotes(c) })
            noteEditorET.setText(notes ?: "")
            editingItsNote = i
        }

        fun saveNotes(c: Context) {
            noteEditor(c, noteEditor, false, cover)
            if (subjects == null) return
            if (editingItsNote == -1 || subjects!!.size <= editingItsNote) return
            if (subjects!![editingItsNote].notes == noteEditorET.text.toString()) return
            subjects!![editingItsNote].notes = noteEditorET.text.toString()
            subjects!![editingItsNote].dateModified = now()
            Work(
                c, handler, Work.UPDATE_ONE, SUBJECT,
                listOf(subjects!![editingItsNote], editingItsNote)
            ).start()
            editingItsNote = -1
        }

        fun newSubject(c: Context, subjects: List<Subject>): Subject {
            val new = c.resources.getString(R.string.sAddNew)
            var n = 1
            for (s in subjects) if (s.name.length > new.length)
                if (s.name.substring(0, new.length) == new) n += 1
            return Subject(new + n, 0, false, "", now(), now())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subjects)

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
        sHeadTV1 = findViewById(R.id.sHeadTV1)
        sHeadPointer1 = findViewById(R.id.sHeadPointer1)
        sHeadTV2 = findViewById(R.id.sHeadTV2)
        sHeadPointer2 = findViewById(R.id.sHeadPointer2)
        sHeadTV3 = findViewById(R.id.sHeadTV3)
        basicRV = findViewById(R.id.basicRV)
        addNewButton = findViewById(R.id.addNewButton)
        addNewButtonBG = findViewById(R.id.addNewButtonBG)
        addNewButtonTV = findViewById(R.id.addNewButtonTV)
        addNewButtonFG = findViewById(R.id.addNewButtonFG)
        sFAB1 = findViewById(R.id.sFAB1)
        sFAB2 = findViewById(R.id.sFAB2)
        sFAB3 = findViewById(R.id.sFAB3)
        sFAB4 = findViewById(R.id.sFAB4)
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
        sFABs = listOf(sFAB1, sFAB2, sFAB3, sFAB4)
        sp = PreferenceManager.getDefaultSharedPreferences(c)
        sHeadPointers = listOf(sHeadPointer1, sHeadPointer2)


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
                        subjects = msg.obj as ArrayList<Subject>
                        arrangeLists()
                    }
                    Work.VIEW_ONE -> when (msg.arg1) {
                        //Work.ADD_NEW_ITEM -> { }
                    }
                    Work.INSERT_ONE -> {
                        /*Work(
                            c, handler, Work.VIEW_ONE, SUBJECT,
                            listOf(msg.obj as Long, Work.ADD_NEW_ITEM)
                        ).start()*/
                        inserting = false
                        (inserted as Subject).id = msg.obj as Long
                        subjects!!.add(inserted as Subject)
                        itemCreated.add(false)
                        itemMarked.add(false)
                        savedIds.add(View.generateViewId())
                        basicAdapter.notifyItemInserted(subjects!!.size - 1)
                        if (subjects!!.size > 1) basicRV.smoothScrollToPosition(subjects!!.size - 2)

                        val load = va(motor1, "translationX", Home.loadDur, 10f, 0f)
                        load.addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                try {
                                    SAdapter.saved(
                                        findViewById(savedIds[savedIds.size - 1]), 1440, 1000
                                    )
                                } catch (ignored: Exception) {
                                }
                            }
                        })
                        inserted = null
                    }
                    Work.UPDATE_ONE -> try {
                        SAdapter.saved(findViewById(savedIds[msg.arg1]))
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
        sHeadTV1.setOnClickListener {
            lastSave()
            saveOnBlur = false
            howToSort(1, !descending)
            arrangeLists()
        }
        sHeadTV2.setOnClickListener {
            lastSave()
            saveOnBlur = false
            howToSort(2, !descending)
            arrangeLists()
        }
        sFAB1.setOnClickListener {
            if (subjects == null || deletingMass) return@setOnClickListener
            var deleteMass = ArrayList<Subject>()
            for (i in 0 until itemMarked.size) if (itemMarked[i]) deleteMass.add(subjects!![i])
            if (deleteMass.isNotEmpty()) {
                if (deleteMass.size == 1) deleteMass(deleteMass)
                else {
                    var ask = AlertDialog.Builder(this).apply {
                        setIcon(R.drawable.subjects_1)
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
        sFAB2.setOnClickListener { if (marking && !deletingMass) markAll(true) }
        sFAB3.setOnClickListener { if (marking && !deletingMass) markAll(false) }
        sFAB4.setOnClickListener { if (!deletingMass) marking(false) }
        noteEditorBtn.setOnClickListener { saveNotes(c) }
        for (p in sHeadPointers) p.colorFilter =
            PorterDuffColorFilter(
                ContextCompat.getColor(c, R.color.basicRVHeadPointer),
                PorterDuff.Mode.SRC_IN
            )
        Home.sortPointers(sHeadPointers, sortBy - 1, descending)

        // Add New Item
        addNewButtonTV.typeface = anbFont
        addNewButtonBG.setOnClickListener {
            if (subjects == null) return@setOnClickListener
            inserted = newSubject(c, subjects!!)
            Work(c, handler, Work.INSERT_ONE, SUBJECT, listOf(inserted!!)).start()
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
        Work(c, handler, Work.VIEW_ALL, SUBJECT).start()
    }

    fun arrangeLists() {
        itemCreated.clear()
        itemMarked.clear()
        savedIds.clear()
        for (m in subjects!!) itemCreated.add(false)
        for (m in subjects!!) itemMarked.add(false)
        for (m in subjects!!) savedIds.add(View.generateViewId())
        Collections.sort(subjects, Sort.SortSubjects(0))
        Collections.sort(subjects, Sort.SortSubjects(sortBy))
        if (descending) subjects!!.reverse()
        basicAdapter = SAdapter(c, subjects!!)
        basicRV.adapter = basicAdapter
    }

    fun deleteMass(deleteMass: ArrayList<Subject>) {
        deletingMass = true
        Work(c, handler, Work.DELETE_MASS, SUBJECT, listOf(deleteMass)).start()
    }

    fun howToSort(by: Int, des: Boolean) {
        sortBy = by
        descending = des
        var editor = sp.edit()
        editor.putInt(sSortBy, sortBy)
        editor.putBoolean(sDescending, descending)
        editor.apply()
        Home.sortPointers(sHeadPointers, sortBy - 1, descending)
    }

    fun lastSave() {
        for (f in 0 until basicRV.childCount) {
            var i = basicRV.getChildAt(f) as ViewGroup
            var et =
                (i.getChildAt(SAdapter.llPos) as ViewGroup).getChildAt(SAdapter.etPos) as EditText
            if (et.hasFocus()) SAdapter.saveET(c, et, basicRV.getChildLayoutPosition(i))
        }
    }
}
