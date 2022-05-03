package com.example.empon_app.ui.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.empon_app.R
import com.example.empon_app.model.Empon
import com.example.empon_app.R.layout.fragment_list_empon_card
import com.squareup.picasso.Picasso


class InfoEmponListAdapter(val emponList:ArrayList<Empon>)
    : RecyclerView.Adapter<InfoEmponListAdapter.EmponViewHolder>() {

    class EmponViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        val jenisEmpon = itemView.findViewById<TextView>(R.id.textViewNamaEmpon)
        val namalatinEmpon = itemView.findViewById<TextView>(R.id.textViewNamaLatin)
        val imgEmpon = itemView.findViewById<ImageView>(R.id.imageViewEmpon)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmponViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.fragment_list_empon_card, parent, false)
        val view = inflater.inflate(fragment_list_empon_card, parent, false)

        return EmponViewHolder(view)
    }

    override fun getItemCount(): Int {
        return emponList.size
    }
    override fun onBindViewHolder(holder: EmponViewHolder, position: Int) {
        holder.jenisEmpon.text = emponList[position].jenis
        holder.namalatinEmpon.text = emponList[position].jenis
//        Picasso.get().load(R.drawable.quiz2).into(holder.view.imgPaket_card)
//        holder.imgEmpon
    }

    //supaya bisa diupdate
    fun updateEmponList(newEmponList: List<Empon>) {
        emponList.clear()
        emponList.addAll(newEmponList)
        notifyDataSetChanged()
    }

}