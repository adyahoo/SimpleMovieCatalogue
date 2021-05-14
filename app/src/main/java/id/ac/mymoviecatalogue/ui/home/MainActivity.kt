package id.ac.mymoviecatalogue.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import id.ac.mymoviecatalogue.R
import id.ac.mymoviecatalogue.databinding.ActivityMainBinding
import id.ac.mymoviecatalogue.ui.favorite.FavoriteActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.homeViewPager.adapter = sectionPagerAdapter
        binding.homeTabs.setupWithViewPager(binding.homeViewPager)

        supportActionBar?.elevation = 0f
    }
}