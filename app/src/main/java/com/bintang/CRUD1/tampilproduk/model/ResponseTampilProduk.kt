package com.bintang.CRUD1.tampilproduk.model

import com.google.gson.annotations.SerializedName

data class ResponseTampilProduk(

	@field:SerializedName("response_status")
	val responseStatus: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)

data class DataItem(

	@field:SerializedName("kode_barang")
	val kodeBarang: String? = null,

	@field:SerializedName("nama_barang")
	val namaBarang: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("stock")
	val stock: Int? = null
)
