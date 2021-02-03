package com.bintang.CRUD1.tambahproduk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.bintang.CRUD1.R
import com.bintang.CRUD1.network.NetworkClient
import com.bintang.CRUD1.tambahproduk.model.ResponseTambahProduk
import kotlinx.android.synthetic.main.activity_tambah_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahProduk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_produk)

        btnTambah.setOnClickListener {
            actionTambah()
        }
    }

    private fun actionTambah() {
        val kode_barang = tambahkodebarang?.text.toString()
        val nama_barang = tambahnamabarang?.text.toString()
        val stock = tambahstock?.text.toString()
        val deskripsi = tambahdeskripsi?.text.toString()

        if(TextUtils.isEmpty(kode_barang)) {
            tambahkodebarang?.error = "Wajib Diisi"
            tambahkodebarang?.requestFocus()
        } else if (TextUtils.isEmpty(nama_barang)) {
            tambahnamabarang.error = "Wajib Diisi"
            tambahnamabarang.requestFocus()
        } else if(TextUtils.isEmpty(stock)) {
            tambahstock.error = "Wajib Diisi"
            tambahstock.requestFocus()
        } else if (TextUtils.isEmpty(deskripsi)) {
            tambahdeskripsi.error = "Wajib Diisi"
            tambahdeskripsi.requestFocus()
        } else {
            NetworkClient.service().TambahProduk(kode_barang, nama_barang, stock, deskripsi).enqueue(object : Callback<ResponseTambahProduk> {
                override fun onResponse(call: Call<ResponseTambahProduk>, response: Response<ResponseTambahProduk>) {
                    if (response.isSuccessful) {
                        val result = response.body()?.isSuccess
                        val message = response.body()?.message

                        if (result == true){
                            val intent = Intent(applicationContext, TambahProduk::class.java)

                            startActivity(intent)

                            Toast.makeText(this@TambahProduk, message, Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this@TambahProduk, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseTambahProduk>, t: Throwable) {
                    Toast.makeText(this@TambahProduk, ""+t.message,  Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}