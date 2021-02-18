package com.bintang.CRUD1.tambahproduk

import android.text.TextUtils
import com.bintang.CRUD1.network.NetworkClient
import com.bintang.CRUD1.tambahproduk.model.ResponseTambahProduk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahProdukPresenter(val  view : AddView) {


    fun tambah(kode_barang : String,nama_barang : String,stock : String,deskripsi : String){
        if(TextUtils.isEmpty(kode_barang)) {

            view.isEmpty("kode tidak boleh kosong")
        } else if (TextUtils.isEmpty(nama_barang)) {

        } else if(TextUtils.isEmpty(stock)) {
        } else if (TextUtils.isEmpty(deskripsi)) {
        } else {
            NetworkClient.service().TambahProduk(kode_barang, nama_barang, stock, deskripsi).enqueue(object :
                Callback<ResponseTambahProduk> {
                override fun onResponse(call: Call<ResponseTambahProduk>, response: Response<ResponseTambahProduk>) {
                    if (response.isSuccessful) {

                        response.body()?.let { view.onSuccessAdd(it) }

                    }
                }

                override fun onFailure(call: Call<ResponseTambahProduk>, t: Throwable) {
                    view.onErrorServer(t.localizedMessage)
                }

            })
        }
    }
}