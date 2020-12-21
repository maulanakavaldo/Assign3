package com.assign3.room

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.assign3.room.db.Perpustakaan
import com.assign3.room.db.Buku
import kotlinx.android.synthetic.main.activity_addbuku.*

class AddBuku : AppCompatActivity() {

    val booksDao = Perpustakaan.getInstance().bookDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addbuku)

        val actionbar = supportActionBar
        actionbar!!.title = "Tambah Buku"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayShowHomeEnabled(true)

        btnCancelBook.setOnClickListener {
            finish()
        }

        btnSaveBook.setOnClickListener {

            if (etEditJudul.text.isNullOrEmpty() || etEditPenulis.text.isNullOrEmpty()) {
                when {
                    etEditJudul.text.isNullOrEmpty() -> {
                        etEditJudul.error = "Judul perlu diisi"
                        etEditJudul.requestFocus()
                    }
                    else -> {
                        etEditPenulis.error = "Penulis perlu diisi"
                        etEditPenulis.requestFocus()
                    }
                }
            } else {

                val title = etEditJudul.text.toString()
                val penulis = etEditPenulis.text.toString()
                val jh_buku = etEditjh_buku.text.toString()
                val th_terbit = etEditth_terbit.text.toString()
                val penerbit = etEditpenerbit.text.toString()

                val book = Buku(0, title, penulis, jh_buku, th_terbit, penerbit)

                booksDao.insertBook(book)

                Log.d("TAG", "Buku ditambahkan title: $title, penulis: $penulis")

                Toast.makeText(this, "Buku tersimpan!!", Toast.LENGTH_SHORT).show()

                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }
}