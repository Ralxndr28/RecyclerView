package coba.paba.recyclerview

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var _nama: MutableList<String>
    private lateinit var _karakter: MutableList<String>
    private lateinit var _deskripsi: MutableList<String>
    private lateinit var _gambar: MutableList<String>

    private var arWayang = arrayListOf<Wayang>()
    private lateinit var _rvWayang: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _rvWayang = findViewById(R.id.rvWayang)

        SiapkanData()
        TambahData()
        TampilkanData()
    }

    fun SiapkanData() {
        _nama = resources.getStringArray(R.array.namaWayang).toMutableList()
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang).toMutableList()
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang).toMutableList()
        _gambar = resources.getStringArray(R.array.gambarWayang).toMutableList()
    }

    private fun TambahData() {
        for (position in _nama.indices) {
            val data = Wayang(
                foto = _gambar[position],
                nama = _nama[position],
                karakter = _karakter[position],
                deskripsi = _deskripsi[position]
            )
            arWayang.add(data)
        }
    }

    private fun TampilkanData() {
        _rvWayang.layoutManager = GridLayoutManager(this, 2)

        // Membuat instance adapter dan menghubungkannya ke RecyclerView
        val adapterWayang = adapterRecView(arWayang)
        _rvWayang.adapter = adapterWayang

        // Mengatur callback untuk menangani item yang diklik
        adapterWayang.setOnItemClickCallback(object : adapterRecView.OnItemClickCallback {
            override fun onItemClicked(data: Wayang) {
                // Menampilkan toast atau memulai intent ke Activity detail
                Toast.makeText(this@MainActivity, data.nama, Toast.LENGTH_LONG).show()

                // Memulai Activity detail dan mengirim data Wayang melalui Intent
                val intent = Intent(this@MainActivity, detWayang::class.java)
                intent.putExtra("EXTRA_WAYANG", data)
                startActivity(intent)
            }

            override fun delData(pos: Int) {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("HAPUS DATA")
                    .setMessage("Apakah Benar Data " +_nama[pos]+" akan dihapus?")
                    .setPositiveButton(
                        "HAPUS",
                        DialogInterface.OnClickListener{dialog, which ->
                            _gambar.removeAt(pos)
                            _nama.removeAt(pos)
                            _deskripsi.removeAt(pos)
                            _karakter.removeAt(pos)

                            TambahData()
                            TampilkanData()
                        }
                    )
                    .setNegativeButton(
                        "BATAL",
                        DialogInterface.OnClickListener{dialog, which ->
                            Toast.makeText(
                                this@MainActivity,
                                "Data Batal Dihapus", Toast.LENGTH_LONG)
                                .show()
                        }
                    ).show()
            }
        })
    }
}