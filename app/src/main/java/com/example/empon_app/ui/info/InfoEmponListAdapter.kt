package com.example.empon_app.ui.info

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.empon_app.MainActivity
import com.example.empon_app.R
import com.example.empon_app.R.layout.fragment_list_empon_card
import com.example.empon_app.model.Empon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_list_empon_card.view.*


class InfoEmponListAdapter(val emponList: ArrayList<Empon>) :
    RecyclerView.Adapter<InfoEmponListAdapter.EmponViewHolder>() {

    class EmponViewHolder(var view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmponViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(fragment_list_empon_card, parent, false)

        return EmponViewHolder(view)
    }

    override fun getItemCount(): Int {
        return emponList.size
//        return 3
    }

    override fun onBindViewHolder(holder: EmponViewHolder, position: Int) {


        holder.view.textViewNamaEmpon.text = emponList[position].jenis
        holder.view.textViewNamaLatin.text = emponList[position].namaLatin
        holder.view.tag = emponList[position].id
        Log.d("idlist Empon", MainActivity.imageIdList[position].toString())
        holder.view.imageViewEmpon.setImageResource(MainActivity.imageIdList[position])
        holder.view.cardEmpon.setOnClickListener {
            val emponId = emponList[position].id!!.toInt()
            val action = InfoFragmentDirections.actionDetailEmpon(emponId)
            Navigation.findNavController(it).navigate(action)
            Log.d("Id Empon", emponId.toString())
        }
    }

    //supaya bisa diupdate
    fun updateEmponList(newEmponList: List<Empon>) {
        emponList.clear()
        emponList.addAll(newEmponList)
        notifyDataSetChanged()
    }


    fun Context.resIdByName(resIdName: String?, resType: String): Int {
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }

}