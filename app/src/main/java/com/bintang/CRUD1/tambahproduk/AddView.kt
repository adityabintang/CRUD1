package com.bintang.CRUD1.tambahproduk

import com.bintang.CRUD1.tambahproduk.model.ResponseTambahProduk
import retrofit2.Response

interface AddView {

    fun isEmpty(msg : String)

    fun onSuccessAdd(response:ResponseTambahProduk)

    fun onErrorServer(msg : String)
}