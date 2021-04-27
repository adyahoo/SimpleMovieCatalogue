package id.ac.mymoviecatalogue.ui.show

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ShowViewModelTest {
    private lateinit var viewModel: ShowViewModel

    @Before
    fun setUp() {
        viewModel = ShowViewModel()
    }

    @Test
    fun getShows() {
        val shows = viewModel.getShows()
        assertNotNull(shows)
        assertEquals(12, shows.size)
    }
}