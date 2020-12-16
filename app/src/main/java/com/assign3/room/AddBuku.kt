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

            if (etEditTitle.text.isNullOrEmpty() || etEditAuthor.text.isNullOrEmpty()) {
                when {
                    etEditTitle.text.isNullOrEmpty() -> {
                        etEditTitle.error = "Judul perlu diisi"
                        etEditTitle.requestFocus()
                    }
                    else -> {
                        etEditAuthor.error = "Penulis perlu diisi"
                        etEditAuthor.requestFocus()
                    }
                }
            } else {

                val title = etEditTitle.text.toString()
                val author = etEditAuthor.text.toString()
                val jh_buku = etEditjh_buku.text.toString()
                val th_terbit = etEditth_terbit.text.toString()
                val penerbit = etEditpenerbit.text.toString()

                val book = Buku(0, title, author, jh_buku, th_terbit, penerbit)

                booksDao.insertBook(book)

                Log.d("TAG", "Buku ditambahkan title: $title, author: $author")

                Toast.makeText(this, "Buku tersimpan!!", Toast.LENGTH_SHORT).show()

                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }
}