package uk.easys.calculations.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Entities {
    // Table Names
    public static final String INCOME = "income", EXPENSE = "expense", DEBT = "debt", CREDIT = "credit", BANK = "bank",
            CHEST = "chest", CASH = "cash", CONTACT = "contact", RELATIVE = "relative", SUBJECT = "subject", PROJECT = "project";
    // GLOBAL
    public static final String ID = "id", NOTES = "notes", DATE_CREATED = "date_created", DATE_MODIFIED = "date_modified";
    // Transactions
    public static final String DATE = "date", AMOUNT = "amount", CURRENCY = "currency", SUBJECT_ID = "subject_id"/*+Project*/,
            CONTACT_ID = "contact_id"/*+Project*/, TREASURY_TYPE = "treasury_type", TREASURY_ID = "treasury_id",
            BEHALF = "behalf", IS_CHEQUE = "is_cheque", ATTACHMENT = "attachment", PROJECT_ID = "project_id",
            REPAYMENT_DATE = "repayment_date", NOTIFY = "notify";
    // Treasury
    public static final String NAME = "name"/*+Project*/, ACCOUNT_NUMBER = "account_number"/*+Contact*/,
            CARD_NUMBER = "card_number"/*+Contact*/, INITIAL = "initial", INITIAL_CURRENCY = "initial_currency",
            DATE_FOUNDED = "date_founded", COLOUR = "colour", YEAR_EXPIRED = "year_expired", MONTH_EXPIRED = "month_expired",
            SUBSCRIPTION_CODE = "subscription_code", DATE_EARNED = "date_earned";
    // OTHER
    public static final String FIRST_NAME = "first_name", LAST_NAME = "last_name", PHONE = "phone", EMAIL = "email",
            RELATIVITY = "relativity", REPETITION = "repetition", FOR_COMPANY = "for_company", DATE_STARTED = "date_started",
            DATE_ENDED = "date_ended";


    // TRANSACTIONS
    @Entity
    public static class Income {
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = DATE)
        public long date;
        @ColumnInfo(name = AMOUNT)
        public double amount;
        @ColumnInfo(name = CURRENCY)
        public String currency;
        @ColumnInfo(name = SUBJECT_ID)
        public long subjectId;
        @ColumnInfo(name = CONTACT_ID)
        public long contactId;//Nullable
        @ColumnInfo(name = TREASURY_TYPE)
        public byte treasuryType;
        @ColumnInfo(name = TREASURY_ID)
        public long treasuryId;
        @ColumnInfo(name = BEHALF)
        public long behalf;//Nullable
        @ColumnInfo(name = IS_CHEQUE)
        public boolean isCheque;// = false
        @ColumnInfo(name = ATTACHMENT)
        public String attachment;//Nullable
        @ColumnInfo(name = PROJECT_ID)
        public long projectId;//Nullable
        @ColumnInfo(name = NOTES)
        public String notes;//Nullable
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Income(long date, double amount, String currency, long subjectId, long contactId, byte treasuryType, long treasuryId,
                      long behalf, boolean isCheque, String attachment, long projectId, String notes, long dateCreated, long dateModified) {
            this.date = date;
            this.amount = amount;
            this.currency = currency;
            this.subjectId = subjectId;
            this.contactId = contactId;
            this.treasuryType = treasuryType;
            this.treasuryId = treasuryId;
            this.behalf = behalf;
            this.isCheque = isCheque;
            this.attachment = attachment;
            this.projectId = projectId;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Income setId(long id) {
            this.id = id;
            return this;
        }
    }

    @Entity
    public static class Expense {
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = DATE)
        public long date;
        @ColumnInfo(name = AMOUNT)
        public double amount;
        @ColumnInfo(name = CURRENCY)
        public String currency;
        @ColumnInfo(name = SUBJECT_ID)
        public long subjectId;
        @ColumnInfo(name = CONTACT_ID)
        public long contactId;//Nullable
        @ColumnInfo(name = TREASURY_TYPE)
        public byte treasuryType;
        @ColumnInfo(name = TREASURY_ID)
        public long treasuryId;
        @ColumnInfo(name = BEHALF)
        public long behalf;//Nullable
        @ColumnInfo(name = IS_CHEQUE)
        public boolean isCheque;// = false
        @ColumnInfo(name = ATTACHMENT)
        public String attachment;//Nullable
        @ColumnInfo(name = PROJECT_ID)
        public long projectId;//Nullable
        @ColumnInfo(name = NOTES)
        public String notes;//Nullable
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Expense(long date, double amount, String currency, long subjectId, long contactId, byte treasuryType, long treasuryId,
                       long behalf, boolean isCheque, String attachment, long projectId, String notes, long dateCreated, long dateModified) {
            this.date = date;
            this.amount = amount;
            this.currency = currency;
            this.subjectId = subjectId;
            this.contactId = contactId;
            this.treasuryType = treasuryType;
            this.treasuryId = treasuryId;
            this.behalf = behalf;
            this.isCheque = isCheque;
            this.attachment = attachment;
            this.projectId = projectId;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Expense setId(long id) {
            this.id = id;
            return this;
        }
    }

    @Entity
    public static class Debt {//بدهی
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = AMOUNT)
        public double amount;
        @ColumnInfo(name = CURRENCY)
        public String currency;
        @ColumnInfo(name = SUBJECT_ID)
        public long subjectId;
        @ColumnInfo(name = CONTACT_ID)
        public long contactId;//Nullable
        @ColumnInfo(name = REPAYMENT_DATE)
        public long repaymentDate;
        @ColumnInfo(name = TREASURY_TYPE)
        public byte treasuryType;
        @ColumnInfo(name = TREASURY_ID)
        public long treasuryId;
        @ColumnInfo(name = BEHALF)
        public long behalf;//Nullable
        @ColumnInfo(name = NOTIFY)
        public boolean notify;
        @ColumnInfo(name = NOTES)
        public String notes;//Nullable
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Debt(double amount, String currency, long subjectId, long contactId, long repaymentDate, byte treasuryType,
                    long treasuryId, long behalf, boolean notify, String notes, long dateCreated, long dateModified) {
            this.amount = amount;
            this.currency = currency;
            this.subjectId = subjectId;
            this.contactId = contactId;
            this.repaymentDate = repaymentDate;
            this.treasuryType = treasuryType;
            this.treasuryId = treasuryId;
            this.behalf = behalf;
            this.notify = notify;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Debt setId(long id) {
            this.id = id;
            return this;
        }
    }

    @Entity
    public static class Credit {//طلب
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = AMOUNT)
        public double amount;
        @ColumnInfo(name = CURRENCY)
        public String currency;
        @ColumnInfo(name = SUBJECT_ID)
        public long subjectId;
        @ColumnInfo(name = CONTACT_ID)
        public long contactId;//Nullable
        @ColumnInfo(name = REPAYMENT_DATE)
        public long repaymentDate;
        @ColumnInfo(name = TREASURY_TYPE)
        public byte treasuryType;
        @ColumnInfo(name = TREASURY_ID)
        public long treasuryId;
        @ColumnInfo(name = BEHALF)
        public long behalf;//Nullable
        @ColumnInfo(name = NOTIFY)
        public boolean notify;
        @ColumnInfo(name = NOTES)
        public String notes;//Nullable
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Credit(double amount, String currency, long subjectId, long contactId, long repaymentDate, byte treasuryType,
                      long treasuryId, long behalf, boolean notify, String notes, long dateCreated, long dateModified) {
            this.amount = amount;
            this.currency = currency;
            this.subjectId = subjectId;
            this.contactId = contactId;
            this.repaymentDate = repaymentDate;
            this.treasuryType = treasuryType;
            this.treasuryId = treasuryId;
            this.behalf = behalf;
            this.notify = notify;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Credit setId(long id) {
            this.id = id;
            return this;
        }
    }


    // TREASURY
    @Entity
    public static class Bank {
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = NAME)
        public String name;
        @ColumnInfo(name = ACCOUNT_NUMBER)
        public String accountNumber;//Nullable
        @ColumnInfo(name = CARD_NUMBER)
        public String cardNumber;
        @ColumnInfo(name = INITIAL)
        public double initial;//Nullable
        @ColumnInfo(name = INITIAL_CURRENCY)
        public String initialCurrency;//Nullable
        @ColumnInfo(name = DATE_FOUNDED)
        public long dateFounded;
        @ColumnInfo(name = YEAR_EXPIRED)
        public String yearExpired;
        @ColumnInfo(name = MONTH_EXPIRED)
        public String monthExpired;
        @ColumnInfo(name = COLOUR)
        public String colour;
        @ColumnInfo(name = NOTES)
        public String notes;//Nullable
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Bank(String name, String accountNumber, String cardNumber, double initial, String initialCurrency,
                    long dateFounded, String yearExpired, String monthExpired, String colour, String notes,
                    long dateCreated, long dateModified) {
            this.name = name;
            this.accountNumber = accountNumber;
            this.cardNumber = cardNumber;
            this.initial = initial;
            this.initialCurrency = initialCurrency;
            this.dateFounded = dateFounded;
            this.yearExpired = yearExpired;
            this.monthExpired = monthExpired;
            this.colour = colour;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Bank setId(long id) {
            this.id = id;
            return this;
        }
    }

    @Entity
    public static class Chest {
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = NAME)
        public String name;
        @ColumnInfo(name = SUBSCRIPTION_CODE)
        public String subscriptionCode;//Nullable
        @ColumnInfo(name = INITIAL)
        public double initial;//Nullable
        @ColumnInfo(name = INITIAL_CURRENCY)
        public String initialCurrency;//Nullable
        @ColumnInfo(name = DATE_FOUNDED)
        public long dateFounded;
        @ColumnInfo(name = NOTES)
        public String notes;//Nullable
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Chest(String name, String subscriptionCode, double initial, String initialCurrency,
                     long dateFounded, String notes, long dateCreated, long dateModified) {
            this.name = name;
            this.subscriptionCode = subscriptionCode;
            this.initial = initial;
            this.initialCurrency = initialCurrency;
            this.dateFounded = dateFounded;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Chest setId(long id) {
            this.id = id;
            return this;
        }
    }

    @Entity
    public static class Cash {
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = INITIAL)
        public double initial;
        @ColumnInfo(name = INITIAL_CURRENCY)
        public String initialCurrency;
        @ColumnInfo(name = DATE_EARNED)
        public long dateEarned;
        @ColumnInfo(name = NOTES)
        public String notes;
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Cash(double initial, String initialCurrency, long dateEarned, String notes, long dateCreated,
                    long dateModified) {
            this.initial = initial;
            this.initialCurrency = initialCurrency;
            this.dateEarned = dateEarned;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Cash setId(long id) {
            this.id = id;
            return this;
        }
    }


    // OTHER
    @Entity
    public static class Contact {
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = FIRST_NAME)
        public String firstName;
        @ColumnInfo(name = LAST_NAME)
        public String lastName;
        @ColumnInfo(name = PHONE)
        public String phone;//Nullable
        @ColumnInfo(name = EMAIL)
        public String email;//Nullable
        @ColumnInfo(name = ACCOUNT_NUMBER)
        public String accountNumber;//Nullable
        @ColumnInfo(name = CARD_NUMBER)
        public String cardNumber;//Nullable
        @ColumnInfo(name = NOTES)
        public String notes;//Nullable
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Contact(String firstName, String lastName, String phone, String email, String accountNumber, String cardNumber, String notes,
                       long dateCreated, long dateModified) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
            this.accountNumber = accountNumber;
            this.cardNumber = cardNumber;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Contact setId(long id) {
            this.id = id;
            return this;
        }
    }

    @Entity
    public static class Relative {
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = FIRST_NAME)
        public String firstName;
        @ColumnInfo(name = LAST_NAME)
        public String lastName;
        @ColumnInfo(name = RELATIVITY)
        public byte relativity;
        @ColumnInfo(name = PHONE)
        public String phone;//Nullable
        @ColumnInfo(name = NOTES)
        public String notes;//Nullable
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Relative(String firstName, String lastName, byte relativity, String phone, String notes, long dateCreated, long dateModified) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.relativity = relativity;
            this.phone = phone;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Relative setId(long id) {
            this.id = id;
            return this;
        }
    }

    @Entity
    public static class Subject {
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = NAME)
        public String name;
        @ColumnInfo(name = REPETITION)
        public byte repetition;
        @ColumnInfo(name = FOR_COMPANY)
        public boolean forCompany;
        @ColumnInfo(name = NOTES)
        public String notes;//Nullable
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Subject(String name, byte repetition, boolean forCompany, String notes, long dateCreated, long dateModified) {
            this.name = name;
            this.repetition = repetition;
            this.forCompany = forCompany;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Subject setId(long id) {
            this.id = id;
            return this;
        }
    }

    @Entity
    public static class Project {
        @PrimaryKey(autoGenerate = true)
        public long id;
        @ColumnInfo(name = NAME)
        public String name;
        @ColumnInfo(name = DATE_STARTED)
        public long dateStarted;
        @ColumnInfo(name = DATE_ENDED)
        public long dateEnded;
        @ColumnInfo(name = CONTACT_ID)
        public long contactId;//Nullable
        @ColumnInfo(name = NOTES)
        public String notes;//Nullable
        @ColumnInfo(name = DATE_CREATED)
        public long dateCreated;
        @ColumnInfo(name = DATE_MODIFIED)
        public long dateModified;

        public Project(String name, long dateStarted, long dateEnded, long contactId, String notes, long dateCreated, long dateModified) {
            this.name = name;
            this.dateStarted = dateStarted;
            this.dateEnded = dateEnded;
            this.contactId = contactId;
            this.notes = notes;
            this.dateCreated = dateCreated;
            this.dateModified = dateModified;
        }

        public Project setId(long id) {
            this.id = id;
            return this;
        }
    }
}
