package com.blogspot.ketikanmd.appcatatanagenda;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CatatAgenda extends AppCompatActivity {
    protected Cursor cursor;

    private AgendaDAO agendaDAO;
    private EditText et_nama;
    private EditText et_id;
    private EditText et_tempat;
    private EditText et_keterangan;
    private EditText et_tanggal;
    private RadioGroup et_status;
    private RadioButton pilihan1, pilihan2, pilihan;
    private Button button_tambahdata;

    private boolean TAG = true;
    private Agenda agenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catat_agenda);
        et_nama = findViewById(R.id.nama);
        et_tempat = findViewById(R.id.tempat);
        et_id = findViewById(R.id.id);
        agenda = new Agenda();
        agendaDAO = new AgendaDAOImp(this);
        pilihan1 = findViewById(R.id.penting);
        pilihan2 = findViewById(R.id.biasa);
        et_keterangan = findViewById(R.id.keterangan);
        et_tanggal = findViewById(R.id.tanggal);
        et_status = findViewById(R.id.status);
        button_tambahdata = findViewById(R.id.btntambah);
        Intent i = getIntent();

        if (!TextUtils.isEmpty(i.getStringExtra("id"))) {
            String id = i.getStringExtra("id");
            String nama = i.getStringExtra("nama");
            String tanggal = i.getStringExtra("tanggal");
            String tempat = i.getStringExtra("tempat");
            String keterangan = i.getStringExtra("keterangan");
            String status = i.getStringExtra("status");
            et_id.setText(id);
            et_nama.setText(nama);
            et_tanggal.setText(tanggal);
            et_tempat.setText(tempat);
            et_keterangan.setText(keterangan);
            if (status.equals("Penting")) {
                pilihan1.setChecked(true);
            } else {
                pilihan2.setChecked(true);
            }

            TAG = false;
            et_id.setEnabled(false);
            button_tambahdata.setText("Update");
        }

        button_tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    if (et_nama.length() > 0 && et_tanggal.length() > 0 && et_keterangan.length() > 0) {

                        int id = Integer.valueOf(et_id.getText().toString());
                        String nama = et_nama.getText().toString();
                        String tempat = et_tempat.getText().toString();
                        String keterangan = et_keterangan.getText().toString();
                        String tanggal = et_tanggal.getText().toString();
                        int form_status = et_status.getCheckedRadioButtonId();
                        pilihan = findViewById(form_status);
                        String status = (String) pilihan.getText();
                        agenda.setId(id);
                        agenda.setNama(nama);
                        agenda.setTempat(tempat);
                        agenda.setKeterangan(keterangan);
                        agenda.setTanggal(tanggal);
                        agenda.setStatus(status);

                        if (TAG) {
                            boolean cek = agendaDAO.create(agenda);
                            if (cek) {
                                Toast.makeText(CatatAgenda.this, "berhasil disimpan", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CatatAgenda.this, DaftarAgenda.class));
                                finish();
                            } else {
                                Toast.makeText(CatatAgenda.this, "Gagal di simpan", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            boolean cek = agendaDAO.update(agenda);
                            if (cek) {
                                Toast.makeText(CatatAgenda.this, "berhasil diupdate", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CatatAgenda.this, DaftarAgenda.class));
                                finish();
                            } else {
                                Toast.makeText(CatatAgenda.this, "Gagal di Update", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(CatatAgenda.this, "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(CatatAgenda.this, "Id Agenda Sama", Toast.LENGTH_SHORT).show();

                }
            }

        });


    }


//

}





