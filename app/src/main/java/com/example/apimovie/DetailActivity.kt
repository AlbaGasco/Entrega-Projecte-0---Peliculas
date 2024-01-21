package com.example.apimovie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent: Intent = intent
        if (intent.hasExtra("movie")) {
            val movie: Movie = intent.getSerializableExtra("movie") as Movie
            // Mostrar los datos en la interfaz de usuario (TextViews, ImageViews, etc.)
            findViewById<TextView>(R.id.titleTextView).text = movie.title
            findViewById<TextView>(R.id.releaseDateTextView).text = movie.releaseDate
            findViewById<TextView>(R.id.overviewTextView).text = movie.overview
        }
    }
}
