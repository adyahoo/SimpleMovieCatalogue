package id.ac.mymoviecatalogue.data

data class MoviesEntity(
    var movieId: Int,
    var title: String,
    var releaseDate: String,
    var overview: String,
    var genre: String?,
    var production: String?,
    var poster: String
)