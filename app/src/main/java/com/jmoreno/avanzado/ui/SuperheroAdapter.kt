package com.jmoreno.avanzado.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jmoreno.avanzado.R
import com.jmoreno.avanzado.databinding.ItemSuperheroBinding
import com.jmoreno.avanzado.ui.model.Superhero
import com.squareup.picasso.Picasso

class SuperheroAdapter(private val onClick: (String) -> (Unit)) :
    ListAdapter<Superhero,SuperheroAdapter.SuperheroViewHolder>(SuperheroDiffCallback()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuperheroViewHolder {  // Crea las celdas
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        return SuperheroViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: SuperheroViewHolder,
        position: Int
    ) { // Rellena las celdas

        holder.bind(getItem(position))
    }

    inner class SuperheroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val superheroName = itemView.findViewById<TextView>(R.id.tvName)
        private val superheroImage = itemView.findViewById<ImageView>(R.id.ivPersonaje)

        private lateinit var superhero: Superhero

        init {
            itemView.setOnClickListener{
                onClick(superhero.id)
            }
        }

        fun bind(superhero: Superhero) {
            this.superhero = superhero
            superheroName.text = superhero.name
            Picasso.get().load(superhero.photo)
                .into(superheroImage)
        }
    }
    class SuperheroDiffCallback: DiffUtil.ItemCallback<Superhero>(){
        override fun areItemsTheSame(oldItem: Superhero, newItem: Superhero): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Superhero, newItem: Superhero): Boolean {
            return oldItem == newItem
        }

    }
}