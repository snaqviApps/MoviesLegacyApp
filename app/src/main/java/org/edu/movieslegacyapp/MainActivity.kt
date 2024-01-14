package org.edu.movieslegacyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movieslegacyapp.R
import com.example.movieslegacyapp.databinding.ActivityMainBinding
import org.edu.movieslegacyapp.view.ui.PopularMoviesFragment
import org.edu.movieslegacyapp.view.ui.UpComingMoviesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        bindingMain.bottomNavView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_item_popular_movies -> setCurrentFragment(PopularMoviesFragment())
                R.id.nav_item_popular_movies -> setCurrentFragment(UpComingMoviesFragment())
                else -> {
                }
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.
        beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }

    }

