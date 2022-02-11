package com.damikkg.test65appsfragments.presentation.personslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.damikkg.test65appsfragments.R
import com.damikkg.test65appsfragments.domain.models.Person

class PersonsListAdapter (
    private val data:List<Person>,
    private val onItemClicked:(person: Person)->Unit
) : RecyclerView.Adapter<PersonsListAdapter.PersonViewHolder>()
{
    class PersonViewHolder(item: View) : RecyclerView.ViewHolder(item)
    {
        val title: TextView = item.findViewById(R.id.pi_title)
        val age: TextView = item.findViewById(R.id.pi_age)
        val ageCard: CardView = item.findViewById(R.id.pi_ageCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_item, parent,false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.title.text =
            holder.itemView.context.getString(
                R.string.template_fname_sname,
                data[position].firstName.lowercase().replaceFirstChar { it.uppercase() },
                data[position].secondName.lowercase().replaceFirstChar { it.uppercase() }
            )
        if(data[position].getAge()>0)
        {
            holder.age.text = holder.itemView
                .context
                .getString(
                    R.string.template_age,
                    data[position].getAge()
                )
        }
        else
        {
            holder.ageCard.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            onItemClicked(data[position])
        }
    }

    override fun getItemCount() = data.size
}