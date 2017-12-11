package com.tyler_buchheim.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tyler_buchheim.habittracker.data.HabitContract.HabitEntry;

public final class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habits.db";
    private static final int DATABASE_VERSION = 1;
    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_COMMENT + " TEXT, "
                + HabitEntry.COLUMN_HABIT_COUNT + " INTEGER NOT NULL DEFAULT 0);";

        Log.v(LOG_TAG, "Creating table: " + HabitEntry.TABLE_NAME);
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME + ";");
        onCreate(db);
    }

    public void insertHabit(String name, String comment, int count) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_COMMENT, comment);
        values.put(HabitEntry.COLUMN_HABIT_COUNT, count);
        db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    public Cursor readHabits() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_COMMENT,
                HabitEntry.COLUMN_HABIT_COUNT
        };

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,  // Table name
                projection,             // Columns to return
                null,                   // Columns of WHERE clause
                null,                   // Values of WHERE clause
                null,                   // Don't group rows
                null,                   // Don't filter by row groups
                null);                  // Sort order

        return cursor;
    }

}
