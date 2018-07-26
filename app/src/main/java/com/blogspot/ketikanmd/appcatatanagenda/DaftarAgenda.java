package com.blogspot.ketikanmd.appcatatanagenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DaftarAgenda extends AppCompatActivity {
    private AgendaDAO agendaDAO;
    //    private FloatingActionButton fb;
    private ListView tvAgenda;
    private ArrayList<HashMap<String, String>> listAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_agenda);
        tvAgenda = findViewById(R.id.tvAgenda);
        agendaDAO = new AgendaDAOImp(this);
        setTitle("Daftar Agenda");
        ReadAgenda();
        tvAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {

                String[] pilihan = {"Tampil data", "Ubah data", "Hapus data"};
//                    Toast.makeText(MainActivity.this,listBiodata.get(position).get("nim"), Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(DaftarAgenda.this);
                final AlertDialog.Builder confirm = new AlertDialog.Builder(DaftarAgenda.this);
                builder.setTitle("Pilihan");
                builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int pilih) {
                        switch (pilih) {
                            case 0:
//                                TODO:TAMPIL DATA
                                Intent intent1 = new Intent(DaftarAgenda.this, TampilAgenda.class);
                                intent1.putExtra("id", listAgenda.get(position).get("id"));
                                intent1.putExtra("nama", listAgenda.get(position).get("nama"));
                                intent1.putExtra("tempat", listAgenda.get(position).get("tempat"));
                                intent1.putExtra("keterangan", listAgenda.get(position).get("keterangan"));
                                intent1.putExtra("tanggal", listAgenda.get(position).get("tanggal"));
                                intent1.putExtra("status", listAgenda.get(position).get("status"));
                                startActivity(intent1);
//                              Toast.makeText(DaftarAgenda.this, "id nomer"+listAgenda.get(position).get("status"), Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
//                                TODO:EDIT DATA
                                Intent intent = new Intent(DaftarAgenda.this, CatatAgenda.class);
                                intent.putExtra("id", listAgenda.get(position).get("id"));
                                intent.putExtra("nama", listAgenda.get(position).get("nama"));
                                intent.putExtra("tempat", listAgenda.get(position).get("tempat"));
                                intent.putExtra("keterangan", listAgenda.get(position).get("keterangan"));
                                intent.putExtra("tanggal", listAgenda.get(position).get("tanggal"));
                                intent.putExtra("status", listAgenda.get(position).get("status"));
                                startActivity(intent);
                                break;
                            case 2:


//                               TODO:HAPUS DATA
                                confirm.setMessage("Yakin ingin hapus");
                                confirm.setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                confirm.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        int id = Integer.valueOf(listAgenda.get(position).get("id"));
                                        boolean cek = agendaDAO.delete(id);
                                        if (cek) {
                                            ReadAgenda();
                                            Toast.makeText(DaftarAgenda.this, "Data Berhasil di Hapus", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(DaftarAgenda.this, "Data Gagal di Hapus", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                confirm.show();

                                break;

                        }

                    }
                });
                builder.show();
            }
        });


    }

    private void ReadAgenda() {
        listAgenda = new ArrayList<>();
        Cursor cursor = agendaDAO.read();
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("id", cursor.getString(0));
                hm.put("nama", cursor.getString(1));
                hm.put("tempat", cursor.getString(2));
                hm.put("keterangan", cursor.getString(3));
                hm.put("tanggal", cursor.getString(4));
                hm.put("status", cursor.getString(5));
                listAgenda.add(hm);
            } while (cursor.moveToNext());
            String[] key = {"nama", "tanggal", "tempat"};
            int[] komponen = {R.id.txt_resultnama, R.id.txt_resulttanggal, R.id.txt_resulttempat};
            SimpleAdapter adapter = new SimpleAdapter(this, listAgenda, R.layout.list_item_agenda, key, komponen);
            tvAgenda.setAdapter(adapter);
        }
    }


    public void tambahbaru(View view) {
        startActivity(new Intent(DaftarAgenda.this, CatatAgenda.class));
    }
}

