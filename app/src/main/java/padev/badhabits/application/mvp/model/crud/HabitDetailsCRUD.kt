package padev.badhabits.application.mvp.model.crud;

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import java.util.ArrayList
import padev.badhabits.application.mvp.model.entity.HabitDetailsEntity

class HabitDetailsCRUD(context: Context): SQLiteOpenHelper(context, "padev.badhabits.db", null, 1) {

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

    fun insertData(habitDetailsEntity: HabitDetailsEntity) {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_HABIT_ID, habitDetailsEntity.habitId)
        values.put(KEY_DOSE, habitDetailsEntity.dose)
        values.put(KEY_CONCENTRATION, habitDetailsEntity.concentration)
        values.put(KEY_WEIGHT, habitDetailsEntity.weight)

        habitDetailsEntity.id = db.insert(TABLE_HABITS_DETAILS, null, values)

        db.close()
    }

    @SuppressLint("Recycle")
    fun selectData(id: Int): HabitDetailsEntity {
        val db = this.readableDatabase

        val cursor = db.query(TABLE_HABITS_DETAILS, arrayOf(KEY_ID,
                        KEY_DOSE, KEY_CONCENTRATION, KEY_WEIGHT), "$KEY_ID=?",
                arrayOf(id.toString()), null, null, null, null)

        cursor?.moveToFirst()

        return HabitDetailsEntity(
                cursor.getString(0).toLong(),
                cursor.getString(1).toLong(),
                cursor.getString(2).toInt(),
                cursor.getString(3).toInt(),
                cursor.getString(4).toInt())
    }

    fun selectDataByHabitId(habitId: Long): ArrayList<HabitDetailsEntity>  {

        val habitDetails = ArrayList<HabitDetailsEntity>()

        val db = this.readableDatabase

        val cursor = db.query(TABLE_HABITS_DETAILS, null, "$KEY_HABIT_ID = ?", arrayOf(habitId.toString()), null, null,null)

        if (cursor.moveToFirst()) {
            do {
                val habitDetail = HabitDetailsEntity(
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

    fun selectAllData(): ArrayList<HabitDetailsEntity> {

        // TODO if you want to
        return ArrayList()
    }

    fun updateData(habitDetailsEntity: HabitDetailsEntity): Int {

        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_DOSE, habitDetailsEntity.dose)
        values.put(KEY_CONCENTRATION, habitDetailsEntity.concentration)
        values.put(KEY_WEIGHT, habitDetailsEntity.weight)

        return db.update(TABLE_HABITS_DETAILS, values, "$KEY_ID = ?",
                arrayOf(habitDetailsEntity.id.toString()))
    }

    fun deleteData(id: Long) {

        val db = this.writableDatabase;
        db.delete(TABLE_HABITS_DETAILS, "$KEY_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    fun deleteAllData() {

        val db = this.writableDatabase
        db.delete(TABLE_HABITS_DETAILS, null, null)
        db.close()
    }
}