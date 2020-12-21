package com.assign3.room

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.assign3.room.db.Buku
import com.assign3.room.db.Perpustakaan
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val booksDao = Perpustakaan.getInstance().bookDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayBooks()

        btnTambahBuku.setOnClickListener {
            startAddNewBookActivity()
        }

    }

    private fun startAddNewBookActivity() {
        Intent(this, AddBuku::class.java).apply {
            startActivity(this)
        }
    }

    private fun displayBooks() {
        rvRecyclerViewBooks.adapter = BookAdapter(booksDao.getAllbuku(), this)
        rvRecyclerViewBooks.layoutManager = LinearLayoutManager(this)
        rvRecyclerViewBooks.setHasFixedSize(true)
    }

    private fun destroy() {
        booksDao.nukeDb()
        displayBooks()
    }

    private fun generateDummyList(size: Int): List<Buku> {

        val listOfBooks = ArrayList<Buku>()

        for (i in 0 until size) {

            val book = Buku(i, "judul $i", "penulis $i", "jh_halaman $i", "th_terbit", "penerbit")
            listOfBooks += book

        }
        return listOfBooks
    }

    override fun onClickedItem(buku: Buku) {

        Intent(this, PilihBuku::class.java).also {

            val judul = buku.judul
            val penulis = buku.penulis
            val jh_buku = buku.jh_buku
            val th_terbit = buku.th_terbit
            val penerbit = buku.penerbit
            val id = buku.id

            it.putExtra("EXTRA_JUDUL", judul)
            it.putExtra("EXTRA_PENULIS", penulis)
            it.putExtra("EXTRA_JHBuku", jh_buku)
            it.putExtra("EXTRA_THTerbit", th_terbit)
            it.putExtra("EXTRA_Penerbit", penerbit)
            it.putExtra("EXTRA_ID", id)

            startActivity(it)
        }
        Log.d("MainAct", "judul=${buku.judul} penulis=${buku.penulis}")
    }
}