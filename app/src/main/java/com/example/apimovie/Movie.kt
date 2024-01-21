package com.example.apimovie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("original_title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String
) : Serializable

data class MovieResponse(
    @SerializedName("results") val movies: List<Movie>
)