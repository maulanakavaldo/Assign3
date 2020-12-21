package com.assign3.room

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.assign3.room.db.Perpustakaan
import kotlinx.android.synthetic.main.activity_pilih_buku.*

class PilihBuku : AppCompatActivity() {

    private val booksDao = Perpustakaan.getInstance().bookDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_buku)

        val actionbar = supportActionBar
        actionbar!!.title = "Edit Buku"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayShowHomeEnabled(true)

        btnCancelSelectedBook.setOnClickListener {
            finish()
        }

        val judul = intent.getStringExtra("EXTRA_JUDUL")
        val author = intent.getStringExtra("EXTRA_PENULIS")
        val jh_buku = intent.getStringExtra("EXTRA_JHBuku")
        val th_terbit = intent.getStringExtra("EXTRA_THTerbit")
        val penerbit = intent.getStringExtra("EXTRA_Penerbit")
        val id = intent.getIntExtra("EXTRA_ID", 0)

        etEditJudul.setText(judul)
        etEditPenulis.setText(author)
        etEditjh_buku.setText(jh_buku)
        etEditth_terbit.setText(th_terbit)
        etEditpenerbit.setText(penerbit)

        btnUpdateSelectedBook.setOnClickListener {

            if (etEditJudul.text.isNullOrEmpty() || etEditPenulis.text.isNullOrEmpty()) {
                when {
                    etEditJudul.text.isNullOrEmpty() -> {
                        etEditJudul.error = "Perlu diisi"
                        etEditJudul.requestFocus()
                    }
                    else -> {
                        etEditPenulis.error = "Perlu diisi"
                        etEditPenulis.requestFocus()
                    }
                }
            } else {
                val updatedJudul = etEditJudul.text.toString()
                val updatedPenulis = etEditPenulis.text.toString()
                val updatedJH_Buku = etEditjh_buku.text.toString()
                val updatedTH_Terbit= etEditth_terbit.text.toString()
                val updatedPenerbit = etEditpenerbit.text.toString()

                booksDao.updateBook(id, updatedJudul, updatedPenulis, updatedJH_Buku, updatedTH_Terbit, updatedPenerbit)
                Toast.makeText(
                    this,
                    "Buku diupdate $judul to $updatedJudul",
                    Toast.LENGTH_SHORT
                ).show()
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }

        btnDeleteSelectedBook.setOnClickListener {
            val alertDialogDelete = AlertDialog.Builder(this)
            alertDialogDelete.setTitle("Hapus data")
            alertDialogDelete.setMessage("Apakah Anda ingin menghapusnya?")
            alertDialogDelete.setPositiveButton("Ya") { _, _ ->
                booksDao.deleteByUserId(id)
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
            alertDialogDelete.setNegativeButton("Tidak") { _, _ -> }
            alertDialogDelete.show()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}