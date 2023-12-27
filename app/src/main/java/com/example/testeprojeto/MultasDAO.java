package com.example.testeprojeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MultasDAO {

    public static void inserir(Context context,Multas multas){
        //Classe que armazena os dados no banco
        ContentValues values = new ContentValues();
        values.put("nome",multas.getNome());
        values.put("placa",multas.getPlaca());
        values.put("valor",multas.getValor());
        values.put("gravidade",multas.getGravidade());


        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();


        db.insert("multas", null, values);
    }

    public static void editar(Context context, Multas multas) {
        if (multas != null) {
            ContentValues values = new ContentValues();
            values.put("nome", multas.getNome());
            values.put("placa", multas.getPlaca());
            values.put("valor", multas.getValor());
            values.put("gravidade", multas.getGravidade());

            Banco conn = new Banco(context);
            SQLiteDatabase db = conn.getWritableDatabase();

            db.update("multas", values, "idMultas=?", new String[]{String.valueOf(multas.getId())});
        } else {
            System.out.println("Multas é nulo");
        }
    }


    public static void excluir(Context context, int idMulta) {
        Banco conn = new Banco(context);

        try (SQLiteDatabase db = conn.getWritableDatabase()) {
            String whereClause = "idMultas = ?";
            String[] whereArgs = {String.valueOf(idMulta)};

            db.delete("multas", whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<Multas> getMultas(Context context){
        List<Multas> lista = new ArrayList<>();
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        //Classe que percorre os registros do banco
        Cursor cursor = db.rawQuery("SELECT * FROM multas ORDER BY nome",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Multas mult = new Multas();
                mult.setId(cursor.getInt(0));
                mult.setNome(cursor.getString(1));
                mult.setPlaca(cursor.getString(2));
                mult.setValor(cursor.getString(3));
                mult.setGravidade(cursor.getString(4));
                lista.add(mult);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public static Multas getMultasbyId(Context context, int idMultas){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM multas WHERE idMultas = ?", new String[]{String.valueOf(idMultas)});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            Multas mult = new Multas();
            mult.setId(cursor.getInt(0));
            mult.setNome(cursor.getString(1));
            mult.setPlaca(cursor.getString(2));
            mult.setValor(cursor.getString(3));
            mult.setGravidade(cursor.getString(4));
            return mult;
            }
        else
        {
            return null;
        }
    }
}
