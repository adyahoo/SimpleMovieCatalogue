package id.ac.mymoviecatalogue.data

data class MoviesEntity(
    val movieId: String,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val genre: String,
    val director: String,
    val poster: Int
)