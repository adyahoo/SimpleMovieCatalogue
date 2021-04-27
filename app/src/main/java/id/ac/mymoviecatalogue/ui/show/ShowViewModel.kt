package id.ac.mymoviecatalogue.ui.show

import androidx.lifecycle.ViewModel
import id.ac.mymoviecatalogue.data.TvShowEntity
import id.ac.mymoviecatalogue.utils.DataDummy

class ShowViewModel : ViewModel() {
    fun getShows(): ArrayList<TvShowEntity> {
        return DataDummy.getShows()
    }
}