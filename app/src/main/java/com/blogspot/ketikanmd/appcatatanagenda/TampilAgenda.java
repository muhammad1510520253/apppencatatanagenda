package com.blogspot.ketikanmd.appcatatanagenda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TampilAgenda extends AppCompatActivity {
    TextView tvnama, tvtanggal, tvtempat, tvketerangan, tvstatus;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_agenda);
        setTitle("Detail Agenda");
        tvnama = findViewById(R.id.nama);
        tvtanggal = findViewById(R.id.tanggal);
        tvtempat = findViewById(R.id.tempat);
        tvketerangan = findViewById(R.id.keterangan);
        tvstatus = findViewById(R.id.status);
        Intent intent = getIntent();
        tvnama.setText(intent.getStringExtra("nama"));
        tvtanggal.setText(intent.getStringExtra("tanggal"));
        tvtempat.setText(intent.getStringExtra("tempat"));
        tvketerangan.setText(intent.getStringExtra("keterangan"));
        tvstatus.setText(intent.getStringExtra("status"));
    }


}
