package uk.easys.calculations.room

import android.content.Context
import android.os.Handler
import androidx.room.Room
import uk.easys.calculations.Home
import uk.easys.calculations.room.Entities.*
/*import uk.easys.calculations.room.Entities.Companion.BANK
import uk.easys.calculations.room.Entities.Companion.CASH
import uk.easys.calculations.room.Entities.Companion.CHEST
import uk.easys.calculations.room.Entities.Companion.CONTACT
import uk.easys.calculations.room.Entities.Companion.CREDIT
import uk.easys.calculations.room.Entities.Companion.DEBT
import uk.easys.calculations.room.Entities.Companion.EXPENSE
import uk.easys.calculations.room.Entities.Companion.INCOME
import uk.easys.calculations.room.Entities.Companion.PROJECT
import uk.easys.calculations.room.Entities.Companion.RELATIVE
import uk.easys.calculations.room.Entities.Companion.SUBJECT*/
import java.lang.Exception

class Work(
    val c: Context,
    val handler: Handler?,
    val action: Int,
    val type: String,
    val values: List<Any>? = null
) : Thread() {
    companion object {
        // ACTIONS
        const val VIEW_ALL = 0
        const val VIEW_ONE = 1
        const val INSERT_ALL = 2
        const val INSERT_ONE = 3
        const val UPDATE_ALL = 4
        const val UPDATE_ONE = 5
        const val DELETE_MASS = 6
        const val DELETE_ONE = 7
        const val REPLACE_ALL = 8
        const val REPLACE_ONE = 9
        const val DELETE_BY_ID = 10
        const val DELETE_MASS_BY_ID = 11
        const val INSERT_BY_TYPE = 12
        const val UPDATE_BY_TYPE = 13
        const val DELETE_BY_TYPE = 14
        const val REPLACE_BY_TYPE = 15

        // PURPOSES
        const val ADD_NEW_ITEM = 0
    }

    override fun run() {
        var db = Room.databaseBuilder(c, Database::class.java, Home.dbName).build()
        var dao = db.dao()
        when (action) {
            VIEW_ALL -> if (handler != null) handler.obtainMessage(
                action,
                if (values != null && values.size > 0) values[0] as Int else 0,
                if (values != null && values.size > 1) values[1] as Int else 0,
                when (type) {
                    INCOME -> dao.getIncomes()
                    EXPENSE -> dao.getExpenses()
                    DEBT -> dao.getDebts()
                    CREDIT -> dao.getCredits()
                    BANK -> dao.getBanks()
                    CHEST -> dao.getChests()
                    CASH -> dao.getCashes()
                    CONTACT -> dao.getContacts()
                    RELATIVE -> dao.getRelatives()
                    SUBJECT -> dao.getSubjects()
                    PROJECT -> dao.getProjects()
                    else -> null
                }
            ).sendToTarget()
            VIEW_ONE -> if (values != null && values.isNotEmpty() && handler != null) handler.obtainMessage(
                action,
                if (values.size > 1) values[1] as Int else 0,
                if (values.size > 2) values[2] as Int else 0,
                when (type) {
                    INCOME -> dao.getIncome(values[0] as Long)
                    EXPENSE -> dao.getExpense(values[0] as Long)
                    DEBT -> dao.getDebt(values[0] as Long)
                    CREDIT -> dao.getCredit(values[0] as Long)
                    BANK -> dao.getBank(values[0] as Long)
                    CHEST -> dao.getChest(values[0] as Long)
                    CASH -> dao.getCash(values[0] as Long)
                    CONTACT -> dao.getContact(values[0] as Long)
                    RELATIVE -> dao.getRelative(values[0] as Long)
                    SUBJECT -> dao.getSubject(values[0] as Long)
                    PROJECT -> dao.getProject(values[0] as Long)
                    else -> null
                }
            ).sendToTarget()
            INSERT_ALL -> if (values != null && values.isNotEmpty()) {
                when (type) {
                    INCOME -> dao.insertIncomes(values[0] as List<Income>)
                    EXPENSE -> dao.insertExpenses(values[0] as List<Expense>)
                    DEBT -> dao.insertDebts(values[0] as List<Debt>)
                    CREDIT -> dao.insertCredits(values[0] as List<Credit>)
                    BANK -> dao.insertBanks(values[0] as List<Bank>)
                    CHEST -> dao.insertChests(values[0] as List<Chest>)
                    CASH -> dao.insertCashes(values[0] as List<Cash>)
                    CONTACT -> dao.insertContacts(values[0] as List<Contact>)
                    RELATIVE -> dao.insertRelatives(values[0] as List<Relative>)
                    SUBJECT -> dao.insertSubjects(values[0] as List<Subject>)
                    PROJECT -> dao.insertProjects(values[0] as List<Project>)
                    else -> (-1).toLong()
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            }
            INSERT_ONE -> if (values != null && values.isNotEmpty()) {
                var result = when (type) {
                    INCOME -> dao.insertIncome(values[0] as Income)
                    EXPENSE -> dao.insertExpense(values[0] as Expense)
                    DEBT -> dao.insertDebt(values[0] as Debt)
                    CREDIT -> dao.insertCredit(values[0] as Credit)
                    BANK -> dao.insertBank(values[0] as Bank)
                    CHEST -> dao.insertChest(values[0] as Chest)
                    CASH -> dao.insertCash(values[0] as Cash)
                    CONTACT -> dao.insertContact(values[0] as Contact)
                    RELATIVE -> dao.insertRelative(values[0] as Relative)
                    SUBJECT -> dao.insertSubject(values[0] as Subject)
                    PROJECT -> dao.insertProject(values[0] as Project)
                    else -> (-1).toLong()
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, result
                )?.sendToTarget()
            }
            UPDATE_ALL -> if (values != null && values.isNotEmpty()) {
                when (type) {
                    INCOME -> dao.updateIncomes(values[0] as List<Income>)
                    EXPENSE -> dao.updateExpenses(values[0] as List<Expense>)
                    DEBT -> dao.updateDebts(values[0] as List<Debt>)
                    CREDIT -> dao.updateCredits(values[0] as List<Credit>)
                    BANK -> dao.updateBanks(values[0] as List<Bank>)
                    CHEST -> dao.updateChests(values[0] as List<Chest>)
                    CASH -> dao.updateCashes(values[0] as List<Cash>)
                    CONTACT -> dao.updateContacts(values[0] as List<Contact>)
                    RELATIVE -> dao.updateRelatives(values[0] as List<Relative>)
                    SUBJECT -> dao.updateSubjects(values[0] as List<Subject>)
                    PROJECT -> dao.updateProjects(values[0] as List<Project>)
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            }
            UPDATE_ONE -> if (values != null && values.isNotEmpty()) {
                when (type) {
                    INCOME -> dao.updateIncome(values[0] as Income)
                    EXPENSE -> dao.updateExpense(values[0] as Expense)
                    DEBT -> dao.updateDebt(values[0] as Debt)
                    CREDIT -> dao.updateCredit(values[0] as Credit)
                    BANK -> dao.updateBank(values[0] as Bank)
                    CHEST -> dao.updateChest(values[0] as Chest)
                    CASH -> dao.updateCash(values[0] as Cash)
                    CONTACT -> dao.updateContact(values[0] as Contact)
                    RELATIVE -> dao.updateRelative(values[0] as Relative)
                    SUBJECT -> dao.updateSubject(values[0] as Subject)
                    PROJECT -> dao.updateProject(values[0] as Project)
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            }
            DELETE_MASS -> if (values != null && values.isNotEmpty()) {
                when (type) {
                    INCOME -> dao.deleteIncomes(values[0] as List<Income>)
                    EXPENSE -> dao.deleteExpenses(values[0] as List<Expense>)
                    DEBT -> dao.deleteDebts(values[0] as List<Debt>)
                    CREDIT -> dao.deleteCredits(values[0] as List<Credit>)
                    BANK -> dao.deleteBanks(values[0] as List<Bank>)
                    CHEST -> dao.deleteChests(values[0] as List<Chest>)
                    CASH -> dao.deleteCashes(values[0] as List<Cash>)
                    CONTACT -> dao.deleteContacts(values[0] as List<Contact>)
                    RELATIVE -> dao.deleteRelatives(values[0] as List<Relative>)
                    SUBJECT -> dao.deleteSubjects(values[0] as List<Subject>)
                    PROJECT -> dao.deleteProjects(values[0] as List<Project>)
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            }
            DELETE_ONE -> if (values != null && values.isNotEmpty()) {
                when (type) {
                    INCOME -> dao.deleteIncome(values[0] as Income)
                    EXPENSE -> dao.deleteExpense(values[0] as Expense)
                    DEBT -> dao.deleteDebt(values[0] as Debt)
                    CREDIT -> dao.deleteCredit(values[0] as Credit)
                    BANK -> dao.deleteBank(values[0] as Bank)
                    CHEST -> dao.deleteChest(values[0] as Chest)
                    CASH -> dao.deleteCash(values[0] as Cash)
                    CONTACT -> dao.deleteContact(values[0] as Contact)
                    RELATIVE -> dao.deleteRelative(values[0] as Relative)
                    SUBJECT -> dao.deleteSubject(values[0] as Subject)
                    PROJECT -> dao.deleteProject(values[0] as Project)
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            }
            REPLACE_ALL -> if (values != null && values.isNotEmpty()) {
                when (type) {
                    INCOME -> dao.replaceIncomes(values[0] as List<Income>)
                    EXPENSE -> dao.replaceExpenses(values[0] as List<Expense>)
                    DEBT -> dao.replaceDebts(values[0] as List<Debt>)
                    CREDIT -> dao.replaceCredits(values[0] as List<Credit>)
                    BANK -> dao.replaceBanks(values[0] as List<Bank>)
                    CHEST -> dao.replaceChests(values[0] as List<Chest>)
                    CASH -> dao.replaceCashes(values[0] as List<Cash>)
                    CONTACT -> dao.replaceContacts(values[0] as List<Contact>)
                    RELATIVE -> dao.replaceRelatives(values[0] as List<Relative>)
                    SUBJECT -> dao.replaceSubjects(values[0] as List<Subject>)
                    PROJECT -> dao.replaceProjects(values[0] as List<Project>)
                    else -> (-1).toLong()
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            }
            REPLACE_ONE -> if (values != null && values.isNotEmpty()) {
                var result = when (type) {
                    INCOME -> dao.replaceIncome(values[0] as Income)
                    EXPENSE -> dao.replaceExpense(values[0] as Expense)
                    DEBT -> dao.replaceDebt(values[0] as Debt)
                    CREDIT -> dao.replaceCredit(values[0] as Credit)
                    BANK -> dao.replaceBank(values[0] as Bank)
                    CHEST -> dao.replaceChest(values[0] as Chest)
                    CASH -> dao.replaceCash(values[0] as Cash)
                    CONTACT -> dao.replaceContact(values[0] as Contact)
                    RELATIVE -> dao.replaceRelative(values[0] as Relative)
                    SUBJECT -> dao.replaceSubject(values[0] as Subject)
                    PROJECT -> dao.replaceProject(values[0] as Project)
                    else -> (-1).toLong()
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, result
                )?.sendToTarget()
            }


            DELETE_BY_ID -> if (values != null && values.isNotEmpty()) {
                val id = values[0] as Long
                when (type) {
                    INCOME -> dao.deleteIncomeById(id)
                    EXPENSE -> dao.deleteExpenseById(id)
                    DEBT -> dao.deleteDebtById(id)
                    CREDIT -> dao.deleteCreditById(id)
                    BANK -> dao.deleteBankById(id)
                    CHEST -> dao.deleteChestById(id)
                    CASH -> dao.deleteCashById(id)
                    CONTACT -> dao.deleteContactById(id)
                    RELATIVE -> dao.deleteRelativeById(id)
                    SUBJECT -> dao.deleteSubjectById(id)
                    PROJECT -> dao.deleteProjectById(id)
                    else -> (-1).toLong()
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            }
            DELETE_MASS_BY_ID -> if (values != null && values.isNotEmpty()) {
                val ids = values[0] as List<Long>
                for (id in ids) when (type) {
                    INCOME -> dao.deleteIncomeById(id)
                    EXPENSE -> dao.deleteExpenseById(id)
                    DEBT -> dao.deleteDebtById(id)
                    CREDIT -> dao.deleteCreditById(id)
                    BANK -> dao.deleteBankById(id)
                    CHEST -> dao.deleteChestById(id)
                    CASH -> dao.deleteCashById(id)
                    CONTACT -> dao.deleteContactById(id)
                    RELATIVE -> dao.deleteRelativeById(id)
                    SUBJECT -> dao.deleteSubjectById(id)
                    PROJECT -> dao.deleteProjectById(id)
                    else -> (-1).toLong()
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            }


            INSERT_BY_TYPE -> if (values != null) try {
                var everything = values[0] as ArrayList<Any?>
                for (e in everything) when (e) {
                    is Income -> dao.insertIncome(e)
                    is Expense -> dao.insertExpense(e)
                    is Debt -> dao.insertDebt(e)
                    is Credit -> dao.insertCredit(e)
                    is Bank -> dao.insertBank(e)
                    is Chest -> dao.insertChest(e)
                    is Cash -> dao.insertCash(e)
                    is Contact -> dao.insertContact(e)
                    is Relative -> dao.insertRelative(e)
                    is Subject -> dao.insertSubject(e)
                    is Project -> dao.insertProject(e)
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            } catch (ignored: Exception) {
            }
            UPDATE_BY_TYPE -> if (values != null) try {
                var everything = values[0] as ArrayList<Any?>
                for (e in everything) when (e) {
                    is Income -> dao.updateIncome(e)
                    is Expense -> dao.updateExpense(e)
                    is Debt -> dao.updateDebt(e)
                    is Credit -> dao.updateCredit(e)
                    is Bank -> dao.updateBank(e)
                    is Chest -> dao.updateChest(e)
                    is Cash -> dao.updateCash(e)
                    is Contact -> dao.updateContact(e)
                    is Relative -> dao.updateRelative(e)
                    is Subject -> dao.updateSubject(e)
                    is Project -> dao.updateProject(e)
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            } catch (ignored: Exception) {
            }
            DELETE_BY_TYPE -> if (values != null) try {
                var everything = values[0] as ArrayList<Any?>
                for (e in everything) when (e) {
                    is Income -> dao.deleteIncome(e)
                    is Expense -> dao.deleteExpense(e)
                    is Debt -> dao.deleteDebt(e)
                    is Credit -> dao.deleteCredit(e)
                    is Bank -> dao.deleteBank(e)
                    is Chest -> dao.deleteChest(e)
                    is Cash -> dao.deleteCash(e)
                    is Contact -> dao.deleteContact(e)
                    is Relative -> dao.deleteRelative(e)
                    is Subject -> dao.deleteSubject(e)
                    is Project -> dao.deleteProject(e)
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            } catch (ignored: Exception) {
            }
            REPLACE_BY_TYPE -> if (values != null) try {
                var everything = values[0] as ArrayList<Any?>
                for (e in everything) when (e) {
                    is Income -> dao.replaceIncome(e)
                    is Expense -> dao.replaceExpense(e)
                    is Debt -> dao.replaceDebt(e)
                    is Credit -> dao.replaceCredit(e)
                    is Bank -> dao.replaceBank(e)
                    is Chest -> dao.replaceChest(e)
                    is Cash -> dao.replaceCash(e)
                    is Contact -> dao.replaceContact(e)
                    is Relative -> dao.replaceRelative(e)
                    is Subject -> dao.replaceSubject(e)
                    is Project -> dao.replaceProject(e)
                }
                handler?.obtainMessage(
                    action, if (values.size > 1) values[1] as Int else 0,
                    if (values.size > 2) values[2] as Int else 0, null
                )?.sendToTarget()
            } catch (ignored: Exception) {
            }
        }
        db.close()
    }
}