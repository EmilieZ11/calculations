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
import uk.easys.calculations.AnimDepot.Companion.noteEditor
import uk.easys.calculations.Home.Companion.dirLtr
import uk.easys.calculations.adapters.RAdapter
import uk.easys.calculations.room.Entities.RELATIVE
import uk.easys.calculations.room.Entities.Relative
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.util.*
import kotlin.collections.ArrayList

class Relatives : AppCompatActivity() {
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
    lateinit var rHeadTV1: TextView
    lateinit var rHeadPointer1: ImageView
    lateinit var rHeadTV2: TextView
    lateinit var rHeadPointer2: ImageView
    lateinit var rHeadTV3: TextView
    lateinit var rHeadPointer3: ImageView
    lateinit var rHeadTV4: TextView
    lateinit var basicRV: RecyclerView
    lateinit var addNewButtonBG: View
    lateinit var addNewButtonTV: TextView
    lateinit var addNewButtonFG: View
    lateinit var rFAB1: ComplexView
    lateinit var rFAB2: ComplexView
    lateinit var rFAB3: ComplexView
    lateinit var rFAB4: ComplexView

    lateinit var c: Context
    lateinit var titleFont: Typeface
    lateinit var anbFont: Typeface
    lateinit var sp: SharedPreferences
    lateinit var rHeadPointers: List<ImageView>

    var sortBy = 1
    var descending = false
    var inserting = false
    var inserted: Relative? = null

    companion object {
        lateinit var addNewButton: ComplexView
        lateinit var cover: View
        lateinit var noteEditor: ConstraintLayout
        lateinit var noteEditorTitle: TextView
        lateinit var noteEditorET: EditText
        lateinit var noteEditorBtn: ImageView

        lateinit var handler: Handler
        lateinit var rFABs: List<ComplexView>
        lateinit var basicAdapter: RAdapter

        var dm = DisplayMetrics()
        var rvlManager: LinearLayoutManager? = null
        var relatives: ArrayList<Relative>? = null
        val itemCreated = ArrayList<Boolean>()
        val itemMarked = ArrayList<Boolean>()
        val savedIds = ArrayList<Int>()
        var marking = false
        var deletingMass = false
        var editingItsNote = -1
        var rSortBy = "rSortBy"
        var rDescending = "rDescending"
        var saveOnBlur = true

        fun dp(px: Int): Int = (px * dm.density).toInt()

        fun marking(b: Boolean) {
            if (marking == b) return
            marking = b
            markAll(false)
            for (m in 0 until itemMarked.size) itemMarked[m] = false
            if (rvlManager != null) for (i in 0 until rvlManager!!.childCount) {
                var cl = rvlManager!!.getChildAt(i) as ConstraintLayout
                var cover = cl.getChildAt(RAdapter.coverPos) as View
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
            if (::rFABs.isInitialized) for (f in 0 until rFABs.size) {
                val transhideY = dm.density * 444f
                if (b) {
                    rFABs[f].translationY = transhideY
                    rFABs[f].visibility = View.VISIBLE
                }
                var show = oa(
                    rFABs[f], "translationY", showHideDur,
                    if (b) 0f else transhideY, (f * 65).toLong()
                )
                if (!b) show.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        rFABs[f].visibility = View.GONE
                    }
                })
            }
        }

        fun markAll(b: Boolean) {
            for (m in 0 until itemMarked.size) itemMarked[m] = b
            if (rvlManager != null) for (i in 0 until rvlManager!!.childCount) {
                var cl = rvlManager!!.getChildAt(i) as ConstraintLayout
                var marker = cl.getChildAt(RAdapter.markerPos) as View
                marker.visibility = if (b) View.VISIBLE else View.GONE
            }
        }

        fun notes(c: Context, i: Int, notes: String?) {
            if (relatives == null) return
            noteEditor(c, noteEditor, true, cover, View.OnClickListener { saveNotes(c) })
            noteEditorET.setText(notes ?: "")
            editingItsNote = i
        }

        fun saveNotes(c: Context) {
            noteEditor(c, noteEditor, false, cover)
            if (relatives == null) return
            if (editingItsNote == -1 || relatives!!.size <= editingItsNote) return
            if (relatives!![editingItsNote].notes == noteEditorET.text.toString()) return
            relatives!![editingItsNote].notes = noteEditorET.text.toString()
            relatives!![editingItsNote].dateModified = Home.now()
            Work(
                c, handler, Work.UPDATE_ONE, RELATIVE,
                listOf(relatives!![editingItsNote], editingItsNote)
            ).start()
            editingItsNote = -1
        }

        fun newRelative(c: Context, relatives: List<Relative>): Relative {
            val new = c.resources.getString(R.string.rAddNew)
            var n = 1
            for (s in relatives) if (s.firstName.length > new.length)
                if (s.firstName.substring(0, new.length) == new) n += 1
            return Relative(new + n, "", 0, "", "", Home.now(), Home.now())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.relatives)

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
        rHeadTV1 = findViewById(R.id.rHeadTV1)
        rHeadPointer1 = findViewById(R.id.rHeadPointer1)
        rHeadTV2 = findViewById(R.id.rHeadTV2)
        rHeadPointer2 = findViewById(R.id.rHeadPointer2)
        rHeadTV3 = findViewById(R.id.rHeadTV3)
        rHeadPointer3 = findViewById(R.id.rHeadPointer3)
        rHeadTV4 = findViewById(R.id.rHeadTV4)
        basicRV = findViewById(R.id.basicRV)
        addNewButton = findViewById(R.id.addNewButton)
        addNewButtonBG = findViewById(R.id.addNewButtonBG)
        addNewButtonTV = findViewById(R.id.addNewButtonTV)
        addNewButtonFG = findViewById(R.id.addNewButtonFG)
        rFAB1 = findViewById(R.id.rFAB1)
        rFAB2 = findViewById(R.id.rFAB2)
        rFAB3 = findViewById(R.id.rFAB3)
        rFAB4 = findViewById(R.id.rFAB4)
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
        rFABs = listOf(rFAB1, rFAB2, rFAB3, rFAB4)
        sp = PreferenceManager.getDefaultSharedPreferences(c)
        rHeadPointers = listOf(rHeadPointer1, rHeadPointer2, rHeadPointer3)


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
                        relatives = msg.obj as ArrayList<Relative>
                        arrangeLists()
                    }
                    Work.VIEW_ONE -> when (msg.arg1) {
                        //Work.ADD_NEW_ITEM -> { }
                    }
                    Work.INSERT_ONE -> {
                        /*Work(
                            c, handler, Work.VIEW_ONE, RELATIVE,
                            listOf(msg.obj as Long, Work.ADD_NEW_ITEM)
                        ).start()*/
                        inserting = false
                        (inserted as Relative).id = msg.obj as Long
                        relatives!!.add(inserted as Relative)
                        itemCreated.add(false)
                        itemMarked.add(false)
                        savedIds.add(View.generateViewId())
                        basicAdapter.notifyItemInserted(relatives!!.size - 1)
                        if (relatives!!.size > 1) basicRV.smoothScrollToPosition(relatives!!.size - 2)

                        val load = va(motor1, "translationX", Home.loadDur, 10f, 0f)
                        load.addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                try {
                                    RAdapter.saved(
                                        findViewById(savedIds[savedIds.size - 1]), 1440, 1000
                                    )
                                } catch (ignored: Exception) {
                                }
                            }
                        })
                        inserted = null
                    }
                    Work.UPDATE_ONE -> try {
                        RAdapter.saved(findViewById(savedIds[msg.arg1]))
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
        rHeadTV1.setOnClickListener {
            lastSave()
            saveOnBlur = false
            howToSort(1, !descending)
            arrangeLists()
        }
        rHeadTV2.setOnClickListener {
            lastSave()
            saveOnBlur = false
            howToSort(2, !descending)
            arrangeLists()
        }
        rHeadTV3.setOnClickListener {
            lastSave()
            saveOnBlur = false
            howToSort(3, !descending)
            arrangeLists()
        }
        rFAB1.setOnClickListener {
            if (relatives == null || deletingMass) return@setOnClickListener
            var deleteMass = ArrayList<Relative>()
            for (i in 0 until itemMarked.size) if (itemMarked[i]) deleteMass.add(relatives!![i])
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
        rFAB2.setOnClickListener { if (marking && !deletingMass) markAll(true) }
        rFAB3.setOnClickListener { if (marking && !deletingMass) markAll(false) }
        rFAB4.setOnClickListener { if (!deletingMass) marking(false) }
        noteEditorBtn.setOnClickListener { saveNotes(c) }
        for (p in rHeadPointers) p.colorFilter =
            PorterDuffColorFilter(
                ContextCompat.getColor(c, R.color.basicRVHeadPointer),
                PorterDuff.Mode.SRC_IN
            )
        Home.sortPointers(rHeadPointers, sortBy - 1, descending)

        // Add New Item
        addNewButtonTV.typeface = anbFont
        addNewButtonBG.setOnClickListener {
            if (relatives == null) return@setOnClickListener
            inserted = newRelative(c, relatives!!)
            Work(c, handler, Work.INSERT_ONE, RELATIVE, listOf(inserted!!)).start()
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
            lastSave(); super.onBackPressed(); finish()
        }
    }


    fun loadRoom() {
        Work(c, handler, Work.VIEW_ALL, RELATIVE).start()
    }

    fun arrangeLists() {
        itemCreated.clear()
        itemMarked.clear()
        savedIds.clear()
        for (m in relatives!!) itemCreated.add(false)
        for (m in relatives!!) itemMarked.add(false)
        for (m in relatives!!) savedIds.add(View.generateViewId())
        Collections.sort(relatives, Sort.SortRelatives(0))
        Collections.sort(relatives, Sort.SortRelatives(sortBy))
        if (descending) relatives!!.reverse()
        basicAdapter = RAdapter(c, relatives!!)
        basicRV.adapter = basicAdapter
    }

    fun deleteMass(deleteMass: ArrayList<Relative>) {
        deletingMass = true
        Work(c, handler, Work.DELETE_MASS, RELATIVE, listOf(deleteMass)).start()
    }

    fun howToSort(by: Int, des: Boolean) {
        sortBy = by
        descending = des
        var editor = sp.edit()
        editor.putInt(rSortBy, sortBy)
        editor.putBoolean(rDescending, descending)
        editor.apply()
        Home.sortPointers(rHeadPointers, sortBy - 1, descending)
    }

    fun lastSave() {
        for (f in 0 until basicRV.childCount) {
            var i = basicRV.getChildAt(f) as ViewGroup
            var ll = i.getChildAt(RAdapter.llPos) as ViewGroup
            var et1 = ll.getChildAt(RAdapter.et1Pos) as EditText
            var et2 = ll.getChildAt(RAdapter.et2Pos) as EditText
            if (et1.hasFocus() || et2.hasFocus()) {
                val pos = basicRV.getChildLayoutPosition(i)
                if (et1.hasFocus()) RAdapter.saveET1(c, et1, pos)
                if (et2.hasFocus()) RAdapter.saveET2(c, et2, pos)
            }
        }
    }
}
