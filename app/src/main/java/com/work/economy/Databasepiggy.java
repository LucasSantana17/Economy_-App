package com.work.economy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.work.economy.Piggybank;

public class Databasepiggy extends SQLiteOpenHelper {

    SQLiteDatabase database;
    private static final String DB_NAME = "Economy";
    private static final int DB_VERSION = 1;


    private static final String TABLE_MOVEMENT = "CREATE TABLE IF NOT EXISTS movement (" +
            "id_movement INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "value_movement REAL NOT NULL, " +
            "date_movement DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            "type_movement TEXT NOT NULL" +
            ")";

    private static final String TABLE_PIGGYBANK = "CREATE TABLE IF NOT EXISTS piggybank (" +
            "id_piggybank INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "id_currentbalance REAL NOT NULL, " +
            "id_movement INTEGER NOT NULL, " +
            "FOREIGN KEY (id_movement) REFERENCES movement(id_movement)" +
            ")";


    public Databasepiggy(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(TABLE_MOVEMENT);
        sqLiteDatabase.execSQL(TABLE_PIGGYBANK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS piggybank");
        db.execSQL("DROP TABLE IF EXISTS movement");
        onCreate(db);
    }

    // Metodo de incerção no banco de dados
    public boolean insert(String table, Piggybank date){
        ContentValues values = new ContentValues();

        values.put(table, date.getInputValue());
        values.put(table, date.getNameValue());

        return database.insert(TABLE_MOVEMENT, null, values) > 0;
    }
}
