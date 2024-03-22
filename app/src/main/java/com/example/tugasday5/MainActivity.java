package com.example.tugasday5;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNamaPelanggan, etKodeBarang, etJumlahBarang;
    private RadioGroup radioGroup;
    private Button btnProses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNamaPelanggan = findViewById(R.id.etNamaPelanggan);
        etKodeBarang = findViewById(R.id.etKodeBarang);
        etJumlahBarang = findViewById(R.id.etJumlahBarang);
        etJumlahBarang.setInputType(InputType.TYPE_CLASS_NUMBER); // Tambahkan baris ini
        radioGroup = findViewById(R.id.radioGroup);
        btnProses = findViewById(R.id.btnProses);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaPelanggan = etNamaPelanggan.getText().toString();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(selectedId);
                String tipePelanggan = rbSelected != null ? rbSelected.getText().toString() : "";
                String kodeBarang = etKodeBarang.getText().toString().toUpperCase();
                String jumlahBarangStr = etJumlahBarang.getText().toString();
                int jumlahBarang = Integer.parseInt(jumlahBarangStr);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("namaPelanggan", namaPelanggan);
                intent.putExtra("tipePelanggan", tipePelanggan);
                intent.putExtra("kodeBarang", kodeBarang);
                intent.putExtra("jumlahBarang", jumlahBarang);
                startActivity(intent);
            }
        });
    }
}
