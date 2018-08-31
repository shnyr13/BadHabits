package padev.badhabits.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class HabitDetailsCRUD extends SQLiteOpenHelper implements IDataAccessObject {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "badHabitsManager";
    private static final String TABLE_HABITS_DETAILS = "habitsDetails";
    private static final String KEY_ID = "id";
    private static final String KEY_DOSE = "dose";
    private static final String KEY_CONCENTRATION = "concentration";
    private static final String KEY_WEIGHT = "weight";

    public HabitDetailsCRUD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_HABITS_DETAILS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DOSE + " INTEGER," + KEY_CONCENTRATION +  " INTEGER," + KEY_WEIGHT +  " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HABITS_DETAILS);

        onCreate(db);
    }

    @Override
    public void insertData(IData data) {

        HabitDetails habitDetails = (HabitDetails) data;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DOSE, habitDetails.getDose());
        values.put(KEY_CONCENTRATION, habitDetails.getConcentration());
        values.put(KEY_WEIGHT, habitDetails.getWeight());

        habitDetails.setId(db.insert(TABLE_HABITS_DETAILS, null, values));

        db.close();
    }

    @Override
    public IData selectData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HABITS_DETAILS, new String[] { KEY_ID,
                        KEY_DOSE, KEY_CONCENTRATION, KEY_WEIGHT}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        assert cursor != null;

        return new HabitDetails(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
    }

    @Override
    public ArrayList<IData> selectAllData() {

        // TODO if you want to
        return new ArrayList<>();
    }

    @Override
    public int updateData(IData data) {
        HabitDetails habitDetails = (HabitDetails) data;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DOSE, habitDetails.getDose());
        values.put(KEY_CONCENTRATION, habitDetails.getConcentration());
        values.put(KEY_WEIGHT, habitDetails.getWeight());

        return db.update(TABLE_HABITS_DETAILS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(habitDetails.getId()) });
    }

    @Override
    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HABITS_DETAILS, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    @Override
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HABITS_DETAILS, null, null);
        db.close();
    }
}
