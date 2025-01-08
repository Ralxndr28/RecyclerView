package coba.paba.recyclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class detWayang : AppCompatActivity() {

    // Deklarasi variabel untuk ImageView dan TextView
    private lateinit var ivFoto: ImageView
    private lateinit var tvNama: TextView
    private lateinit var tvDes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_det_wayang)

        // Inisialisasi variabel dengan view dari layout
        ivFoto = findViewById(R.id.ivFoto)
        tvNama = findViewById(R.id.tvNama)
        tvDes = findViewById(R.id.tvDetail)

        // Menerima data Wayang yang dikirim dari MainActivity
        val dataIntent = intent.getParcelableExtra<Wayang>("kirimData")

        // Memastikan data tidak null sebelum menggunakannya
        if (dataIntent != null) {
            Picasso.get()
                .load(dataIntent.foto) // Memuat gambar dari URL foto
                .into(ivFoto)

            tvNama.text = dataIntent.nama
            tvDes.text = dataIntent.deskripsi
        }
    }
}
