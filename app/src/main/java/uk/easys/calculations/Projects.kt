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
import uk.easys.calculations.AnimDepot.Companion.oa
import uk.easys.calculations.Home.Companion.dirLtr
import uk.easys.calculations.adapters.PAdapter
import uk.easys.calculations.room.Entities.PROJECT
import uk.easys.calculations.room.Entities.Project
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.util.*
import kotlin.collections.ArrayList
import uk.easys.calculations.AnimDepot.Companion.isVis
import uk.easys.calculations.AnimDepot.Companion.noteEditor
import uk.easys.calculations.AnimDepot.Companion.va

class Projects : AppCompatActivity() {
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
    lateinit var pHeadTV1: TextView
    lateinit var pHeadPointer1: ImageView
    lateinit var pHeadTV2: TextView
    lateinit var pHeadPointer2: ImageView
    lateinit var pHeadTV3: TextView
    lateinit var pHeadPointer3: ImageView
    lateinit var pHeadTV4: TextView
    lateinit var basicRV: RecyclerView
    lateinit var addNewButtonBG: View
    lateinit var addNewButtonTV: TextView
    lateinit var addNewButtonFG: View
    lateinit var pFAB1: ComplexView
    lateinit var pFAB2: ComplexView
    lateinit var pFAB3: ComplexView
    lateinit var pFAB4: ComplexView

    lateinit var c: Context
    lateinit var titleFont: Typeface
    lateinit var anbFont: Typeface
    lateinit var sp: SharedPreferences
    lateinit var pHeadPointers: List<ImageView>

    var sortBy = 1
    var descending = false
    var inserting = false
    var inserted: Project? = null

    companion object {
        lateinit var addNewButton: ComplexView
        lateinit var cover: View
        lateinit var noteEditor: ConstraintLayout
        lateinit var noteEditorTitle: TextView
        lateinit var noteEditorET: EditText
        lateinit var noteEditorBtn: ImageView

        lateinit var handler: Handler
        lateinit var pFABs: List<ComplexView>
        lateinit var basicAdapter: PAdapter

        var dm = DisplayMetrics()
        var rvlManager: LinearLayoutManager? = null
        var projects: ArrayList<Project>? = null
        val itemCreated = ArrayList<Boolean>()
        val itemMarked = ArrayList<Boolean>()
        val savedIds = ArrayList<Int>()
        var marking = false
        var deletingMass = false
        var editingItsNote = -1
        var pSortBy = "pSortBy"
        var pDescending = "pDescending"
        var saveOnBlur = true

        fun dp(px: Int): Int = (px * dm.density).toInt()

        fun marking(b: Boolean) {
            if (marking == b) return
            marking = b
            markAll(false)
            for (m in 0 until itemMarked.size) itemMarked[m] = false
            if (rvlManager != null) for (i in 0 until rvlManager!!.childCount) {
                var cl = rvlManager!!.getChildAt(i) as ConstraintLayout
                var cover = cl.getChildAt(PAdapter.coverPos) as View
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
            if (::pFABs.isInitialized) for (f in 0 until pFABs.size) {
                val transhideY = dm.density * 444f
                if (b) {
                    pFABs[f].translationY = transhideY
                    pFABs[f].visibility = View.VISIBLE
                }
                var show = oa(
                    pFABs[f], "translationY", showHideDur,
                    if (b) 0f else transhideY, (f * 65).toLong()
                )
                if (!b) show.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        pFABs[f].visibility = View.GONE
                    }
                })
            }
        }

        fun markAll(b: Boolean) {
            for (m in 0 until itemMarked.size) itemMarked[m] = b
            if (rvlManager != null) for (i in 0 until rvlManager!!.childCount) {
                var cl = rvlManager!!.getChildAt(i) as ConstraintLayout
                var marker = cl.getChildAt(PAdapter.markerPos) as View
                marker.visibility = if (b) View.VISIBLE else View.GONE
            }
        }

        fun notes(c: Context, i: Int, notes: String?) {
            if (projects == null) return
            noteEditor(c, noteEditor, true, cover, View.OnClickListener { saveNotes(c) })
            noteEditorET.setText(notes ?: "")
            editingItsNote = i
        }

        fun saveNotes(c: Context) {
            noteEditor(c, noteEditor, false, cover)
            if (projects == null) return
            if (editingItsNote == -1 || projects!!.size <= editingItsNote) return
            if (projects!![editingItsNote].notes == noteEditorET.text.toString()) return
            projects!![editingItsNote].notes = noteEditorET.text.toString()
            projects!![editingItsNote].dateModified = Home.now()
            Work(
                c, handler, Work.UPDATE_ONE, PROJECT,
                listOf(projects!![editingItsNote], editingItsNote)
            ).start()
            editingItsNote = -1
        }

        fun newProject(c: Context, projects: List<Project>): Project {
            val new = c.resources.getString(R.string.pAddNew)
            var n = 1
            for (s in projects) if (s.name.length > new.length)
                if (s.name.substring(0, new.length) == new) n += 1
            return Project(new + n, Home.now(), Home.now(), -1, "", Home.now(), Home.now())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.projects)

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
        pHeadTV1 = findViewById(R.id.pHeadTV1)
        pHeadPointer1 = findViewById(R.id.pHeadPointer1)
        pHeadTV2 = findViewById(R.id.pHeadTV2)
        pHeadPointer2 = findViewById(R.id.pHeadPointer2)
        pHeadTV3 = findViewById(R.id.pHeadTV3)
        pHeadPointer3 = findViewById(R.id.pHeadPointer3)
        pHeadTV4 = findViewById(R.id.pHeadTV4)
        basicRV = findViewById(R.id.basicRV)
        addNewButton = findViewById(R.id.addNewButton)
        addNewButtonBG = findViewById(R.id.addNewButtonBG)
        addNewButtonTV = findViewById(R.id.addNewButtonTV)
        addNewButtonFG = findViewById(R.id.addNewButtonFG)
        pFAB1 = findViewById(R.id.pFAB1)
        pFAB2 = findViewById(R.id.pFAB2)
        pFAB3 = findViewById(R.id.pFAB3)
        pFAB4 = findViewById(R.id.pFAB4)
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
        pFABs = listOf(pFAB1, pFAB2, pFAB3, pFAB4)
        sp = PreferenceManager.getDefaultSharedPreferences(c)
        pHeadPointers = listOf(pHeadPointer1, pHeadPointer2, pHeadPointer3)


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
                        projects = msg.obj as ArrayList<Project>
                        arrangeLists()
                    }
                    Work.VIEW_ONE -> when (msg.arg1) {
                        //Work.ADD_NEW_ITEM -> { }
                    }
                    Work.INSERT_ONE -> {
                        /*Work(
                            c, handler, Work.VIEW_ONE, PROJECT,
                            listOf(msg.obj as Long, Work.ADD_NEW_ITEM)
                        ).start()*/
                        inserting = false
                        (inserted as Project).id = msg.obj as Long
                        projects!!.add(inserted as Project)
                        itemCreated.add(false)
                        itemMarked.add(false)
                        savedIds.add(View.generateViewId())
                        basicAdapter.notifyItemInserted(projects!!.size - 1)
                        if (projects!!.size > 1) basicRV.smoothScrollToPosition(projects!!.size - 2)

                        val load = va(motor1, "translationX", Home.loadDur, 10f, 0f)
                        load.addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                try {
                                    PAdapter.saved(
                                        findViewById(savedIds[savedIds.size - 1]), 1440, 1000
                                    )
                                } catch (ignored: Exception) {
                                }
                            }
                        })
                        inserted = null
                    }
                    Work.UPDATE_ONE -> try {
                        PAdapter.saved(findViewById(savedIds[msg.arg1]))
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
        pHeadTV1.setOnClickListener {
            lastSave()
            saveOnBlur = false
            howToSort(1, !descending)
            arrangeLists()
        }
        pHeadTV2.setOnClickListener {
            lastSave()
            saveOnBlur = false
            howToSort(2, !descending)
            arrangeLists()
        }
        pHeadTV3.setOnClickListener {
            lastSave()
            saveOnBlur = false
            howToSort(3, !descending)
            arrangeLists()
        }
        pFAB1.setOnClickListener {
            if (projects == null || deletingMass) return@setOnClickListener
            var deleteMass = ArrayList<Project>()
            for (i in 0 until itemMarked.size) if (itemMarked[i]) deleteMass.add(projects!![i])
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
        pFAB2.setOnClickListener { if (marking && !deletingMass) markAll(true) }
        pFAB3.setOnClickListener { if (marking && !deletingMass) markAll(false) }
        pFAB4.setOnClickListener { if (!deletingMass) marking(false) }
        noteEditorBtn.setOnClickListener { saveNotes(c) }
        for (p in pHeadPointers) p.colorFilter =
            PorterDuffColorFilter(
                ContextCompat.getColor(c, R.color.basicRVHeadPointer),
                PorterDuff.Mode.SRC_IN
            )
        Home.sortPointers(pHeadPointers, sortBy - 1, descending)

        // Add New Item
        addNewButtonTV.typeface = anbFont
        addNewButtonBG.setOnClickListener {
            if (projects == null) return@setOnClickListener
            inserted = newProject(c, projects!!)
            Work(c, handler, Work.INSERT_ONE, PROJECT, listOf(inserted!!)).start()
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
        Work(c, handler, Work.VIEW_ALL, PROJECT).start()
    }

    fun arrangeLists() {
        itemCreated.clear()
        itemMarked.clear()
        savedIds.clear()
        for (m in projects!!) itemCreated.add(false)
        for (m in projects!!) itemMarked.add(false)
        for (m in projects!!) savedIds.add(View.generateViewId())
        Collections.sort(projects, Sort.SortProjects(0))
        Collections.sort(projects, Sort.SortProjects(sortBy))
        if (descending) projects!!.reverse()
        basicAdapter = PAdapter(c, projects!!, this)
        basicRV.adapter = basicAdapter
    }

    fun deleteMass(deleteMass: ArrayList<Project>) {
        deletingMass = true
        Work(c, handler, Work.DELETE_MASS, PROJECT, listOf(deleteMass)).start()
    }

    fun howToSort(by: Int, des: Boolean) {
        sortBy = by
        descending = des
        var editor = sp.edit()
        editor.putInt(pSortBy, sortBy)
        editor.putBoolean(pDescending, descending)
        editor.apply()
        Home.sortPointers(pHeadPointers, sortBy - 1, descending)
    }

    fun lastSave() {
        for (f in 0 until basicRV.childCount) {
            var i = basicRV.getChildAt(f) as ViewGroup
            var et =
                (i.getChildAt(PAdapter.llPos) as ViewGroup).getChildAt(PAdapter.etPos) as EditText
            if (et.hasFocus()) PAdapter.saveET(c, et, basicRV.getChildLayoutPosition(i))
        }
    }
}
