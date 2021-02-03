package com.bintang.CRUD1.tampilproduk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bintang.CRUD1.R
import com.bintang.CRUD1.network.NetworkClient
import com.bintang.CRUD1.tambahproduk.TambahProduk
import com.bintang.CRUD1.tampilproduk.adapter.AdapterTampilProduk
import com.bintang.CRUD1.tampilproduk.model.DataItem
import com.bintang.CRUD1.tampilproduk.model.ResponseTampilProduk
import kotlinx.android.synthetic.main.activity_tampil_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TampilProduk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampil_produk)

        showProduk()
        searchProduk()

        btntambahactivity.setOnClickListener {
            val intent = Intent(this@TampilProduk, TambahProduk::class.java)
            startActivity(intent)
        }
    }
    private lateinit var dataItemList: List<DataItem?>
    fun showProduk() {
        NetworkClient.service().TampilProduk().enqueue(object : Callback<ResponseTampilProduk> {
            override fun onResponse(call: Call<ResponseTampilProduk>, response: Response<ResponseTampilProduk>) {
                if (response.isSuccessful) {
                    dataItemList = response.body()?.data!!
                }
            }

            override fun onFailure(call: Call<ResponseTampilProduk>, t: Throwable) {

            }

        })
    }
    fun searchProduk() {
      this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            recyclerview.layoutManager = LinearLayoutManager(this)
        searchbox.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
              val keyword = searchbox.text.toString()
                NetworkClient.service().requestSearchProduk(keyword).enqueue(object : Callback<ResponseTampilProduk> {

                    override fun onResponse(call: Call<ResponseTampilProduk>, response: Response<ResponseTampilProduk>) {
                        if(response.isSuccessful) {
                            if (response.body()?.isSuccess?: false) {
                                recyclerview.adapter = response.body()?.data?.let { AdapterTampilProduk(this@TampilProduk, it) }
                            }else {
                                Toast.makeText(this@TampilProduk, "pencarian tidak ditemukan", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseTampilProduk>, t: Throwable) {
                        t.message
                    }

                })
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}