package com.bintang.CRUD1.detailproduk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.widget.Toast
import com.bintang.CRUD1.R
import com.bintang.CRUD1.detailproduk.model.ResponseHapusProduk
import com.bintang.CRUD1.editproduk.EditProduk
import com.bintang.CRUD1.network.NetworkClient
import com.bintang.CRUD1.tambahproduk.TambahProduk
import com.bintang.CRUD1.tampilproduk.TampilProduk
import kotlinx.android.synthetic.main.activity_detail_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProduk : AppCompatActivity() {
    private  var id : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        id = intent.getIntExtra("id", 0)
//        detailid.text = intent.getStringExtra("id")
        detailkodebarang.text = intent.getStringExtra("kode_barang")
        detailnamabarang.text = intent.getStringExtra("nama_barang")
        detailstock.text = intent.getStringExtra("stock")
        detaildeskripsi.text = intent.getStringExtra("deskripsi")

        btnedit.setOnClickListener {
            val intent = Intent(this@DetailProduk, EditProduk::class.java)
            intent.putExtra("id", id)
            intent.putExtra("kode_barang", detailkodebarang.text)
            intent.putExtra("nama_barang", detailnamabarang.text)
            intent.putExtra("stock", detailstock.text)
            intent.putExtra("deskripsi", detaildeskripsi.text)

            startActivity(intent)
        }

        btnhapus.setOnClickListener {
            val intent = Intent(this@DetailProduk, EditProduk::class.java)
//            val idUser = intent.getStringExtra("id")
            NetworkClient.service().deleteProduk(id?:0).enqueue(object : Callback<ResponseHapusProduk> {
                override fun onResponse(call: Call<ResponseHapusProduk>, response: Response<ResponseHapusProduk>) {
                    if (response.isSuccessful) {
                        val result = response.body()?.isSuccess
                        val pesan = response.body()?.message
                        if (result == true) {
                            val intent = Intent(applicationContext, TampilProduk::class.java)
                            startActivity(intent)
                            Toast.makeText(this@DetailProduk, pesan, Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this@DetailProduk, pesan, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseHapusProduk>, t: Throwable) {
                    Toast.makeText(this@DetailProduk, ""+t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}