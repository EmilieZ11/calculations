package uk.easys.calculations

import android.Manifest
import android.animation.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.util.Base64
import android.net.NetworkInfo
import android.os.*
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.blure.complexview.ComplexView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.*
import com.bumptech.glide.request.transition.Transition
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar
import uk.easys.calculations.AnimDepot.Companion.oa
import uk.easys.calculations.AnimDepot.Companion.va
import uk.easys.calculations.adapters.*
import uk.easys.calculations.anychart.APIlib
import uk.easys.calculations.anychart.AnyChart
import uk.easys.calculations.anychart.AnyChartView
import uk.easys.calculations.anychart.chart.common.dataentry.DataEntry
import uk.easys.calculations.anychart.chart.common.dataentry.ValueDataEntry
import uk.easys.calculations.anychart.chart.common.listener.Event
import uk.easys.calculations.anychart.chart.common.listener.ListenersInterface
import uk.easys.calculations.anychart.charts.Pie
import uk.easys.calculations.anychart.enums.*
import uk.easys.calculations.hijridatepicker.date.gregorian.GregorianDatePickerDialog
import uk.easys.calculations.hijridatepicker.date.hijri.HijriDatePickerDialog
import uk.easys.calculations.hijridatepicker.time.TimePickerDialog
import uk.easys.calculations.room.Entities.*
import uk.easys.calculations.room.Sort
import uk.easys.calculations.room.Work
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.system.exitProcess
import uk.easys.calculations.room.Sync
import kotlin.collections.HashMap
import uk.easys.calculations.AnimDepot.Companion.cover

class Home : AppCompatActivity() {
    lateinit var body: ConstraintLayout
    lateinit var motor1: View
    lateinit var header: ImageView
    lateinit var headerTV: TextView
    lateinit var tbIconCanvas: View
    lateinit var tbIconBar1: View
    lateinit var tbIconBar2: View
    lateinit var tbIconBar3: View
    lateinit var tbIcon: View
    lateinit var divUser: ConstraintLayout
    lateinit var divUserTV1: TextView
    lateinit var divUserTV2: TextView
    lateinit var hUserFormButton: ComplexView
    lateinit var hUserFormButtonCL: ConstraintLayout
    lateinit var hUserFormButtonTV: TextView
    lateinit var circleUserShadow: ImageView
    lateinit var circleUserBG: ImageView
    lateinit var circleUser: ImageView
    lateinit var hPage1: ConstraintLayout
    lateinit var hDiv1: ComplexView
    lateinit var hBalance: ConstraintLayout
    lateinit var hBalanceTitle: TextView
    lateinit var hChart1Link: View
    lateinit var hTreasury: TextView
    lateinit var hPage2: ConstraintLayout
    lateinit var hDiv2: ComplexView
    lateinit var hIncomeChart: ConstraintLayout
    lateinit var hIncomeChartTitle: TextView
    lateinit var hChart2Link: View
    lateinit var hIncomes: RecyclerView
    lateinit var hPage3: ConstraintLayout
    lateinit var hDiv3: ComplexView
    lateinit var hExpenseChart: ConstraintLayout
    lateinit var hExpenseChartTitle: TextView
    lateinit var hChart3Link: View
    lateinit var hExpenses: RecyclerView
    lateinit var hNavSV: ScrollView
    lateinit var hNav: ConstraintLayout
    lateinit var hNavVLine: View
    lateinit var hNavBtn1IV: ImageView
    lateinit var hNavBtn1TV: TextView
    lateinit var hNavBtn1: View
    lateinit var hNavBtn2IV: ImageView
    lateinit var hNavBtn2TV: TextView
    lateinit var hNavBtn2: View
    lateinit var hNavHLine1: View
    lateinit var hNavBtn3IV: ImageView
    lateinit var hNavBtn3TV: TextView
    lateinit var hNavBtn3: View
    lateinit var hNavBtn4IV: ImageView
    lateinit var hNavBtn4TV: TextView
    lateinit var hNavBtn4: View
    lateinit var hFAB1: ComplexView
    lateinit var hFAB2: ComplexView
    lateinit var navigator: View
    lateinit var cover: View
    lateinit var hUserForm: ConstraintLayout
    lateinit var hUSignUp: TextView
    lateinit var hUSignOut: TextView
    lateinit var hUMailHint: TextView
    lateinit var hUMail: EditText
    lateinit var hUMailLine: View
    lateinit var hUPassHint: TextView
    lateinit var hUPass: EditText
    lateinit var hUPassLine: View
    lateinit var hUPassRepHint: TextView
    lateinit var hUPassRep: EditText
    lateinit var hUPassRepLine: View
    lateinit var hUFNameHint: TextView
    lateinit var hUFName: EditText
    lateinit var hUFNameLine: View
    lateinit var hULNameHint: TextView
    lateinit var hULName: EditText
    lateinit var hULNameLine: View
    lateinit var hUSUButton: ConstraintLayout
    lateinit var hUSUButtonTV: TextView
    lateinit var hUSIButton: ConstraintLayout
    lateinit var hUSIButtonTV: TextView

    lateinit var sp: SharedPreferences
    lateinit var titleFont: Typeface
    lateinit var anbFont: Typeface
    lateinit var hPages: List<ConstraintLayout>
    lateinit var hNavBtns: List<View>
    lateinit var hNavBtnIVs: List<ImageView>
    lateinit var hNavBtnTVs: List<TextView>
    lateinit var hNavHLines: List<View>
    lateinit var navbar: List<View>
    lateinit var tbIconAll: List<View>
    lateinit var userAll: List<ImageView>
    lateinit var hFABs: List<ComplexView>
    lateinit var monthNamesAbbr: Array<String>
    lateinit var monthNames: Array<String>
    lateinit var daysOfWeek: Array<String>
    lateinit var hULines: List<View>
    lateinit var hUAdditional: List<View>
    lateinit var hUHints: List<TextView>

    var firstBackToExit = false
    var curPage = 0
    var navOpening = false
    var navOpened = false
    var chart2Loaded = false
    var chart3Loaded = false
    var treasurySizes: List<Int>? = null
    var showingUserForm = false
    var movingUserForm = false
    var signedIn = false
    var hUComplete = false
    var signingIn = false
    val pickAvatarReq = 222
    val captureAvatarReq = 933
    var workingOnAvatar = false
    var roomLoaded = false
    val permES = 111
    var anNavig: AnimatorSet? = null

    companion object {
        lateinit var editorShadow: View
        lateinit var editor2: ScrollView
        lateinit var editor2CL: ConstraintLayout
        lateinit var editor2Header: TextView
        lateinit var hEDateHint: TextView
        lateinit var hEDate1: TextView
        lateinit var hEDate2: TextView
        lateinit var hEDate1Line: View
        lateinit var hEDate2Line: View
        lateinit var hEAmountCur: Spinner
        lateinit var hEAmount: EditText
        lateinit var hEAmountLine: View
        lateinit var hESubjectHint: TextView
        lateinit var hESubject: Spinner
        lateinit var hESubjectMark: ImageView
        lateinit var hESubjectLine: View
        lateinit var hEContactHint: TextView
        lateinit var hEContact: Spinner
        lateinit var hEContactMark: ImageView
        lateinit var hEContactLine: View
        lateinit var hETreasuryHint: TextView
        lateinit var hETreasury: Spinner
        lateinit var hETreasuryMark: ImageView
        lateinit var hETreasuryLine: View
        lateinit var hEBehalfHint: TextView
        lateinit var hEBehalf: Spinner
        lateinit var hEBehalfMark: ImageView
        lateinit var hEBehalfLine: View
        lateinit var hEProjectHint: TextView
        lateinit var hEProject: Spinner
        lateinit var hEProjectMark: ImageView
        lateinit var hEProjectLine: View
        lateinit var hENotesHint: TextView
        lateinit var hENotes: EditText
        lateinit var hENotesLine: View
        lateinit var addNewButton: ComplexView
        lateinit var addNewButtonBG: View
        lateinit var addNewButtonTV: TextView
        lateinit var addNewButtonFG: View

        lateinit var c: Context
        lateinit var handler: Handler
        lateinit var syncHandler: Handler
        lateinit var currencies: Array<String>
        lateinit var currencyNames: Array<String>
        lateinit var hELines: List<View>
        lateinit var hEMarks: List<ImageView>
        lateinit var hEHints: List<TextView>
        lateinit var adIncomeDrw: Drawable
        lateinit var adExpenseDrw: Drawable

        const val loadDur: Long = 179
        const val notifyItemRemovedDur: Long = 1650
        const val aDay: Long = 86400000
        const val exUser = "user"
        const val exPass = "pass"
        const val exFName = "first_name"
        const val exLName = "last_name"
        const val exUID = "user_id"
        const val exUserType = "user_type"
        const val exAvatar = "avatar"
        const val exLastSync = "last_sync"
        const val chartBG = "#FFFFFF"//#F6F6FA
        const val insertionDelay: Long = 2000
        const val defUserIcon = R.drawable.default_user_2
        const val sortPointersShow: Long = 44
        var dm = DisplayMetrics()
        var dirLtr = true
        val supportedRtlLangs = listOf("ar")
        val navigateDur: Long = 365
        var dbName = "user0"
        var user: String? = null
        var pass: String? = null
        var fName: String? = null
        var lName: String? = null
        var userId: Long? = null
        var userType: String? = null
        var avatar: String? = null
        var banks: ArrayList<Bank>? = null
        var chests: ArrayList<Chest>? = null
        var cashes: ArrayList<Cash>? = null
        var incomes: ArrayList<Income>? = null
        var expenses: ArrayList<Expense>? = null
        var contacts: ArrayList<Contact>? = null
        var relatives: ArrayList<Relative>? = null
        var subjects: ArrayList<Subject>? = null
        var projects: ArrayList<Project>? = null
        var hIncomesManager: LinearLayoutManager? = null
        var hExpensesManager: LinearLayoutManager? = null
        var incomeAdapter: IAdapter? = null
        var expenseAdapter: EAdapter? = null
        var showingEditor = false
        var showEditorDur: Long = 444
        var editingIt = -1
        var editorMode = 0
        var hEYear = -1
        var hEMonth = -1
        var hEDay = -1
        var hEHour = -1
        var hEMinute = -1
        var hESecond = 0
        val iSavedIds = ArrayList<Int>()
        val eSavedIds = ArrayList<Int>()

        @kotlin.jvm.JvmField
        var whichChart = 0

        fun dp(px: Int): Int = (px * dm.density).toInt()

        fun isOnline(c: Context): Boolean {
            val cm = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            var nwi: NetworkInfo? = null
            if (cm != null) nwi = cm.activeNetworkInfo
            return (nwi != null && nwi.isConnected)
        }

        fun compileTime(time: Long): String {
            val lm = Calendar.getInstance()
            lm.timeInMillis = time
            var y = lm.get(Calendar.YEAR)
            var m = lm.get(Calendar.MONTH) + 1
            var d = lm.get(Calendar.DAY_OF_MONTH)
            var h = lm.get(Calendar.HOUR_OF_DAY)
            var n = lm.get(Calendar.MINUTE)
            return "$y." + (if (m < 10) "0" else "") + "$m." + (if (d < 10) "0" else "") + "$d" // - " +
            //(if (h < 10) "0" else "") + "$h:" + (if (n < 10) "0" else "") + "$n"
        }

        fun fixPrice(price: String): String {
            var p = StringBuilder()
            var dropZero = price.contains(".")
            var passedDot = false
            if (!price.contains(".")) passedDot = true
            var three = 0
            for (i in (price.length - 1) downTo 0) {
                if (dropZero) {// DON'T MERGE THESE!
                    if (price[i].toString() != "0") {
                        dropZero = false
                        if (price[i].toString() != "." || p.isNotEmpty()) p.insert(0, price[i])
                    }
                } else {
                    p.insert(0, price[i])
                    if (passedDot) {
                        if (three == 2) {
                            if (i != 0) p.insert(0, ",")
                            three = 0
                        } else three += 1
                    }
                }
                if (price[i].toString() == ".") passedDot = true
            }
            return p.toString()
        }

        fun php(
            c: Context, get: String,
            lis: com.android.volley.Response.Listener<String>,
            err: com.android.volley.Response.ErrorListener?,
            hashMap: HashMap<String, String>,
            tag: String, timeout: Int = 7000
        ) {
            var req = Volley.newRequestQueue(c)
            var srt = object : StringRequest(
                Method.POST, "https://easys.uk/calculations/login.php" + get, lis, err
            ) {
                @Throws(AuthFailureError::class)
                override fun getParams() = hashMap
            }
            srt.tag = tag
            srt.retryPolicy = DefaultRetryPolicy(
                timeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            req.add(srt)
        }

        fun phpParams(user: String, pass: String): HashMap<String, String> {
            val params = HashMap<String, String>()
            params["user"] = user
            params["pass"] = pass
            return params
        }

        fun explode(
            c: Context, v: View, dur: Long = 522,
            src: Int = R.drawable.circle_cp, alpha: Float = 1f, max: Float = 4f
        ) {
            var parent: ConstraintLayout? = null
            try {
                parent = v.parent as ConstraintLayout
            } catch (ignored: java.lang.Exception) {
                return
            }
            if (parent == null) return

            var ex = View(c)
            var exLP = ConstraintLayout.LayoutParams(0, 0)
            exLP.topToTop = v.id
            exLP.leftToLeft = v.id
            exLP.rightToRight = v.id
            exLP.bottomToBottom = v.id
            ex.background = ContextCompat.getDrawable(c, src)
            ex.translationX = v.translationX
            ex.translationY = v.translationY
            ex.scaleX = v.scaleX
            ex.scaleY = v.scaleY
            ex.alpha = alpha
            parent.addView(ex, parent.indexOfChild(v), exLP)

            var explode = AnimatorSet().setDuration(dur)
            var hide = ObjectAnimator.ofFloat(ex, "alpha", 0f)
            hide.startDelay = explode.duration / 4
            explode.playTogether(
                ObjectAnimator.ofFloat(ex, "scaleX", ex.scaleX * max),
                ObjectAnimator.ofFloat(ex, "scaleY", ex.scaleY * max),
                hide
            )
            explode.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    parent.removeView(ex)
                }
            })
            explode.start()
        }

        fun updateNavigator(navigator: View, animate: Boolean, cur: Int) {
            val dist = dm.widthPixels.toFloat() / 3.5f
            val to = when (cur) {
                0 -> if (dirLtr) -dist else dist
                2 -> if (dirLtr) dist else -dist
                else -> 0f
            }
            if (animate) oa(navigator, "translationX", navigateDur, to)
            else navigator.translationX = to
        }

        fun now() = Calendar.getInstance(TimeZone.getTimeZone("GMT")).timeInMillis

        fun fixCardNumber(str: String): String {
            var sb = StringBuffer()
            var parts = (str.length / 4).toInt()
            for (p in 0..parts) {
                sb.append(str.substring(0 + (4 * p), 4 + (4 * p)))
                if (p != parts) sb.append("-")
            }
            return sb.toString()
        }

        fun entityType(i: Int = 0): String = when (i) {
            1 -> EXPENSE; else -> INCOME; }

        fun editor(c: Context, data: Any? = null, i: Int? = null) {
            if (showingEditor) return
            var doShow = true
            when {
                data is Income && i != null -> {
                    editingIt = i
                    editorMode = 0
                    editor2Header.text = c.resources.getString(R.string.hEHIncome) + data.id
                    addNewButtonTV.text = c.resources.getString(R.string.saveChanges)
                    setETimes(data.date)
                    updateEDateTVs()
                    hEAmountCur.setSelection(currencies.indexOf(data.currency))
                    hEAmount.setText(data.amount.toString())
                    var sid = SAdapter.findSubjectById(data.subjectId, subjects!!)
                    if (sid != null) hESubject.setSelection(sid + 1)
                    if (data.contactId == (-1).toLong()) hEContact.setSelection(0)
                    else {
                        var cid = CAdapter.findContactById(data.contactId, contacts!!)
                        if (cid != null) hEContact.setSelection(cid + 1)
                    }
                    var tStartPoint = 1
                    when (data.treasuryType) {
                        0.toByte() -> {
                            val treas = T1Adapter.findBankById(data.treasuryId, banks!!)
                            if (treas != null) hETreasury.setSelection(tStartPoint + treas)
                        }
                        1.toByte() -> {
                            tStartPoint += chests!!.size
                            val treas = T2Adapter.findChestById(data.treasuryId, chests!!)
                            if (treas != null) hETreasury.setSelection(tStartPoint + treas)
                        }
                        2.toByte() -> {
                            tStartPoint += cashes!!.size
                            val treas = T3Adapter.findCashById(data.treasuryId, cashes!!)
                            if (treas != null) hETreasury.setSelection(tStartPoint + treas)
                        }
                    }
                    if (data.behalf == (-1).toLong()) hEBehalf.setSelection(0)
                    else {
                        var rid = RAdapter.findRelativeById(data.behalf, relatives!!)
                        if (rid != null) hEBehalf.setSelection(rid + 1)
                    }
                    if (data.projectId == (-1).toLong()) hEProject.setSelection(0)
                    else {
                        var pid = PAdapter.findProjectById(data.projectId, projects!!)
                        if (pid != null) hEProject.setSelection(pid + 1)
                    }
                    hENotes.setText(data.notes)
                }
                data is Expense && i != null -> {
                    editingIt = i
                    editorMode = 1
                    editor2Header.text = c.resources.getString(R.string.hEHExpense) + data.id
                    addNewButtonTV.text = c.resources.getString(R.string.saveChanges)
                    setETimes(data.date)
                    updateEDateTVs()
                    hEAmountCur.setSelection(currencies.indexOf(data.currency))
                    hEAmount.setText(data.amount.toString())
                    var sid = SAdapter.findSubjectById(data.subjectId, subjects!!)
                    if (sid != null) hESubject.setSelection(sid + 1)
                    if (data.contactId == (-1).toLong()) hEContact.setSelection(0)
                    else {
                        var cid = CAdapter.findContactById(data.contactId, contacts!!)
                        if (cid != null) hEContact.setSelection(cid + 1)
                    }
                    var tStartPoint = 1
                    when (data.treasuryType) {
                        (0).toByte() -> {
                            val treas = T1Adapter.findBankById(data.treasuryId, banks!!)
                            if (treas != null) hETreasury.setSelection(tStartPoint + treas)
                        }
                        (1).toByte() -> {
                            tStartPoint += banks!!.size
                            val treas = T2Adapter.findChestById(data.treasuryId, chests!!)
                            if (treas != null) hETreasury.setSelection(tStartPoint + treas)
                        }
                        (2).toByte() -> {
                            tStartPoint += chests!!.size
                            val treas = T3Adapter.findCashById(data.treasuryId, cashes!!)
                            if (treas != null) hETreasury.setSelection(tStartPoint + treas)
                        }
                    }
                    if (data.behalf == (-1).toLong()) hEBehalf.setSelection(0)
                    else {
                        var rid = RAdapter.findRelativeById(data.behalf, relatives!!)
                        if (rid != null) hEBehalf.setSelection(rid + 1)
                    }
                    if (data.projectId == (-1).toLong()) hEProject.setSelection(0)
                    else {
                        var pid = PAdapter.findProjectById(data.projectId, projects!!)
                        if (pid != null) hEProject.setSelection(pid + 1)
                    }
                    hENotes.setText(data.notes)
                }
                data == null && i == 0 -> {
                    editorMode = 2
                    editor2Header.text = c.resources.getString(R.string.hEHNewIncome)
                    addNewButtonTV.text = c.resources.getString(R.string.hCreateNewIncome)
                    setETimes(now())
                    updateEDateTVs()
                }
                data == null && i == 1 -> {
                    editorMode = 3
                    editor2Header.text = c.resources.getString(R.string.hEHNewExpense)
                    addNewButtonTV.text = c.resources.getString(R.string.hCreateNewExpense)
                    setETimes(now())
                    updateEDateTVs()
                }
                else -> doShow = false
            }
            if (doShow) showEditor(true)
        }

        fun setETimes(time: Long) {
            if (dirLtr) {
                val cal = Calendar.getInstance()
                cal.timeInMillis = time
                hEYear = cal[Calendar.YEAR]
                hEMonth = cal[Calendar.MONTH]
                hEDay = cal[Calendar.DAY_OF_MONTH]
                hEHour = cal[Calendar.HOUR]
                hEMinute = cal[Calendar.MINUTE]
            } else {
                val cal = UmmalquraCalendar()
                cal.timeInMillis = time
                hEYear = cal[UmmalquraCalendar.YEAR]
                hEMonth = cal[UmmalquraCalendar.MONTH]
                hEDay = cal[UmmalquraCalendar.DAY_OF_MONTH]
                hEHour = cal[UmmalquraCalendar.HOUR]
                hEMinute = cal[UmmalquraCalendar.MINUTE]
            }
        }

        fun updateEDateTVs() {
            hEDate1.text =
                "$hEYear." + (if (hEMonth < 10) "0" else "") + "$hEMonth." + (if (hEDay < 10) "0" else "") + "$hEDay"
            hEDate2.text =
                (if (hEHour < 10) "0" else "") + "$hEHour:" + (if (hEMinute < 10) "0" else "") + "$hEMinute"
        }

        fun resetEditor() {
            hEDate1.text = ""
            hEDate2.text = ""
            hEAmount.setText("")
            hESubject.setSelection(0)
            hEContact.setSelection(0)
            hETreasury.setSelection(0)
            hEBehalf.setSelection(0)
            hEProject.setSelection(0)
            hENotes.setText("")
            editingIt = -1
            editorMode = 0
            hEYear = -1
            hEMonth = -1
            hEDay = -1
            hEHour = -1
            hEMinute = -1
        }

        fun showEditor(b: Boolean) {
            if (b == showingEditor) return
            showingEditor = b
            var dest = dm.heightPixels.toFloat() * 1.11f
            var scroll = AnimatorSet().setDuration(showEditorDur)
            scroll.playTogether(
                ObjectAnimator.ofFloat(
                    editor2, "translationY", if (b) dest else 0f, if (b) 0f else dest
                ), ObjectAnimator.ofFloat(
                    editorShadow, "translationY", if (b) dest else 0f, if (b) 0f else dest
                ), ObjectAnimator.ofFloat(
                    addNewButton, "translationY", if (b) dest else 0f, if (b) 0f else dest
                )
            )
            scroll.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    if (b) {
                        editor2.visibility = View.VISIBLE
                        editorShadow.visibility = View.VISIBLE
                        addNewButton.visibility = View.VISIBLE
                        for (l in hELines) l.scaleX = 0.65f
                    }
                }

                override fun onAnimationEnd(animation: Animator?) {
                    if (!b) {
                        resetEditor()
                        editor2.visibility = View.GONE
                        editorShadow.visibility = View.GONE
                        addNewButton.visibility = View.GONE
                    } else {
                        editor2.smoothScrollTo(0, 0)
                        for (l in hELines) oa(l, "scaleX", 650, 1f)
                    }
                }
            })
            scroll.start()
        }

        fun getTreasuryByPos(pos: Int): Any? {//OK
            if (banks == null || chests == null || cashes == null) return null
            var hereSize = 1
            if (pos >= banks!!.size + hereSize) hereSize += banks!!.size
            else {// It's a bank!
                return banks!![pos - hereSize]
            }
            if (pos >= chests!!.size + hereSize) hereSize += chests!!.size
            else {// It's a chest!
                return chests!![pos - hereSize]
            }
            if (pos >= cashes!!.size + hereSize) hereSize += cashes!!.size
            else {// It's a cash!
                return cashes!![pos - hereSize]
            }
            return null
        }

        fun toast(msg: Any, long: Boolean = true) {
            if (msg is Int)
                Toast.makeText(c, msg, if (long) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
            if (msg is String)
                Toast.makeText(c, msg, if (long) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
        }

        fun bmpRound(bitmap: Bitmap): Bitmap {
            var widthLight = bitmap.width
            var heightLight = bitmap.height
            var output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            var canvas = Canvas(output)
            var paintColor = Paint()
            paintColor.flags = Paint.ANTI_ALIAS_FLAG
            var rectF = RectF(Rect(0, 0, widthLight, heightLight))
            canvas.drawRoundRect(rectF, widthLight / 2f, heightLight / 2f, paintColor)
            var paintImage = Paint()
            paintImage.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
            canvas.drawBitmap(bitmap, 0f, 0f, paintImage)
            return output
        }

        fun imageToString(bitmap: Bitmap): String {
            var byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            var imgbytes = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(imgbytes, Base64.DEFAULT)
        }

        fun glideAvatar(that: AppCompatActivity, iv: ImageView) {
            if (avatar != null) Glide.with(that).asBitmap().load(avatar)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                        iv.setImageResource(defUserIcon)
                    }

                    override fun onResourceReady(res: Bitmap, trans: Transition<in Bitmap>?) {
                        iv.setImageBitmap(bmpRound(res))
                    }
                }) else iv.setImageResource(defUserIcon)
        }

        fun sortPointers(pointers: List<ImageView>, by: Int, des: Boolean) {
            for (p in 0 until pointers.size)
                oa(pointers[p], "alpha", sortPointersShow, if (by == p) 1f else 0f)
            oa(pointers[by], "rotation", sortPointersShow, if (des) 90f else -90f)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        body = findViewById(R.id.body)
        motor1 = findViewById(R.id.motor1)
        header = findViewById(R.id.header)
        headerTV = findViewById(R.id.headerTV)
        tbIconCanvas = findViewById(R.id.tbIconCanvas)
        tbIconBar1 = findViewById(R.id.tbIconBar1)
        tbIconBar2 = findViewById(R.id.tbIconBar2)
        tbIconBar3 = findViewById(R.id.tbIconBar3)
        tbIcon = findViewById(R.id.tbIcon)
        divUser = findViewById(R.id.divUser)
        divUserTV1 = findViewById(R.id.divUserTV1)
        divUserTV2 = findViewById(R.id.divUserTV2)
        hUserFormButton = findViewById(R.id.hUserFormButton)
        hUserFormButtonCL = findViewById(R.id.hUserFormButtonCL)
        hUserFormButtonTV = findViewById(R.id.hUserFormButtonTV)
        circleUserShadow = findViewById(R.id.circleUserShadow)
        circleUserBG = findViewById(R.id.circleUserBG)
        circleUser = findViewById(R.id.circleUser)
        hPage1 = findViewById(R.id.hPage1)
        hDiv1 = findViewById(R.id.hDiv1)
        hBalance = findViewById(R.id.hBalance)
        hBalanceTitle = findViewById(R.id.hBalanceTitle)
        hChart1Link = findViewById(R.id.hChart1Link)
        hTreasury = findViewById(R.id.hTreasury)
        hPage2 = findViewById(R.id.hPage2)
        hDiv2 = findViewById(R.id.hDiv2)
        hIncomeChart = findViewById(R.id.hIncomeChart)
        hIncomeChartTitle = findViewById(R.id.hIncomeChartTitle)
        hChart2Link = findViewById(R.id.hChart2Link)
        hIncomes = findViewById(R.id.hIncomes)
        hPage3 = findViewById(R.id.hPage3)
        hDiv3 = findViewById(R.id.hDiv3)
        hExpenseChart = findViewById(R.id.hExpenseChart)
        hExpenseChartTitle = findViewById(R.id.hExpenseChartTitle)
        hChart3Link = findViewById(R.id.hChart3Link)
        hExpenses = findViewById(R.id.hExpenses)
        hNavSV = findViewById(R.id.hNavSV)
        hNav = findViewById(R.id.hNav)
        hNavVLine = findViewById(R.id.hNavVLine)
        hNavBtn1IV = findViewById(R.id.hNavBtn1IV)
        hNavBtn1TV = findViewById(R.id.hNavBtn1TV)
        hNavBtn1 = findViewById(R.id.hNavBtn1)
        hNavBtn2IV = findViewById(R.id.hNavBtn2IV)
        hNavBtn2TV = findViewById(R.id.hNavBtn2TV)
        hNavBtn2 = findViewById(R.id.hNavBtn2)
        hNavHLine1 = findViewById(R.id.hNavHLine1)
        hNavBtn3IV = findViewById(R.id.hNavBtn3IV)
        hNavBtn3TV = findViewById(R.id.hNavBtn3TV)
        hNavBtn3 = findViewById(R.id.hNavBtn3)
        hNavBtn4IV = findViewById(R.id.hNavBtn4IV)
        hNavBtn4TV = findViewById(R.id.hNavBtn4TV)
        hNavBtn4 = findViewById(R.id.hNavBtn4)
        hFAB1 = findViewById(R.id.hFAB1)
        hFAB2 = findViewById(R.id.hFAB2)
        navigator = findViewById(R.id.navigator)

        editorShadow = findViewById(R.id.editorShadow)
        editor2 = findViewById(R.id.editor2)
        editor2CL = findViewById(R.id.editor2CL)
        editor2Header = findViewById(R.id.editor2Header)
        hEDateHint = findViewById(R.id.hEDateHint)
        hEDate1 = findViewById(R.id.hEDate1)
        hEDate2 = findViewById(R.id.hEDate2)
        hEDate1Line = findViewById(R.id.hEDate1Line)
        hEDate2Line = findViewById(R.id.hEDate2Line)
        hEAmountCur = findViewById(R.id.hEAmountCur)
        hEAmount = findViewById(R.id.hEAmount)
        hEAmountLine = findViewById(R.id.hEAmountLine)
        hESubjectHint = findViewById(R.id.hESubjectHint)
        hESubject = findViewById(R.id.hESubject)
        hESubjectMark = findViewById(R.id.hESubjectMark)
        hESubjectLine = findViewById(R.id.hESubjectLine)
        hEContactHint = findViewById(R.id.hEContactHint)
        hEContact = findViewById(R.id.hEContact)
        hEContactMark = findViewById(R.id.hEContactMark)
        hEContactLine = findViewById(R.id.hEContactLine)
        hETreasuryHint = findViewById(R.id.hETreasuryHint)
        hETreasury = findViewById(R.id.hETreasury)
        hETreasuryMark = findViewById(R.id.hETreasuryMark)
        hETreasuryLine = findViewById(R.id.hETreasuryLine)
        hEBehalfHint = findViewById(R.id.hEBehalfHint)
        hEBehalf = findViewById(R.id.hEBehalf)
        hEBehalfMark = findViewById(R.id.hEBehalfMark)
        hEBehalfLine = findViewById(R.id.hEBehalfLine)
        hEProjectHint = findViewById(R.id.hEProjectHint)
        hEProject = findViewById(R.id.hEProject)
        hEProjectMark = findViewById(R.id.hEProjectMark)
        hEProjectLine = findViewById(R.id.hEProjectLine)
        hENotesHint = findViewById(R.id.hENotesHint)
        hENotes = findViewById(R.id.hENotes)
        hENotesLine = findViewById(R.id.hENotesLine)
        addNewButton = findViewById(R.id.addNewButton)
        addNewButtonBG = findViewById(R.id.addNewButtonBG)
        addNewButtonTV = findViewById(R.id.addNewButtonTV)
        addNewButtonFG = findViewById(R.id.addNewButtonFG)

        cover = findViewById(R.id.cover)
        hUserForm = findViewById(R.id.hUserForm)
        hUSignUp = findViewById(R.id.hUSignUp)
        hUSignOut = findViewById(R.id.hUSignOut)
        hUMailHint = findViewById(R.id.hUMailHint)
        hUMail = findViewById(R.id.hUMail)
        hUMailLine = findViewById(R.id.hUMailLine)
        hUPassHint = findViewById(R.id.hUPassHint)
        hUPass = findViewById(R.id.hUPass)
        hUPassLine = findViewById(R.id.hUPassLine)
        hUPassRepHint = findViewById(R.id.hUPassRepHint)
        hUPassRep = findViewById(R.id.hUPassRep)
        hUPassRepLine = findViewById(R.id.hUPassRepLine)
        hUFNameHint = findViewById(R.id.hUFNameHint)
        hUFName = findViewById(R.id.hUFName)
        hUFNameLine = findViewById(R.id.hUFNameLine)
        hULNameHint = findViewById(R.id.hULNameHint)
        hULName = findViewById(R.id.hULName)
        hULNameLine = findViewById(R.id.hULNameLine)
        hUSUButton = findViewById(R.id.hUSUButton)
        hUSUButtonTV = findViewById(R.id.hUSUButtonTV)
        hUSIButton = findViewById(R.id.hUSIButton)
        hUSIButtonTV = findViewById(R.id.hUSIButtonTV)


        c = applicationContext
        windowManager.defaultDisplay.getMetrics(dm)
        for (lang in supportedRtlLangs)
            if (Locale.getDefault().language == lang) {
                body.layoutDirection = View.LAYOUT_DIRECTION_RTL
                dirLtr = false
            }
        sp = PreferenceManager.getDefaultSharedPreferences(c)
        titleFont = Typeface.createFromAsset(assets, resources.getString(R.string.titleFont))
        anbFont = Typeface.createFromAsset(assets, resources.getString(R.string.anbFont))
        hPages = listOf(hPage1, hPage2, hPage3)
        hNavBtns = listOf(hNavBtn1, hNavBtn2, hNavBtn3)
        hNavBtnIVs = listOf(hNavBtn1IV, hNavBtn2IV, hNavBtn3IV)
        hNavBtnTVs = listOf(hNavBtn1TV, hNavBtn2TV, hNavBtn3TV)
        hNavHLines = listOf(hNavHLine1)
        navbar =
            listOf(circleUserShadow, circleUserBG, circleUser, hNavSV, divUser, hUserFormButton)
        tbIconAll = listOf(tbIconCanvas, tbIconBar1, tbIconBar2, tbIconBar3, tbIcon)
        userAll = listOf(circleUserShadow, circleUserBG, circleUser)
        hFABs = listOf(hFAB1, hFAB2)
        if (!dirLtr) header.rotationY = 180f
        monthNamesAbbr = resources.getStringArray(R.array.calendar)
        monthNames = resources.getStringArray(R.array.calendarFull)
        daysOfWeek = resources.getStringArray(R.array.daysOfWeek)
        currencyNames = resources.getStringArray(R.array.currencyNames)
        currencies = resources.getStringArray(R.array.currencies)
        hELines = listOf(
            hEDate1Line, hEDate2Line, hEAmountLine, hESubjectLine, hEContactLine,
            hETreasuryLine, hEBehalfLine, hEProjectLine, hENotesLine
        )
        hEMarks = listOf(hESubjectMark, hEContactMark, hETreasuryMark, hEBehalfMark, hEProjectMark)
        hULines = listOf(hUMailLine, hUPassLine, hUPassRepLine, hUFNameLine, hULNameLine)
        hUAdditional = listOf(
            hUPassRepHint, hUPassRep, hUPassRepLine, hUFNameHint, hUFName, hUFNameLine,
            hULNameHint, hULName, hULNameLine
        )
        hUHints = listOf(hUMailHint, hUPassHint, hUPassRepHint, hUFNameHint, hULNameHint)
        hEHints = listOf(
            hEDateHint, hESubjectHint, hEContactHint, hETreasuryHint,
            hEBehalfHint, hEProjectHint, hENotesHint
        )

        // Loading
        val anLoad1 = va(motor1, "translationX", loadDur, 10f, 0f, 790)
        anLoad1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                updateNavigator(navigator, false, curPage)
                for (p in 1 until hPages.size) hPages[p].visibility = View.VISIBLE

                /*var cs = ConstraintSet()// EACH OF ITS CHILDREN MUST HAVE AN ID IN BOTH LAYOUTS
                cs.clone(body)//R.layout.h_fab_1
                TransitionManager.beginDelayedTransition(body, AutoTransition().setDuration(1000))
                //cs.connect(header.id, ConstraintSet.TOP, body.id, ConstraintSet.TOP)
                cs.clear(header.id, ConstraintSet.BOTTOM)//constrainPercentWidth
                cs.applyTo(body)*/
            }
        })
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) headerTV.setOnClickListener {
            var ani = ObjectAnimator.ofArgb(
                headerTV, "textColor", headerTV.currentTextColor,
                ContextCompat.getColor(c, R.color.CP)
            ).setDuration(500)
            ani.repeatCount = 1//ObjectAnimator.INFINITE
            ani.repeatMode = ObjectAnimator.REVERSE// IT ACTS LIKE DING DONG DING DONG!!
            ani.start()
        }*/

        // Layout Transition
        //var lat = LayoutTransition()
        //lat = instillToggle(c, lat, R.animator.cover, cover)
        //body.layoutTransition = lat


        // Header
        headerTV.typeface = titleFont//, Typeface.BOLD
        tbIcon.setOnClickListener { if (!navOpened) nav(!navOpened) }

        // Divisions
        hTreasury.setOnClickListener { startActivity(Intent(c, Treasury::class.java)) }
        hTreasury.typeface = anbFont
        hBalanceTitle.typeface = anbFont
        hIncomeChartTitle.typeface = anbFont
        hExpenseChartTitle.typeface = anbFont
        navigate(0)

        // Navigation Bar
        hNavBtn1.setOnClickListener { startActivity(Intent(c, Contacts::class.java)) }
        hNavBtn2.setOnClickListener { startActivity(Intent(c, Relatives::class.java)) }
        hNavBtn3.setOnClickListener { startActivity(Intent(c, Subjects::class.java)) }
        hNavBtn4.setOnClickListener { startActivity(Intent(c, Projects::class.java)) }

        // User Section
        signedIn(false)
        if (sp.contains(exUser) && sp.contains(exPass)) {
            user = sp.getString(exUser, null)
            pass = sp.getString(exPass, null)
            if (sp.contains(exFName)) fName = sp.getString(exFName, null)
            if (sp.contains(exLName)) lName = sp.getString(exLName, null)
            if (sp.contains(exUID)) userId =
                if (sp.getLong(exUID, (-1).toLong()) != (-1).toLong())
                    sp.getLong(exUID, -1) else null
            if (sp.contains(exUserType)) userType = sp.getString(exUserType, null)
            if (sp.contains(exAvatar)) avatar = sp.getString(exAvatar, null)
        }
        if (user != null && pass != null) signIn(user!!, pass!!, false, false)
        hUComplete(signedIn)
        hUserFormButtonCL.setOnClickListener { userForm(true) }
        hUserForm.setOnClickListener { }
        hUSUButton.setOnClickListener { userForm(false) }
        hUSignUp.setOnClickListener { hUComplete(!hUComplete) }
        hUSIButton.setOnClickListener {
            if (userFormIncomplete()) {
                toast(R.string.allFields)
                return@setOnClickListener
            }
            if (checkPWIncompatibility()) {
                toast(R.string.incompatiblePWs)
                return@setOnClickListener
            }
            if (!isOnline(c)) {
                toast(R.string.noInternet)
                return@setOnClickListener
            }
            if (signedIn && (user == null || pass == null)) return@setOnClickListener
            signIn(
                if (!signedIn) hUMail.text.toString() else user!!,
                if (!signedIn) hUPass.text.toString() else pass!!,
                if (!signedIn) hUComplete else false
            )
        }
        circleUser.setOnClickListener {
            if (user == null || pass == null) return@setOnClickListener
            if (!showingUserForm) {
                userForm(true); return@setOnClickListener; }
            var popup = PopupMenu(this, it)
            popup.setOnMenuItemClickListener { itged ->
                when (itged.itemId) {
                    R.id.hPMIChangeAvatar -> {
                        val perm = Manifest.permission.WRITE_EXTERNAL_STORAGE
                        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() &&
                            ContextCompat.checkSelfPermission(c, perm) !=
                            PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT >= 23
                        ) {
                            ActivityCompat.requestPermissions(this, arrayOf(perm), permES)
                            return@setOnMenuItemClickListener true
                        }
                        pickAvatar()
                        return@setOnMenuItemClickListener true
                    }
                    R.id.hPMICaptureAvatar -> Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                        takePictureIntent.resolveActivity(packageManager)?.also {
                            startActivityForResult(takePictureIntent, captureAvatarReq)
                        }
                    }
                    R.id.hPMIDeleteAvatar -> {
                        if (user == null || pass == null) return@setOnMenuItemClickListener true
                        workingOnAvatar = true
                        php(c, "?action=delete_avatar",
                            com.android.volley.Response.Listener { res ->
                                workingOnAvatar = false
                                if (res.length >= 7 && res.substring(0, 7) == "welcome") {
                                    avatar = null
                                    var editor = sp.edit()
                                    editor.remove(exAvatar)
                                    editor.apply()
                                    updateUserInfo()
                                } else when (res) {
                                    "notFound" -> toast(R.string.notFound)
                                    "incomInfo" -> toast(R.string.incomInfo)
                                    else -> toast(R.string.unknownError)
                                }
                            }, com.android.volley.Response.ErrorListener {
                                workingOnAvatar = false
                                toast(R.string.unknownError)
                            }, phpParams(user!!, pass!!), "delete_avatar"
                        )
                        return@setOnMenuItemClickListener true
                    }
                }
                return@setOnMenuItemClickListener false
            }
            popup.inflate(if (avatar == null) R.menu.avatar_edit_click_1 else R.menu.avatar_edit_click_2)
            popup.show()
        }
        hUSignOut.setOnClickListener {
            if (signedIn) AlertDialog.Builder(this).apply {
                setTitle(R.string.hUSignOut)
                setMessage(R.string.hUSignOutMsg)
                setPositiveButton(R.string.yes) { _, _ ->
                    userForm(false)
                    signOut()
                }
                setNegativeButton(R.string.no) { _, _ -> }
            }.create().show()
        }
        if (!dirLtr) {
            hUserFormButtonTV.typeface = anbFont
            hUserFormButtonTV.typeface = anbFont
            divUserTV1.typeface = anbFont
            for (h in hUHints) {
                h.typeface = anbFont
                h.textSize = dm.density * 14f
            }
            hUSIButtonTV.typeface = anbFont
            hUSUButtonTV.typeface = anbFont
            hUSIButtonTV.textSize = dm.density * 18f
            hUSUButtonTV.textSize = dm.density * 18f
        }
        hUMailHint.setOnClickListener { hUMail.requestFocus() }
        hUPassHint.setOnClickListener { hUPass.requestFocus() }
        hUPassRepHint.setOnClickListener { hUPassRep.requestFocus() }
        hUFNameHint.setOnClickListener { hUFName.requestFocus() }
        hULNameHint.setOnClickListener { hULName.requestFocus() }

        // Sync
        syncHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message?) {
                if (msg == null) return
                when (msg.what) {
                    1 -> {
                        Toast.makeText(c, msg.obj as String, Toast.LENGTH_LONG).show()
                    }
                    0 -> {
                    }
                }
            }
        }

        // FloatingActionButtons
        hFAB1.setOnClickListener {
            explode(c, it, 444, R.drawable.circle_cp, 0.35f, 2.22f)
            if (canTransact()) editor(c, null, 0)
            else if (subjects.isNullOrEmpty()) Toast.makeText(
                c,
                R.string.noSubjects,
                Toast.LENGTH_SHORT
            ).show()
        }
        hFAB2.setOnClickListener {
            explode(c, it, 444, R.drawable.circle_cpd, 0.35f, 2.22f)
            if (canTransact()) editor(c, null, 1)
            else if (subjects.isNullOrEmpty()) Toast.makeText(
                c,
                R.string.noSubjects,
                Toast.LENGTH_SHORT
            ).show()
        }

        // Handler
        handler = object : Handler() {
            override fun handleMessage(msg: Message?) {
                if (msg == null) return
                when (msg.what) {
                    Work.VIEW_ALL -> {
                        when (msg.arg1) {
                            0 -> banks = msg.obj as ArrayList<Bank>
                            1 -> chests = msg.obj as ArrayList<Chest>
                            2 -> cashes = msg.obj as ArrayList<Cash>
                            3 -> incomes = msg.obj as ArrayList<Income>
                            4 -> expenses = msg.obj as ArrayList<Expense>
                            5 -> contacts = msg.obj as ArrayList<Contact>
                            6 -> relatives = msg.obj as ArrayList<Relative>
                            7 -> subjects = msg.obj as ArrayList<Subject>
                            8 -> projects = msg.obj as ArrayList<Project>
                        }
                        checkRoomLoaded()
                    }
                    Work.VIEW_ONE -> when (msg.arg1) {
                        Work.ADD_NEW_ITEM -> when (msg.arg2) {
                            0 -> {
                                val income = msg.obj as Income
                                incomes!!.add(income)
                                iSavedIds.add(View.generateViewId())
                                //incomeAdapter!!.notifyItemInserted(incomes!!.size - 1)
                                //if (incomes!!.size > 1) hIncomes.smoothScrollToPosition(incomes!!.size - 2)
                                sortIncomes()
                                incomeAdapter!!.notifyDataSetChanged()
                                checkRoomLoaded(false)
                                navigate(1)
                            }
                            1 -> {
                                val expense = msg.obj as Expense
                                expenses!!.add(expense)
                                eSavedIds.add(View.generateViewId())
                                //expenseAdapter!!.notifyItemInserted(expenses!!.size - 1)
                                //if (expenses!!.size > 1) hExpenses.smoothScrollToPosition(expenses!!.size - 2)
                                sortExpenses()
                                expenseAdapter!!.notifyDataSetChanged()
                                checkRoomLoaded(false)
                                navigate(2)
                            }
                        }
                    }
                    Work.INSERT_ONE -> Work(
                        c, handler, Work.VIEW_ONE, entityType(msg.arg1),
                        listOf(msg.obj as Long, Work.ADD_NEW_ITEM, msg.arg1)
                    ).start()
                    Work.UPDATE_ONE -> when (msg.arg2) {
                        0 -> {
                            try {
                                IAdapter.saved(findViewById(iSavedIds[msg.arg1]))
                            } catch (ignored: Exception) {
                            }
                            checkRoomLoaded(false)
                        }
                        1 -> {
                            try {
                                EAdapter.saved(findViewById(eSavedIds[msg.arg1]))
                            } catch (ignored: Exception) {
                            }
                            checkRoomLoaded(false)
                        }
                    }
                    Work.DELETE_ONE -> when (msg.arg2) {
                        0 -> if (incomes != null && incomes!!.size > msg.arg1) {
                            incomes!!.remove(incomes!![msg.arg1])
                            incomeAdapter!!.notifyItemRemoved(msg.arg1)
                            object : CountDownTimer(notifyItemRemovedDur, notifyItemRemovedDur) {
                                override fun onTick(p0: Long) {}
                                override fun onFinish() {
                                    incomeAdapter!!.notifyDataSetChanged()
                                }
                            }.start()
                            checkRoomLoaded(false)
                        }
                        1 -> if (expenses != null && expenses!!.size > msg.arg1) {
                            expenses!!.remove(expenses!![msg.arg1])
                            expenseAdapter!!.notifyItemRemoved(msg.arg1)
                            object : CountDownTimer(notifyItemRemovedDur, notifyItemRemovedDur) {
                                override fun onTick(p0: Long) {}
                                override fun onFinish() {
                                    expenseAdapter!!.notifyDataSetChanged()
                                }
                            }.start()
                            checkRoomLoaded(false)
                        }
                    }
                }
            }
        }

        // Lists
        hIncomes.setHasFixedSize(false)
        hExpenses.setHasFixedSize(false)
        hIncomesManager = LinearLayoutManager(c)
        hExpensesManager = LinearLayoutManager(c)
        hIncomes.layoutManager = hIncomesManager
        hExpenses.layoutManager = hExpensesManager
        hIncomes.addItemDecoration(IAdapter.itemDecoration)
        hExpenses.addItemDecoration(EAdapter.itemDecoration)
        hIncomes.setOnTouchListener { view, event -> swipe(event); return@setOnTouchListener false }
        hExpenses.setOnTouchListener { view, event -> swipe(event); return@setOnTouchListener false }
        hBalance.setOnTouchListener { view, event -> swipe(event); return@setOnTouchListener false }
        hChart1Link.setOnClickListener { }
        hChart2Link.setOnClickListener { }
        hChart3Link.setOnClickListener { }

        // Editor
        hEDate1.setOnClickListener {
            if (!showingEditor) return@setOnClickListener
            if (dirLtr) GregorianDatePickerDialog.newInstance(
                { view, year, monthOfYear, dayOfMonth ->
                    hEYear = year
                    hEMonth = monthOfYear
                    hEDay = dayOfMonth
                    updateEDateTVs()
                }, hEYear, hEMonth, hEDay
            ).show(supportFragmentManager, "hEDate1")
            else HijriDatePickerDialog.newInstance(
                { view, year, monthOfYear, dayOfMonth ->
                    hEYear = year
                    hEMonth = monthOfYear
                    hEDay = dayOfMonth
                    updateEDateTVs()
                }, hEYear, hEMonth, hEDay
            ).show(supportFragmentManager, "hEDate1")
        }
        hEDate2.setOnClickListener {
            TimePickerDialog.newInstance(
                { view, hourOfDay, minute, second ->
                    hEHour = hourOfDay
                    hEMinute = minute
                    updateEDateTVs()
                }, hEHour, hEMinute, hESecond, true
            ).show(supportFragmentManager, "hEDate2")
        }
        var amountAdapter = ArrayAdapter(c, R.layout.spn_currency_1, currencyNames)
        amountAdapter.setDropDownViewResource(R.layout.basic_spn_1_dd)
        hEAmountCur.adapter = amountAdapter
        for (m in hEMarks)
            m.setColorFilter(ContextCompat.getColor(c, R.color.basicSPNMark))

        // SaveChanges / AddNew Button
        addNewButtonTV.typeface = anbFont
        addNewButtonBG.setOnClickListener {
            if (!checkEditor()) {
                Toast.makeText(c, R.string.allFields, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            when (editorMode) {
                0 -> {
                    if (incomes == null || editingIt == -1) return@setOnClickListener
                    if (incomes!!.size <= editingIt) return@setOnClickListener
                    incomes!![editingIt].date = hETimeinMillis()
                    incomes!![editingIt].amount = hEAmount.text.toString().toDouble()
                    incomes!![editingIt].currency =
                        resources.getStringArray(R.array.currencies)[hEAmountCur.selectedItemPosition]
                    incomes!![editingIt].subjectId = if (hESubject.selectedItemPosition == 0) -1
                    else subjects!![hESubject.selectedItemPosition - 1].id
                    incomes!![editingIt].contactId = if (hEContact.selectedItemPosition == 0) -1
                    else contacts!![hEContact.selectedItemPosition - 1].id
                    var treas = getTreasuryByPos(hETreasury.selectedItemPosition)
                    when {
                        treas is Bank -> {
                            incomes!![editingIt].treasuryType = 0
                            incomes!![editingIt].treasuryId = treas.id
                        }
                        treas is Chest -> {
                            incomes!![editingIt].treasuryType = 1
                            incomes!![editingIt].treasuryId = treas.id
                        }
                        treas is Cash -> {
                            incomes!![editingIt].treasuryType = 2
                            incomes!![editingIt].treasuryId = treas.id
                        }
                    }
                    incomes!![editingIt].behalf = if (hEBehalf.selectedItemPosition == 0) -1
                    else relatives!![hEBehalf.selectedItemPosition - 1].id
                    incomes!![editingIt].projectId = if (hEProject.selectedItemPosition == 0) -1
                    else projects!![hEProject.selectedItemPosition - 1].id
                    incomes!![editingIt].notes = hENotes.text.toString()
                    Work(
                        c, handler, Work.UPDATE_ONE, INCOME,
                        listOf(incomes!![editingIt], editingIt, 0)
                    ).start()
                    sortIncomes()
                    incomeAdapter!!.notifyDataSetChanged()
                }
                1 -> {
                    if (expenses == null || editingIt == -1) return@setOnClickListener
                    if (expenses!!.size <= editingIt) return@setOnClickListener
                    expenses!![editingIt].date = hETimeinMillis()
                    expenses!![editingIt].amount = hEAmount.text.toString().toDouble()
                    expenses!![editingIt].currency =
                        resources.getStringArray(R.array.currencies)[hEAmountCur.selectedItemPosition]
                    expenses!![editingIt].subjectId =
                        if (hESubject.selectedItemPosition == 0) -1
                        else subjects!![hESubject.selectedItemPosition - 1].id
                    expenses!![editingIt].contactId =
                        if (hEContact.selectedItemPosition == 0) -1
                        else contacts!![hEContact.selectedItemPosition - 1].id
                    var treas = getTreasuryByPos(hETreasury.selectedItemPosition)
                    when {
                        treas is Bank -> {
                            expenses!![editingIt].treasuryType = 0
                            expenses!![editingIt].treasuryId = treas.id
                        }
                        treas is Chest -> {
                            expenses!![editingIt].treasuryType = 1
                            expenses!![editingIt].treasuryId = treas.id
                        }
                        treas is Cash -> {
                            expenses!![editingIt].treasuryType = 2
                            expenses!![editingIt].treasuryId = treas.id
                        }
                    }
                    expenses!![editingIt].behalf = if (hEBehalf.selectedItemPosition == 0) -1
                    else relatives!![hEBehalf.selectedItemPosition - 1].id
                    expenses!![editingIt].projectId =
                        if (hEProject.selectedItemPosition == 0) -1
                        else projects!![hEProject.selectedItemPosition - 1].id
                    expenses!![editingIt].notes = hENotes.text.toString()
                    Work(
                        c, handler, Work.UPDATE_ONE, EXPENSE,
                        listOf(expenses!![editingIt], editingIt, 1)
                    ).start()
                    sortExpenses()
                    expenseAdapter!!.notifyDataSetChanged()
                }
                2 -> {
                    var treas = getTreasuryByPos(hETreasury.selectedItemPosition)
                    var income = Income(
                        hETimeinMillis(), hEAmount.text.toString().toDouble(),
                        resources.getStringArray(R.array.currencies)[hEAmountCur.selectedItemPosition],
                        if (hESubject.selectedItemPosition == 0) -1 else subjects!![hESubject.selectedItemPosition - 1].id,
                        if (hEContact.selectedItemPosition == 0) -1 else contacts!![hEContact.selectedItemPosition - 1].id,
                        when {
                            treas is Bank -> 0; treas is Chest -> 1; treas is Cash -> 2; else -> -1
                        }, when {
                            treas is Bank -> treas.id; treas is Chest -> treas.id; treas is Cash -> treas.id;else -> -1
                        },
                        if (hEBehalf.selectedItemPosition == 0) -1 else relatives!![hEBehalf.selectedItemPosition - 1].id,
                        false, null,
                        if (hEProject.selectedItemPosition == 0) -1 else projects!![hEProject.selectedItemPosition - 1].id,
                        hENotes.text.toString(), now(), now()
                    )
                    Work(c, handler, Work.INSERT_ONE, INCOME, listOf(income, 0)).start()
                }
                3 -> {
                    var treas = getTreasuryByPos(hETreasury.selectedItemPosition)
                    var expense = Expense(
                        hETimeinMillis(), hEAmount.text.toString().toDouble(),
                        resources.getStringArray(R.array.currencies)[hEAmountCur.selectedItemPosition],
                        if (hESubject.selectedItemPosition == 0) -1 else subjects!![hESubject.selectedItemPosition - 1].id,
                        if (hEContact.selectedItemPosition == 0) -1 else contacts!![hEContact.selectedItemPosition - 1].id,
                        when {
                            treas is Bank -> 0; treas is Chest -> 1; treas is Cash -> 2; else -> -1
                        }, when {
                            treas is Bank -> treas.id; treas is Chest -> treas.id; treas is Cash -> treas.id;else -> -1
                        },
                        if (hEBehalf.selectedItemPosition == 0) -1 else relatives!![hEBehalf.selectedItemPosition - 1].id,
                        false, null,
                        if (hEProject.selectedItemPosition == 0) -1 else projects!![hEProject.selectedItemPosition - 1].id,
                        hENotes.text.toString(), now(), now()
                    )
                    Work(c, handler, Work.INSERT_ONE, EXPENSE, listOf(expense, 1)).start()
                }
            }
            showEditor(false)
        }
        adIncomeDrw = ContextCompat.getDrawable(c, R.drawable.income_1)!!.mutate()
        adIncomeDrw.setColorFilter(
            ContextCompat.getColor(c, R.color.hADDrw), PorterDuff.Mode.SRC_IN
        )
        adExpenseDrw = ContextCompat.getDrawable(c, R.drawable.expense_2)!!.mutate()
        adExpenseDrw.setColorFilter(
            ContextCompat.getColor(c, R.color.hADDrw), PorterDuff.Mode.SRC_IN
        )
        if (!dirLtr) {
            editor2Header.typeface = anbFont
            editor2Header.textSize = dm.density * 40f
            for (h in hEHints) {
                h.typeface = anbFont
                editor2Header.textSize = dm.density * 28f
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadRoom()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val anCon = va(motor1, "translationX", loadDur, 10f, 0f)
        anCon.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                windowManager.defaultDisplay.getMetrics(dm)
                updateNavigator(navigator, false, curPage)
            }
        })
    }

    override fun onBackPressed() {
        if (showingUserForm) {
            userForm(false); return; }
        if (navOpened) {
            nav(false); return; }
        if (showingEditor) {
            when (editorMode) {
                0 -> {
                    var inc = incomes!![editingIt]
                    if (inc.date == hETimeinMillis() &&
                        inc.amount == hEAmount.text.toString().toDouble() &&
                        inc.currency == resources.getStringArray(R.array.currencies)[hEAmountCur.selectedItemPosition] &&
                        inc.subjectId == subjects!![hESubject.selectedItemPosition - 1].id &&
                        inc.contactId == contacts!![hEContact.selectedItemPosition - 1].id
                    ) {
                        showEditor(false); return; }
                }
                1 -> {
                    var exp = expenses!![editingIt]
                    if (exp.date == hETimeinMillis() &&
                        exp.amount == hEAmount.text.toString().toDouble() &&
                        exp.currency == resources.getStringArray(R.array.currencies)[hEAmountCur.selectedItemPosition] &&
                        exp.subjectId == subjects!![hESubject.selectedItemPosition - 1].id &&
                        exp.contactId == contacts!![hEContact.selectedItemPosition - 1].id
                    ) {
                        showEditor(false); return; }
                }
                else -> if (hEAmount.text.toString() == "" && hESubject.selectedItemPosition == 0 &&
                    hEContact.selectedItemPosition == 0 && hETreasury.selectedItemPosition == 0 &&
                    hEBehalf.selectedItemPosition == 0 && hEProject.selectedItemPosition == 0 &&
                    hENotes.text.toString() == ""
                ) {
                    showEditor(false); return; }
            }
            var ask = AlertDialog.Builder(this).apply {
                setIcon(if (editorMode == 0 || editorMode == 2) adIncomeDrw else adExpenseDrw)
                setTitle(R.string.discardChanges)
                setMessage(R.string.discardChangesMsg)
                setPositiveButton(R.string.yes) { _, _ -> showEditor(false) }
                setNegativeButton(R.string.no) { _, _ -> }
            }
            ask.create().show()
            return
        }
        if (!firstBackToExit) {
            firstBackToExit = true
            Toast.makeText(c, R.string.toExit, Toast.LENGTH_SHORT).show()
            val exit = va(motor1, "translationX", 4000, 4f, 0f)
            exit.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    firstBackToExit = false
                }
            }); return
        }
        moveTaskToBack(true)
        Process.killProcess(Process.myPid())
        exitProcess(1)//System.exit(1)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        swipe(event)
        return super.onTouchEvent(event)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Usually "data" in Kotlin and "intent" in Java
        if (resultCode != RESULT_OK) return//data.data == null
        when (requestCode) {//pickAvatarReq, captureAvatarReq -> //
            pickAvatarReq -> if (data!!.data != null) try {
                workingOnAvatar = true
                uploadAvatar(BitmapFactory.decodeStream(contentResolver.openInputStream(data.data!!)))
            } catch (ignored: Exception) {
                toast(R.string.couldNotLoadAvatar)
                workingOnAvatar = false
            } else toast(R.string.couldNotLoadAvatar)
            captureAvatarReq -> if (data!!.extras != null) try {
                workingOnAvatar = true
                uploadAvatar(data.extras!!.get("data") as Bitmap)
            } catch (ignored: Exception) {
                toast(R.string.couldNotLoadAvatar)
                workingOnAvatar = false
            } else toast(R.string.couldNotLoadAvatar)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when (requestCode) {
            permES -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) pickAvatar()
        }
    }


    fun navigate(into: Int) {
        var to = into
        if (to < 0) to = 0
        if (to > 2) to = 2
        var wScreen = dm.widthPixels.toFloat()
        if (!dirLtr) wScreen = 0f - wScreen
        cancelNavig()
        anNavig = AnimatorSet().setDuration(navigateDur)
        anNavig!!.playTogether(
            ObjectAnimator.ofFloat(
                hPages[0], "translationX", when (to) {
                    1 -> -wScreen; 2 -> -wScreen * 2f; else -> 0f; }
            ), ObjectAnimator.ofFloat(
                hPages[1], "translationX", when (to) {
                    1 -> 0f; 2 -> -wScreen; else -> wScreen; }
            ), ObjectAnimator.ofFloat(
                hPages[2], "translationX", when (to) {
                    1 -> wScreen; 2 -> 0f; else -> wScreen * 2f; }
            )
        )
        anNavig!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                if (roomLoaded && !chart2Loaded && to == 1) calculateIncomeChart()
                if (roomLoaded && !chart3Loaded && to == 2) calculateExpenseChart()
                curPage = to
                anNavig = null
            }
        })
        anNavig!!.start()
        updateNavigator(navigator, true, to)
    }

    fun cancelNavig() {
        if (anNavig != null) try {
            anNavig!!.cancel()
        } catch (ignored: Exception) {
        }
        anNavig = null
    }

    fun nav(b: Boolean) {
        if (navOpening || navOpened == b) return
        navOpening = true
        var before = if (b) -dm.widthPixels.toFloat() else 0f
        var after = if (b) 0f else -dm.widthPixels.toFloat()
        if (!dirLtr) {
            before = 0f - before
            after = 0f - after
        }
        var openDur: Long = 222
        var openSDs: ArrayList<Long> = arrayListOf(79, 165, 242)
        if (b) for (n in navbar) n.visibility = View.VISIBLE
        else {
            hPages[curPage].visibility = View.VISIBLE
            navigator.visibility = View.VISIBLE
            headerTV.visibility = View.VISIBLE
            for (t in tbIconAll) t.visibility = View.VISIBLE
            for (f in hFABs) f.visibility = View.VISIBLE
            openSDs.reverse()
        }

        for (s in hPages) oa(
            s, "translationY", openDur,
            if (b) dm.heightPixels.toFloat() else 0f,
            if (b) 0 else openSDs[0]
        )
        var tbIconDest = dm.widthPixels.toFloat()
        if (!dirLtr) tbIconDest = 0f - tbIconDest
        for (t in 0 until tbIconAll.size) va(
            tbIconAll[t], "translationX", openDur,
            if (b) 0f else tbIconDest / 2f,
            if (b) tbIconDest / 2f else 0f,
            (t * 44).toLong()
        )
        for (u in userAll) {
            var destScale = if (u != circleUserShadow) 1f else 1.2f
            va(u, "scaleX", openDur, if (b) 0f else destScale, if (b) destScale else 0f)
            va(u, "scaleY", openDur, if (b) 0f else destScale, if (b) destScale else 0f)
            if (u != circleUserShadow)
                va(u, "rotation", openDur, if (b) -180f else 0f, if (b) 0f else -180f)
        }
        var headerTVDest = dm.widthPixels.toFloat() / -2f
        if (!dirLtr) headerTVDest = 0f - headerTVDest
        va(
            headerTV, "translationX", openDur,
            if (b) 0f else headerTVDest,
            if (b) headerTVDest else 0f
        )
        oa(hFAB1, "translationX", openDur, if (b) -headerTVDest else 0f, if (b) 0 else 75)
        oa(hFAB2, "translationX", openDur, if (b) -headerTVDest else 0f, if (b) 75 else 0)
        var open1 = AnimatorSet().setDuration(openDur)
        open1.startDelay = openSDs[0]
        open1.playTogether(
            ObjectAnimator.ofFloat(hNavBtn2IV, "translationX", before, after),
            ObjectAnimator.ofFloat(hNavBtn4IV, "translationX", before, after),
            ObjectAnimator.ofFloat(
                hNavVLine, "translationY",
                if (b) -dm.heightPixels.toFloat() else 0f, if (b) 0f else -dm.heightPixels.toFloat()
            )
        )
        var open2 = AnimatorSet().setDuration(openDur)
        open2.startDelay = openSDs[1]
        open2.playTogether(
            ObjectAnimator.ofFloat(hNavBtn1IV, "translationX", before, after),
            ObjectAnimator.ofFloat(hNavBtn3IV, "translationX", before, after),
            ObjectAnimator.ofFloat(hNavBtn2TV, "translationX", before, after),
            ObjectAnimator.ofFloat(hNavBtn4TV, "translationX", before, after)
        )
        var open3 = AnimatorSet().setDuration(openDur)
        open3.startDelay = openSDs[2]
        open3.playTogether(
            ObjectAnimator.ofFloat(hNavBtn1TV, "translationX", before, after),
            ObjectAnimator.ofFloat(hNavBtn3TV, "translationX", before, after),
            ObjectAnimator.ofFloat(hNavHLine1, "translationX", before, after)
        )
        var open = AnimatorSet()
        open.playTogether(open1, open2, open3)
        open.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                if (b) {
                    hPages[curPage].visibility = View.GONE
                    navigator.visibility = View.GONE
                    headerTV.visibility = View.GONE
                    for (t in tbIconAll) t.visibility = View.GONE
                    for (f in hFABs) f.visibility = View.GONE
                } else for (n in navbar) n.visibility = View.GONE
                navOpened = b
                navOpening = false
            }
        })
        open.start()
    }

    fun loadRoom() {
        roomLoaded = false
        banks = null
        chests = null
        banks = null
        cashes = null
        incomes = null
        expenses = null
        contacts = null
        relatives = null
        subjects = null
        projects = null
        chart2Loaded = false
        chart3Loaded = false
        Work(c, handler, Work.VIEW_ALL, BANK, listOf(0)).start()
        Work(c, handler, Work.VIEW_ALL, CHEST, listOf(1)).start()
        Work(c, handler, Work.VIEW_ALL, CASH, listOf(2)).start()
        Work(c, handler, Work.VIEW_ALL, INCOME, listOf(3)).start()
        Work(c, handler, Work.VIEW_ALL, EXPENSE, listOf(4)).start()
        Work(c, handler, Work.VIEW_ALL, CONTACT, listOf(5)).start()
        Work(c, handler, Work.VIEW_ALL, RELATIVE, listOf(6)).start()
        Work(c, handler, Work.VIEW_ALL, SUBJECT, listOf(7)).start()
        Work(c, handler, Work.VIEW_ALL, PROJECT, listOf(8)).start()
    }

    fun checkRoomLoaded(onlyForUpdate: Boolean = false) {
        if (banks != null && chests != null && cashes != null && incomes != null && expenses != null && contacts != null &&
            relatives != null && subjects != null && projects != null
        ) {
            roomLoaded = true
            calculateBalance()
            arrangeIncomes()
            arrangeExpenses()
            if (onlyForUpdate) return

            var subjectNames = ArrayList<String>()
            subjectNames.add("")
            for (s in subjects!!) subjectNames.add(s.name)
            var subjectAdapter = ArrayAdapter(c, R.layout.h_spinner_1, subjectNames)
            subjectAdapter.setDropDownViewResource(R.layout.basic_spn_1_dd)
            hESubject.adapter = subjectAdapter

            var contactNames = ArrayList<String>()
            contactNames.add("")
            for (s in contacts!!) contactNames.add("${s.firstName} ${s.lastName}")
            var contactAdapter = ArrayAdapter(c, R.layout.h_spinner_1, contactNames)
            contactAdapter.setDropDownViewResource(R.layout.basic_spn_1_dd)
            hEContact.adapter = contactAdapter

            var treasuries = ArrayList<String>()
            treasuries.add("")
            for (b in banks!!) treasuries.add(if (b.name != null) b.name else fixCardNumber(b.cardNumber))
            for (t in 0 until chests!!.size)
                treasuries.add(
                    when {
                        chests!![t].name != null -> chests!![t].name
                        chests!![t].subscriptionCode != null -> chests!![t].subscriptionCode!!
                        else -> "Chest ${t + 1}"
                    }
                )
            for (s in 0 until cashes!!.size)
                treasuries.add("Cash ${s + 1} (${cashes!![s].initial} ${cashes!![s].initialCurrency})")
            treasurySizes = listOf(banks!!.size, chests!!.size, cashes!!.size)
            var treasuryAdapter = ArrayAdapter(c, R.layout.h_spinner_1, treasuries)
            treasuryAdapter.setDropDownViewResource(R.layout.basic_spn_1_dd)
            hETreasury.adapter = treasuryAdapter

            var relativeNames = ArrayList<String>()
            relativeNames.add(resources.getString(R.string.hMyself))
            for (r in relatives!!) relativeNames.add("${r.firstName} ${r.lastName}")
            var relativeAdapter = ArrayAdapter(c, R.layout.h_spinner_1, relativeNames)
            relativeAdapter.setDropDownViewResource(R.layout.basic_spn_1_dd)
            hEBehalf.adapter = relativeAdapter

            var projectNames = ArrayList<String>()
            projectNames.add("")
            for (p in projects!!) projectNames.add("${p.name}")
            var projectAdapter = ArrayAdapter(c, R.layout.h_spinner_1, projectNames)
            projectAdapter.setDropDownViewResource(R.layout.basic_spn_1_dd)
            hEProject.adapter = projectAdapter

            sync()
        }
    }

    fun calculateBalance() {
        if (hBalance.childCount != 0) hBalance.removeAllViews()
        if (banks == null || chests == null || cashes == null) return
        if (banks!!.size == 0 && chests!!.size == 0 && cashes!!.size == 0) return
        Collections.sort(banks!!, Sort.SortBanks())
        Collections.sort(chests!!, Sort.SortChests())
        Collections.sort(cashes!!, Sort.SortCashes())

        val anyChart = AnyChartView(c)
        hBalance.addView(
            anyChart, ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        APIlib.getInstance().setActiveAnyChartView(anyChart)
        //anyChart.setProgressBar(findViewById(R.id.progress_bar))
        val pie: Pie = AnyChart.pie()
        pie.setOnClickListener(object : ListenersInterface.OnClickListener(arrayOf("x", "value")) {
            override fun onClick(event: Event) {
                /*Toast.makeText(
                    this@Home, event.data.get("x").toString() + ":" + event.data.get("value"), Toast.LENGTH_SHORT
                ).show()*/
            }
        })

        val data: ArrayList<DataEntry> = ArrayList()
        for (b in 0 until banks!!.size) {
            var balance = banks!![b].initial
            if (incomes != null) for (i in incomes!!)
                if (i.treasuryType == (0).toByte() && i.treasuryId == banks!![b].id)
                    balance += i.amount
            if (expenses != null) for (e in expenses!!)
                if (e.treasuryType == (0).toByte() && e.treasuryId == banks!![b].id)
                    balance -= e.amount
            if (balance < 0.0) balance = 0.0
            data.add(
                ValueDataEntry(
                    if (banks!![b].name != "") banks!![b].name else resources.getString(R.string.hChartCardAltern) + (b + 1),
                    balance
                )
            )
        }
        for (s in 0 until chests!!.size) {
            var balance = chests!![s].initial
            if (incomes != null) for (i in incomes!!)
                if (i.treasuryType == (1).toByte() && i.treasuryId == chests!![s].id)
                    balance += i.amount
            if (expenses != null) for (e in expenses!!)
                if (e.treasuryType == (1).toByte() && e.treasuryId == chests!![s].id)
                    balance -= e.amount
            if (balance < 0.0) balance = 0.0
            data.add(
                ValueDataEntry(
                    if (chests!![s].name != "") chests!![s].name else resources.getString(R.string.hChartChestAltern) + (s + 1),
                    balance
                )
            )
        }
        for (h in 0 until cashes!!.size) {
            var balance = cashes!![h].initial
            if (incomes != null) for (i in incomes!!)
                if (i.treasuryType == (2).toByte() && i.treasuryId == cashes!![h].id)
                    balance += i.amount
            if (expenses != null) for (e in expenses!!)
                if (e.treasuryType == (2).toByte() && e.treasuryId == cashes!![h].id)
                    balance -= e.amount
            if (balance < 0.0) balance = 0.0
            data.add(
                ValueDataEntry(resources.getString(R.string.hChartCashAltern) + (h + 1), balance)
            )
        }
        pie.data(data)
        pie.background(chartBG)
        //pie.fill("#FDAB01").stroke(chartBG)

        pie.labels().position("outside")
        pie.legend().title().enabled(false)
        /*pie.legend().title()// NECESSARY
            .text(resources.getString(R.string.hChartBalance))
            .fontSize(22f)
            .fontColor("#27337D")
            .padding(0.0, 0.0, dm.density * 20.0, 0.0)
            .fontWeight(7f)*/
        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)
        //anyChart.setBackgroundColor("#00000000")
        //anyChart.addCss("file:///android_asset/fix_chart_1.css")
        //anyChart.addScript("file:///android_asset/fix_chart_1.js")
        anyChart.setChart(pie)
        hBalanceTitle.visibility = View.VISIBLE
    }

    fun calculateIncomeChart() {
        if (hIncomeChart.childCount != 0) hIncomeChart.removeAllViews()
        if (incomes == null) return
        if (incomes!!.size == 0) return
        chart2Loaded = true
        val anyChart = AnyChartView(c)
        hIncomeChart.addView(
            anyChart, ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        APIlib.getInstance().setActiveAnyChartView(anyChart)
        //anyChart.setProgressBar(findViewById(R.id.progress_bar))
        var cartesian = AnyChart.column()
        var data = ArrayList<DataEntry>()
        var NOW = now()
        data.add(ValueDataEntry(hChartTime(NOW), IAdapter.sum(IAdapter.findIncomeByDay(NOW))))
        for (d in 0..10) {
            NOW -= aDay
            data.add(ValueDataEntry(hChartTime(NOW), IAdapter.sum(IAdapter.findIncomeByDay(NOW))))
        }
        data.reverse()

        var column = cartesian.column(data)
        column.fill("#FDAB01").stroke("#FDAB01")
        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(5.0)
            .format("{%Value}{groupsSeparator: }")
            .fontColor("#F9F9FB")
            .fontSize(15f)
            .background("#27337D 0.65")
        cartesian.animation(true)
        /*cartesian.title(resources.getString(R.string.incomeChartTitle))
        cartesian.title()
            .fontColor("#27337D")
            .fontSize(22f)
            .fontFamily("tahoma")
            .fontWeight(7f)*/
        cartesian.yScale().minimum(0.0)
        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }")
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)
        cartesian.background(chartBG)
        //cartesian.xAxis(0).title("Product")
        //cartesian.yAxis(0).title("Revenue")
        anyChart.setChart(cartesian)
        hIncomeChartTitle.visibility = View.VISIBLE
    }

    fun calculateExpenseChart() {
        if (hExpenseChart.childCount != 0) hExpenseChart.removeAllViews()
        if (expenses == null) return
        if (expenses!!.size == 0) return
        chart3Loaded = true
        val anyChart = AnyChartView(c)
        hExpenseChart.addView(
            anyChart, ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        APIlib.getInstance().setActiveAnyChartView(anyChart)
        //anyChart.setProgressBar(findViewById(R.id.progress_bar))
        var cartesian = AnyChart.column()
        var data = ArrayList<DataEntry>()
        var NOW = now()
        data.add(ValueDataEntry(hChartTime(NOW), EAdapter.sum(EAdapter.findExpenseByDay(NOW))))
        for (d in 0..10) {
            NOW -= aDay
            data.add(ValueDataEntry(hChartTime(NOW), EAdapter.sum(EAdapter.findExpenseByDay(NOW))))
        }
        data.reverse()

        var column = cartesian.column(data)
        column.fill("#27337D").stroke("#27337D")//#FDAB01
        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(5.0)
            .format("{%Value}{groupsSeparator: }")
            .fontColor("#F9F9FB")
            .fontSize(15f)
            .background("#27337D 0.8")
        cartesian.animation(true)
        /*cartesian.title(resources.getString(R.string.expenseChartTitle))
        cartesian.title()
            .fontColor("#27337D")
            .fontSize(22f)
            .fontFamily("tahoma")
            .fontWeight(7f)*/
        cartesian.yScale().minimum(0.0)
        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }")
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)
        cartesian.background(chartBG)
        //cartesian.xAxis(0).title("Product")
        //cartesian.yAxis(0).title("Revenue")
        anyChart.setChart(cartesian)
        hExpenseChartTitle.visibility = View.VISIBLE
    }

    fun hChartTime(date: Long): String {
        if (dirLtr) {
            var cal = Calendar.getInstance()
            cal.timeInMillis = date
            return "${cal[Calendar.DAY_OF_MONTH]} ${monthNamesAbbr[cal[Calendar.MONTH]]}"
        } else {
            var cal = UmmalquraCalendar()
            cal.timeInMillis = date
            return "${cal[UmmalquraCalendar.DAY_OF_MONTH]} ${monthNamesAbbr[cal[UmmalquraCalendar.MONTH]]}"
        }
    }

    fun arrangeIncomes() {
        iSavedIds.clear()
        for (i in incomes!!) iSavedIds.add(View.generateViewId())
        sortIncomes()
        incomeAdapter = IAdapter(c, incomes!!, this)
        hIncomes.adapter = incomeAdapter
    }

    fun arrangeExpenses() {
        eSavedIds.clear()
        for (e in expenses!!) eSavedIds.add(View.generateViewId())
        sortExpenses()
        expenseAdapter = EAdapter(c, expenses!!, this)
        hExpenses.adapter = expenseAdapter
    }

    fun sortIncomes() {
        Collections.sort(incomes, Sort.SortIncomes())
        Collections.sort(incomes, Sort.SortIncomes(1))
        incomes!!.reverse()
    }

    fun sortExpenses() {
        Collections.sort(expenses, Sort.SortExpenses())
        Collections.sort(expenses, Sort.SortExpenses(1))
        expenses!!.reverse()
    }

    fun checkEditor() =
        hEYear != -1 && hEMonth != -1 && hEDay != -1 && hEHour != -1 && hEMinute != -1 && hEAmount.text.toString() != ""
                && hESubject.selectedItemPosition != 0 && hETreasury.selectedItemPosition != 0

    var x1 = 0f
    var divPoses: FloatArray? = null
    fun swipe(event: MotionEvent) {
        if (event.pointerCount != 2 && !navOpened) when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                cancelNavig()
                divPoses = FloatArray(hPages.size)
                for (d in 0 until divPoses!!.size) divPoses!![d] = hPages[d].translationX
                x1 = event.x
            }
            MotionEvent.ACTION_MOVE -> if (divPoses != null) {
                for (d in 0 until hPages.size)
                    hPages[d].translationX = divPoses!![d] + (event.x - x1)
                val navigMax = dm.widthPixels / 2f
                if ((event.x - x1) < -navigMax || (event.x - x1) > navigMax) goSwipe(event)
            }
            MotionEvent.ACTION_UP -> goSwipe(event)
        }
    }

    fun goSwipe(event: MotionEvent) {
        val navigMin = dm.widthPixels / 6f
        when {
            (event.x - x1) < -navigMin -> navigate(curPage + 1)
            (event.x - x1) > navigMin -> navigate(curPage - 1)
            else -> navigate(curPage)
        }
        divPoses = null
    }

    fun hETimeinMillis() = if (dirLtr) {
        val cul = Calendar.getInstance()
        cul.set(hEYear, hEMonth, hEDay, hEHour, hEMinute, hESecond)
        cul.timeInMillis
    } else UmmalquraCalendar(
        hEYear, hEMonth, hEDay, hEHour, hEMinute, hESecond
    ).timeInMillis

    fun canTransact() = incomes != null && expenses != null &&
            ((banks != null && banks!!.size > 0) || (chests != null && chests!!.size > 0) || (cashes != null && cashes!!.size > 0))
            && (subjects != null && subjects!!.size > 0)

    fun userForm(b: Boolean, dur: Long = 555) {
        if (movingUserForm || showingUserForm == b || !navOpened) return
        movingUserForm = true
        showingUserForm = b
        cover(cover, b)
        if (b) {
            hUserForm.visibility = View.VISIBLE
            for (l in hULines) l.scaleX = 0.65f
        }
        var scroll = va(
            hUserForm, "translationY", dur,
            if (b) dm.heightPixels.toFloat() else 0f,
            if (b) 0f else dm.heightPixels.toFloat()
        )
        scroll.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                movingUserForm = false
                if (!b) {
                    hUPass.setText("")
                    hUPassRep.setText("")
                    hUserForm.visibility = View.GONE
                } else for (l in hULines) oa(l, "scaleX", 650, 1f)
                if (signedIn) preEditProfile()
            }
        })
        if (b) cover.setOnClickListener { userForm(false) }
        else cover.setOnClickListener { }
    }

    fun signedIn(b: Boolean) {
        signedIn = b
        hUserFormButtonTV.text =
            resources.getString(if (b) R.string.hEditProfile else R.string.hSignIn)
        hUComplete(b)
        hUSignUp.visibility = if (b) View.GONE else View.VISIBLE
        hUSignOut.visibility = if (b) View.VISIBLE else View.GONE
        updateUserInfo()
    }

    fun userFormIncomplete(): Boolean {
        if (!signedIn) {
            if (hUComplete) return hUMail.text.toString() == "" || hUPass.text.toString() == "" ||
                    hUPassRep.text.toString() == "" || hUFName.text.toString() == "" || hULName.text.toString() == ""
            else return hUMail.text.toString() == "" || hUPass.text.toString() == ""
        } else return hUMail.text.toString() == ""
    }

    fun checkPWIncompatibility() = hUComplete && hUPass.text.toString() != hUPassRep.text.toString()

    fun signIn(u: String, p: String, signUp: Boolean = false, toast: Boolean = true) {
        if (signingIn) return
        signingIn = true
        var params = phpParams(u, p)
        if (signUp) {
            params["first_name"] = hUFName.text.toString()
            params["last_name"] = hULName.text.toString()
        } else if (signedIn) {
            var changed = false
            if (hUFName.text.toString() != fName) {
                params["first_name"] = hUFName.text.toString()
                changed = true
            }
            if (hULName.text.toString() != lName) {
                params["last_name"] = hULName.text.toString()
                changed = true
            }
            if (hUMail.text.toString() != user) {
                params["new_user"] = hUMail.text.toString()
                changed = true
            }
            if (hUPass.text.toString() != pass && hUPass.text.toString() != "" &&
                hUPass.text.toString() == hUPassRep.text.toString()
            ) {
                params["new_pass"] = hUPass.text.toString()
                changed = true
            }
            if (!changed) {
                signingIn = false
                userForm(false)
                return
            }
        }
        php(
            c, "?action=" + if (signedIn) "edit_profile" else if (!signUp) "sign_in" else "sign_up",
            com.android.volley.Response.Listener { res ->
                signingIn = false
                if (res.length >= 7 && res.substring(0, 7) == "welcome") {
                    var split = res.substring(7).split("<separator>")
                    user = u
                    pass = p
                    fName = if (split.size > 1) split[1] else null
                    lName = if (split.size > 2) split[2] else null
                    userId = if (split.size > 0) split[0].toLong() else null
                    userType = if (split.size > 3) split[3] else null
                    avatar = if (split.size > 4) (if (split[4] != "") split[4] else null) else null
                    var editor = sp.edit()
                    editor.putString(exUser, user)
                    editor.putString(exPass, pass)
                    if (fName != null) editor.putString(exFName, fName)
                    else editor.remove(exFName)
                    if (lName != null) editor.putString(exLName, lName)
                    else editor.remove(exLName)
                    if (userId != null) editor.putLong(exUID, userId!!)
                    else editor.remove(exUID)
                    if (userType != null) editor.putString(exUserType, userType!!)
                    else editor.remove(exUserType)
                    if (split.size > 4 && split[4] != "") editor.putString(exAvatar, avatar)
                    else editor.remove(exAvatar)
                    editor.apply()
                    signedIn(true)
                    if (showingUserForm) userForm(false)
                    sync()
                } else if (toast) when (res) {
                    "alreadyExists" -> toast(R.string.alreadyExists)
                    "notFound" -> toast(R.string.notFound)
                    "incomInfo" -> toast(R.string.incomInfo)
                    else -> toast(R.string.unknownError)
                }
            },
            com.android.volley.Response.ErrorListener {
                signingIn = false
                if (toast) toast(R.string.unknownError)
            }, params, if (!signUp) "sign_in" else "sign_up", 10000
        )
    }

    fun preEditProfile() {
        hUMail.setText(if (user != null) user else "")
        hUPass.setText("")
        hUPassRep.setText("")
        hUFName.setText(if (fName != null) fName else "")
        hULName.setText(if (lName != null) lName else "")
    }

    fun updateUserInfo(b: Boolean = signedIn) {
        divUserTV1.text = if (b && (fName != null || lName != null)) {
            (if (fName != null) fName else "") + " " + (if (lName != null) lName else "")
        } else resources.getString(R.string.guest)
        divUserTV2.text = if (user != null) user else ""
        divUserTV2.visibility = if (user != null) View.VISIBLE else View.GONE
        glideAvatar(this, circleUserBG)
    }

    fun hUComplete(b: Boolean) {
        hUComplete = b
        for (a in hUAdditional) a.visibility = if (b) View.VISIBLE else View.GONE
        hUSignUp.text = resources.getString(if (b) R.string.hSignIn else R.string.hUSignUp)
        hUSIButtonTV.text = resources.getString(
            if (signedIn) R.string.hSignInAfter
            else if (b) R.string.hUSignUp
            else R.string.hSignIn
        )
    }

    fun signOut() {
        user = null
        pass = null
        fName = null
        lName = null
        userId = null
        userType = null
        avatar = null
        var editor = sp.edit()
        editor.remove(exUser)
        editor.remove(exPass)
        editor.remove(exFName)
        editor.remove(exLName)
        editor.remove(exUID)
        editor.remove(exUserType)
        editor.remove(exAvatar)
        editor.remove(exLastSync)
        editor.apply()
        hUMail.setText("")
        signedIn(false)
    }

    fun sync() {
        /*if (Sync.working || !signedIn || user == null || pass == null) return
        if (incomes == null || expenses == null || banks == null || chests == null || cashes == null
            || contacts == null || relatives == null || subjects == null || projects == null
        ) return*/
        Sync(c, user!!, pass!!, sp.getLong(exLastSync, 0)).start()
        // incomes!!, expenses!!, banks!!, chests!!, cashes!!, contacts!!, relatives!!, subjects!!, projects!!
    }

    fun pickAvatar() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, pickAvatarReq)
    }

    fun uploadAvatar(gotIcon: Bitmap?) {
        if (gotIcon == null) {
            toast(R.string.couldNotLoadAvatar); return; }
        var icon = gotIcon!!
        if (icon.width != icon.height) {
            lateinit var size: IntArray
            var position = intArrayOf(0, 0)
            if (icon.width > icon.height) {
                size = intArrayOf(icon.height, icon.height)
                position[0] = ((icon.width - icon.height) / 2).toInt()
            } else {
                size = intArrayOf(icon.width, icon.width)
                position[1] = ((icon.height - icon.width) / 2).toInt()
            }
            icon = Bitmap.createBitmap(icon, position[0], position[1], size[0], size[1])
        }
        if (user == null || pass == null) return
        var params = phpParams(user!!, pass!!)
        params["new_avatar"] = imageToString(icon)
        php(
            c, "?action=upload_avatar", com.android.volley.Response.Listener { res ->
                workingOnAvatar = false
                if (res.substring(0, 7) == "welcome") {
                    avatar = res.substring(7)
                    var editor = sp.edit()
                    if (avatar != null) editor.putString(exAvatar, avatar!!)
                    else editor.remove(exAvatar)
                    editor.apply()
                    updateUserInfo()
                } else when (res) {
                    "notFound" -> toast(R.string.notFound)
                    "incomInfo" -> toast(R.string.incomInfo)
                    else -> toast(R.string.unknownError)
                }
            }, com.android.volley.Response.ErrorListener {
                workingOnAvatar = false
                toast(R.string.unknownError)
            }, params, "upload_avatar", 15000
        )
    }
}
