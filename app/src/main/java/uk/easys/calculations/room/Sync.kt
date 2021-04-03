package uk.easys.calculations.room

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.preference.PreferenceManager
import android.util.JsonReader
import androidx.room.Room
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Response.*
import com.google.gson.Gson
import uk.easys.calculations.Home
import uk.easys.calculations.adapters.*
import uk.easys.calculations.room.Entities.*
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.HashMap

class Sync(val c: Context, val user: String, val pass: String, val lastSync: Long = 0) : Thread() {
    lateinit var sp: SharedPreferences
    val types = listOf(
        "incomes", "expenses", "banks", "chests", "cashes",
        "contacts", "relatives", "subjects", "projects", "debts", "credits"
    )
    var edit: Edition? = null
    lateinit var incomes: List<Income>
    lateinit var expenses: List<Expense>
    lateinit var banks: List<Bank>
    lateinit var chests: List<Chest>
    lateinit var cashes: List<Cash>
    lateinit var contacts: List<Contact>
    lateinit var relatives: List<Relative>
    lateinit var subjects: List<Subject>
    lateinit var projects: List<Project>

    companion object {
        lateinit var editHandler: Handler
        const val phpSync = "https://easys.uk/calculations/sync.php"
        var working = false
    }

    override fun run() {
        sp = PreferenceManager.getDefaultSharedPreferences(c)
        Looper.prepare()
        editHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message?) {
                if (msg != null) when (msg.arg1) {
                    0 -> applyEdition(c, 1)
                    1 -> applyEdition(c, 2)
                    2 -> applyEdition(c, 3)
                    3 -> applyEdition(c, 4)
                }
            }
        }

        var db = Room.databaseBuilder(c, Database::class.java, Home.dbName).build()
        var dao = db.dao()
        val NOW = Home.now().toString()
        incomes = dao.getIncomes()
        expenses = dao.getExpenses()
        banks = dao.getBanks()
        chests = dao.getChests()
        cashes = dao.getCashes()
        contacts = dao.getContacts()
        relatives = dao.getRelatives()
        subjects = dao.getSubjects()
        projects = dao.getProjects()
        db.close()


        working = true
        var params = HashMap<String, String>()
        params["user"] = user
        params["pass"] = pass
        params[types[0]] = Gson().toJson(brief(incomes))
        params[types[1]] = Gson().toJson(brief(expenses))
        params[types[2]] = Gson().toJson(brief(banks))
        params[types[3]] = Gson().toJson(brief(chests))
        params[types[4]] = Gson().toJson(brief(cashes))
        params[types[5]] = Gson().toJson(brief(contacts))
        params[types[6]] = Gson().toJson(brief(relatives))
        params[types[7]] = Gson().toJson(brief(subjects))
        params[types[8]] = Gson().toJson(brief(projects))
        //params[types[9]] = Gson().toJson(brief(debts))
        //params[types[10]] = Gson().toJson(brief(credits))
        params["last_sync"] = lastSync.toString()
        params["now"] = NOW
        params["upload_type"] = "brief"

        Volley.newRequestQueue(c).add(
            object : StringRequest(
                Method.POST, phpSync, Listener { res ->
                    if (res.length > 4 && res.substring(0, 4) == "done") {
                        //Home.syncHandler.obtainMessage(1, res.substring(4)).sendToTarget()
                        edit = readEdition(res.substring(4))
                        applyEdition(c, 0)

                        var editor = sp.edit()
                        editor.putLong(Home.exLastSync, Home.now())
                        editor.apply()
                    }// else { }
                    working = false
                }, ErrorListener {
                    working = false
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams() = params
            }.setTag("sync").setRetryPolicy(
                DefaultRetryPolicy(
                    40000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
            )
        )
    }

    fun readEdition(res: String): Edition? {
        var reader = JsonReader(
            InputStreamReader(
                ByteArrayInputStream(res.toByteArray(Charset.forName("UTF-8"))), "UTF-8"
            )
        )
        try {
            var requests: ArrayList<Request> = ArrayList()
            var recreations: ArrayList<Recreation> = ArrayList()
            var deletions: ArrayList<Deletion> = ArrayList()
            var updates: ArrayList<Update> = ArrayList()
            var insertions: ArrayList<Insertion> = ArrayList()

            reader.beginObject()
            while (reader.hasNext()) when (reader.nextName()) {
                "REQUEST" -> {
                    reader.beginArray()
                    while (reader.hasNext())
                        requests.add(readReport(reader, 0) as Request)
                    reader.endArray()
                }
                "RECREATE" -> {
                    reader.beginArray()
                    while (reader.hasNext())
                        recreations.add(readReport(reader, 1) as Recreation)
                    reader.endArray()
                }
                "DELETE" -> {
                    reader.beginArray()
                    while (reader.hasNext())
                        deletions.add(readReport(reader, 2) as Deletion)
                    reader.endArray()
                }
                "UPDATE" -> {
                    reader.beginArray()
                    while (reader.hasNext())
                        updates.add(readAction(reader, 3) as Update)
                    reader.endArray()
                }
                "INSERT" -> {
                    reader.beginArray()
                    while (reader.hasNext())
                        insertions.add(readAction(reader, 4) as Insertion)
                    reader.endArray()
                }
                else -> reader.skipValue()
            }
            reader.endObject()
            return Edition(requests, recreations, deletions, updates, insertions)
        } catch (ignored: IOException) {
            return null
        } finally {
            reader.close()
        }
    }

    @Throws(IOException::class)
    fun readAction(reader: JsonReader, actionType: Int): Any? {
        var type = -1
        var id: Long = -1
        var date: Long = 0
        var amount = 0.0
        var currency = ""
        var subjectId: Long = -1
        var contactId: Long = -1
        var treasuryType: Byte = -1
        var treasuryId: Long = -1
        var behalf: Long = -1
        var isCheque = false
        var attachment = ""
        var projectId: Long = -1
        var name = ""
        var accountNumber = ""
        var cardNumber = ""
        var initial = 0.0
        var initialCurrency = ""
        var dateFounded: Long = -1
        var yearExpired = ""
        var monthExpired = ""
        var colour = ""
        var subscriptionCode = ""
        var dateEarned: Long = -1
        var firstName = ""
        var lastName = ""
        var phone = ""
        var email = ""
        var relativity: Byte = 0
        var repetition: Byte = 0
        var forCompany = false
        var dateStarted: Long = 0
        var dateEnded: Long = 0
        var repaymentDate: Long = 0
        var notify = false
        var notes = ""
        var dateCreated: Long = 0
        var dateModified: Long = 0

        reader.beginObject()
        while (reader.hasNext()) when (reader.nextName()) {
            "type" -> type = reader.nextInt()
            "id" -> id = reader.nextLong()
            "date" -> date = reader.nextLong()
            "amount" -> amount = reader.nextDouble()
            "currency" -> currency = reader.nextString()
            "subjectId" -> subjectId = reader.nextLong()
            "contactId" -> contactId = reader.nextLong()
            "treasuryType" -> treasuryType = reader.nextInt().toByte()
            "treasuryId" -> treasuryId = reader.nextLong()
            "behalf" -> behalf = reader.nextLong()
            "isCheque" -> isCheque = reader.nextString().toLowerCase() == "true"
            "attachment" -> attachment = reader.nextString()
            "projectId" -> projectId = reader.nextLong()
            "name" -> name = reader.nextString()
            "accountNumber" -> accountNumber = reader.nextString()
            "cardNumber" -> cardNumber = reader.nextString()
            "initial" -> initial = reader.nextDouble()
            "initialCurrency" -> initialCurrency = reader.nextString()
            "dateFounded" -> dateFounded = reader.nextLong()
            "yearExpired" -> yearExpired = reader.nextString()
            "monthExpired" -> monthExpired = reader.nextString()
            "colour" -> colour = reader.nextString()
            "subscriptionCode" -> subscriptionCode = reader.nextString()
            "dateEarned" -> dateEarned = reader.nextLong()
            "firstName" -> firstName = reader.nextString()
            "lastName" -> lastName = reader.nextString()
            "phone" -> phone = reader.nextString()
            "email" -> email = reader.nextString()
            "relativity" -> relativity = reader.nextInt().toByte()
            "repetition" -> repetition = reader.nextInt().toByte()
            "forCompany" -> forCompany = reader.nextString().toLowerCase() == "true"
            "dateStarted" -> dateStarted = reader.nextLong()
            "dateEnded" -> dateEnded = reader.nextLong()
            "repaymentDate" -> repaymentDate = reader.nextLong()
            "notify" -> notify = reader.nextString().toLowerCase() == "true"
            "notes" -> notes = reader.nextString()
            "dateCreated" -> dateCreated = reader.nextLong()
            "dateModified" -> dateModified = reader.nextLong()
            else -> reader.skipValue()
        }
        reader.endObject()

        var obj: Any? = when (type) {
            0 -> Income(
                date, amount, currency, subjectId, contactId, treasuryType, treasuryId,
                behalf, isCheque, attachment, projectId, notes, dateCreated, dateModified
            ).setId(id)
            1 -> Expense(
                date, amount, currency, subjectId, contactId, treasuryType, treasuryId,
                behalf, isCheque, attachment, projectId, notes, dateCreated, dateModified
            ).setId(id)
            2 -> Bank(
                name, accountNumber, cardNumber, initial, initialCurrency, dateFounded,
                yearExpired, monthExpired, colour, notes, dateCreated, dateModified
            ).setId(id)
            3 -> Chest(
                name, subscriptionCode, initial, initialCurrency, dateFounded,
                notes, dateCreated, dateModified
            ).setId(id)
            4 -> Cash(
                initial, initialCurrency, dateEarned, notes, dateCreated, dateModified
            ).setId(id)
            5 -> Contact(
                firstName, lastName, phone, email, accountNumber, cardNumber,
                notes, dateCreated, dateModified
            ).setId(id)
            6 -> Relative(
                firstName, lastName, relativity, phone, notes, dateCreated, dateModified
            ).setId(id)
            7 -> Subject(
                name, repetition, forCompany, notes, dateCreated, dateModified
            ).setId(id)
            8 -> Project(
                name, dateStarted, dateEnded, contactId, notes, dateCreated, dateModified
            ).setId(id)
            9 -> Debt(
                amount, currency, subjectId, contactId, repaymentDate, treasuryType,
                treasuryId, behalf, notify, notes, dateCreated, dateModified
            ).setId(id)
            10 -> Credit(
                amount, currency, subjectId, contactId, repaymentDate, treasuryType,
                treasuryId, behalf, notify, notes, dateCreated, dateModified
            ).setId(id)
            else -> null
        }
        return when (actionType) {
            3 -> Update(type, obj!!)
            4 -> Insertion(type, obj!!)
            else -> null
        }
    }

    @Throws(IOException::class)
    fun readReport(reader: JsonReader, actionType: Int = -1): Any? {
        var type = -1
        var id: Long = -1

        reader.beginArray()
        var i = 0
        while (reader.hasNext()) {
            when (i) {
                0 -> type = reader.nextInt()
                1 -> id = reader.nextLong()
            }
            i += 1
        }
        reader.endArray()

        return when (actionType) {
            0 -> Request(type, id)
            1 -> Recreation(type, id)
            2 -> Deletion(type, id)
            else -> null
        }
    }

    fun applyEdition(c: Context, what: Int) {
        var everything = ArrayList<Any?>()
        if (edit != null) when (what) {
            0 -> if (edit!!.requests != null) {
                val rIncomes = ArrayList<Income>()
                val rExpenses = ArrayList<Expense>()
                val rBanks = ArrayList<Bank>()
                val rChests = ArrayList<Chest>()
                val rCashes = ArrayList<Cash>()
                val rContacts = ArrayList<Contact>()
                val rRelatives = ArrayList<Relative>()
                val rSubjects = ArrayList<Subject>()
                val rProjects = ArrayList<Project>()
                //val rDebts = ArrayList<Debt>()
                //val rCredits = ArrayList<Credit>()

                for (e in edit!!.requests!!) when (e.type) {
                    0 -> IAdapter.getIncomeById(e.id, incomes)?.let { rIncomes.add(it) }
                    1 -> EAdapter.getExpenseById(e.id, expenses)?.let { rExpenses.add(it) }
                    2 -> T1Adapter.getBankById(e.id, banks)?.let { rBanks.add(it) }
                    3 -> T2Adapter.getChestById(e.id, chests)?.let { rChests.add(it) }
                    4 -> T3Adapter.getCashById(e.id, cashes)?.let { rCashes.add(it) }
                    5 -> CAdapter.getContactById(e.id, contacts)?.let { rContacts.add(it) }
                    6 -> RAdapter.getRelativeById(e.id, relatives)?.let { rRelatives.add(it) }
                    7 -> SAdapter.getSubjectById(e.id, subjects)?.let { rSubjects.add(it) }
                    8 -> PAdapter.getProjectById(e.id, projects)?.let { rProjects.add(it) }
                }

                var params = HashMap<String, String>()
                params["user"] = user
                params["pass"] = pass
                params[types[0]] = Gson().toJson(rIncomes)
                params[types[1]] = Gson().toJson(rExpenses)
                params[types[2]] = Gson().toJson(rBanks)
                params[types[3]] = Gson().toJson(rChests)
                params[types[4]] = Gson().toJson(rCashes)
                params[types[5]] = Gson().toJson(rContacts)
                params[types[6]] = Gson().toJson(rRelatives)
                params[types[7]] = Gson().toJson(rSubjects)
                params[types[8]] = Gson().toJson(rProjects)
                //params[types[9]] = Gson().toJson(rDebts)
                //params[types[10]] = Gson().toJson(rCredits)
                params["upload_type"] = "long"

                Volley.newRequestQueue(c).add(
                    object :
                        StringRequest(Method.POST, phpSync, Listener { res -> }, ErrorListener {}) {
                        @Throws(AuthFailureError::class)
                        override fun getParams() = params
                    }.setTag("post_sync").setRetryPolicy(
                        DefaultRetryPolicy(
                            40000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                        )
                    )
                )
                next(what)
            }
            1 -> if (edit!!.recreations != null) {
                for (e in edit!!.recreations!!) {
                    everything.add(recreate(e.type, e.id))
                    if (edit!!.deletions == null) edit!!.deletions = ArrayList()
                    edit!!.deletions!!.add(Deletion(e.type, e.id))
                }
                if (everything.isEmpty()) next(what)
                else Work(c, editHandler, Work.INSERT_BY_TYPE, "", listOf(everything, what)).start()
            } else next(what)
            2 -> if (edit!!.deletions != null) {
                var dByType = ArrayList<ArrayList<Long>>()
                for (t in types) dByType.add(ArrayList())
                for (e in 0 until edit!!.deletions!!.size) {
                    val dItems = dByType[edit!!.deletions!![e].type]
                    dItems.add(edit!!.deletions!![e].id)
                    dByType[edit!!.deletions!![e].type] = dItems
                }
                for (t in 0 until dByType.size) if (!dByType[t].isNullOrEmpty()) Work(
                    c, editHandler, Work.DELETE_MASS_BY_ID, strType(t), listOf(dByType[t], what)
                ).start()
                next(what)
            } else next(what)
            3 -> if (edit!!.updates != null) {
                for (e in edit!!.updates!!) everything.add(e.obj)
                if (everything.isEmpty()) next(what)
                else Work(
                    c, editHandler, Work.REPLACE_BY_TYPE, "", listOf(everything, what)
                ).start()// UPDATE_BY_TYPE
            } else next(what)
            4 -> if (edit!!.insertions != null) {
                for (e in edit!!.insertions!!) everything.add(e.obj)
                if (everything.isEmpty()) next(what)
                else Work(c, editHandler, Work.INSERT_BY_TYPE, "", listOf(everything, what)).start()
            } else next(what)
        }
    }

    fun next(what: Int) {
        editHandler.obtainMessage(0, what, 0, null).sendToTarget()
    }

    fun strType(type: Int) = when (type) {
        1 -> Entities.EXPENSE
        2 -> Entities.BANK
        3 -> Entities.CHEST
        4 -> Entities.CASH
        5 -> Entities.CONTACT
        6 -> Entities.RELATIVE
        7 -> Entities.SUBJECT
        8 -> Entities.PROJECT
        9 -> Entities.DEBT
        10 -> Entities.CREDIT
        else -> Entities.INCOME
    }

    fun brief(gotList: Any?): ArrayList<LongArray>? {
        if (gotList !is List<*>) return null
        val l: List<*> = gotList
        val a = ArrayList<LongArray>()
        if (l.isNotEmpty()) when (l[0]) {
            is Income -> for (i in l) a.add(longArrayOf((i as Income).id, i.dateModified))
            is Expense -> for (i in l) a.add(longArrayOf((i as Expense).id, i.dateModified))
            is Debt -> for (i in l) a.add(longArrayOf((i as Debt).id, i.dateModified))
            is Credit -> for (i in l) a.add(longArrayOf((i as Credit).id, i.dateModified))
            is Bank -> for (i in l) a.add(longArrayOf((i as Bank).id, i.dateModified))
            is Chest -> for (i in l) a.add(longArrayOf((i as Chest).id, i.dateModified))
            is Cash -> for (i in l) a.add(longArrayOf((i as Cash).id, i.dateModified))
            is Contact -> for (i in l) a.add(longArrayOf((i as Contact).id, i.dateModified))
            is Relative -> for (i in l) a.add(longArrayOf((i as Relative).id, i.dateModified))
            is Subject -> for (i in l) a.add(longArrayOf((i as Subject).id, i.dateModified))
            is Project -> for (i in l) a.add(longArrayOf((i as Project).id, i.dateModified))
        }
        return a
    }

    fun recreate(type: Int, id: Long): Any? = when (type) {
        0 -> {
            val g = IAdapter.getIncomeById(id, incomes)
            if (g != null) Income(
                g.date, g.amount, g.currency, g.subjectId, g.contactId, g.treasuryType,
                g.treasuryId, g.behalf, g.isCheque, g.attachment, g.projectId, g.notes,
                g.dateCreated, g.dateModified
            ) else null
        }
        1 -> {
            val g = EAdapter.getExpenseById(id, expenses)
            if (g != null) Expense(
                g.date, g.amount, g.currency, g.subjectId, g.contactId, g.treasuryType,
                g.treasuryId, g.behalf, g.isCheque, g.attachment, g.projectId, g.notes,
                g.dateCreated, g.dateModified
            ) else null
        }
        2 -> {
            val g = T1Adapter.getBankById(id, banks)
            if (g != null) Bank(
                g.name, g.accountNumber, g.cardNumber, g.initial, g.initialCurrency,
                g.dateFounded, g.yearExpired, g.monthExpired, g.colour, g.notes,
                g.dateCreated, g.dateModified
            ) else null
        }
        3 -> {
            val g = T2Adapter.getChestById(id, chests)
            if (g != null) Chest(
                g.name, g.subscriptionCode, g.initial, g.initialCurrency, g.dateFounded,
                g.notes, g.dateCreated, g.dateModified
            ) else null
        }
        4 -> {
            val g = T3Adapter.getCashById(id, cashes)
            if (g != null) Cash(
                g.initial, g.initialCurrency, g.dateEarned, g.notes, g.dateCreated,
                g.dateModified
            ) else null
        }
        5 -> {
            val g = CAdapter.getContactById(id, contacts)
            if (g != null) Contact(
                g.firstName, g.lastName, g.phone, g.email, g.accountNumber, g.cardNumber,
                g.notes, g.dateCreated, g.dateModified
            ) else null
        }
        6 -> {
            val g = RAdapter.getRelativeById(id, relatives)
            if (g != null) Relative(
                g.firstName, g.lastName, g.relativity, g.phone, g.notes, g.dateCreated,
                g.dateModified
            ) else null
        }
        7 -> {
            val g = SAdapter.getSubjectById(id, subjects)
            if (g != null) Subject(
                g.name, g.repetition, g.forCompany, g.notes, g.dateCreated, g.dateModified
            ) else null
        }
        8 -> {
            val g = PAdapter.getProjectById(id, projects)
            if (g != null) Project(
                g.name, g.dateStarted, g.dateEnded, g.contactId, g.notes, g.dateCreated,
                g.dateModified
            ) else null
        }
        else -> null
    }


    data class Edition(
        var requests: ArrayList<Request>?,
        var recreations: ArrayList<Recreation>?,
        var deletions: ArrayList<Deletion>?,
        var updates: ArrayList<Update>?,
        var insertions: ArrayList<Insertion>?
    )

    data class Request(val type: Int, val id: Long)
    data class Recreation(val type: Int, val id: Long)
    data class Deletion(val type: Int, val id: Long)
    data class Update(val type: Int, val obj: Any)
    data class Insertion(val type: Int, val obj: Any)
}