package com.example.posterlife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.posterlife.databinding.ActivityMainBinding

/**
 * @source https://developer.android.com/codelabs/basic-android-kotlin-training-fragments-navigation-component#10
 *      https://youtu.be/Q2HY58s9cHs
 */
class MainActivity : AppCompatActivity() {
    //something +
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navControl = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navControl)
    }
}