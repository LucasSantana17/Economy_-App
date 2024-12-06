package com.work.economy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.work.economy.Piggybank;

public class Databasepiggy extends SQLiteOpenHelper {

    private static final String TAG = "database";
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

    public boolean insert(Piggybank piggy){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("type_movement", piggy.getTypeValue());
        values.put("value_movement", piggy.getInputValue());

        long result = database.insert("movement", null, values);

        return result > 0;
    }



    public String displayData() {

        StringBuilder resultString = new StringBuilder();

        try (SQLiteDatabase database = getReadableDatabase();

             Cursor cursor = database.rawQuery("SELECT * FROM movement", null)) {

            if (cursor != null && cursor.moveToFirst()) {

                do {
                    // Obter os valores das colunas com verificação do índice
                    int idColumnIndex = cursor.getColumnIndex("id_movement");
                    int valueColumnIndex = cursor.getColumnIndex("value_movement");
                    int nameColumnIndex = cursor.getColumnIndex("type_movement");
                    int dateColumnIndex = cursor.getColumnIndex("date_movement");

                    if(idColumnIndex != -1){
                        int id = cursor.getInt(idColumnIndex);
                        resultString.append("ID: ").append(id).append("\n");
                    }

                    if (valueColumnIndex != -1) {
                        double value = cursor.getDouble(valueColumnIndex);
                        resultString.append("Valor: ").append(value).append("\n");
                        Log.d(TAG, "VALOR: " + value);
                    } else {
                        Log.d(TAG, "Coluna 'value_movement' não encontrada");
                    }



                    if (nameColumnIndex != -1) {
                        String name = cursor.getString(nameColumnIndex);
                        resultString.append("Nome: ").append(name).append("\n");
                        Log.d(TAG, "NOME: " + name);
                    } else {
                        Log.d(TAG, "Coluna 'type_movement' não encontrada");
                    }



                    if (dateColumnIndex != -1) {
                        String date = cursor.getString(dateColumnIndex);
                        resultString.append("Data: ").append(date).append("\n");
                        Log.d(TAG, "DATA: " + date);
                    } else {
                        Log.d(TAG, "Coluna 'date_movement' não encontrada");
                    }
                    resultString.append("______________________________");
                    resultString.append("\n");
                    resultString.append("\n");

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            resultString.append("Erro ao acessar o banco de dados.");
        }

        return resultString.toString();
    }

}
