package id.ac.mymoviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ac.mymoviecatalogue.databinding.ActivityMainBinding

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