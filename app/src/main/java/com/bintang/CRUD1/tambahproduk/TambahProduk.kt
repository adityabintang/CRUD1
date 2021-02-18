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

class TambahProduk : AppCompatActivity(),AddView {

    private var presenter : TambahProdukPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_produk)

        presenter = TambahProdukPresenter(this)
        btnTambah.setOnClickListener {
            actionTambah()
        }
    }

    private fun actionTambah() {
        val kode_barang = tambahkodebarang?.text.toString()
        val nama_barang = tambahnamabarang?.text.toString()
        val stock = tambahstock?.text.toString()
        val deskripsi = tambahdeskripsi?.text.toString()


        presenter?.tambah(kode_barang,nama_barang,stock,deskripsi)
    }

    override fun isEmpty(msg: String) {
        //show alert
    }

    override fun onSuccessAdd(response: ResponseTambahProduk) {
        if(response.isSuccess == true){

            //intent
        }
        else{
            response.message
            //show di alert
        }
    }

    override fun onErrorServer(msg: String) {
        //show alert

    }
}