package com.example.empon_app.ui.info

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.empon_app.ui.MainActivity
import com.example.empon_app.R.layout.card_empon
import com.example.empon_app.model.Empon
import kotlinx.android.synthetic.main.card_empon.view.*


class InfoEmponAdapter(private val emponList: ArrayList<Empon>) :
    RecyclerView.Adapter<InfoEmponAdapter.EmponViewHolder>() {

    class EmponViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmponViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(card_empon, parent, false)

        return EmponViewHolder(view)
    }

    override fun getItemCount(): Int {
        return emponList.size
    }

    override fun onBindViewHolder(holder: EmponViewHolder, position: Int) {

        holder.view.textViewNamaEmpon.text = emponList[position].namaJenis
        holder.view.textViewNamaLatin.text = emponList[position].namaLatin
        holder.view.tag = emponList[position].id
        holder.view.imageViewEmpon.setImageResource(MainActivity.imageIdList[position])

        holder.view.cardEmpon.setOnClickListener {
            val emponId = emponList[position].id!!.toInt()
            val action = InfoFragmentDirections.actionDetailEmpon(emponId)
            Navigation.findNavController(it).navigate(action)
        }
    }

    // supaya bisa diupdate
    fun updateEmponList(newEmponList: List<Empon>) {
        emponList.clear()
        emponList.addAll(newEmponList)
        notifyDataSetChanged()
    }


}