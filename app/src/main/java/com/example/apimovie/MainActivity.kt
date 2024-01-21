package com.example.apimovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val adapter = MovieAdapter { position -> onItemClick(position) }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val movieApi = retrofit.create(MovieApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        var currentPage = 1
        loadData(currentPage)
    }

    private fun loadData(page: Int) {
        val call = movieApi.getPopularMovies("46c89edb53a45dea5ab58f6b9019f272", page)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val movies = response.body()?.movies
                    adapter.addMovies(movies ?: emptyList())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun onItemClick(position: Int) {
        val selectedMovie = adapter.movies[position]
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", selectedMovie)
        startActivity(intent)
    }
}
