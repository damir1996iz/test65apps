package com.damikkg.test65appsfragments.presentation.speclist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.damikkg.test65appsfragments.R
import com.damikkg.test65appsfragments.domain.models.Speciality

class SpecialtyListAdapter (
    private val data:List<Speciality>,
    private val onItemClicked:(speciality:Speciality)->Unit
) : RecyclerView.Adapter<SpecialtyListAdapter.SpecialtyViewHolder>()
{
    class SpecialtyViewHolder(item: View) : RecyclerView.ViewHolder(item)
    {
        val text: TextView = item.findViewById(R.id.si_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.speciality_item, parent,false)
        return SpecialtyViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpecialtyViewHolder, position: Int) {
        holder.text.text = data[position].name
        holder.itemView.setOnClickListener {
            onItemClicked(data[position])
        }
    }

    override fun getItemCount() = data.size
}