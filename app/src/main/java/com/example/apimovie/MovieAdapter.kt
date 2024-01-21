package com.example.apimovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private var onItemClick: (Int) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    public val movies: MutableList<Movie> = mutableListOf()

    fun addMovies(newMovies: List<Movie>) {
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.title

        // Cargar y mostrar la carátula utilizando una biblioteca de imágenes (por ejemplo, Glide)
        /*Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .placeholder(R.drawable.placeholder) // Puedes agregar un placeholder opcional
            .error(R.drawable.error) // Puedes agregar una imagen de error opcional
            .into(holder.posterImageView)*/

        holder.itemView.setOnClickListener { onItemClick(position) }
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        // Otros inicializaciones si es necesario
    }

    fun clear() {
        movies.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClick = listener
    }
}