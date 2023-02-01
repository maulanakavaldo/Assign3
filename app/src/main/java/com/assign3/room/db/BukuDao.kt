/**
 * Created by maulana kavaldo on 18/12/2020.
 */
 
 package com.assign3.room.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BukuDao {

    @Insert
    fun insertBook(buku: Buku)

    @Delete
    fun deleteBook(buku: Buku)

    @Query("SELECT * FROM Buku")
    fun getAllbuku(): List<Buku>

    @Query("SELECT * FROM buku WHERE penulis = :penulis")
    fun getAllbukuByAuthor(penulis: String): List<Buku>

    @Query("DELETE FROM buku")
    fun nukeDb()

    @Query("DELETE FROM buku WHERE id = :userId")
    fun deleteByUserId(userId: Int)

    @Query("UPDATE buku SET judul = :judul, penulis = :penulis, jh_buku = :jh_buku, th_terbit = :th_terbit ,penerbit = :penerbit WHERE id = :userId")
    fun updateBook(userId: Int, judul: String, penulis: String, jh_buku: String, th_terbit: String, penerbit: String)

}