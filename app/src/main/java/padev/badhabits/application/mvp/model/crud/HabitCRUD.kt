package padev.badhabits.application.mvp.model.crud

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import padev.badhabits.application.mvp.model.entity.HabitEntity
import java.util.*

class HabitCRUD(context: Context?): SQLiteOpenHelper(context, "padev.badhabits.db", null, 1) {

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

    fun insertData(habitEntity: HabitEntity) {

        val values = ContentValues()
        values.put(KEY_NAME, habitEntity.name)

        val db = this.writableDatabase

        habitEntity.id = db.insert(TABLE_HABITS, null, values)

        db.close()
    }

    fun selectData(id: Int): HabitEntity {

        val db = this.readableDatabase

        val cursor = db.query(TABLE_HABITS, arrayOf(KEY_ID, KEY_NAME), "$KEY_ID=?",
                arrayOf(id.toString()), null, null, null, null)

        cursor?.moveToFirst()

        return HabitEntity(cursor.getString(0).toLong(), cursor.getString(1), false, false, false)
    }

    fun selectAllData(): ArrayList<HabitEntity> {

        val habits = ArrayList<HabitEntity>()

        val selectQuery = "SELECT  * FROM $TABLE_HABITS"

        val db = this.writableDatabase;
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val habit = HabitEntity(cursor.getString(0).toLong(), cursor.getString(1), false, false, false)
                habits.add(habit)
            } while (cursor.moveToNext())
        }

        return habits
    }

    fun updateData(habitEntity: HabitEntity): Int {

        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_NAME, habitEntity.name)

        return db.update(TABLE_HABITS, values, "$KEY_ID = ?",
                arrayOf(habitEntity.id.toString()))
    }

    fun deleteData(id: Long) {

        val db = this.writableDatabase
        db.delete(TABLE_HABITS, "$KEY_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    fun deleteAllData() {

        val db = this.writableDatabase
        db.delete(TABLE_HABITS, null, null)
        db.close()
    }

}