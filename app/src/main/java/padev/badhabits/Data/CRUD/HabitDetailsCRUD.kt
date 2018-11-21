package padev.badhabits.Data.CRUD;

import android.annotation.SuppressLint
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import padev.badhabits.Data.AbstractData;
import padev.badhabits.Data.HabitDetails;
import padev.badhabits.Data.IDataAccessObject;

class HabitDetailsCRUD(context: Context): SQLiteOpenHelper(context, "padev.badhabits.db", null, 1), IDataAccessObject {

/*
    private val DATABASE_VERSION = 1
    private val DATABASE_NAME = "badHabitsManager"
*/

    private val TABLE_HABITS_DETAILS = "habitsDetails"
    private val KEY_ID = "id"
    private val KEY_HABIT_ID = "habit_id"
    private val KEY_DOSE = "dose"
    private val KEY_CONCENTRATION = "concentration"
    private val KEY_WEIGHT = "weight"


    init {
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_HABITS_DETAILS($KEY_ID INTEGER PRIMARY KEY,$KEY_HABIT_ID INTEGER,$KEY_DOSE INTEGER,$KEY_CONCENTRATION INTEGER,$KEY_WEIGHT INTEGER)")
    }

    override fun onCreate(db: SQLiteDatabase) {
        //db.execSQL("PRAGMA foreign_keys=ON;");
        db.execSQL("CREATE TABLE $TABLE_HABITS_DETAILS($KEY_ID INTEGER PRIMARY KEY,$KEY_HABIT_ID INTEGER,$KEY_DOSE INTEGER,$KEY_CONCENTRATION INTEGER,$KEY_WEIGHT INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HABITS_DETAILS")

        onCreate(db)
    }

    override fun insertData(data: AbstractData) {

        val habitDetails = data as HabitDetails

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_HABIT_ID, habitDetails.habitId)
        values.put(KEY_DOSE, habitDetails.dose)
        values.put(KEY_CONCENTRATION, habitDetails.concentration)
        values.put(KEY_WEIGHT, habitDetails.weight)

        habitDetails.id = db.insert(TABLE_HABITS_DETAILS, null, values)

        db.close()
    }

    @SuppressLint("Recycle")
    override fun selectData(id: Int): AbstractData {
        val db = this.readableDatabase

        val cursor = db.query(TABLE_HABITS_DETAILS, arrayOf(KEY_ID,
                        KEY_DOSE, KEY_CONCENTRATION, KEY_WEIGHT), "$KEY_ID=?",
                arrayOf(id.toString()), null, null, null, null)

        cursor?.moveToFirst()

        return HabitDetails(cursor.getString(0).toLong(),
                cursor.getString(1).toInt(),
                cursor.getString(2).toInt(),
                cursor.getString(3).toInt())
    }

    fun selectDataByHabitId(habitId: Long): ArrayList<AbstractData>  {

        val habitDetails = ArrayList<AbstractData>()

        val db = this.readableDatabase

        val cursor = db.query(TABLE_HABITS_DETAILS, null, "$KEY_HABIT_ID = ?", arrayOf(habitId.toString()), null, null,null)

        if (cursor.moveToFirst()) {
            do {
                val habitDetail = HabitDetails(
                        cursor.getString(0).toLong(),
                        cursor.getString(1).toLong(),
                        cursor.getString(2).toInt(),
                        cursor.getString(3).toInt(),
                        cursor.getString(4).toInt())
                                                                                                                                                                                                           habitDetails.add(habitDetail);
            } while (cursor.moveToNext())
        }

        return habitDetails
    }

    override fun selectAllData(): ArrayList<AbstractData> {

        // TODO if you want to
        return ArrayList()
    }

    override fun updateData(data: AbstractData): Int {
        val habitDetails = data as HabitDetails

        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_DOSE, habitDetails.dose)
        values.put(KEY_CONCENTRATION, habitDetails.concentration)
        values.put(KEY_WEIGHT, habitDetails.weight)

        return db.update(TABLE_HABITS_DETAILS, values, "$KEY_ID = ?",
                arrayOf(habitDetails.id.toString()))
    }

    override fun deleteData(id: Int) {
        val db = this.writableDatabase;
        db.delete(TABLE_HABITS_DETAILS, "$KEY_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    override fun deleteAllData() {
        val db = this.writableDatabase
        db.delete(TABLE_HABITS_DETAILS, null, null)
        db.close()
    }
}