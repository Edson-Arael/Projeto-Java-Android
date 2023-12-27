package com.example.testeprojeto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO ="AppPersist";

    public Banco(@Nullable Context context) {
        super(context, NOME_BANCO, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  multas( "+
                " idMultas INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , "+
                " nome TEXT NOT NULL, " +
                " placa TEXT NOT NULL, " +
                " valor TEXT NOT NULL, " +
                " gravidade TEXT NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}