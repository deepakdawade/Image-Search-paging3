package com.devdd.framework.imagesearch_pagination.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.devdd.framework.imagesearch_pagination.R
import com.devdd.framework.imagesearch_pagination.databinding.ActivityMainBinding
import com.devdd.framework.imagesearch_pagination.util.extenstion.bindingWithLifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding)

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingWithLifecycleOwner(R.layout.activity_main)
        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_activity_fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        val appConfiguration = AppBarConfiguration(navGraph = navController.graph)
        setupActionBarWithNavController(navController,appConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}