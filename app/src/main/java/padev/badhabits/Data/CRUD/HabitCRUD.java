package padev.badhabits.Data.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import padev.badhabits.Data.AbstractData;
import padev.badhabits.Data.Habit;
import padev.badhabits.Data.IDataAccessObject;

public class HabitCRUD extends SQLiteOpenHelper implements IDataAccessObject, CRUDSingleton {

    private static HabitCRUD instance = null;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "badHabitsManager";
    private static final String TABLE_HABITS = "habits";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    private HabitCRUD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HABITS_TABLE = "CREATE TABLE " + TABLE_HABITS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HABITS);

        onCreate(db);
    }

    @Override
    public void insertData(AbstractData data) {

        Habit habit = (Habit) data;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, habit.getName());

        habit.setId((int) db.insert(TABLE_HABITS, null, values));

        db.close();
    }

    @Override
    public AbstractData selectData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HABITS, new String[] { KEY_ID,
                        KEY_NAME}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        assert cursor != null;

        return new Habit(Long.parseLong(cursor.getString(0)), cursor.getString(1));
    }

    @Override
    public ArrayList<AbstractData> selectAllData() {

        ArrayList<AbstractData> habits = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_HABITS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Habit habit = new Habit(Long.parseLong(cursor.getString(0)), cursor.getString(1));
                habits.add(habit);
            } while (cursor.moveToNext());
        }

        return habits;
    }

    @Override
    public int updateData(AbstractData data) {
        Habit habit = (Habit) data;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, habit.getName());

        return db.update(TABLE_HABITS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(habit.getId()) });
    }

    @Override
    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HABITS, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    @Override
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HABITS, null, null);
        db.close();
    }

    public static HabitCRUD getInstance(Context context) {
        if (instance == null) instance = new HabitCRUD(context);

        return instance;
    }
}