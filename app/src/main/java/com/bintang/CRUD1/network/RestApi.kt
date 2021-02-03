package com.bintang.CRUD1.network

import com.bintang.CRUD1.detailproduk.model.ResponseHapusProduk
import com.bintang.CRUD1.editproduk.model.ResponseEditProduk
import com.bintang.CRUD1.tambahproduk.TambahProduk
import com.bintang.CRUD1.tambahproduk.model.ResponseTambahProduk
import com.bintang.CRUD1.tampilproduk.model.ResponseTampilProduk
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @FormUrlEncoded
    @POST("add")
    fun TambahProduk(
            @Field("kode_barang") kode_barang: String,
            @Field("nama_barang") nama_barang: String,
            @Field("stock") stock: String,
            @Field("deskripsi") deskripsi: String
    ):Call<ResponseTambahProduk>

    @GET("get")
    fun TampilProduk(): Call<ResponseTampilProduk>

    @GET("cari")
    fun requestSearchProduk(
            @Query("q") keyword: String,
    ): Call<ResponseTampilProduk>

    @FormUrlEncoded
    @POST("delete")
    fun deleteProduk(
            @Field("id") idUser: Int
    ): Call<ResponseHapusProduk>

    @FormUrlEncoded
    @POST("modif/{id}")
    fun editProduk(
            @Path("id") id: Int,
            @Field("kode_barang") kode_barang: String,
            @Field("nama_barang") nama_barang: String,
            @Field("stock") stock: String,
            @Field("deskripsi") deskripsi: String
    ): Call<ResponseEditProduk>





}