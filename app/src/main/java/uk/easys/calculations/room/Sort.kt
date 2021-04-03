package uk.easys.calculations.room

import java.util.Comparator
import uk.easys.calculations.room.Entities.*

class Sort {
    class SortIncomes(val by: Int = 0) : Comparator<Income> {
        override fun compare(a: Income, b: Income) = when (by) {
            1 -> a.date.compareTo(b.date)
            else -> a.dateCreated.compareTo(b.dateCreated)
        }
    }

    class SortExpenses(val by: Int = 0) : Comparator<Expense> {
        override fun compare(a: Expense, b: Expense) = when (by) {
            1 -> a.date.compareTo(b.date)
            else -> a.dateCreated.compareTo(b.dateCreated)
        }
    }


    class SortBanks(val by: Int = 0) : Comparator<Bank> {
        override fun compare(a: Bank, b: Bank) = when (by) {
            1 -> a.initial.compareTo(b.initial)
            else -> a.dateCreated.compareTo(b.dateCreated)
        }
    }

    class SortChests(val by: Int = 0) : Comparator<Chest> {
        override fun compare(a: Chest, b: Chest) = when (by) {
            1 -> a.initial.compareTo(b.initial)
            else -> a.dateCreated.compareTo(b.dateCreated)
        }
    }

    class SortCashes(val by: Int = 0) : Comparator<Cash> {
        override fun compare(a: Cash, b: Cash) = when (by) {
            1 -> a.initial.compareTo(b.initial)
            else -> a.dateCreated.compareTo(b.dateCreated)
        }
    }


    class SortContacts(val by: Int = 0) : Comparator<Contact> {
        override fun compare(a: Contact, b: Contact) = when (by) {
            1 -> a.firstName.compareTo(b.firstName)
            2 -> a.lastName.compareTo(b.lastName)
            else -> a.dateCreated.compareTo(b.dateCreated)
        }
    }

    class SortRelatives(val by: Int = 0) : Comparator<Relative> {
        override fun compare(a: Relative, b: Relative) = when (by) {
            1 -> a.firstName.compareTo(b.firstName)
            2 -> a.lastName.compareTo(b.lastName)
            3 -> a.relativity.compareTo(b.relativity)
            else -> a.dateCreated.compareTo(b.dateCreated)
        }
    }

    class SortSubjects(val by: Int = 0) : Comparator<Subject> {
        override fun compare(a: Subject, b: Subject) = when (by) {
            1 -> a.name.compareTo(b.name)
            2 -> a.repetition.compareTo(b.repetition)
            else -> a.dateCreated.compareTo(b.dateCreated)
        }
    }

    class SortProjects(val by: Int = 0) : Comparator<Project> {
        override fun compare(a: Project, b: Project) = when (by) {
            1 -> a.name.compareTo(b.name)
            2 -> a.dateStarted.compareTo(b.dateStarted)
            3 -> a.dateEnded.compareTo(b.dateEnded)
            else -> a.dateCreated.compareTo(b.dateCreated)
        }
    }
}