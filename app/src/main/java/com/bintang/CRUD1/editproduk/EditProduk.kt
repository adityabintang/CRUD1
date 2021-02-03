package com.bintang.CRUD1.editproduk

import android.content.Intent
import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.bintang.CRUD1.R
import com.bintang.CRUD1.editproduk.model.ResponseEditProduk
import com.bintang.CRUD1.network.NetworkClient
import com.bintang.CRUD1.tampilproduk.TampilProduk
import kotlinx.android.synthetic.main.activity_edit_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProduk : AppCompatActivity() {
    private var id: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_produk)

        id = intent.getIntExtra("id", 0)
        editkodebarang.setText(intent.getStringExtra("kode_barang"))
        editnamabarang.setText(intent.getStringExtra("nama_barang"))
        editstock.setText(intent.getStringExtra("stock"))
        editdeskripsi.setText(intent.getStringExtra("deskripsi"))

        editbtn.setOnClickListener {
            val kodebarang = editkodebarang?.text.toString()
            val namabarang = editnamabarang?.text.toString()
            val stock = editstock?.text.toString()
            val deskripsi = editdeskripsi?.text.toString()

            if (TextUtils.isEmpty(kodebarang)) {
                editkodebarang?.error = "Wajib Diisi"
                editkodebarang?.requestFocus()
            }else if (TextUtils.isEmpty(namabarang)) {
                editnamabarang?.error = "Wajib Diisi"
                editnamabarang?.requestFocus()
            }else if (TextUtils.isEmpty(stock)) {
                editstock?.error = "Wajib Diisi"
                editstock?.requestFocus()
            }else if (TextUtils.isEmpty(deskripsi)) {
                editdeskripsi?.error = "Wajib Diisi"
                editdeskripsi?.requestFocus()
            }else {
                NetworkClient.service().editProduk(id?:0, kodebarang ,namabarang, stock, deskripsi).enqueue(object : Callback<ResponseEditProduk>{
                    override fun onResponse(call: Call<ResponseEditProduk>, response: Response<ResponseEditProduk>) {
                        if (response.isSuccessful) {
                            val result = response.body()?.isSuccess
                            val pesan = response.body()?.message
                            if (result == true) {
                                val intent = Intent(applicationContext, TampilProduk::class.java)
                                startActivity(intent)
                                Toast.makeText(this@EditProduk, pesan, Toast.LENGTH_SHORT).show()
                            }else {
                                Toast.makeText(this@EditProduk, pesan, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseEditProduk>, t: Throwable) {
                        Toast.makeText(this@EditProduk, ""+t.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }
//            val id = editid?.text.toString()
        }
    }

    }
