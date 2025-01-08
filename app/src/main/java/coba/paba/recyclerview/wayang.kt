package coba.paba.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Model data untuk Wayang
@Parcelize
data class Wayang(
    var foto: String,
    var nama: String,
    var karakter: String,
    var deskripsi: String
) : Parcelable

// Adapter untuk RecyclerView
class adapterRecView(private val listWayang: ArrayList<Wayang>) : RecyclerView.Adapter<adapterRecView.ListViewHolder>() {

    // Deklarasi variabel untuk callback
    private lateinit var onItemClickCallback: OnItemClickCallback

    // ViewHolder untuk menghubungkan view dari item_recycler.xml
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambarWayang: ImageView = itemView.findViewById(R.id.gambarWayang)
        val namaWayang: TextView = itemView.findViewById(R.id.namaWayang)
        val karakterWayang: TextView = itemView.findViewById(R.id.karakterWayang)
        val deskripsiWayang: TextView = itemView.findViewById(R.id.deskripsiWayang)
        val btnHapus: TextView = itemView.findViewById(R.id.btnHapus)
    }

    // Membuat dan menghubungkan ViewHolder dengan layout item_recycler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ListViewHolder(view)
    }

    // Menghubungkan data dengan view di ListViewHolder
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val wayang = listWayang[position]
        holder.namaWayang.text = wayang.nama
        holder.deskripsiWayang.text = wayang.deskripsi
        holder.karakterWayang.text = wayang.karakter

        Picasso.get()
            .load(wayang.foto) // URL atau sumber gambar dari objek wayang
            .into(holder.gambarWayang)

        // Mengatur klik listener pada item dan memanggil callback
        holder.gambarWayang.setOnClickListener {
            onItemClickCallback.onItemClicked(listWayang[position])
        }

        holder.btnHapus.setOnClickListener {
            onItemClickCallback.delData(position)

        }
    }
    // Mendapatkan jumlah item dalam dataset
    override fun getItemCount(): Int {
        return listWayang.size
    }

    // Interface untuk menangani klik pada item
    interface OnItemClickCallback {
        fun onItemClicked(data: Wayang)
        fun delData(pos:Int)
    }

    // Fungsi untuk mengatur callback dari luar
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}
