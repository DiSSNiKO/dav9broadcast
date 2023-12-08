package com.example.dav8

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (private val addables: ArrayList<AddableItemModel>) : RecyclerView.Adapter<RecyclerViewAdapter.TheViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.addable_item,parent,false)
        return TheViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return addables.size
    }

    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        val currentItem = addables[position]
        holder.addableName.text = currentItem.addableName
        holder.addableMail.text = currentItem.addableEmail
        holder.addableNumber.text = currentItem.addableNumber
        holder.addableLocation.text = currentItem.addableLocation
    }

    class TheViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val addableName : TextView = itemView.findViewById(R.id.addableName)
        val addableMail : TextView = itemView.findViewById(R.id.addableMail)
        val addableNumber : TextView = itemView.findViewById(R.id.addableNumber)
        val addableLocation : TextView = itemView.findViewById(R.id.addableLocation)

    }

}