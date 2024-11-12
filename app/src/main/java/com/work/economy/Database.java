package com.work.economy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String name_data_base ="Economy";
    private static final String version_data_base = "1";

    private static final String CREATE_DATA_BASE = "CREATE TABLE Economy ("
                                                 + "id_value INTEGER PRIMARY KEY AUTOINCREMENT" + ""

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
