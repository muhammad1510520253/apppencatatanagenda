package com.blogspot.ketikanmd.appcatatanagenda;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AgendaDAOImp extends SQLiteOpenHelper implements AgendaDAO {
    public AgendaDAOImp(Context context) {
        super(context, "db_agenda", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE tbl_agenda (id INTEGER PRIMARY KEY,nama TEXT,tempat TEXT,keterangan TEXT,tanggal TEXT,status TEXT)");
        db.execSQL("CREATE TABLE agenda (id INTEGER PRIMARY KEY ,nama TEXT,tempat TEXT,keterangan TEXT,tanggal TEXT,status TEXT)");
//        db.execSQL("DROP TABLE agenda");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE tbl_agenda");
        db.execSQL("DROP TABLE agenda");
    }

    @Override
    public Cursor read() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM agenda", null);
    }

    public Cursor readpenting() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM agenda WHERE status = 'Penting' ", null);
    }

    @Override
    public boolean create(Agenda agenda) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO agenda VALUES('" + agenda.getId() + "','" + agenda.getNama() + "','" + agenda.getTempat() + "','" + agenda.getKeterangan() + "','" + agenda.getTanggal() + "','" + agenda.getStatus() + "')");
        return true;
    }

    @Override
    public boolean update(Agenda agenda) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE agenda SET nama ='" + agenda.getNama() + "',tempat ='" + agenda.getTempat() + "',keterangan ='" + agenda.getKeterangan() + "',tanggal ='" + agenda.getTanggal() + "',status ='" + agenda.getStatus() + "' WHERE id ='" + agenda.getId() + "'");
        return true;
    }

    @Override
    public boolean delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM agenda WHERE id ='" + id + "'");
        return true;
    }
}
