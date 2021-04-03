package uk.easys.calculations.room

import androidx.room.*
import androidx.room.Dao
import uk.easys.calculations.room.Entities.*

@Dao
interface Dao {// DON'T CTRL + ALT + L
    // VIEW ALL
    @Query("SELECT * FROM $INCOME") fun getIncomes(): List<Income>
    @Query("SELECT * FROM $EXPENSE") fun getExpenses(): List<Expense>
    @Query("SELECT * FROM $DEBT") fun getDebts(): List<Debt>
    @Query("SELECT * FROM $CREDIT") fun getCredits(): List<Credit>
    @Query("SELECT * FROM $BANK") fun getBanks(): List<Bank>
    @Query("SELECT * FROM $CHEST") fun getChests(): List<Chest>
    @Query("SELECT * FROM $CASH") fun getCashes(): List<Cash>
    @Query("SELECT * FROM $CONTACT") fun getContacts(): List<Contact>
    @Query("SELECT * FROM $RELATIVE") fun getRelatives(): List<Relative>
    @Query("SELECT * FROM $SUBJECT") fun getSubjects(): List<Subject>
    @Query("SELECT * FROM $PROJECT") fun getProjects(): List<Project>

    // VIEW ONE
    @Query("SELECT * FROM $INCOME WHERE $ID LIKE :id LIMIT 1") fun getIncome(id: Long): Income
    @Query("SELECT * FROM $EXPENSE WHERE $ID LIKE :id LIMIT 1") fun getExpense(id: Long): Expense
    @Query("SELECT * FROM $DEBT WHERE $ID LIKE :id LIMIT 1") fun getDebt(id: Long): Debt
    @Query("SELECT * FROM $CREDIT WHERE $ID LIKE :id LIMIT 1") fun getCredit(id: Long): Credit
    @Query("SELECT * FROM $BANK WHERE $ID LIKE :id LIMIT 1") fun getBank(id: Long): Bank
    @Query("SELECT * FROM $CHEST WHERE $ID LIKE :id LIMIT 1") fun getChest(id: Long): Chest
    @Query("SELECT * FROM $CASH WHERE $ID LIKE :id LIMIT 1") fun getCash(id: Long): Cash
    @Query("SELECT * FROM $CONTACT WHERE $ID LIKE :id LIMIT 1") fun getContact(id: Long): Contact
    @Query("SELECT * FROM $RELATIVE WHERE $ID LIKE :id LIMIT 1") fun getRelative(id: Long): Relative
    @Query("SELECT * FROM $SUBJECT WHERE $ID LIKE :id LIMIT 1") fun getSubject(id: Long): Subject
    @Query("SELECT * FROM $PROJECT WHERE $ID LIKE :id LIMIT 1") fun getProject(id: Long): Project
    
    // INSERT ALL
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertIncomes(item: List<Income>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertExpenses(item: List<Expense>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertDebts(item: List<Debt>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertCredits(item: List<Credit>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertBanks(item: List<Bank>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertChests(item: List<Chest>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertCashes(item: List<Cash>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertContacts(item: List<Contact>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertRelatives(item: List<Relative>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertSubjects(item: List<Subject>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertProjects(item: List<Project>)

    // INSERT ONE
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertIncome(item: Income): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertExpense(item: Expense): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertDebt(item: Debt): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertCredit(item: Credit): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertBank(item: Bank): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertChest(item: Chest): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertCash(item: Cash): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertContact(item: Contact): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertRelative(item: Relative): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertSubject(item: Subject): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertProject(item: Project): Long

    // UPDATE ALL
    @Update fun updateIncomes(item: List<Income>)
    @Update fun updateExpenses(item: List<Expense>)
    @Update fun updateDebts(item: List<Debt>)
    @Update fun updateCredits(item: List<Credit>)
    @Update fun updateBanks(item: List<Bank>)
    @Update fun updateChests(item: List<Chest>)
    @Update fun updateCashes(item: List<Cash>)
    @Update fun updateContacts(item: List<Contact>)
    @Update fun updateRelatives(item: List<Relative>)
    @Update fun updateSubjects(item: List<Subject>)
    @Update fun updateProjects(item: List<Project>)
    
    // UPDATE ONE
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateIncome(item: Income)
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateExpense(item: Expense)
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateDebt(item: Debt)
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateCredit(item: Credit)
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateBank(item: Bank)
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateChest(item: Chest)
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateCash(item: Cash)
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateContact(item: Contact)
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateRelative(item: Relative)
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateSubject(item: Subject)
    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateProject(item: Project)

    // DELETE MASS
    @Delete fun deleteIncomes(item: List<Income>)
    @Delete fun deleteExpenses(item: List<Expense>)
    @Delete fun deleteDebts(item: List<Debt>)
    @Delete fun deleteCredits(item: List<Credit>)
    @Delete fun deleteBanks(item: List<Bank>)
    @Delete fun deleteChests(item: List<Chest>)
    @Delete fun deleteCashes(item: List<Cash>)
    @Delete fun deleteContacts(item: List<Contact>)
    @Delete fun deleteRelatives(item: List<Relative>)
    @Delete fun deleteSubjects(item: List<Subject>)
    @Delete fun deleteProjects(item: List<Project>)

    // DELETE ONE
    @Delete fun deleteIncome(item: Income)
    @Delete fun deleteExpense(item: Expense)
    @Delete fun deleteDebt(item: Debt)
    @Delete fun deleteCredit(item: Credit)
    @Delete fun deleteBank(item: Bank)
    @Delete fun deleteChest(item: Chest)
    @Delete fun deleteCash(item: Cash)
    @Delete fun deleteContact(item: Contact)
    @Delete fun deleteRelative(item: Relative)
    @Delete fun deleteSubject(item: Subject)
    @Delete fun deleteProject(item: Project)

    // REPLACE ALL
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceIncomes(item: List<Income>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceExpenses(item: List<Expense>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceDebts(item: List<Debt>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceCredits(item: List<Credit>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceBanks(item: List<Bank>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceChests(item: List<Chest>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceCashes(item: List<Cash>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceContacts(item: List<Contact>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceRelatives(item: List<Relative>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceSubjects(item: List<Subject>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceProjects(item: List<Project>)

    // REPLACE ONE
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceIncome(item: Income): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceExpense(item: Expense): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceDebt(item: Debt): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceCredit(item: Credit): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceBank(item: Bank): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceChest(item: Chest): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceCash(item: Cash): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceContact(item: Contact): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceRelative(item: Relative): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceSubject(item: Subject): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun replaceProject(item: Project): Long

    // DELETE BY ID
    @Query("DELETE FROM $INCOME WHERE $ID = :id") fun deleteIncomeById(id: Long)
    @Query("DELETE FROM $EXPENSE WHERE $ID = :id") fun deleteExpenseById(id: Long)
    @Query("DELETE FROM $DEBT WHERE $ID = :id") fun deleteDebtById(id: Long)
    @Query("DELETE FROM $CREDIT WHERE $ID = :id") fun deleteCreditById(id: Long)
    @Query("DELETE FROM $BANK WHERE $ID = :id") fun deleteBankById(id: Long)
    @Query("DELETE FROM $CHEST WHERE $ID = :id") fun deleteChestById(id: Long)
    @Query("DELETE FROM $CASH WHERE $ID = :id") fun deleteCashById(id: Long)
    @Query("DELETE FROM $CONTACT WHERE $ID = :id") fun deleteContactById(id: Long)
    @Query("DELETE FROM $RELATIVE WHERE $ID = :id") fun deleteRelativeById(id: Long)
    @Query("DELETE FROM $SUBJECT WHERE $ID = :id") fun deleteSubjectById(id: Long)
    @Query("DELETE FROM $PROJECT WHERE $ID = :id") fun deleteProjectById(id: Long)
}
