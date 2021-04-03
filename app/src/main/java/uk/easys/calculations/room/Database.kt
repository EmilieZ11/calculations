package uk.easys.calculations.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.easys.calculations.room.Entities.*

@Database(
    entities = [
        Income::class, Expense::class, Debt::class, Credit::class,
        Bank::class, Chest::class, Cash::class,
        Contact::class, Relative::class, Subject::class, Project::class
    ], version = 1, exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}