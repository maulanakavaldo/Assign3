package com.assign3.room.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Buku")
data class Buku(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "judul") val judul: String,
    @ColumnInfo(name = "penulis") val penulis: String,
    @ColumnInfo(name = "jh_buku") val jh_buku: String,
    @ColumnInfo(name = "th_terbit") val th_terbit: String,
    @ColumnInfo(name = "penerbit") val penerbit: String
)