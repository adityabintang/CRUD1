package com.bintang.CRUD1.tampilproduk.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintang.CRUD1.detailproduk.DetailProduk
import com.bintang.CRUD1.R
import com.bintang.CRUD1.tampilproduk.model.DataItem
import kotlinx.android.synthetic.main.item_produk.view.*

class AdapterTampilProduk (val context : Context, val dataItem: List<DataItem?>) :

        RecyclerView.Adapter<AdapterTampilProduk.MyViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_produk, parent, false))
    }
    override fun getItemCount(): Int {
        return dataItem.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        dataItem.get(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailProduk::class.java)
            intent.putExtra("id", dataItem.get(position)?.id)
            intent.putExtra("kode_barang", dataItem.get(position)?.kodeBarang)
            intent.putExtra("nama_barang", dataItem.get(position)?.namaBarang)
            intent.putExtra("stock", dataItem.get(position)?.stock)
            intent.putExtra("deskripsi", dataItem.get(position)?.deskripsi)

            context.startActivity(intent)
        }
    }


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(itemData: DataItem) {
            itemView.kodebarang.text = "Kode barang :"+itemData?.kodeBarang
            itemView.namabarang.text = "Nama Barang :"+itemData?.namaBarang
            itemView.stock.text = "Stock :"+itemData?.stock
            itemView.deskripsi.text = "Deskripsi :"+itemData.deskripsi
        }
    }

}