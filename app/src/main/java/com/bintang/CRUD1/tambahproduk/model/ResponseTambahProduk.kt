package com.bintang.CRUD1.tambahproduk.model

import com.google.gson.annotations.SerializedName

data class ResponseTambahProduk(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Data(

	@field:SerializedName("kode_barang")
	val kodeBarang: String? = null,

	@field:SerializedName("nama_barang")
	val namaBarang: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("stock")
	val stock: String? = null
)
