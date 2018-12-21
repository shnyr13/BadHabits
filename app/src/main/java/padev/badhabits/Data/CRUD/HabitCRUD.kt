package padev.badhabits.Data.CRUD

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import padev.badhabits.Data.AbstractData
import padev.badhabits.Data.Habit
import padev.badhabits.Data.IDataAccessObject
import java.util.*

class HabitCRUD(context: Context): SQLiteOpenHelper(context, "padev.badhabits.db", null, 1), IDataAccessObject {

/*
    private val DATABASE_VERSION = 1
    private val DATABASE_NAME = "badHabitsManager"
*/
    private val TABLE_HABITS = "habits"
    private val KEY_ID = "id"
    private val KEY_NAME = "name"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_HABITS($KEY_ID INTEGER PRIMARY KEY,$KEY_NAME TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HABITS")

        onCreate(db)
    }

    override fun insertData(data: AbstractData) {

        val habit = data as Habit

        val values = ContentValues()
        values.put(KEY_NAME, habit.name)

        val db = this.writableDatabase

        habit.id = db.insert(TABLE_HABITS, null, values)

        db.close()
    }

    override fun selectData(id: Int): AbstractData {

        val db = this.readableDatabase

        val cursor = db.query(TABLE_HABITS, arrayOf(KEY_ID, KEY_NAME), "$KEY_ID=?",
                arrayOf(id.toString()), null, null, null, null)

        cursor?.moveToFirst()

        return Habit(cursor.getString(0).toLong(), cursor.getString(1), false, false, false)
    }

    override fun selectAllData(): ArrayList<AbstractData> {

        val habits = ArrayList<AbstractData>()

        val selectQuery = "SELECT  * FROM $TABLE_HABITS"

        val db = this.writableDatabase;
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val habit = Habit(cursor.getString(0).toLong(), cursor.getString(1), false, false, false)
                habits.add(habit)
            } while (cursor.moveToNext())
        }

        return habits
    }

    override fun updateData(data: AbstractData): Int {
        val habit = data as Habit

        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_NAME, habit.name)

        return db.update(TABLE_HABITS, values, "$KEY_ID = ?",
                arrayOf(habit.id.toString()))
    }

    override fun deleteData(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_HABITS, "$KEY_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    override fun deleteAllData() {
        val db = this.writableDatabase
        db.delete(TABLE_HABITS, null, null)
        db.close()
    }

}