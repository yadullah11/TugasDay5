package com.example.tugasday5;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tugasday5.R;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tvNamaPelanggan = findViewById(R.id.tvNamaPelanggan);
        TextView tvTipePelanggan = findViewById(R.id.tvTipePelanggan);
        TextView tvKodeBarang = findViewById(R.id.tvKodeBarang);
        TextView tvNamaBarang = findViewById(R.id.tvNamaBarang);
        TextView tvHarga = findViewById(R.id.tvHarga);
        TextView tvTotalHarga = findViewById(R.id.tvTotalHarga);
        TextView tvDiskonHarga = findViewById(R.id.tvDiskonHarga);
        TextView tvDiskonMember = findViewById(R.id.tvDiskonMember);
        TextView tvJumlahBayar = findViewById(R.id.tvJumlahBayar);
        Button btnShare = findViewById(R.id.btnShare);

        Intent intent = getIntent();
        String namaPelanggan = intent.getStringExtra("namaPelanggan");
        String tipePelanggan = intent.getStringExtra("tipePelanggan");
        String kodeBarang = intent.getStringExtra("kodeBarang");
        int jumlahBarang = intent.getIntExtra("jumlahBarang", 0);

        double hargaBarang = 0;
        double totalHarga = 0;
        double diskonHarga = 0;
        double diskonMember = 0;
        final double jumlahBayar;

        if (kodeBarang != null) {
            switch (kodeBarang) {
                case "SGS":
                    hargaBarang = 12999999;
                    break;
                case "PCO":
                    hargaBarang = 2730551;
                    break;
                case "AAE":
                    hargaBarang = 8676981;
                    break;
            }
            totalHarga = hargaBarang * jumlahBarang;

            if (totalHarga > 10000000) {
                diskonHarga = 100000;
            }
            if (tipePelanggan != null) {
                switch (tipePelanggan) {
                    case "Gold":
                        diskonMember = 0.1 * totalHarga;
                        break;
                    case "Silver":
                        diskonMember = 0.05 * totalHarga;
                        break;
                    case "Reguler":
                        diskonMember = 0.02 * totalHarga;
                        break;
                }
            }

            jumlahBayar = totalHarga - diskonHarga - diskonMember;

            Resources res = getResources();
            tvNamaPelanggan.setText(res.getString(R.string.selamat_datang) + " " + namaPelanggan);
            tvTipePelanggan.setText(res.getString(R.string.tipe_pelanggan) + " : " + tipePelanggan);
            tvKodeBarang.setText(res.getString(R.string.kode_barang_label) + " : " + kodeBarang);
            tvNamaBarang.setText(res.getString(R.string.nama_barang_label) + " : " + getNamaBarang(kodeBarang));
            tvHarga.setText(res.getString(R.string.harga_label) + " : Rp " + String.format("%,.0f", hargaBarang));
            tvTotalHarga.setText(res.getString(R.string.total_harga_label) + " : Rp " + String.format("%,.0f", totalHarga));
            tvDiskonHarga.setText(res.getString(R.string.diskon_harga_label) + " : Rp " + String.format("%,.0f", diskonHarga));
            tvDiskonMember.setText(res.getString(R.string.diskon_member_label) + " : Rp " + String.format("%,.0f", diskonMember));
            tvJumlahBayar.setText(res.getString(R.string.jumlah_bayar_label) + " : Rp " + String.format("%,.0f", jumlahBayar));

            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String namaProduk = getNamaBarang(kodeBarang);
                    String deskripsiProduk = getDeskripsiProduk(kodeBarang, jumlahBayar);
                    String shareMessage = res.getString(R.string.nama_barang_label) + " : " + namaProduk + "\nDeskripsi: " + deskripsiProduk;

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(intent, res.getString(R.string.share_button_label)));
                }
            });
        }
    }

    private String getNamaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "SGS":
                return "Samsung Galaxy S20";
            case "PCO":
                return "POCO M3";
            case "AAE":
                return "Acer Aspire E14";
            default:
                return "";
        }
    }
    private String getDeskripsiProduk(String kodeBarang, double jumlahBayar) {
        String deskripsi = "";
        Resources res = getResources();
        switch (kodeBarang) {
            case "SGS":
                deskripsi = "\n" + res.getString(R.string.transaksi_hari_ini_label) + " Rp. " + String.format("%,.0f", jumlahBayar) + " pada " + res.getString(R.string.app_name);
                break;
            case "PCO":
                deskripsi = "\n" + res.getString(R.string.transaksi_hari_ini_label) + " Rp. " + String.format("%,.0f", jumlahBayar) + " pada " + res.getString(R.string.app_name);
                break;
            case "AAE":
                deskripsi = "\n" + res.getString(R.string.transaksi_hari_ini_label) + " Rp. " + String.format("%,.0f", jumlahBayar) + " pada " + res.getString(R.string.app_name);
                break;
        }
        return deskripsi;
    }
}
