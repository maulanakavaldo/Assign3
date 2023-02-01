/**
 * Created by maulana kavaldo on 18/12/2020.
 */
 
package com.assign3.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assign3.room.db.Buku
import kotlinx.android.synthetic.main.buku_row.view.*

class BookAdapter(private val bukuList: List<Buku>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.buku_row, parent, false)

        return BookViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        val currentItem = bukuList[position]
        holder.judul.text = currentItem.judul
        holder.penulis.text = currentItem.penulis
        holder.th_terbit.text = currentItem.th_terbit
        holder.penerbit.text = currentItem.penerbit
        holder.id.text = currentItem.id.toString()

        holder.bind(currentItem,itemClickListener)

    }

    override fun getItemCount() = bukuList.size

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val judul: TextView = itemView.tvJudul
        val penulis: TextView = itemView.tvPenulis
        val th_terbit: TextView = itemView.tvThTerbit
        val jh_buku: TextView = itemView.tvJumlahBuku
        val penerbit: TextView = itemView.tvPenerbit
        val id: TextView = itemView.tvId

        fun bind(buku: Buku, clickListener: OnItemClickListener) {
            judul.text = buku.judul
            penulis.text =  buku.penulis
            th_terbit.text = buku.th_terbit
            jh_buku.text = buku.jh_buku
            penerbit.text =  buku.penerbit
            id.text = buku.id.toString()

            itemView.setOnClickListener {
                clickListener.onClickedItem(buku)
            }
        }
    }
}

interface OnItemClickListener {
    fun onClickedItem(buku: Buku)
}