package id.ac.mymoviecatalogue.data

data class TvShowEntity(
    var showId: String,
    var title: String,
    var releaseYear: String,
    var overview: String,
    var creator: String,
    var genre: String,
    var poster: Int
)
