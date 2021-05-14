package id.ac.mymoviecatalogue.data

data class TvShowEntity(
    var showId: Int,
    var title: String,
    var releaseDate: String,
    var overview: String,
    var creator: String?,
    var genre: String?,
    var poster: String
)
