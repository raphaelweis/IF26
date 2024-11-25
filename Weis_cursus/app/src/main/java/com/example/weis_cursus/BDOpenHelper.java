package com.example.weis_cursus;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class BDOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "cursus.db";

    private final String TAG = "BDHelper";

    private static final String MODULE_TABLE_NAME = "modules";
    private static final String MODULE_ATTRIBUT_SIGLE = "sigle";
    private static final String MODULE_ATTRIBUT_PARCOURS = "parcours";
    private static final String MODULE_ATTRIBUT_CATEGORIE = "categorie";
    private static final String MODULE_ATTRIBUT_CREDIT = "credit";

    public BDOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String MODULE_TABLE_CREATE = "CREATE TABLE " + MODULE_TABLE_NAME + "(" +
                MODULE_ATTRIBUT_SIGLE + " TEXT PRIMARY KEY," +
                MODULE_ATTRIBUT_CATEGORIE + " TEXT," +
                MODULE_ATTRIBUT_PARCOURS + " TEXT," +
                MODULE_ATTRIBUT_CREDIT + " INTEGER)";
        db.execSQL(MODULE_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String MODULE_TABLE_DROP = "DROP TABLE IF EXISTS " + MODULE_TABLE_NAME;
        db.execSQL(MODULE_TABLE_DROP);

        onCreate(db);
    }

    public void createModule(Module module) {
        ContentValues tuple = new ContentValues();
        tuple.put(MODULE_ATTRIBUT_SIGLE, module.getSigle());
        tuple.put(MODULE_ATTRIBUT_CATEGORIE, module.getCategorie());
        tuple.put(MODULE_ATTRIBUT_PARCOURS, module.getParcours());
        tuple.put(MODULE_ATTRIBUT_CREDIT, module.getCredit());

        Log.i(TAG + ":createEtudiant:t = ", tuple.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(MODULE_TABLE_NAME, null, tuple);
        db.close();
    }

    public Module getModule(String sigle) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + MODULE_TABLE_NAME +
                " WHERE " + MODULE_ATTRIBUT_SIGLE + " = " + sigle;

        Log.i(TAG + ":getEtudiant:q = ", query);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null) return null;

        cursor.moveToFirst();

        @SuppressLint("Range") Module tuple = new Module(
                cursor.getString(cursor.getColumnIndex(MODULE_ATTRIBUT_SIGLE)),
                cursor.getString(cursor.getColumnIndex(MODULE_ATTRIBUT_CATEGORIE)),
                cursor.getString(cursor.getColumnIndex(MODULE_ATTRIBUT_PARCOURS)),
                cursor.getInt(cursor.getColumnIndex(MODULE_ATTRIBUT_CREDIT))
        );

        cursor.close();
        db.close();
        return tuple;
    }

    @SuppressLint("LongLogTag")
    public ArrayList<Module> getAllModules() {
        ArrayList<Module> listeModules = new ArrayList<Module>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + MODULE_TABLE_NAME;
        Log.i(TAG + ":getAllEtudiants:q = ", query);

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Module tuple = new Module(
                        cursor.getString(cursor.getColumnIndex(MODULE_ATTRIBUT_SIGLE)),
                        cursor.getString(cursor.getColumnIndex(MODULE_ATTRIBUT_CATEGORIE)),
                        cursor.getString(cursor.getColumnIndex(MODULE_ATTRIBUT_PARCOURS)),
                        cursor.getInt(cursor.getColumnIndex(MODULE_ATTRIBUT_CREDIT))
                );

                Log.i(TAG + ":getAllEtudiants:t = ", tuple.toString());

                listeModules.add(tuple);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listeModules;
    }

    @SuppressLint("LongLogTag")
    public int updateModule(Module module) {
        ContentValues tuple = new ContentValues();
        tuple.put(MODULE_ATTRIBUT_SIGLE, module.getSigle());
        tuple.put(MODULE_ATTRIBUT_CATEGORIE, module.getCategorie());
        tuple.put(MODULE_ATTRIBUT_PARCOURS, module.getParcours());
        tuple.put(MODULE_ATTRIBUT_CREDIT, module.getCredit());

        Log.i(TAG + ":updateEtudiant:t = ", tuple.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.update(MODULE_TABLE_NAME, tuple,
                MODULE_ATTRIBUT_SIGLE + " = ?",
                new String[]{String.valueOf(module.getSigle())}
        );

        db.close();
        return result;
    }

    public void deleteModule(String sigle) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                MODULE_TABLE_NAME,
                MODULE_ATTRIBUT_SIGLE + " = ?",
                new String[]{String.valueOf(sigle)}
        );

        db.close();
    }

    public void initModules() {
        final ProgrammeISI isi = new ProgrammeISI();
        ArrayList<Module> data = isi.getModules("ALL");

        for (Module module : data) {
            createModule(module);
        }
    }

    public int countModules() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MODULE_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        int result = cursor.getCount();

        cursor.close();
        return result;
    }
}